package io.builders.module.cliente.repository;

import io.builders.module.cliente.entity.ClienteEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    List<ClienteEntity> findAll(Specification<ClienteEntity> specification);

}

