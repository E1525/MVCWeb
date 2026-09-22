package com.mycompany.mvcweb.dao;

import com.mycompany.mvcweb.config.Conexion;
import com.mycompany.mvcweb.modelo.Puesto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoPuesto implements Crud_Dao <Puesto> {

    @Override
    public boolean insertar(Puesto objeto) {
        String sql = "INSERT INTO puestos (nombre, salario_base) VALUES (?,?)";
        try {
            Connection conn = Conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, objeto.getNombre());
            ps.setFloat(2, objeto.getSueldobase());
            
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar puesto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Puesto objeto) {
        String sql = "UPDATE puestos SET nombre = ?, salario_base = ? WHERE id_puesto = ?";
        try {
            Connection conn = Conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, objeto.getNombre());
            ps.setFloat(2, objeto.getSueldobase());
            ps.setInt(3, objeto.getIdPuesto()); 
            
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar puesto: " );
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM puestos WHERE id_puesto = ?";
        try {
            Connection conn = Conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar puesto: " );
            return false;
        }
    }

    @Override
    public Puesto buscarPorId(int id) {
        String sql = "SELECT id_puesto, nombre, salario_base FROM puestos WHERE id_puesto = ?";
        try {
            Connection conn = Conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapearPuesto(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar puesto por ID: " );
        }
        return null;
    }

    @Override
    public List<Puesto> Listar_Todos() {
        List<Puesto> puestos = new ArrayList<>();
        String sql = "SELECT id_puesto, nombre, salario_base FROM puestos";
        try {
            Connection conn = Conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                puestos.add(mapearPuesto(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar puestos: " );
        }
        return puestos;
    }

    private Puesto mapearPuesto(ResultSet rs) throws SQLException {
        return new Puesto(
            rs.getInt("id_puesto"),
            rs.getString("nombre"),
            rs.getFloat("salario_base")
        );
    }
}
