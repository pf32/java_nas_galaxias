package br.com.b2w.desafio.service;

import br.com.b2w.desafio.entity.Planeta;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface IPlanetaService {

    Planeta salvar(Planeta planeta) ;

    void deletar(BigInteger id);

    Optional<Planeta> findByNome(String nome);

    Optional<Planeta> findById(BigInteger id) ;

    List<Planeta> findAll() ;
}
