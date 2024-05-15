package com.mycompany.CrudThymeleaf.Servicio;

import com.mycompany.CrudThymeleaf.Modelo.Estudiante;

import java.util.List;

public interface EstudianteServicio {

    public List<Estudiante> listarEstudiantes();

    public List<Estudiante> listarPalabraClave(String palabraClave);

    public Estudiante guardarEstudiante(Estudiante estudiante);

    public Estudiante obtenerEstudiante(Long id);

    public Estudiante actualizarEstudiante(Estudiante estudiante);

    public void eliminarEstudiante(Long id);
}
