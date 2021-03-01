package br.com.zup.testes;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import br.com.zup.testes.testes.builder.CriadorDeLeilao;
import br.com.zup.testes.testes.model.Lance;
import br.com.zup.testes.testes.model.Leilao;
import br.com.zup.testes.testes.model.Usuario;
import br.com.zup.testes.testes.services.Avaliador;

@SpringBootTest
public class TesteAvaliador {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;
    
    @Before
    public void criaAvaliador() {
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("Joao");
        this.jose = new Usuario("Jose");
        this.maria = new Usuario("Maria");
    }

    @Test
    public  void deveEntenderLancesEmOrdemCrescente() {

        Leilao leilao = new CriadorDeLeilao().para("Playstation 5")
            .lance(joao, 250.0)
            .lance(jose, 300.0)
            .lance(maria, 400.0)
            .constroi();

        leiloeiro.avalia(leilao);

        double maiorEsperado = 400.0;
        double menorEsperado = 250.0;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);

        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);    
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {

        Leilao leilao = new CriadorDeLeilao().para("Playstation 5")
            .lance(joao, 1000.0)
            .constroi();

        leiloeiro.avalia(leilao);
    
        assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);

        assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {

        Leilao leilao = new CriadorDeLeilao().para("Playstation 5")
            .lance(joao, 100.0)
            .lance(maria, 200.0)
            .lance(joao, 300.0)
            .lance(maria, 400.0)
            .constroi();
        

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());

        assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
        assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
    }

    @Test
    public void deveEntenderLeilaoComValoresRandomicos() {

        Leilao leilao = new CriadorDeLeilao().para("Carro")
            .lance(joao, 40000.0)
            .lance(maria, 41000.0)
            .lance(joao, 10.0)
            .lance(maria, 100000.0)
            .constroi();

        leiloeiro.avalia(leilao);

        assertEquals(100000.0, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(10.0, leiloeiro.getMenorLance(), 0.00001);
    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemDecrescente() {

        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 novo")
            .lance(joao, 400.0)
            .lance(maria, 300.0)
            .lance(joao, 200.0)
            .lance(maria, 100.0)
            .constroi();

        criaAvaliador();
        leiloeiro.avalia(leilao);

        assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
    }
}