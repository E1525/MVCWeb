package com.mycompany.mvcweb.modelo;

import java.time.LocalDate;

public class Empleado extends Persona{
        private int idEmpleado;
        private int idPuesto;
        private LocalDate fechaContratacion;
    
        //constructor Vacio
    public Empleado() {
       
    }
    //Constructor Sobrecargado
    public Empleado (int idEmpleado, int idPuesto, String nombre, String apellido,  LocalDate fechaContratacion) {
         super (nombre, apellido);
         this.idEmpleado = idEmpleado;
         this.idPuesto = idPuesto;
         this.fechaContratacion = fechaContratacion;
}
    public int getIdEmpleado (){
         return idEmpleado;
    }
    public void setIdEmpleado (int idEmpleado) {
         this.idEmpleado = idEmpleado;
    }
    public int getIdPuesto() {
         return idPuesto;
}
    public void setIdPuesto (int idPuesto) {
        this.idPuesto = idPuesto;
    }
    
    public LocalDate getFechaContratacion () {
        return fechaContratacion;
}
    public void setFechaContratacion(LocalDate fechaContratacion) {
         this.fechaContratacion = fechaContratacion;
}
    //Sobre escribe un metodo que existe en la clase padre
    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", idPuesto=" + idPuesto + ", nombre=" + getNombre() + ", apellidos=" + getApellido() + ", fechaContratacion=" + fechaContratacion + '}';
}

}