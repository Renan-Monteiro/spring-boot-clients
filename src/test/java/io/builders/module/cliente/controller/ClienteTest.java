package io.builders.module.cliente.controller;

import io.builders.module.cliente.dto.ClienteDTO;
import io.builders.module.cliente.entity.ClienteEntity;
import io.builders.module.cliente.v1.ClienteController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ClienteTest {

    @Autowired
    private ClienteController clienteController;

    @Test
    public void cadastrarCliente() {
        ResponseEntity<Page<ClienteDTO>> clientes = clienteController.listar(Pageable.unpaged());
        assertNotNull(clientes);
    }


}
