package br.com.atb.marketplace.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Categoria implements Serializable{
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long parent_id;

    // Associações
    //@Transient ( usado para fazer o Springboot ignorar este relacionamento
    //O relacionamento é N x N com os produtos
    // mapped by define o campo, na tabela produtos, que recebe a coleção das categorias vindas
    // desta classe
    // Jsonignore faz com que na listagem dos produtos a categoria venha anexada
    @JsonIgnore
    @ManyToMany(mappedBy = "categorias")
    private Set<Produto> produtos = new HashSet<>();

    public Categoria() {
    }

    public Categoria(Long id, String nome, Long parent_id) {
        this.id = id;
        this.nome = nome;
        this.parent_id = parent_id;
    }

    public Set<Produto> getProdutos() {
        return produtos;
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

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id.equals(categoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
