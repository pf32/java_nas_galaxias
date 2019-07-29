package br.com.b2w.desafio.service;

import br.com.b2w.desafio.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterService implements ICounterService {

    @Autowired
    private CounterRepository counterRepository;

    @Override
    public long getNextUserIdSequence() {
        return counterRepository.getNextPlanetaIdSequence();
    }
}
