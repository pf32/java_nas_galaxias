package br.com.b2w.desafio.repository;

import br.com.b2w.desafio.constantes.AppConstantes;
import br.com.b2w.desafio.entity.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CounterRepository {

    @Autowired
    private CounterRepositorySpringData counterRepositorySpringData;

    @Autowired
    MongoTemplate mongoTemplate;


    public Counter salvar(Counter counter) {
        return counterRepositorySpringData.save(counter);
    }

    public long getNextPlanetaIdSequence() {
        return increaseCounter(AppConstantes.PLANETA_ID_SEQUENCE_NAME);
    }

    private long increaseCounter(String counterName){
        Query query = new Query(Criteria.where("name").is(counterName));
        Update update = new Update().inc("sequence", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        Counter counter = mongoTemplate.findAndModify(query, update, options,Counter.class);

        if(counter==null)
            return iniciarCounter();

        return counter.getSequence();
    }


    private long iniciarCounter(){
        Counter counter = new Counter();
        counter.setName(AppConstantes.PLANETA_ID_SEQUENCE_NAME);
        counter.setSequence(1);
        salvar(counter);
        return counter.getSequence();
    }

    interface CounterRepositorySpringData extends MongoRepository<Counter, String> {
    }
}
