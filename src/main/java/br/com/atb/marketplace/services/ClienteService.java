package br.com.atb.marketplace.services;

import br.com.atb.marketplace.entities.Cliente;
import br.com.atb.marketplace.repositories.ClienteRepository;
import br.com.atb.marketplace.services.exceptions.DataBaseException;
import br.com.atb.marketplace.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public List<Cliente> findAll(){
       return repository.findAll();
    }

    public Cliente findById(Long id){

        // Um objeto opcional porque a busca pode retornar vazia
        Optional<Cliente> obj;
        obj = repository.findById(id);
        // A linha abaixo funciona, mas retorna 500 quando não acha um registro
        // return obj.get() ; // vai pegar o objeto tipo Cliente que estiver na variável obj

        return obj.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public Cliente insert(Cliente obj)
    {
        // o método save já retorna o objeto salvo
        return repository.save(obj);
    }

    public void delete(Long id)
    {
        // Usamos o RuntimeException, que o tipo mais genérico de exceção
        // e não obriga tratamento
        try {
            repository.deleteById(id);
        }
        //catch (RuntimeException e){
            // Usamos o printStackTrace para imprimir o erro na tela
            // e depois definir qual tipo devemos capturar;
            //e.printStackTrace();
       // }
        catch (EmptyResultDataAccessException e)
        {
            throw new ResourceNotFoundException(id);
        }
        catch(DataIntegrityViolationException e)
        {
            throw new DataBaseException(e.getMessage());
        }


    }

    public Cliente update(Long id, Cliente obj)
    {
        // Neste caso, getOne é melhor doq eu findById
        try
        {
            Cliente entidade = repository.getOne(id);
            updateData(entidade, obj);
            return repository.save(entidade);

        }
        catch (EntityNotFoundException e)
        {
            //e.printStackTrace();
            // se não colocar a linha abaixo, o compilador reclama que não estamos retornando nada
            throw new ResourceNotFoundException(id);
        }

    }

    private void updateData(Cliente entidade, Cliente obj) {

        entidade.setNome(obj.getNome());
        entidade.setNome_social(obj.getNome_social());
        entidade.setNome_usuario(obj.getNome_usuario());
        entidade.setData_nascimento(obj.getData_nascimento());
    }
}
