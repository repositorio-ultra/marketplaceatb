package br.com.atb.marketplace.repositories;

import br.com.atb.marketplace.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}
