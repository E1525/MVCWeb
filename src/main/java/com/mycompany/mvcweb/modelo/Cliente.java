package com.mycompany.mvcweb.modelo;

public class Cliente extends Persona{
    private int  idClientes;
    private String nit;
    private String correo;
    
    public Cliente () {
        
    }
    
    public Cliente (int idClientes, String nit, String nombre, String apellido,   String correo ) {
        super (nombre, apellido);
        this.idClientes = idClientes;
        this.nit = nit;
        this.correo = correo;
    }
    
    public int getIdClientes () {
        return idClientes;
    }
    public void setIdClientes (int idClientes) {
        this.idClientes = idClientes;
    }
    
    public String getNit ( ) {
        return nit;
    }
    public void setNit (String nit) {
        this.nit = nit;
    }
    
    public String getCorreo () {
        return correo;
    }
    public void setCorreo (String correo) {
        this.correo = correo;
    }
    
}
