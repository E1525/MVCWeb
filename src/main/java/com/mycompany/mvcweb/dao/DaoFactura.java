
package com.mycompany.mvcweb.dao;

import com.mycompany.mvcweb.config.Conexion;
import com.mycompany.mvcweb.modelo.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DaoFactura implements Crud_Dao <Factura> {

    @Override
    public boolean insertar(Factura objeto) {
       String sql = " INSERT INTO facturas (id_facturas, id_clientes, id_empleado, fecha, total ) VALUES (?,?,?,?,?) ";
          try {
              Connection conn = Conexion.IniciarConexion();
              PreparedStatement ps = conn.prepareStatement(sql);
              
              ps.setInt(1, objeto.getIdFactura());
              ps.setInt(2, objeto.getIdCliente());
              ps.setInt(3, objeto.getIdEmpleado());
              ps.setTimestamp(4, java.sql.Timestamp.valueOf(objeto.getFecha()));
              ps.setFloat(5, objeto.getTotal());
              
              ps.execute();
              return true;

          } catch (SQLException e) {
              System.out.println("Error al insertar factura");
              return false;
          }
    }

    @Override
    public boolean actualizar(Factura objeto) {
    String sql = "UPDATE facturas SET id_clientes =?, id_empleado =?, fecha =?, total =? WHERE id_facturas =? ";
            try {
                Connection conn = Conexion.IniciarConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
             
                ps.setInt(1, objeto.getIdCliente());
                ps.setInt(2, objeto.getIdEmpleado());
                ps.setTimestamp(3, java.sql.Timestamp.valueOf(objeto.getFecha()));
                ps.setFloat(4, objeto.getTotal());
                   ps.setInt(5, objeto.getIdFactura());
                
                ps.executeUpdate();
                return true;
                                
            } catch (SQLException e) {
                System.out.println("Error al momento de actualizar factura");
                return false;
            }
    }

    @Override
    public boolean eliminar(int id) {
          String sql = "DELETE  FROM facturas WHERE id_facturas =?";
                try { 
                        Connection conn = Conexion.IniciarConexion();
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setInt(1, id);
                        
                        ps.execute();
                        return true;
                    
                } catch (SQLException e ) {
                    System.out.println("Error al momento de eliminar la factura");
                    return false;
                }
    }

    @Override
    public Factura buscarPorId(int id) {
        String sql = "SELECT id_facturas, id_clientes, id_empleado, fecha, total FROM facturas WHERE id_facturas =? ";
            try {
                    Connection conn = Conexion.IniciarConexion();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next() ){
                               return mapearFactura(rs);
                    }
            
            } catch (SQLException e ) {
                System.out.println("Error al momento de buscar factura");
            }
            return null;
    }

    @Override
    public List<Factura> Listar_Todos() {
     List<Factura> facturas = new ArrayList<>();
     String sql = "SELECT id_facturas, id_clientes, id_empleado, fecha, total FROM facturas ";
        try {
                Connection conn = Conexion.IniciarConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                            facturas.add(mapearFactura(rs));
                      }
            
        } catch (SQLException e ) {
            System.err.println("Error al buscar factura");
        }
        return facturas;
    }
    
    private Factura mapearFactura(ResultSet rs ) throws SQLException {
        java.sql.Timestamp sqlTimestamp = rs.getTimestamp("fecha");
        java.time.LocalDateTime fecha = (sqlTimestamp !=null) ? sqlTimestamp.toLocalDateTime() : null;
        return new Factura (
                rs.getInt("id_facturas"),
                rs.getInt("id_clientes"),
                rs.getInt("id_empleado"),
                 fecha,
                 rs.getFloat("total")
        );
    }
    
}
