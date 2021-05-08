package br.com.atb.marketplace.entities;

import br.com.atb.marketplace.entities.enums.ProdutoAtributo;
import br.com.atb.marketplace.entities.enums.ProdutoTipoComissao;
import br.com.atb.marketplace.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private Long ean;
    private String nome;
    private String nomeReduzido;
    private Double peso;
    private Double preco;
    private ProdutoAtributo produtoAtributo;
    private Integer produtoLimitacao;
    private Boolean produtoFreteGratis;
    private ProdutoTipoComissao produtoTipoComissao;
    private String produtoImagemURL;
    private Status produtoStatus;



    //Associações
    // Usamos Set, porque ele obriga valores únicos, diferentemente da List
    // Set é uma interface, portanto não podemos instaciá-lo. Temos que usar uma implementação
    // da interface, que no caso é o HashSet
    // Inicializamos a colection porque não desejamos um valor inicial null ( mas uma lista vazia )
    //@Transient uso o transiente para evitar que o Spring boot processe esta associação

    //Estou associando muitos produtos com muitas categorias
    // Estou ou a classe produto ou a classe categoria
    // Escolhi produto
    // Associação muitos para muitos
    @ManyToMany
    // Crio a tabela de relacionamento N x N e defino o seu nome
    // nos próximos parâmetros eu defino o nome dos campos que vão receber as fks
    // JoinColumn define o nome do campo que vai receber a fk que se refere a esta tabela ( produto )
    // o inverseJoincolumn vai ser usado para definir a fk que se refere a outra tabela do NXN, no caso
    // a categoria, e defino o nome do campo
    // Depois vou para a classe Categoria para indicar o Mapeamento
    @JoinTable(name = "produto_categoria_rel",
            joinColumns = @JoinColumn(name = "id_produto"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<Categoria> categorias = new HashSet<>();


    // Lista de pedidos associados a um produto
    // CUIDADO AO UTILIZAR ISTO
    // Lembra que a coleção SET é para que não haja itens repetidos
    @OneToMany(mappedBy = "id.produto")
    private Set<PedidoItem> itens = new HashSet<>();

    public Produto() {
    }

    public Produto(Long id, String codigo, Long ean, String nome, String nomeReduzido, Double peso, Double preco, ProdutoAtributo produtoAtributo, Integer produtoLimitacao, Boolean produtoFreteGratis, ProdutoTipoComissao produtoTipoComissao, String produtoImagemURL, Status produtoStatus) {
        this.id = id;
        this.codigo = codigo;
        this.ean = ean;
        this.nome = nome;
        this.nomeReduzido = nomeReduzido;
        this.peso = peso;
        this.preco = preco;
        this.produtoAtributo = produtoAtributo;
        this.produtoLimitacao = produtoLimitacao;
        this.produtoFreteGratis = produtoFreteGratis;
        this.produtoTipoComissao = produtoTipoComissao;
        this.produtoImagemURL = produtoImagemURL;
        this.produtoStatus = produtoStatus;


    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getEan() {
        return ean;
    }

    public void setEan(Long ean) {
        this.ean = ean;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeReduzido() {
        return nomeReduzido;
    }

    public void setNomeReduzido(String nomeReduzido) {
        this.nomeReduzido = nomeReduzido;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public ProdutoAtributo getProdutoAtributo() {
        return produtoAtributo;
    }

    public void setProdutoAtributo(ProdutoAtributo produtoAtributo) {
        this.produtoAtributo = produtoAtributo;
    }

    public Integer getProdutoLimitacao() {
        return produtoLimitacao;
    }

    public void setProdutoLimitacao(Integer produtoLimitacao) {
        this.produtoLimitacao = produtoLimitacao;
    }

    public Boolean getProdutoFreteGratis() {
        return produtoFreteGratis;
    }

    public void setProdutoFreteGratis(Boolean produtoFreteGratis) {
        this.produtoFreteGratis = produtoFreteGratis;
    }

    public ProdutoTipoComissao getProdutoTipoComissao() {
        return produtoTipoComissao;
    }

    public void setProdutoTipoComissao(ProdutoTipoComissao produtoTipoComissao) {
        this.produtoTipoComissao = produtoTipoComissao;
    }

    public String getProdutoImagemURL() {
        return produtoImagemURL;
    }

    public void setProdutoImagemURL(String produtoImagemURL) {
        this.produtoImagemURL = produtoImagemURL;
    }

    public Status getProdutoStatus() {
        return produtoStatus;
    }

    public void setProdutoStatus(Status produtoStatus) {
        this.produtoStatus = produtoStatus;
    }

    //Antes do JsonIgnore, a consulta de cada produto
    //ESTAVA TRAZENDO TODOS OS PEDIDOS ASSOCIADOS A ELE!!!!!
    // Agora, nos pedidos
    // em cada item de pedido
    // vai haver o detalhe do produto
    @JsonIgnore
    public Set<Pedido> getPedidos(){
        Set<Pedido> pedidos = new HashSet<>();
        for(PedidoItem x : itens)
        {
            pedidos.add(x.getPedido());
        }

        return pedidos;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id.equals(produto.id) && Objects.equals(codigo, produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo);
    }
}


