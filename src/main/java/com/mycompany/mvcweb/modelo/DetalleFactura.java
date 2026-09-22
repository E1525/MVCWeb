package com.mycompany.mvcweb.modelo;

public class DetalleFactura {
    private int idDetalle;
    private int idFacturas;
    private int idProductos;
    private int cantidad;
    private float precioUni;
    private float subtotal;
    
    public DetalleFactura () {
        
    }
    public DetalleFactura (int idDetalle, int idFacturas, int idProductos, int cantidad, float precioUni, float subtotal) {
        this.idDetalle = idDetalle;
        this.idFacturas = idFacturas;
        this.idProductos = idProductos;
        this.cantidad = cantidad;
        this.precioUni = precioUni;
        this.subtotal = subtotal;
    }
    //getters y setters
    public int getIdDetalle () {
        return idDetalle;
    }
    public void setIdDetalle (int idDetalle) {
        this.idDetalle = idDetalle;
    }
    public int getIdFactura () {
        return idFacturas;
    }
    public void setIdFacturas (int idFacturas) {
        this.idFacturas = idFacturas;
    }
    public int getIdProductos (){
        return idProductos;
    }
    public void setIdProductos (int idProductos) {
        this.idProductos = idProductos;
    }
    public int getCantidad () {
        return cantidad;
    }
    public void setCantidad (int cantidad) {
        this.cantidad = cantidad;
    }
    public float getPrecioUni () {
        return precioUni;
    }
    public void setPrecioUni (float precioUni) {
        this.precioUni = precioUni;
    }
    public float getSubtotal () {
        return subtotal;
    }
    public void setSubtotal (float subtotal) {
        this.subtotal = subtotal;
    }
    
   
}
