package com.mycompany.mvcweb.dao;

import java.util.List;

public interface Crud_Dao <T> {
        boolean insertar (T objeto);
        boolean actualizar (T objeto);
        boolean eliminar (int id);
        T buscarPorId(int id);
        List <T> Listar_Todos();
        
    
    
}
