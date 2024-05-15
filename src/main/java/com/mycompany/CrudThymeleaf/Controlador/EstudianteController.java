package com.mycompany.CrudThymeleaf.Controlador;


import com.mycompany.CrudThymeleaf.Modelo.Estudiante;
import com.mycompany.CrudThymeleaf.Servicio.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EstudianteController {
    @Autowired
    private EstudianteServicio servicio;

    @GetMapping({"/estudiantes", "/"})
    public String listarEstudiantes(Model model, @Param("palabraClave") String palabraClave) {
        List<Estudiante> estudiantes = servicio.listarPalabraClave(palabraClave);
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("palabraClave", palabraClave);

        return "estudiantes";
    }

    @GetMapping("/estudiantes/nuevo")
    public String crearEstudianteFormulario(Model model) {
        Estudiante estudiante = new Estudiante();
        model.addAttribute("estudiante", estudiante);
        return "crear_estudiantes";
    }

    @PostMapping("/estudiantes")
    public String guardarEstudiante(@Validated @ModelAttribute("estudiante") Estudiante estudiante, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("estudiante", estudiante);
            return "crear_estudiantes";
        } else {
            try{
                servicio.guardarEstudiante(estudiante);
                redirectAttributes.addFlashAttribute("msgExito", "Estudiante guardado correctamente");
            } catch (DataIntegrityViolationException e){
                bindingResult.rejectValue("email", "error.estudiante", "Este email ya está registrado.");
                return "crear_estudiantes";
            }

            return "redirect:/estudiantes";
        }


    }

    @GetMapping("/estudiantes/editar/{id}")
    public String editarEstudianteFormulario(@PathVariable Long id, Model model) {
        model.addAttribute("estudiante", servicio.obtenerEstudiante(id));
        return "editar_estudiantes";
    }

    @PostMapping("/estudiantes/{id}")
    public String actualizarEstudiante(@PathVariable Long id, @Validated @ModelAttribute("estudiante") Estudiante estudiante, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        Estudiante estudianteExistente = servicio.obtenerEstudiante(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("estudiante", estudiante);
            return "editar_estudiantes";
        } else {
            estudianteExistente.setId(id);
            estudianteExistente.setNombre(estudiante.getNombre());
            estudianteExistente.setApellido(estudiante.getApellido());
            estudianteExistente.setEmail(estudiante.getEmail());
            estudianteExistente.setCelular(estudiante.getCelular());
            estudianteExistente.setFechaNacimiento(estudiante.getFechaNacimiento());
            servicio.actualizarEstudiante(estudianteExistente);
            redirectAttributes.addFlashAttribute("msgExito", "Estudiante actualizado correctamente");
            return "redirect:/estudiantes";
        }

    }

    @GetMapping("/estudiantes/eliminar/{id}")
    public String eliminarEstudianteFormulario(@PathVariable Long id, Model model) {
        model.addAttribute("estudiante", servicio.obtenerEstudiante(id));
        return "eliminar_estudiantes";
    }

    @PostMapping("/estudiantes/eliminarE/{id}")
    public String eliminarEstudiante(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        servicio.eliminarEstudiante(id);
        redirectAttributes.addFlashAttribute("msgExito", "Estudiante eliminado correctamente");

        return "redirect:/estudiantes";
    }

}
