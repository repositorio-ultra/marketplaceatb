package br.com.atb.marketplace.entities.enums;

public enum ProdutoTipoComissao {

    NORMAL(1),
    REDUZIDA(2),
    SUPER_REDUZIDA(3),
    CHEIA(4),
    PREMIUM(5);

    private int code;

    // criamos um construtor que TEM QUE SER PRIVATE

    private ProdutoTipoComissao(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return this.code;
    }

    public static ProdutoTipoComissao valueOf(int code){
        for (ProdutoTipoComissao value : ProdutoTipoComissao.values()){
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código do Tipo de Comissão é Inválido");
    }


}
