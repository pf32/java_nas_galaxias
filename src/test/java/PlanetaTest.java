
import br.com.b2w.desafio.Application;
import br.com.b2w.desafio.config.BundleConfig;
import br.com.b2w.desafio.constantes.AppConstantes;
import br.com.b2w.desafio.entity.Planeta;
import br.com.b2w.desafio.repository.PlanetaRepository;
import org.codehaus.jackson.map.ObjectMapper;

import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.math.BigInteger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PlanetaTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PlanetaRepository repository;

    private MockMvc mockMvc;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Planeta planeta1 = new Planeta();
        planeta1.setId(new BigInteger("123"));
        planeta1.setNome("Planeta 1");
        planeta1.setClima("Polar");
        planeta1.setTerreno("gelo");
        planeta1.setQtdAparicao(1);
        repository.salvar(planeta1);

        Planeta planeta2 = new Planeta();
        planeta2.setId(new BigInteger("321"));
        planeta2.setNome("Planeta 2");
        planeta2.setClima("Tropical");
        planeta2.setTerreno("floresta");
        planeta2.setQtdAparicao(1);
        repository.salvar(planeta2);

        Planeta planeta3 = new Planeta();
        planeta3.setId(new BigInteger("111"));
        planeta3.setNome("Planeta 3");
        planeta3.setClima("Semiarido");
        planeta3.setTerreno("serrado");
        planeta3.setQtdAparicao(1);
        repository.salvar(planeta3);
    }


    @Test
    public void testCadastrarPlanetaComDadosInconsistentesSemValores()throws Exception {
        Planeta planeta = new Planeta();
        testCadastrarPlanetaComDadosInconsistente(planeta);
    }

    @Test
    public void testCadastrarPlanetaComDadosInconsistentesSemNome()throws Exception {
        Planeta planeta = new Planeta();
        planeta.setClima("Tropical");
        planeta.setTerreno("Arenoso");

        testCadastrarPlanetaComDadosInconsistente(planeta);
    }

    @Test
    public void testCadastrarPlanetaComDadosInconsistentesSemTerreno()throws Exception {
        Planeta planeta = new Planeta();
        planeta.setNome("Planeta 1");
        planeta.setClima("Tropical");

        testCadastrarPlanetaComDadosInconsistente(planeta);
    }

    @Test
    public void testCadastrarPlanetaComDadosInconsistentesSemClima()throws Exception {
        Planeta planeta = new Planeta();
        planeta.setNome("Planeta 1");
        planeta.setTerreno("Arenoso");

        testCadastrarPlanetaComDadosInconsistente(planeta);
    }

    private void testCadastrarPlanetaComDadosInconsistente(Planeta planeta)throws Exception {
        mockMvc.perform(post("/api/v1/planetas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(planeta)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"status\":400,\"detail\":\"Dados inconsistentes para cadastro.\",\"developerMessage\":\"br.com.b2w.desafio.exception.DadosInconsistentesException\"}"));
    }

    @Test
    public void testCadastrarPlanetaComSucesso()throws Exception {
        Planeta planeta = new Planeta();
        planeta.setNome("Planeta 3");
        planeta.setClima("Desertico");
        planeta.setTerreno("Arenoso");

        mockMvc.perform(post("/api/v1/planetas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(planeta)))
                .andExpect(status().isCreated())
                .andExpect(content().string(BundleConfig.getMensagem(AppConstantes.MSG_PLANETA_CRIADO_SUCESSO)));
    }

    @Test
    public void testFindPlanetaByNome()throws Exception {
        mockMvc.perform(get("/api/v1/planetas/Planeta 1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":123,\"nome\":\"Planeta 1\",\"clima\":\"Polar\",\"terreno\":\"gelo\",\"qtdAparicao\":1}"));
    }

    @Test
    public void testFindPlanetaById()throws Exception {
        mockMvc.perform(get("/api/v1/planetas/321"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":321,\"nome\":\"Planeta 2\",\"clima\":\"Tropical\",\"terreno\":\"floresta\",\"qtdAparicao\":1}"));
    }

    @Test
    public void testDeletarPlanetaComSucesso()throws Exception {
        mockMvc.perform(delete("/api/v1/planetas/111"))
                .andExpect(status().isOk())
                .andExpect(content().string(BundleConfig.getMensagem(AppConstantes.MSG_PLANETA_REMOVIDO_SUCESSO)));
    }

    @Test
    public void testDeletarPlanetaNaoEncontrado()throws Exception {
        mockMvc.perform(delete("/api/v1/planetas/333"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"status\":404,\"detail\":\"Erro ao remover Planeta. Registro não encontrado.\",\"developerMessage\":\"br.com.b2w.desafio.exception.PlanetaInexistenteException\"}"));
    }

   private String convertObjectToJsonBytes(Object object) throws IOException {
       ObjectMapper Obj = new ObjectMapper();
       return Obj.writeValueAsString(object);
    }

}
