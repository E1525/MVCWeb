
package com.mycompany.mvcweb.dao;

import com.mycompany.mvcweb.config.Conexion;
import com.mycompany.mvcweb.modelo.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoEmpleado implements Crud_Dao <Empleado> {

    @Override
    public boolean insertar(Empleado objeto) {
         String sql = "INSERT INTO empleados (id_empleado, id_puesto, nombre, apellidos,  fecha_contratacion) VALUES (?,?,?,?,?)";
          try {
                Connection conn = Conexion.IniciarConexion();
                 PreparedStatement ps = conn.prepareStatement (sql);
         
                ps.setInt(1, objeto.getIdEmpleado());
                ps.setInt(2, objeto.getIdPuesto());
                ps.setString(3, objeto.getNombre());
                ps.setString(4, objeto.getApellido());
                ps.setDate(5, java.sql.Date.valueOf(objeto.getFechaContratacion()));
         
         ps.execute();
         return true;
         
            }   catch(SQLException e) {
                System.err.println("Error al insertar empleados ");
                return false; 
            }
    }

    @Override
    public boolean actualizar(Empleado objeto) {
      String sql = "UPDATE  empleados SET   id_puesto=?,nombre=?, apellidos=?,  fecha_contratacion=?  WHERE id_empleado=? " ;
     try {  Connection conn = Conexion.IniciarConexion();
                 PreparedStatement ps = conn.prepareStatement (sql);
                 
                ps.setInt(5, objeto.getIdEmpleado());
                ps.setInt(1, objeto.getIdPuesto());
                ps.setString(2, objeto.getNombre());
                ps.setString(3, objeto.getApellido());
                ps.setDate(4, java.sql.Date.valueOf(objeto.getFechaContratacion()));
                
                ps.executeUpdate();
                return true;
                
           }catch (SQLException e) {
                System.out.println("Error al momento de actualizar el empleado");
                return false;
          }
    }

    @Override
    public boolean eliminar(int id) {
    String sql = "Delete FROM empleados WHERE id_empleado =? ";
        try {
                Connection conn = Conexion.IniciarConexion();
                PreparedStatement ps = conn.prepareStatement (sql);
                ps.setInt(1, id);
                
                ps.executeUpdate();
                return true;
                
        }   catch (SQLException e) {
            System.out.println("Error al momento de intentar eliminar registro empleados");
            return false;
        }
    }

    @Override
    public Empleado buscarPorId(int id) {
     String sql = "SELECT id_empleado, id_puesto, nombre, apellidos,  fecha_contratacion FROM empleados WHERE id_empleado =? ";
    try {
                Connection conn = Conexion.IniciarConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                              return mapearEmpleado(rs);
                }
          }catch (SQLException e) {
            System.out.println("Error al momento de buscar cliente");
            
        }
        return null;
    }

        @Override
        public List<Empleado> Listar_Todos() {
            List<Empleado> empleados = new ArrayList<>();
            String sql = "SELECT id_empleado, id_puesto, nombre, apellidos, fecha_contratacion FROM empleados ";
        try {
            Connection conn = Conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                     empleados.add (mapearEmpleado(rs));
              }
       } catch (SQLException e) {
            System.err.println("Error al buscar un Empleado");
    }
        return empleados;
        
        
        }
        
          private Empleado mapearEmpleado (ResultSet rs) throws SQLException {
              java.sql.Date sqlDate = rs.getDate("fecha_contratacion");
               java.time.LocalDate fecha = (sqlDate != null) ? sqlDate.toLocalDate() : null;
             return new Empleado (
                    rs.getInt("id_empleado"),
                    rs.getInt("id_puesto"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    fecha
             );
          }
        
    }

    
    

