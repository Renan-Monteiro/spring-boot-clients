package io.builders.module.cliente.service;

import io.builders.module.cliente.dto.ClienteDTO;
import io.builders.module.cliente.entity.ClienteEntity;
import io.builders.module.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.Predicate;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteEntity> getTodosClientes(){
        return clienteRepository.findAll();
    }

    public ClienteEntity adicionarCliente(ClienteEntity novoCliente) {
        return acoesEditarCadastrarCliente(novoCliente);
    }

    public ClienteEntity editarCliente(ClienteEntity clienteEditado) {
        return acoesEditarCadastrarCliente(clienteEditado);
    }

    private ClienteEntity acoesEditarCadastrarCliente(ClienteEntity cliente){
        return clienteRepository.save(cliente);
    }

    public ResponseEntity<Void> deletarClientePeloId(Long id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Optional<ClienteEntity> getClienteById(Long id){
        return clienteRepository.findById(id);
    }

    public Page<ClienteEntity> listar(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public List<ClienteEntity> listarPorFiltro(ClienteDTO filtro) {
        return clienteRepository.findAll(filter(filtro));
    }

    private static Specification<ClienteEntity> filter(ClienteDTO filtro) {
        return (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(filtro != null){

                if(filtro.getId() != null){
                    predicates.add(criteriaBuilder.equal(root.get("id"), filtro.getId()));
                }

                if(filtro.getEmail() != null){
                    predicates.add(criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("email")), "%"+filtro.getEmail().toUpperCase()+"%"));
                }
                if(filtro.getNome() != null){
                    predicates.add(criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("nome")), "%"+filtro.getNome().toUpperCase()+"%"));
                }

                if(filtro.getDtNascimento() != null){
                    predicates.add(criteriaBuilder.equal(root.get("dtNascimento"), filtro.getDtNascimento()));
                }

                if(filtro.getTelefone() != null){
                    predicates.add(criteriaBuilder.equal(root.get("telefone"), filtro.getTelefone()));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
