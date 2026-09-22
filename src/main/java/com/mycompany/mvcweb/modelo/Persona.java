package com.mycompany.mvcweb.modelo;

public abstract class Persona {
        private String nombre;
        private String apellido;
        
    //Contructor Vacio        
        public Persona() {
            
        }
        //Constructor sobre cargado
        public Persona (String nombre, String apellido) {
                this.nombre = nombre;
                this.apellido = apellido;    
        }
        // Getters lectura
        // Setters Modifica
        public String getNombre () {
            return nombre;
        }
        public void setNombre (  String nombre) {
            this.nombre = nombre;
        }
        
        public String getApellido () {
            return apellido;
        }
        public void setApellido ( String apellido) {
            this.apellido = apellido;
        }
        
}
