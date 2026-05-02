package com.codereadingfirst.app.controller;

import org.springframework.web.bind.annotation.RestController;
import com.codereadingfirst.app.entity.Alumno;
import com.codereadingfirst.app.service.AlumnoService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "*")
@RestController
public class AlumnoController {

    List<Alumno> alumnos = new ArrayList<>(List.of(
        new Alumno("Sofía", 42),
        new Alumno("Julio", 25),
        new Alumno("Rodrigo", 30)
    ));

    private final AlumnoService alumnoService;
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping("/tbl_alumnos")
    public ResponseEntity<List<Alumno>> listar() {
        return ResponseEntity.ok(alumnoService.listarAlumnos());
    }
    
    @GetMapping("/tbl_alumnos/{alumnoId}")
    public ResponseEntity<Alumno> obtenerAlumno(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(alumnoService.obtenerAlumnoPorId(alumnoId));
    }
    
    @GetMapping("/alumnos")
    public List<Alumno> listarAlumnos() {
        return alumnos;
    }

    @PostMapping("/alumnos")
    public Alumno insertarAlumno(@RequestBody Alumno alumno) {
        alumnos.add(alumno);
        return alumno;
    }
    
    @DeleteMapping("/alumnos/{nombre}")
    public void borrarAlumno(@PathVariable String nombre) {

        boolean eliminado = false;

        for (int i = 0; i < alumnos.size(); i++) {
            Alumno a = alumnos.get(i);

            if (a.getNombre().equals(nombre)) {
                alumnos.remove(i);
                eliminado = true;
                break; // importante: salimos tras borrar
            }
        }

        if (!eliminado) {
            throw new RuntimeException("Alumno no encontrado");
        }
    }

    @PutMapping("/alumnos/{nombre}")
    public Alumno actualizarAlumno(@PathVariable String nombre, @RequestBody Alumno alumno) {
        for (Alumno a : alumnos) {
            if (a.getNombre().equals(nombre)) {
                a.setEdad(alumno.getEdad());
                break;
            }
        }
        
        return alumno;
    }

}
