package io.builders.module.cliente.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String dtNascimento;
    private Integer idade;

}
