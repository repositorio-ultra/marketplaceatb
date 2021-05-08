package br.com.atb.marketplace.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
//para usar uma tabela com nome diferente do nome desta classe @Table(name="tb_cliente")
public class Cliente implements Serializable {
    //id, nome, nome_social, sexo, cpf, data_nascimento, nome_usuario, senha, (fk)id_status_financeiro

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String nome_social;
    private String nome_usuario;
    private String senha;
    private Long cpf;
    private Instant data_nascimento;

    //Associações
    //Pedido
    // Como pedidos é uma coleção, temos que instanciar ( new ArrayList<>())
    // Acrescentamos somente o get para a Lista
    //Um cliente tem 0 ou muitos pedidos (onetomany)
    //IMPORTANTE o mappedby vai identificar qual é (campo que contem ) a chave estrangeira na tabela de destino
    //mappedby é o nome do atributo da classe não o campo da tabela do BD
    //Json ignore é para não entrar em loop
    //Isso faz com que na listagem dos produtos, o cliente venha anexado
    //Relacionamentos Muitos para 1 a parte 1 aparece sozinha ( normal )
    // e a parte muitos traz o cliente associado a cada registro ( fk )
    // se eu colocar o jsonIgnore do lado N, e tirar aqui, o oposto vai ocorrer
    // os pedidos vão aparecer sozinhos e os clientes vão ser listados com os pedidos agregados
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();

    // cliente status financeiro enum
    // status
    // Aceitar Promoções
    // Aceitar LGPD
    // sexo



    public Cliente(Long id, String nome, String nome_social, String nome_usuario, String senha, Long cpf, Instant data_nascimento) {
        this.id = id;
        this.nome = nome;
        this.nome_social = nome_social;
        this.nome_usuario = nome_usuario;
        this.senha = senha;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
    }

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome_social() {
        return nome_social;
    }

    public void setNome_social(String nome_social) {
        this.nome_social = nome_social;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Instant getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Instant data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
