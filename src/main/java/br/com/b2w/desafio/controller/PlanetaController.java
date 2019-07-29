package br.com.b2w.desafio.controller;

import br.com.b2w.desafio.config.BundleConfig;
import br.com.b2w.desafio.constantes.AppConstantes;
import br.com.b2w.desafio.entity.Planeta;
import br.com.b2w.desafio.exception.DadosInconsistentesException;
import br.com.b2w.desafio.exception.PlanetaInexistenteException;
import br.com.b2w.desafio.service.IPlanetaService;
import br.com.b2w.desafio.util.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PlanetaController {

    private static final Logger logger = LogManager.getLogger(PlanetaController.class);

    @Autowired
    private IPlanetaService planetaService;

    @PostMapping("/planetas")
    public ResponseEntity adicionar(@RequestBody Planeta planeta) {
        logger.info("Adicionando um novo planeta : " + planeta);

        if(!isDadosValidos(planeta))
            throw new DadosInconsistentesException(BundleConfig.getMensagem(AppConstantes.MSG_ERRO_DADOS_INCONSISTENTES));

          planetaService.salvar(planeta);
        return new ResponseEntity(BundleConfig.getMensagem(AppConstantes.MSG_PLANETA_CRIADO_SUCESSO), HttpStatus.CREATED);
    }

    @GetMapping("planetas")
    public ResponseEntity<List<Planeta>> listar() {
        logger.info("Listando todos os planetas");
        return new ResponseEntity(planetaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("planetas/{param}")
    public ResponseEntity<Optional<Planeta>> buscar(@PathVariable String param) {
        Optional<Planeta> planeta;

        if(Utils.isOnlyNumber(param)){
            logger.info("Buscando planeta pelo ID ", param);
            planeta = planetaService.findById(new BigInteger(param));
        }else{
            logger.info("Buscando planeta pelo Nome", param);
            planeta = planetaService.findByNome(param);
        }

        return new ResponseEntity(planeta, HttpStatus.OK);
    }

    @DeleteMapping("planetas/{id}")
    public ResponseEntity<String> deletar(@PathVariable BigInteger id) {
        logger.info("Removendo planeta {} " , id);

       Optional<Planeta> planeta= planetaService.findById(id);

       if(!planeta.isPresent())
            throw new PlanetaInexistenteException(BundleConfig.getMensagem(AppConstantes.MSG_ERRO_PLANETA_INEXISTENTE));

        planetaService.deletar(id);
        return new ResponseEntity(BundleConfig.getMensagem(AppConstantes.MSG_PLANETA_REMOVIDO_SUCESSO),HttpStatus.OK);
    }

    private boolean isDadosValidos(Planeta planeta){
        if(planeta==null || (planeta.getNome()== null || "".equalsIgnoreCase(planeta.getNome()))
                || (planeta.getClima()== null || "".equalsIgnoreCase(planeta.getClima()))
                || (planeta.getTerreno()==null || "".equalsIgnoreCase(planeta.getTerreno())))
            return false;

        return true;
    }
}
