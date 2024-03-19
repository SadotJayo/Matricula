package com.mitocode.matricula.service.impl;

import com.mitocode.matricula.model.Curso;
import com.mitocode.matricula.repo.ICursoRepo;
import com.mitocode.matricula.repo.IGenericRepo;
import com.mitocode.matricula.service.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl extends CRUDImpl<Curso, Integer> implements ICursoService {

    private final ICursoRepo repo;

    @Override
    protected IGenericRepo<Curso, Integer> getRepo() {
        return repo;
    }

}
