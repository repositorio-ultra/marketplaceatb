package br.com.atb.marketplace.repositories;

import br.com.atb.marketplace.entities.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItemRepository extends JpaRepository<PedidoItem,Long> {
}
