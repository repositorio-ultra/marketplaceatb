package br.com.atb.marketplace.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

// Relacionamento 1 X 1
// Um pedido pode ter 1  ou 0 PedidoPagamento ( PedidoPagamento é a classe dependente )
// Um pagamento tem que ter 1 pedido
@Entity
public class PedidoPagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant data_pagamento;

    //Pedido pagamento é uma Classe Dependente
    //Nesta classe ( e não na classe forte pedido ) fazemos o relacionamento
    //Usando OneToOne e MapsId
    //Depois vamos para a classe forte Pedido e anotamos no campo que faz o relacionamento
    //Também usando OneToOne
    // Não esquecer do json ignore
    @OneToOne
    @MapsId
    @JsonIgnore
    private Pedido pedido;

    public PedidoPagamento() {
    }

    public PedidoPagamento(Long id, Instant data_pagamento, Pedido pedido) {
        this.id = id;
        this.data_pagamento = data_pagamento;
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(Instant data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoPagamento that = (PedidoPagamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
