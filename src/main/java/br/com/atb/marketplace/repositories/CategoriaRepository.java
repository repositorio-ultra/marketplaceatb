package br.com.atb.marketplace.repositories;

import br.com.atb.marketplace.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
