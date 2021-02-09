package io.builders.module.cliente.service;

import io.builders.module.cliente.entity.ClienteEntity;
import io.builders.module.cliente.repository.ClienteRepository;
import io.builders.module.mock.ClientEntityMock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
public class ClienteServiceTest {

    @Mock
    ClienteRepository clienteRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveTestarSalvar() {
        when(clienteRepository.save(any())).thenReturn(ClientEntityMock.getEntity());

        ClienteEntity entity = clienteRepository.save(ClientEntityMock.getEntity());
        assertNotNull(entity);
    }
}
