
package com.mycompany.mvcweb.controlador;

import com.mycompany.mvcweb.dao.DaoFactura;
import com.mycompany.mvcweb.modelo.Factura;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/factura")
public class FacturaControlador {
    
    private DaoFactura daoF = new DaoFactura ();
  
 @GetMapping ()
 public String listar(Model model) {
     
     model.addAttribute("facturas", daoF.Listar_Todos());
     
        if (!model.containsAttribute("factura")) { 
            model.addAttribute("factura", new Factura());      
        }
             return "factura";
 }
 
 @GetMapping("/editar/{id}")
 public String editar(@PathVariable("id")int id, Model model) {
     
     model.addAttribute("facturas", daoF.Listar_Todos() ); 
     model.addAttribute( "factura", daoF.buscarPorId(id) );
     
     return "factura";
 }
 
 @PostMapping("/guardar")
 public String guardar(@ModelAttribute("factura") Factura factura, RedirectAttributes ra) {
     
     try {
         boolean ok= factura.getIdFactura() == 0
                 ?daoF.insertar(factura)
                 :daoF.actualizar(factura);
         
         if (!ok) {
             ra.addFlashAttribute("error", "Error al momento de registrar la factura");
         } else {
             ra.addFlashAttribute("exito",  "Factura guardada correctamente");
         }
         
     } catch (Exception e) {
         ra.addFlashAttribute("error", e.getMessage());
     }
     return "redirect:/factura";
 }
 
 @PostMapping("/eliminar/{id}")
 public String eliminar(@PathVariable ("id") int id) {
     daoF.eliminar(id);
     return "redirect:/factura";
 }
 
}
