package com.mycompany.CrudThymeleaf.Modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.dynalink.linker.support.Guards;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", length = 150, nullable = false)
    @NotBlank(message = "Debe ingresar el nombre")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre debe contener solo letras" )
    @Size(min = 1, max=20, message = "El nombre debe tener maximo 20 letras")
    private String nombre;

    @Column(name="apellido ", length = 150, nullable = false)
    @NotBlank(message = "Debe ingresar el apellido")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El apellido debe contener solo letras" )
    @Size(min = 1, max=20, message = "El apellido debe tener maximo 20 letras")
    private String apellido;

    @Column(name="email", length = 150, unique = true)
    @NotEmpty(message = "Debe ingresar su email")
    @Email
    private String email;


    @Column(name="celular", length = 10, nullable = false)
    @NotBlank(message = "Debe ingresar el celular")
    @Pattern(regexp = "^[0-9]+$", message = "El celular debe contener solo numeros" )
    @Size(min = 10, max=10, message = "El celular debe tener 10 numeros")
    private String celular;

    @Column(name = "fecha_nacimiento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past
    @NotNull(message = "Debe ingresar una fecha de nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name="fecha_registro")
    private LocalDateTime fechaRegistro;

    @PrePersist
    public void asignarFechaRegistro(){
        fechaRegistro = LocalDateTime.now();
    }

}
