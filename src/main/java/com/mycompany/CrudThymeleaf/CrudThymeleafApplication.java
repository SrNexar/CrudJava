package com.mycompany.CrudThymeleaf;

import com.mycompany.CrudThymeleaf.Modelo.Estudiante;
import com.mycompany.CrudThymeleaf.Repositorio.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudThymeleafApplication implements CommandLineRunner {
    @Autowired
    private EstudianteRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(CrudThymeleafApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Agustin");
        estudiante.setApellido("Toapnta");
        estudiante.setEmail("patoapntaa@uce.edu.ec");
        repository.save(estudiante);
        Estudiante estudiante1 = new Estudiante();
        estudiante1.setNombre("Leslie");
        estudiante1.setApellido("Rojas");
        estudiante1.setEmail("lerojasl@uce.edu.ec");
        repository.save(estudiante1);*/
    }
}
