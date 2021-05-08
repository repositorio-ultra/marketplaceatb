package br.com.atb.marketplace.entities.pk;

import br.com.atb.marketplace.entities.Pedido;
import br.com.atb.marketplace.entities.Produto;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

//Os itens dos pedidos são uma classe auxiliar
//para representar a chave composta produto-pedido
//A CLASSE AUXILIAR NÃO POSSUI OS CONSTRUTORES!!!!

// Usamos o annotation Embeddable para classe auxiliar para chave composta

@Embeddable
public class ItemPedidoPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name= "id_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name= "id_produto")
    private Produto produto;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoPK that = (ItemPedidoPK) o;
        return Objects.equals(pedido, that.pedido) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, produto);
    }
}
