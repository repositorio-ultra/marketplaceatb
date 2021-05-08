package br.com.atb.marketplace.resources;

import br.com.atb.marketplace.entities.Pedido;
import br.com.atb.marketplace.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll(){
        //Pedido cl1 = new Pedido(1L,"Ricardo Vieira da Silva", "Ricardo","ricvsweb", "ric123",14394393892L, Instant.parse("1971-02-25T03:00:00Z"));
        List<Pedido> list = service.findAll();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id){
        Pedido obj = service.findById(id);

        return ResponseEntity.ok().body(obj);
    }
}
