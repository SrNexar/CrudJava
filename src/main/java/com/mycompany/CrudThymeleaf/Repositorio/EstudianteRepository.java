package com.mycompany.CrudThymeleaf.Repositorio;

import com.mycompany.CrudThymeleaf.Modelo.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    @Query("SELECT e FROM Estudiante e WHERE CONCAT(e.id, e.nombre, e.apellido , e.email, e.celular) LIKE %?1%")
    public List<Estudiante> findAll(String palabraClave);
}
