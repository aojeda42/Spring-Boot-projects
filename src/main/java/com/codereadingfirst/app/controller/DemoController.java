package com.codereadingfirst.app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.codereadingfirst.app.model.Alumno;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*")
@RestController
public class DemoController {

    List<Alumno> alumnos = new ArrayList<>(List.of(
        new Alumno("Juan", 20),
        new Alumno("María", 25),
        new Alumno("Pedro", 30)
    ));

    @GetMapping("/alumnos")
    public List<Alumno> metodo() {
        return alumnos;
    }

    @PostMapping("/alumnos")
    public Alumno postMethodName(@RequestBody Alumno alumno) {
        alumnos.add(alumno);
        return alumno;
    }
    
    @DeleteMapping("/alumnos/{nombre}")
    public void borrarAlumno(@PathVariable String nombre) {
        boolean eliminado = alumnos.removeIf(a -> a.getNombre().equals(nombre));
        
        if (!eliminado) {
            throw new RuntimeException("Alumno no encontrado");
        }
    }

    @PutMapping("/alumnos/{nombre}")
    public Alumno putMethodName(@PathVariable String nombre, @RequestBody Alumno alumno) {
        for (Alumno a : alumnos) {
            if (a.getNombre().equals(nombre)) {
                a.setEdad(alumno.getEdad());
                break;
            }
        }
        
        return alumno;
    }

}
