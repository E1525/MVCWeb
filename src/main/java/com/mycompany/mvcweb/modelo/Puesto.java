package com.mycompany.mvcweb.modelo;

public class Puesto {
    private int idPuesto;
    private String nombre;
    private float sueldoBase;
    
    public Puesto () {
        
    }
    public Puesto (int idPuesto, String nombre, float sueldoBase ){
        this.idPuesto = idPuesto;
        this.nombre = nombre;
        this.sueldoBase = sueldoBase;
        
    }
    public int getIdPuesto () {
        return idPuesto;
    }
    public void setIdPuesto (int idPuesto) {
        this.idPuesto = idPuesto;
    }
    
    public String getNombre () {
        return nombre;
    }
    public void setNombre ( String nombre) {
        this.nombre = nombre;
    }
    public float getSueldobase ( ) {
        return sueldoBase;
}
    public void setSueldoBase (float sueldoBase) {
        this.sueldoBase = sueldoBase;
    }
    
     @Override  
     public String toString() {
         return nombre;
     }
    
}
