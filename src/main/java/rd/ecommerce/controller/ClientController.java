package rd.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rd.ecommerce.model.Cliente;
import rd.ecommerce.repository.ClienteRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping("/clientes")
    public ResponseEntity <List<Cliente>> listar(){
        List<Cliente> clientes = repository.findAll();
        return ResponseEntity.status(200).body(clientes);
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> adicionar(@RequestBody Cliente cliente){
        if(cliente == null) {
            return ResponseEntity.status(400).body("Cliente não pode estar vazio");
        }
        Cliente clienteAtualizado = repository.save(cliente);
        return ResponseEntity.status(201).body(clienteAtualizado);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> mostrar(@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(repository.findById(id));
    }

    //@GetMapping("/clientes/{id}")
    //public ResponseEntity<?> mostrar(@PathVariable("id") Long id){
        //Optional<Cliente> opt_cliente = repository.findById(id);
        //Cliente cliente = opt_cliente.orElse(null);
        //if (cliente == null){
            //return ResponseEntity.notFound().build();
        //}
        //return ResponseEntity.status(200).body(cliente);
    //}

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> modificar(@PathVariable("id")Long id,
                                       @RequestBody Cliente clienteDetails) {
        if (id <= 0) {
            return ResponseEntity.status(404).body("ID informado inválido");
        }
//        Cliente cliente = repository.findById(id).orElse(null);
//
//        if (clienteDetails == null) {
//            return ResponseEntity.status(400).body("O Cliente não pode estar vazio");
//        }
            Cliente cliente = repository
                    .findById(id)
                    .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));

            if (cliente == null) {
                return ResponseEntity.status(404).body("O cliente não foi encontrado!");
           }

            if (clienteDetails.getRg() != null) {
                cliente.setRg(clienteDetails.getRg());
            }

            if (clienteDetails.getTelefone() != null) {
                cliente.setTelefone(clienteDetails.getTelefone());
            }

            if (clienteDetails.getNome() != null) {
                cliente.setNome(clienteDetails.getNome());
            }

            if (clienteDetails.getCpf() != null) {

            }

            if (clienteDetails.getEmail() != null) {
                cliente.setEmail(clienteDetails.getEmail());
            }

            Cliente response = repository.save(cliente);

            return ResponseEntity.ok().body(response);

    }

//        @PutMapping("/clientes/{id}")
//        public ResponseEntity<?> modificar(@PathVariable("id")Long id,
//                @RequestBody Cliente cliente){
//            Cliente clienteEntity = repository.findById(id);
//            clienteEntity.setNome(cliente.getNome());
//            clienteEntity.setCpf(cliente.getCpf());
//            clienteEntity.setRg(cliente.getEmail());
//            clienteEntity.setTelefone(cliente.getTelefone());
//            clienteEntity.setEmail(cliente.getEmail());
//            Cliente clienteAtualizado = repository.save(clienteEntity);
//            return ResponseEntity.status(200).body(clienteAtualizado);
//        }
//    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping("/clientes/{id}")
//    public void deleteById(@PathVariable("id") Long id){
//        repository.deleteById(id);
//    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") Long id){
        return repository.findById(id).map(cliente -> {
            repository.delete(cliente);
//            repository.deleteById(id);
            return ResponseEntity.status(203).body("Cliente excluido");
        }) .orElse(ResponseEntity.status(404).build());
    }

//    @ExceptionHandler(EntityNotFoundException.class)
//    public String handlerException(EntityNotFoundException ex){
//        return ("Capturei um erro!");
//    }
}
