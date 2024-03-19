package com.mitocode.matricula.controller;

import com.mitocode.matricula.dto.EstudianteDTO;
import com.mitocode.matricula.dto.GenericResponse;
import com.mitocode.matricula.model.Estudiante;
import com.mitocode.matricula.service.IEstudianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EstudianteController {

    private final IEstudianteService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> readAll() throws Exception{
        List<EstudianteDTO> list = service.readAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<EstudianteDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Estudiante obj = service.readById(id);
        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList( convertToDto(obj))) );
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> save(@Valid @RequestBody EstudianteDTO dto) throws Exception{
        Estudiante obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> update(@Valid @RequestBody EstudianteDTO dto, @PathVariable("id") Integer id) throws Exception{
        Estudiante obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/byAge")
    public ResponseEntity<List<EstudianteDTO>> readEstudiantesPorEdad() throws Exception{
        List<EstudianteDTO> list = service.getEstudiantesPorEdad().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    ///////////////////////////////////////////
    private EstudianteDTO convertToDto(Estudiante obj){
        return modelMapper.map(obj, EstudianteDTO.class);
    }

    private Estudiante convertToEntity(EstudianteDTO dto){
        return modelMapper.map(dto, Estudiante.class);
    }

}
