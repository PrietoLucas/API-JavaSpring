package rd.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rd.ecommerce.model.Cliente;
import rd.ecommerce.repository.ClienteRepository;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping("/clientes")
    public ResponseEntity <List<Cliente>> listar(){
        List<Cliente> clientes = repository.findAll();
        return ResponseEntity.ok().body(clientes);
    }
}
