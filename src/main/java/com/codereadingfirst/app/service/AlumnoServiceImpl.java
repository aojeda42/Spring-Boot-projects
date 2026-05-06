package com.codereadingfirst.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.codereadingfirst.app.entity.Alumno;
import com.codereadingfirst.app.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;
    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public List<Alumno> listarAlumnos() {
        return alumnoRepository.findAll();
    }

    @Override
    public Alumno obtenerAlumnoPorId(Long id) {
        return alumnoRepository.findById(id).orElse(null);
    }

    @Override
    public Alumno insertarAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public Alumno actualizarAlumno(Long id, Alumno alumno) {
        Alumno alumnoExistente = alumnoRepository.findById(id).orElse(null);

        if (alumnoExistente == null) {
            return null;
        }

        alumnoExistente.setNombre(alumno.getNombre());
        alumnoExistente.setEdad(alumno.getEdad());

        return alumnoRepository.save(alumnoExistente);
    }

    @Override
    public void borrarAlumno(Long id) {
        alumnoRepository.deleteById(id);
    }
}
