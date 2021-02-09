package io.builders.module.mock;

import io.builders.module.cliente.entity.ClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientEntityMock {

    public static ClienteEntity getEntity() {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(1L);
        entity.setNome("Renan");
        entity.setEmail("mail@mail.com");
        entity.setTelefone("619123921391");
        entity.setDtNascimento(LocalDate.now());
        return entity;
    }

    public static Optional<ClienteEntity> getOptional() {
        return Optional.of(getEntity());
    }

    public static List<ClienteEntity> getList() {
        List<ClienteEntity> list = new ArrayList<>();

        list.add(getEntity());
        list.add(getEntity());

        return list;
    }

    public static Page<ClienteEntity> getPage() {
        List<ClienteEntity> list = new ArrayList<>();

        list.add(getEntity());
        list.add(getEntity());
        list.add(getEntity());
        list.add(getEntity());
        list.add(getEntity());

        return new PageImpl<>(list, PageRequest.of(0, list.size()), list.size());
    }
}
