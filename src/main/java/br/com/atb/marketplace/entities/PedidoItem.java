package br.com.atb.marketplace.entities;

import br.com.atb.marketplace.entities.pk.ItemPedidoPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class PedidoItem implements Serializable {
    private static final long serialVersionUID = 1L;

    //Quando for chamar um chave composta
    //TEM QUE INICIALIZAR!!!!!!
    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    private Integer quantidade;
    private Double preco;

    public PedidoItem() {
    }

    // NÃO INCLUIR O ID NO CONSTRUTOR
    public PedidoItem(Pedido pedido, Produto produto, Integer quantidade, Double preco) {
        // definimos a pk
        id.setPedido(pedido);
        id.setProduto(produto);
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Para o pedido não chamar os itens de pedidos
    // que contem um id de pedido que que chama o pedido de novo
    // e entra em looping infinito
    // usamos o JsonIgnore
    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    public void setPedido(Pedido pedido){
        id.setPedido(pedido);
    }

    // o mesmo motivo do getPedido
    //@JsonIgnore
    //Removi o Json ignore
    //Porque desta maneira para cada produto aparecem todos os pedidos relacionados
    // too bad
    // vamos colocar o Jsonignore na Class Produtos
    // Jsonignore no getPedidos
    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto(Produto produto){
        id.setProduto(produto);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    // Os métodos para funcionarem no Java Enterprise
    // tem que começar com get
    // então o método subtotal precisa ser chamado getSubtotal
    //public Double subtotal()
    public Double getSubtotal()
    {
        return preco * quantidade;
    }

    // SOMENTE O ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoItem that = (PedidoItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
