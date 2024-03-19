package com.mitocode.matricula.service.impl;

import com.mitocode.matricula.model.DetalleMatricula;
import com.mitocode.matricula.model.Matricula;
import com.mitocode.matricula.repo.IGenericRepo;
import com.mitocode.matricula.repo.IMatriculaRepo;
import com.mitocode.matricula.service.IMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl extends CRUDImpl<Matricula, Integer> implements IMatriculaService {

    private final IMatriculaRepo repo;

    @Override
    protected IGenericRepo<Matricula, Integer> getRepo() {
        return repo;
    }

    @Override
    public Stream<Object> getCursoEstudiante() {
        List<Matricula> matriculas = repo.findAll();
        Stream<List<DetalleMatricula>> lsStream = matriculas.stream().map(Matricula::getDetalles);
        Stream<DetalleMatricula> streamDetail = lsStream.flatMap(Collection::stream);

        //cursos matriculados
        Map<String, Long> byCurso = streamDetail
                .collect(groupingBy(d->d.getCurso().getNombre(), counting()));

        //get estudiantes por curso matriculado
        return byCurso.entrySet()
                .stream().flatMap(
                        e -> {
                            return Stream.of(e.getKey(),
                                    matriculas.stream()
                                            .filter(
                                                    f -> f.getDetalles().stream().filter(
                                                            m -> e.getKey().equals(m.getCurso().getNombre())
                                                    ).findFirst().isPresent()
                                            )
                                            .map(x -> x.getEstudiante().getNombres())
                                            .distinct()
                                            .collect(toList())
                            );
                        }
                );

    }
}
