package io.builders.module.cliente.mapper;

import io.builders.module.base.MapperBase;
import io.builders.module.cliente.dto.ClienteDTO;
import io.builders.module.cliente.entity.ClienteEntity;
import io.builders.util.Util;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ClienteMapper implements MapperBase<ClienteEntity, ClienteDTO> {

    @Override
    public ClienteDTO toDto(ClienteEntity entity) {
        if ( entity == null ) {
            return null;
        }
        ClienteDTO dto = new ClienteDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setNome(entity.getNome());
        dto.setTelefone(entity.getTelefone());
        dto.setDtNascimento(ofPattern("dd/MM/yyyy").format(entity.getDtNascimento()));
        dto.setIdade(Util.calculateAge(entity.getDtNascimento()));

        return dto;
    }

    public ClienteEntity toEntity(ClienteDTO dto){
        if ( dto == null ) {
            return null;
        }

        ClienteEntity.ClienteEntityBuilder clienteEntity = ClienteEntity.builder();

        clienteEntity.id( dto.getId() );
        clienteEntity.email( dto.getEmail() );
        clienteEntity.nome( dto.getNome() );
        clienteEntity.telefone( dto.getTelefone() );
        if(dto.getDtNascimento() != null ){
            clienteEntity.dtNascimento(LocalDate.parse(
                dto.getDtNascimento(), ofPattern("dd/MM/yyyy")
            ));
        }

        return clienteEntity.build();
    }
}
