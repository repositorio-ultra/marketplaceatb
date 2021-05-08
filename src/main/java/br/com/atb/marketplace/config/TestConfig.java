package br.com.atb.marketplace.config;

import br.com.atb.marketplace.entities.*;
import br.com.atb.marketplace.entities.enums.ProdutoAtributo;
import br.com.atb.marketplace.entities.enums.ProdutoTipoComissao;
import br.com.atb.marketplace.entities.enums.Status;
import br.com.atb.marketplace.entities.enums.StatusPedido;
import br.com.atb.marketplace.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
// a implementação do commandline runner é para executar no startup da aplicação
public class TestConfig implements CommandLineRunner {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Override
    public void run(String... args) throws Exception {

        Produto p1 = new Produto(null, "EA993802",7892873984783L,"Primeiro Produto","Primeiro",5.4,1.99, ProdutoAtributo.NORMAL,0,false, ProdutoTipoComissao.NORMAL,"primeiro_produto.jpg", Status.ATIVO);
        Produto p2 = new Produto(null, "EA993802",7899948756253L,"Primeiro Produto","Segundo",5.4,2.99, ProdutoAtributo.NORMAL,0,false, ProdutoTipoComissao.NORMAL,"segundo_produto.jpg", Status.ATIVO);
        Produto p3 = new Produto(null, "EEDF0983", 7898837498738L, "Terceiro Produto", "Terceiro",4.35,3.99, ProdutoAtributo.COMBO, 0,true,ProdutoTipoComissao.NORMAL,"terceiro_produto.jpg",Status.ATIVO);
        Produto p4 = new Produto(null, "EEDF0984", 7894447498738L, "Quarto Produto", "Quarto",4.85,4.99, ProdutoAtributo.NORMAL, 0,true,ProdutoTipoComissao.NORMAL,"quarto_produto.jpg",Status.ATIVO);
        Produto p5 = new Produto(null, "EEDF0985", 7867545498738L, "Quinto Produto", "Quinto",4.77,5.99, ProdutoAtributo.NORMAL, 0,true,ProdutoTipoComissao.NORMAL,"quinto_produto.jpg",Status.ATIVO);
        Produto p6 = new Produto(null, "EEDF0986", 7898999998738L, "Sexto Produto", "Sexto",2.35,6.99, ProdutoAtributo.NORMAL, 0,true,ProdutoTipoComissao.NORMAL,"sexto_produto.jpg",Status.ATIVO);
        Produto p7 = new Produto(null, "EEDF0987", 7891111111738L, "Sétimo Produto", "Sétimo",3.35,7.99, ProdutoAtributo.BRINDE, 0,true,ProdutoTipoComissao.NORMAL,"setimo_produto.jpg",Status.ATIVO);
        Produto p8 = new Produto(null, "EEDF0988", 7898833454738L, "Oitavo Produto", "Oitavo",3.33,8.99, ProdutoAtributo.NORMAL, 0,true,ProdutoTipoComissao.NORMAL,"oitavo_produto.jpg",Status.ATIVO);
        Produto p9 = new Produto(null, "EEDF0989", 7892345223738L, "Nono Produto", "Nono",8.44,9.99, ProdutoAtributo.NORMAL, 0,true,ProdutoTipoComissao.NORMAL,"nono_produto.jpg",Status.ATIVO);
        Produto p10 = new Produto(null, "EEDF0990", 789567334233L, "Décimo Produto", "Décimo",5.55,10.99, ProdutoAtributo.DESCONTO_PROGRESSIVO, 0,true,ProdutoTipoComissao.NORMAL,"decimo_produto.jpg",Status.ATIVO);

        produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10));


        Categoria cat1 = new Categoria(null, "Cosméticos", 0L);
        Categoria cat2 = new Categoria(null, "Perfumes", 0L);
        Categoria cat3 = new Categoria(null, "Moda Feminina",0L);
        categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3));


        p1.getCategorias().add(cat2);
        p2.getCategorias().add(cat1);
        p3.getCategorias().add(cat3);
        p4.getCategorias().add(cat3);
        p5.getCategorias().add(cat1);
        p6.getCategorias().add(cat2);
        p7.getCategorias().add(cat1);
        p8.getCategorias().add(cat3);
        p9.getCategorias().add(cat3);
        p10.getCategorias().add(cat1);
        p1.getCategorias().add(cat1);

        produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10));

        Cliente cli1 = new Cliente(null,"AnnMarie Lacombe", "Anne","amarie", "abc123",634838765342L, Instant.parse("1985-12-03T03:00:00Z"));
        Cliente cli2 = new Cliente(null,"James Brown", "Jimmy","jbrown", "123456",999838299232L, Instant.parse("1992-03-15T03:00:00Z"));
        
        clienteRepository.saveAll(Arrays.asList(cli1,cli2));

        Pedido o1 = new Pedido(null, Instant.parse("2019-06-20T19:53:07Z"), StatusPedido.DELIVERED, cli1);
        Pedido o2 = new Pedido(null, Instant.parse("2019-07-21T03:42:10Z"),StatusPedido.PAID, cli2);
        Pedido o3 = new Pedido(null, Instant.parse("2019-07-22T15:21:22Z"),StatusPedido.SHIPPED, cli1);

        pedidoRepository.saveAll(Arrays.asList(o1,o2,o3));

        //se eu repetir o item do pedido
        //ELE VAI SER SOBREESCRITO!!!!!!
        PedidoItem oi1 = new PedidoItem(o1, p1, 2, p1.getPreco());
        PedidoItem oi2 = new PedidoItem(o1, p3, 1, p3.getPreco());
        PedidoItem oi3 = new PedidoItem(o2, p3, 2, p3.getPreco());
        PedidoItem oi4 = new PedidoItem(o3, p5, 2, p5.getPreco());
        PedidoItem oi5 = new PedidoItem(o1, p1, 9, p1.getPreco());

        pedidoItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4, oi5));

        PedidoPagamento pag1 = new PedidoPagamento(null,Instant.parse("2019-06-20T21:53:07Z"), o1);

        //Para salvarmos um objeto dependente em uma relação 1X1
        // Não chamamos o repository do próprio objeto ( pedidoPagamentoRepository)
        // faço o setPagamento no pedido é a classe ( objeto ) forte do relacionamento 1 x 1
        // e salvo o objeto pedido e não o pagamento.

        o1.setPagamento(pag1);
        //Usamos o save ao invés do saveAll
        pedidoRepository.save(o1);

    }
}
