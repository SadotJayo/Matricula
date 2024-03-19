package com.mitocode.matricula.service;

import com.mitocode.matricula.model.Estudiante;

import java.util.List;

public interface IEstudianteService extends ICRUD<Estudiante, Integer> {
    List<Estudiante> getEstudiantesPorEdad();
}
