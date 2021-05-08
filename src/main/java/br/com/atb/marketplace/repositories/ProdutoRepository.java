package br.com.atb.marketplace.repositories;

import br.com.atb.marketplace.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
