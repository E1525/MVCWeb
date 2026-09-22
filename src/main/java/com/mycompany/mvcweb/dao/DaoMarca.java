
package com.mycompany.mvcweb.dao;

import com.mycompany.mvcweb.config.Conexion;
import com.mycompany.mvcweb.modelo.Marcas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DaoMarca implements Crud_Dao <Marcas> {

    @Override
    public boolean insertar(Marcas objeto) {
        String sql = "INSERT INTO marcas (id_marca, nombre) VALUES (?,?) ";
            try {
                    Connection conn = Conexion.IniciarConexion();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    
                    ps.setInt(1, objeto.getIdMarca());
                    ps.setString(2, objeto.getNombre());
                    
                    ps.execute();
                    return true;
                    
        }catch (SQLException e ) {
                System.err.println("Error al momento de insertar la marca");
                return false;
            }
        
    }

    @Override
    public boolean actualizar(Marcas objeto) {
        String sql = "UPDATE marcas SET  nombre =? WHERE id_marca =?";
            try {
                    Connection conn = Conexion.IniciarConexion();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, objeto.getNombre());
                    ps.setInt(2, objeto.getIdMarca());
                    
                    ps.executeUpdate();
                    return true;
                
            } catch (SQLException e) {
                    System.out.println("Error al actualizar la marca");
                    return false;
            }
    
    }

    @Override
    public boolean eliminar(int id) {
       String sql = "DELETE FROM marcas  WHERE id_marca = ? ";
            try {
                    Connection conn = Conexion.IniciarConexion();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, id);
                    
                    ps.execute();
                    return true;
                    
            } catch (SQLException e ) {
                System.out.println("Error al intentar eliminar la marca");
                return false;
            }
       
        }

    @Override
    public Marcas buscarPorId(int id) {
          String sql = "SELECT id_marca, nombre FROM marcas WHERE id_marca =?";
        try {
                Connection conn = Conexion.IniciarConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                              return mapearMarca(rs);
                }
          }catch (SQLException e) {
            System.out.println("Error al momento de buscar marca");
            
        }
        return null;
    }
    

    
    @Override
    public List<Marcas> Listar_Todos() {
        List<Marcas> marcas = new ArrayList<>();
        String sql = "SELECT id_marca, nombre FROM marcas ";
        try {
            Connection conn = Conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while (rs.next()) {
                     marcas.add (mapearMarca(rs));
              }
       } catch (SQLException e) {
            System.err.println("Error al buscar un cliente");
    }
        return marcas;
    }

    private Marcas mapearMarca( ResultSet rs ) throws SQLException {
        return new Marcas (
                rs.getInt("id_marca"),
                rs.getString("nombre")
        );
    }
    
    
}
