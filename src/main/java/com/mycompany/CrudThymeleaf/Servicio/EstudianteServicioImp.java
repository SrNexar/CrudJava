package com.mycompany.CrudThymeleaf.Servicio;

import com.mycompany.CrudThymeleaf.Modelo.Estudiante;
import com.mycompany.CrudThymeleaf.Repositorio.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServicioImp implements EstudianteServicio {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    @Override
    public List<Estudiante> listarPalabraClave(String palabraClave) {
        if(palabraClave != null){
            return estudianteRepository.findAll(palabraClave);
        }

        return estudianteRepository.findAll();
    }


    @Override
    public Estudiante guardarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante obtenerEstudiante(Long id) {
        return estudianteRepository.findById(id).get();
    }

    @Override
    public Estudiante actualizarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void eliminarEstudiante(Long id) {
        estudianteRepository.deleteById(id);

    }
}
