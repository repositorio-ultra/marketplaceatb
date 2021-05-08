package br.com.atb.marketplace.entities.enums;

public enum StatusPedido {
    //assim como está abaixo funciona, com id a partir do 0
    // não vamos usar assim

    //    WAITING_PAYMENT,
    //    PAID,
    //    SHIPPED,
    //    DELIVERED,
    //    CANCELED

    // para evitar problemas de manutenção, como inserir itens entre os que já existe e quebrar a chave no banco
    // faremos da seguinte forma

        WAITING_PAYMENT(1),
        PAID(2),
        SHIPPED(3),
        DELIVERED(4),
        CANCELED(5);

        private int code;

        // criamos um construtor que TEM QUE SER PRIVATE

    private StatusPedido(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return this.code;
    }

    public static StatusPedido valueOf(int code){
        for (StatusPedido value : StatusPedido.values()){
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código de Status de Pedido Inválido");
    }




}
