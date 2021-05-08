package br.com.atb.marketplace.entities;

import br.com.atb.marketplace.entities.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
//para usar uma tabela com nome diferente do nome desta classe @Table(name="tb_pedido")
public class Pedido implements Serializable{
    //Não esquecer do Serializable
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant data;
    // Associações
    //Muitos Pedidos para 1 usuário
    //JoinColumn para definir a chave estrangeira
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    private Integer statusPedido;

    /* Um pedido tem muitos itens
    // mappedby: na coleção vou ter os itens dos pedidos
    // os itens dos pedidos possuem uma id composto ( pedido e produto )
    // por isso, para pegar o pedido
    // eu chamo id.pedido ( para produto seria id.produto)*/
    @OneToMany(mappedBy = "id.pedido")
    private Set<PedidoItem> itens = new HashSet<>();

    //Na Classe Dependente PedidoPagemento, deve haver o mesmo annotation
    // Cascade = mapeamos ambos pedido e pedidoPagamento para terem o mesmo id
    // se o pedido for id = 5 o pedidopagemento também vai ter o id = 5
    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private PedidoPagamento pagamento;

    public Pedido() {
    }

    public Pedido(Long id, Instant data, StatusPedido statusPedido, Cliente cliente) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        //this.statusPedido = statusPedido;
        setStatusPedido(statusPedido);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public StatusPedido getStatusPedido() {
        return StatusPedido.valueOf(statusPedido);
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        if ( statusPedido != null) {
            this.statusPedido = statusPedido.getCode();
        }
    }

    public Set<PedidoItem> getItens() {
        return itens;
    }

    public PedidoPagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(PedidoPagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Double getTotal()
    {
        double sum = 0.00;
        for (PedidoItem x : itens)
        {
            sum += x.getSubtotal();
        }

        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
