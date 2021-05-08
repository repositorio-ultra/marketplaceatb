package br.com.atb.marketplace.entities.enums;

public enum ProdutoAtributo {
    NORMAL(1),
    BRINDE(2),
    COMBO(3),
    DESCONTO_PROGRESSIVO(4);

    private int code;

    // criamos um construtor que TEM QUE SER PRIVATE

    private ProdutoAtributo(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return this.code;
    }

    public static ProdutoAtributo valueOf(int code){
        for (ProdutoAtributo value : ProdutoAtributo.values()){
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código de Atributo do Produto Inválido");
    }



}
