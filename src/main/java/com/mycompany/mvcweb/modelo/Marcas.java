package com.mycompany.mvcweb.modelo;

public class Marcas {
    private int idMarca;
    private String nombre;
    
    public Marcas (){
        
    }
    public Marcas (int idMarca, String nombre) {
        this.idMarca = idMarca;
        this.nombre = nombre;
    }
    
    public int getIdMarca () {
        return idMarca;
    }
    public void setIdMarca (int idMarca) {
        this.idMarca = idMarca;
    }
    public String getNombre () {
        return nombre;
    }
    public void setnombre (String nombre) {
        this.nombre = nombre;
    }
    
}
