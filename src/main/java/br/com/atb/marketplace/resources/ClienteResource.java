package br.com.atb.marketplace.resources;

import br.com.atb.marketplace.entities.Cliente;
import br.com.atb.marketplace.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

//import java.time.Instant;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        //Cliente cl1 = new Cliente(1L,"Ricardo Vieira da Silva", "Ricardo","ricvsweb", "ric123",14394393892L, Instant.parse("1971-02-25T03:00:00Z"));
        List<Cliente> list = service.findAll();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Cliente obj = service.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    // O annotation RequestBody é para realizar o parsing do Json de chegada no request para o objeto
    public ResponseEntity<Cliente> insert(@RequestBody Cliente obj)
    {
        obj = service.insert(obj);
        // da forma abaixo já funciona retornando 200, mas o ideal é retornar status 201
        //return ResponseEntity.ok().body(obj);

        // retornando 201
        // o /{id} vai retornar a url clientes/{id} do objeto criado no HEADER HTTP LOCATION
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value="/{id}")
    //Usamos o annotation @PathVariable para reconhecer um parâmetro da url
    public ResponseEntity<Void> delete (@PathVariable Long id){
            service.delete(id);
            return ResponseEntity.noContent().build();
    }

    @PutMapping(value= "/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente obj){
        obj = service.update(id,obj);
        return ResponseEntity.ok().body(obj);
    }
}
