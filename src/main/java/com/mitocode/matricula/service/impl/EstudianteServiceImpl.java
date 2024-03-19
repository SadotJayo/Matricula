package com.mitocode.matricula.service.impl;

import com.mitocode.matricula.model.Estudiante;
import com.mitocode.matricula.repo.IEstudianteRepo;
import com.mitocode.matricula.repo.IGenericRepo;
import com.mitocode.matricula.service.IEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl extends CRUDImpl<Estudiante, Integer> implements IEstudianteService {

    private final IEstudianteRepo repo;

    @Override
    protected IGenericRepo<Estudiante, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Estudiante> getEstudiantesPorEdad() {
        return repo.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Estudiante::getEdad).reversed())
                .collect(Collectors.toList());
    }
}
