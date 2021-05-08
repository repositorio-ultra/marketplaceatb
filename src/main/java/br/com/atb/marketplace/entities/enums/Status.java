package br.com.atb.marketplace.entities.enums;

public enum Status {
    ATIVO(1),
    INATIVO(2),
    SUSPENSO(3),
    BLOQUEADO(4),
    DESCONTINUADO(5);

    private int code;

    // criamos um construtor que TEM QUE SER PRIVATE

    private Status(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return this.code;
    }

    public static Status valueOf(int code){
        for (Status value : Status.values()){
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código de Status é Inválido");
    }

}
