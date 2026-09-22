package com.mycompany.mvcweb.dao;

import com.mycompany.mvcweb.config.Conexion;
import com.mycompany.mvcweb.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DaoProducto implements Crud_Dao <Producto> {

    @Override
    public boolean insertar(Producto objeto) {
    String sql =  " INSERT  INTO productos (id_productos, id_marca, nombre, precio, stock) VALUES (?,?,?,?,?) "; 
        try {
                Connection conn = Conexion.IniciarConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, objeto.getIdProducto());
                ps.setInt(2, objeto.getIdMarca());
                ps.setString(3, objeto.getNombre());
                ps.setFloat(4, objeto.getPrecio());
                ps.setInt(5, objeto.getStock());
                
                ps.executeUpdate();
                return true;
                
       } catch (SQLException e ) {
            System.err.println("Error al momento de insertar el producto");
            return false;
        }
    }

    @Override
    public boolean actualizar(Producto objeto) {
        String sql =  " UPDATE productos SET  id_marca=?, nombre=?, precio=?, stock =? WHERE id_productos =? "; 
        try {
                Connection conn = Conexion.IniciarConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, objeto.getIdMarca());
                ps.setString(2, objeto.getNombre());
                ps.setFloat(3, objeto.getPrecio());
                ps.setInt(4, objeto.getStock());
                ps.setInt(5, objeto.getIdProducto());
                
                ps.executeUpdate();
                return true;
                
       } catch (SQLException e ) {
            System.err.println("Error al momento de actualizar el producto");
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql =  " DELETE FROM productos WHERE id_productos = ? "; 
        try {
                Connection conn = Conexion.IniciarConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, id);
                    
                        ps.execute();
                        return true;
          
       } catch (SQLException e ) {
            System.out.println("Error al momento de eliminar el producto");
            return false;
        }
    }

    @Override
    public Producto buscarPorId(int id) {
    String sql = "SELECT  id_productos, id_marca, nombre, precio, stock  FROM productos WHERE id_productos = ?";
        try {
                    Connection conn = Conexion.IniciarConexion();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, id);
                    ResultSet   rs = ps.executeQuery();
                    if (rs.next() ) {
                            return mapearProducto(rs);
                    }
       } catch (SQLException e ) {
            System.out.println("Error al buscar el producto");
        }
         return null;
       }

    @Override
    public List<Producto> Listar_Todos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT id_productos, id_marca, nombre, precio, stock  FROM productos"; 
            try {
                    Connection conn = Conexion.IniciarConexion();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                        while(rs.next()) {
                                productos.add(mapearProducto (rs));
                        }
            } catch (SQLException e) {
                System.out.println("Erro al buscar el prodcuto");
            }
            return productos;
     
    }
    
    private Producto mapearProducto (ResultSet rs ) throws SQLException {
        return new Producto (
                rs.getInt("id_productos"),
                rs.getInt("id_marca"),
                rs.getString("nombre"),
                rs.getFloat("precio"),
                rs.getInt("stock")
        );
    }
    
}
