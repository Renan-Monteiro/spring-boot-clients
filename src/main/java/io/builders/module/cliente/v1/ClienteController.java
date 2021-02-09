package io.builders.module.cliente.v1;

import io.builders.module.cliente.dto.ClienteDTO;
import io.builders.module.cliente.entity.ClienteEntity;
import io.builders.module.cliente.mapper.ClienteMapper;
import io.builders.module.cliente.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/clientes")
@Api(value = "Clientes", description = "Operações sobre clientes", tags = "Cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteMapper clienteMapper;

    @PutMapping
    @ApiOperation(value = "Editar cliente")
    public ResponseEntity<Void> editarCliente(
        @ApiParam(name = "cliente", value = "Representação do cliente a ser editado", required = true)
        @Valid @RequestBody ClienteDTO dto) {
        ClienteEntity novoCliente = clienteMapper.toEntity(dto);
        novoCliente = clienteService.editarCliente(novoCliente);
        ClienteDTO clienteEditado = clienteMapper.toDto(novoCliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
            "/editar").buildAndExpand(clienteEditado.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deleta cliente pelo ID")
    public ResponseEntity<Void> deletarCliente(@PathVariable(value = "id") Long id) {
        ClienteEntity cliente = clienteService.getClienteById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
        clienteService.deletarClientePeloId(cliente.getId());
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @ApiOperation(value = "Cadastrar cliente")
    public ResponseEntity<Void> cadastrarCliente(
            @ApiParam(name = "Cliente", value = "Representação do usuário a ser adicionado", required = true)
            @Valid @RequestBody ClienteDTO dto) {
        ClienteEntity novoCliente = clienteMapper.toEntity(dto);
        novoCliente = clienteService.adicionarCliente(novoCliente);
        ClienteDTO colaboradorCriado = clienteMapper.toDto(novoCliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(colaboradorCriado.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @ApiOperation(value = "Listar clientes")
    public ResponseEntity<Page<ClienteDTO>> listar(Pageable pageable) {
        Page<ClienteDTO> pageDTO = clienteService.listar(pageable).map(clienteMapper::toDto);
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping("listarPorFiltro")
    @ApiOperation(value = "Listar clientes")
    public ResponseEntity<List<ClienteDTO>> listarPorFiltro(@Valid ClienteDTO filtro) {
        List<ClienteDTO> pageDTO = clienteMapper.toDto(clienteService.listarPorFiltro(filtro));
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar clientes por ID.", response = ClienteDTO.class)
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        ClienteDTO dto = clienteMapper.toDto(clienteService.getClienteById(id).get());
        return ResponseEntity.ok(dto);
    }

}
