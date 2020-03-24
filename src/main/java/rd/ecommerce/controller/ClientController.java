package rd.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rd.ecommerce.model.Cliente;
import rd.ecommerce.repository.ClienteRepository;

import javax.servlet.http.PushBuilder;
import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping("/clientes")
    public ResponseEntity <List<Cliente>> listar(){
        List<Cliente> clientes = repository.findAll();
        return ResponseEntity.ok().body(clientes);
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> adicionar(@RequestBody Cliente cliente){
        if(cliente == null) {
            return ResponseEntity.badRequest().body("Cliente não pode estar vazio");
        }
        return new ResponseEntity(repository.save(cliente), HttpStatus.CREATED);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> mostrar(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(repository.findById(id));
    }

    @GetMapping("/clientes2/{id}")
    public ResponseEntity<?> mostrar2(@PathVariable("id") Long id){
        Optional<Cliente> opt_cliente = repository.findById(id);
        Cliente cliente = opt_cliente.orElse(null);
        if (cliente == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cliente);
    }
}
