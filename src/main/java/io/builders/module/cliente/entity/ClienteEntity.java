package io.builders.module.cliente.entity;

import io.builders.module.base.EntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "TB_CLIENTE")
public class ClienteEntity implements EntityBase<Long> {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SQ_CLIENTE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TELEFONE")
    private String telefone;
    @Column(name = "DT_NASCIMENTO")
    private LocalDate dtNascimento;

}
