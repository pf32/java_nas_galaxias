package br.com.b2w.desafio.repository;

import br.com.b2w.desafio.entity.Planeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class PlanetaRepository {

    @Autowired
    private PlanetaRepositorySpringData planetaRepositorySpringData;

    @Autowired
    MongoTemplate mongoTemplate;

    public Planeta salvar(Planeta planeta) {
        return planetaRepositorySpringData.save(planeta);
    }

    public void deletar(BigInteger id) {
        planetaRepositorySpringData.deleteById(id);
    }

    public Optional<Planeta> findByNome(String nome) {
        return planetaRepositorySpringData.findByNome(nome);
    }

    public Optional<Planeta> findById(BigInteger id) {
        return planetaRepositorySpringData.findById(id);
    }

    public List<Planeta> findAll() {
       return planetaRepositorySpringData.findAll();
    }
}

interface PlanetaRepositorySpringData extends MongoRepository<Planeta, BigInteger> {
    Optional<Planeta> findByNome(String nome);
}