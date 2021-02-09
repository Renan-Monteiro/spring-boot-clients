package io.builders.module.cliente.mapper;

import io.builders.module.cliente.dto.ClienteDTO;
import io.builders.module.cliente.entity.ClienteEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-09T18:05:32-0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.10 (AdoptOpenJDK)"
)
@Component
public class ClienteMapperImpl extends ClienteMapper {

    @Override
    public List<ClienteDTO> toDto(List<ClienteEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ClienteDTO> list = new ArrayList<ClienteDTO>( entities.size() );
        for ( ClienteEntity clienteEntity : entities ) {
            list.add( toDto( clienteEntity ) );
        }

        return list;
    }

    @Override
    public List<ClienteEntity> toEntity(List<ClienteDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<ClienteEntity> list = new ArrayList<ClienteEntity>( dtos.size() );
        for ( ClienteDTO clienteDTO : dtos ) {
            list.add( toEntity( clienteDTO ) );
        }

        return list;
    }
}
