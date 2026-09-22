package com.mycompany.mvcweb.dao;

import com.mycompany.mvcweb.config.Conexion;
import com.mycompany.mvcweb.modelo.DetalleFactura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoDetalleFactura implements Crud_Dao <DetalleFactura> {

    @Override
    public boolean insertar(DetalleFactura objeto) {
        String sql = "INSERT INTO detalle_factura (id_detalle, id_facturas, id_productos, cantidad, precio_uni, subtotal) VALUES (?,?,?,?,?,?)";
        try {
            Connection conn = Conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            
        
            ps.setInt(1, objeto.getIdDetalle());
            ps.setInt(2, objeto.getIdFactura());
            ps.setInt(3, objeto.getIdProductos()); 
            ps.setInt(4, objeto.getCantidad());    
            ps.setFloat(5, objeto.getPrecioUni());
            ps.setFloat(6, objeto.getSubtotal());
            
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar detalle de factura: ");
            return false;
        }
    }

    @Override
    public boolean actualizar(DetalleFactura objeto) {
        String sql = "UPDATE detalle_factura SET id_facturas = ?, id_productos = ?, cantidad = ?, precio_uni = ?, subtotal = ? WHERE id_detalle = ?";
        try {
            Connection conn = Conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            
          
            ps.setInt(1, objeto.getIdFactura());
            ps.setInt(2, objeto.getIdProductos()); 
            ps.setInt(3, objeto.getCantidad());    
            ps.setFloat(4, objeto.getPrecioUni());
            ps.setFloat(5, objeto.getSubtotal());
            ps.setInt(6, objeto.getIdDetalle());   
            
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar detalle de factura: " );
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM detalle_factura WHERE id_detalle = ?";
        try {
            Connection conn = Conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar detalle de factura: " );
            return false;
        }
    }

    @Override
    public DetalleFactura buscarPorId(int id) {
        String sql = "SELECT id_detalle, id_facturas, id_productos, cantidad, precio_uni, subtotal FROM detalle_factura WHERE id_detalle = ?";
        try {
            Connection conn = Conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapearDetalle(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar detalle por ID: " );
        }
        return null;
    }

    @Override
    public List<DetalleFactura> Listar_Todos() {
        List<DetalleFactura> detalles = new ArrayList<>();
        String sql = "SELECT id_detalle, id_facturas, id_productos, cantidad, precio_uni, subtotal FROM detalle_factura";
        try {
            Connection conn = Conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                detalles.add(mapearDetalle(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los detalles de facturas: " );
        }
        return detalles;
    }

    private DetalleFactura mapearDetalle(ResultSet rs) throws SQLException {
        return new DetalleFactura(
            rs.getInt("id_detalle"),
            rs.getInt("id_facturas"),
            rs.getInt("id_productos"),
            rs.getInt("cantidad"),
            rs.getFloat("precio_uni"),
            rs.getFloat("subtotal")
        );
    }
}
