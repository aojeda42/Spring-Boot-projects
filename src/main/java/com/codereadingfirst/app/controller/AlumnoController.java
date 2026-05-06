package com.codereadingfirst.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codereadingfirst.app.entity.Alumno;
import com.codereadingfirst.app.service.AlumnoService;

@CrossOrigin(origins = "*")
@RestController
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping("/tbl_alumnos")
    public ResponseEntity<List<Alumno>> listar() {
        return ResponseEntity.ok(alumnoService.listarAlumnos());
    }

    @GetMapping("/tbl_alumnos/{id}")
    public ResponseEntity<Alumno> obtenerAlumno(@PathVariable Long id) {
        Alumno alumno = alumnoService.obtenerAlumnoPorId(id);

        if (alumno == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(alumno);
    }

    @PostMapping("/tbl_alumnos")
    public ResponseEntity<Alumno> insertarAlumno(@RequestBody Alumno alumno) {
        Alumno nuevoAlumno = alumnoService.insertarAlumno(alumno);
        return ResponseEntity.ok(nuevoAlumno);
    }

    @PutMapping("/tbl_alumnos/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(
            @PathVariable Long id,
            @RequestBody Alumno alumno) {

        Alumno alumnoActualizado = alumnoService.actualizarAlumno(id, alumno);

        if (alumnoActualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(alumnoActualizado);
    }

    @DeleteMapping("/tbl_alumnos/{id}")
    public ResponseEntity<Void> borrarAlumno(@PathVariable Long id) {
        alumnoService.borrarAlumno(id);
        return ResponseEntity.noContent().build();
    }
}
