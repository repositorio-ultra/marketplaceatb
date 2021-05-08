package br.com.atb.marketplace.repositories;

import br.com.atb.marketplace.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
