package io.builders.bdd;

import io.builders.module.cliente.entity.ClienteEntity;
import io.builders.module.cliente.repository.ClienteRepository;
import io.builders.module.mock.ClientEntityMock;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

@Slf4j
public class CadastroCliente {

    @Autowired
    ClienteRepository clienteRepository;

    @Dado("que sou um usuário")
    public void dado() {
        Assert.assertTrue(true);
    }

    @Quando("cadastro o novo cliente")
    public void quando() {
        log.info("cadastrando novo cliente");
    }

    @Entao("o sistema irá armazenar as informações do cliente")
    public void entao() {
        ClienteEntity entity = clienteRepository.save(ClientEntityMock.getEntity());
        assertNotNull(entity);
    }

}
