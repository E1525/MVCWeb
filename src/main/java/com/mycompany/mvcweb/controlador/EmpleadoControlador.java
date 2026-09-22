package com.mycompany.mvcweb.controlador;

import com.mycompany.mvcweb.dao.DaoEmpleado;
import com.mycompany.mvcweb.modelo.Empleado;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/empleado")
public class EmpleadoControlador {
    
    private DaoEmpleado daoE = new DaoEmpleado();
    
    @GetMapping()
    public String listar(Model model) {
        
        model.addAttribute("empleados", daoE.Listar_Todos());
        
        if  (!model.containsAttribute("empleado")) {
               model.addAttribute("empleado", new Empleado());
            }
        return "empleado";
    }
    
    @GetMapping("/editar/{id}")
    public String editar (@PathVariable("id") int id, Model model) {
        
        model.addAttribute("empleados", daoE.Listar_Todos());
        model.addAttribute("empleado", daoE.buscarPorId(id));
        
        return "empleado";
    }
    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("empleado")Empleado empleado, RedirectAttributes ra) {
        
    try {
            boolean ok = empleado.getIdEmpleado() == 0
                    ? daoE.insertar(empleado)
                    : daoE.actualizar(empleado);
            
            if  (!ok) {
                ra.addFlashAttribute("error", "Error al momento de almacenar el empleado");
            } else {
                ra.addFlashAttribute("exito", "Empleado almacenado correctamente");
            }
            
    } catch (Exception e) {
        ra.addFlashAttribute("error" , e.getMessage());
    }
    return "redirect:/empleado";
}
    
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable ("id") int id) {
        daoE.eliminar(id);
        return "redirect:/empleado";
    }
    
}
