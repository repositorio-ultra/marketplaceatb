package br.com.atb.marketplace.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id)
    {
        super("Não encontrado. ID: "+id);
    }
}
