package com.codereadingfirst.app.service;

import java.util.List;
import com.codereadingfirst.app.entity.Alumno;

public interface AlumnoService {
    List<Alumno> listarAlumnos();   
    Alumno obtenerAlumnoPorId(Long id);
}



