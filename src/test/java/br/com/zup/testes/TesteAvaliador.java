package br.com.zup.testes;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import br.com.zup.testes.testes.model.Lance;
import br.com.zup.testes.testes.model.Leilao;
import br.com.zup.testes.testes.model.Usuario;
import br.com.zup.testes.testes.services.Avaliador;

@SpringBootTest
public class TesteAvaliador {

    @Test
    public  void deveEntenderLancesEmOrdemCrescente() {
        
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("Jose");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("PlayStation 5");

        leilao.propoe(new Lance(joao, 250.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(joao, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double maiorEsperado = 400.0;
        double menorEsperado = 250.0;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);

        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);    
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {

        Usuario joao = new Usuario("Joao");
        Leilao leilao = new Leilao("Playstation 5");

        leilao.propoe(new Lance(joao, 1000.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
    
        assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);

        assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {

        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 5");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        Avaliador leiloeiro = new Avaliador();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());

        assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
        assertEquals(200.0, maiores.get(2).getValor(), 0.00001);

    }
}