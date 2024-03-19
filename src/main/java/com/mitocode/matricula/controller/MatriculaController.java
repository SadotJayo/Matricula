package com.mitocode.matricula.controller;

import com.mitocode.matricula.dto.GenericResponse;
import com.mitocode.matricula.dto.MatriculaDTO;
import com.mitocode.matricula.model.Matricula;
import com.mitocode.matricula.service.IMatriculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MatriculaController {

    private final IMatriculaService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> readAll() throws Exception{
        List<MatriculaDTO> list = service.readAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<MatriculaDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Matricula obj = service.readById(id);
        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList( convertToDto(obj))) );
    }

    @PostMapping
    public ResponseEntity<MatriculaDTO> save(@Valid @RequestBody MatriculaDTO dto) throws Exception{
        Matricula obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaDTO> update(@Valid @RequestBody MatriculaDTO dto, @PathVariable("id") Integer id) throws Exception{
        Matricula obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/estudiantesporcurso")
    public ResponseEntity<Stream<Object>> readEstudiantesPorCurso() throws Exception{
        //List<MatriculaDTO> list = ;
        return ResponseEntity.ok(service.getCursoEstudiante());
    }

    /////////////////////////////////////////
    private MatriculaDTO convertToDto(Matricula obj){
        return modelMapper.map(obj, MatriculaDTO.class);
    }

    private Matricula convertToEntity(MatriculaDTO dto){
        return modelMapper.map(dto, Matricula.class);
    }

}
