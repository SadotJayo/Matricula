package com.mitocode.matricula.service;

import com.mitocode.matricula.model.Matricula;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface IMatriculaService extends ICRUD<Matricula, Integer> {

    public Stream<Object> getCursoEstudiante();

}
