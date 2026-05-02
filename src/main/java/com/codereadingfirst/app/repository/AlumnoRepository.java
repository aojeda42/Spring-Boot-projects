package com.codereadingfirst.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codereadingfirst.app.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

}

