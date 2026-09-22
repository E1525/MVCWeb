package com.mycompany.mvcweb.controlador;

import com.mycompany.mvcweb.dao.DaoCliente;
import com.mycompany.mvcweb.modelo.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping ("/clientes")
public class ClienteControlador {
    private DaoCliente  daoC = new DaoCliente();
    
    @GetMapping ()
    public String listar(Model  model) {
        
        model.addAttribute("clientes", daoC.Listar_Todos());
        
        if  (!model.containsAttribute("cliente")) {
            model.addAttribute("cliente", new Cliente());
        }
        return "clientes";
    }
    
    @GetMapping ("/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model)  {
            model.addAttribute("clientes", daoC.Listar_Todos());
            model.addAttribute("cliente", daoC.buscarPorId(id));
            return "clientes";
    }
    
    
    @PostMapping ("/guardar")
    public String guardar (@ModelAttribute ("cliente") Cliente cliente, RedirectAttributes ra) {
        try {
                boolean ok = cliente.getIdClientes() == 0
                        ? daoC.insertar(cliente)
                        : daoC.actualizar(cliente);
                if (!ok) {
                    ra.addFlashAttribute("error", "Error al momento de almacenar el cliente");
                }else {
                    ra.addFlashAttribute("exito", "Cliente guardado correctamente");
                }
                
        } catch(Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/clientes";
    }
    
    
    @PostMapping("/eliminar/{id}")
    public  String eliminar(@PathVariable int id) {
        daoC.eliminar(id);
        return "redirect:/clientes";
    }
    
    
    
}
