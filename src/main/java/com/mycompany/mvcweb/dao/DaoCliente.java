package com.mycompany.mvcweb.dao;

import com.mycompany.mvcweb.modelo.Cliente;
import com.mycompany.mvcweb.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCliente implements Crud_Dao <Cliente> {

    @Override
    public boolean insertar(Cliente objeto) {
    String sql =  "INSERT INTO CLIENTES (nit, nombre, apellidos, email) VALUES  (?,?,?,?) " ;
        try {
            Connection  conn = Conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement (sql);
           
            //Asigancion de para metros
            ps.setString (1, objeto.getNit());
            ps.setString(2, objeto.getNombre());
            ps.setString(3, objeto.getApellido());
            ps.setString(4, objeto.getCorreo());
            
// Ejecuta la orden
            ps.executeUpdate();
            return true;
            
        }catch (SQLException e) {
//Notifica al usuario que se genero un error
            System.err.println("Error al momento de insertar un cliente");
            return false;
        }
    }

    @Override
    public boolean actualizar(Cliente objeto) {
        String sql = "UPDATE  clientes SET nit = ?, nombre=?, apellidos=?, email=?  WHERE id_clientes =? ";
        try {
                Connection conn = Conexion.IniciarConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                 ps.setString(1, objeto.getNit());
                ps.setString(2, objeto.getNombre());
                ps.setString(3,objeto.getApellido());
                ps.setString(4, objeto.getCorreo());
                ps.setInt(5, objeto.getIdClientes());
               
                
                ps.executeUpdate();
                return true;
                
        }catch (SQLException e) {
            System.out.println("Error al momento de actualizar el cliente");
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
    String sql = "DELETE FROM clientes  WHERE id_clientes = ? ";
        try {
            Connection conn = Conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
           ps.execute();
           return true;
            
        } catch(SQLException e) {
            System.out.println("Error al momento de intentar elimiar registro clientes");
            return false;
        }
    }

    @Override
    public Cliente buscarPorId(int id) {
        String sql = "SELECT id_clientes, nit, nombre, apellidos, email FROM clientes WHERE id_clientes =?";
        try {
                Connection conn = Conexion.IniciarConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                              return mapearCliente(rs);
                }
          }catch (SQLException e) {
            System.out.println("Error al momento de bucar cliente");
            
        }
        return null;
    }

    @Override
    public List<Cliente> Listar_Todos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id_clientes, nit,  nombre, apellidos,  email  FROM clientes ";
        try {
            Connection conn = Conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while (rs.next()) {
                     clientes.add (mapearCliente(rs));
              }
       } catch (SQLException e) {
            System.err.println("Error al buscar un cliente");
    }
        return clientes;
    }

    private Cliente mapearCliente(ResultSet rs) throws SQLException {
            //Se debe colocar en el mismo orden establecido en el constructor
        return new Cliente   ( 
                rs.getInt("id_clientes"),
                rs.getString("nit") ,
                rs.getString("nombre") ,
                rs.getString("apellidos") ,
                 rs.getString("email")     
        );
    }
    
    
}
