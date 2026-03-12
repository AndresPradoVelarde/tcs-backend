package com.tcs.clientservice.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @NotBlank
    private String nombre;

    @NotBlank
    private String genero;

    @NotNull
    private Integer edad;

    @NotBlank
    private String identificacion;

    private String direccion;

    private String telefono;

    @NotBlank
    private String clienteId;

    @NotBlank
    private String contrasena;

    @NotNull
    private Boolean estado;
}
