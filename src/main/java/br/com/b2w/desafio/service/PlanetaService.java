package br.com.b2w.desafio.service;

import br.com.b2w.desafio.config.BundleConfig;
import br.com.b2w.desafio.constantes.AppConstantes;
import br.com.b2w.desafio.dto.PlanetaDTO;
import br.com.b2w.desafio.dto.ResponseDTO;
import br.com.b2w.desafio.entity.Planeta;
import br.com.b2w.desafio.exception.SWAPIException;
import br.com.b2w.desafio.repository.PlanetaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class PlanetaService implements IPlanetaService {

    private static final Logger logger = LogManager.getLogger(PlanetaService.class);

    private static final String URL_API_STAR_WARS = "https://swapi.co/api/planets?search=";

    @Autowired
    private PlanetaRepository planetaRepository;

    @Autowired
    private CounterService counterService;

    @Override
    public Planeta salvar(Planeta planeta) {
        ResponseDTO responseDTO = consumirSwapi(planeta.getNome());

        PlanetaDTO planetaDTO = responseDTO.getResults().stream()
                .filter(value -> planeta.getNome().equalsIgnoreCase(value.getName()))
                .findFirst().orElse(new PlanetaDTO());

        planeta.setId(BigInteger.valueOf(counterService.getNextUserIdSequence()));
        planeta.setQtdAparicao(Integer.parseInt(Optional.ofNullable(planetaDTO.getFilms()).map(List::size).orElse(0).toString()));
        return planetaRepository.salvar(planeta);
    }

    @Override
    public void deletar(BigInteger id) {
        planetaRepository.deletar(id);
    }

    @Override
    public Optional<Planeta> findByNome(String nome) {
        return planetaRepository.findByNome(nome);
    }

    @Override
    public Optional<Planeta> findById(BigInteger id) {
        return planetaRepository.findById(id);
    }

    @Override
    public List<Planeta> findAll() {
        return planetaRepository.findAll();
    }



    private ResponseDTO consumirSwapi(String nomePlaneta){
        logger.info("Consumindo API pública do Star Wars");

        try{
            RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
            HttpEntity<String> request = new HttpEntity<>("parameters", getHeaders());
            ResponseEntity<ResponseDTO> response = restTemplate.exchange(URL_API_STAR_WARS + nomePlaneta,
                    HttpMethod.GET, request, ResponseDTO.class);

            if(response.getStatusCode().is2xxSuccessful()){
                return response.getBody();
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            throw new SWAPIException(BundleConfig.getMensagem(AppConstantes.MSG_ERRO_SWAPI));
        }

        return null;
    }

    private MultiValueMap<String, String> getHeaders() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");

        return headers;
    }
}
