package com.mitocode.matricula.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstudianteDTO {

    private Integer idEstudiante;

    @NotNull
    @Size(min = 3, max = 100)
    private String nombres;

    @NotNull
    @Size(min = 3, max = 100)
    private String apellidos;

    @NotNull
    @Size(min = 8, max = 8)
    private String dni;

    @NotNull
    @Min(value = 10)
    private Integer edad;

}
