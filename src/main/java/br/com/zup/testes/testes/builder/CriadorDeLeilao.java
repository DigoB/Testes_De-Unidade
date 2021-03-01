package br.com.zup.testes.testes.builder;

import br.com.zup.testes.testes.model.Lance;
import br.com.zup.testes.testes.model.Leilao;
import br.com.zup.testes.testes.model.Usuario;

public class CriadorDeLeilao {

    private Leilao leilao;

    public CriadorDeLeilao para(String descricao) {
        this.leilao = new Leilao(descricao);
        return this;
    }

    public CriadorDeLeilao lance(Usuario usuario, double valor) {
        leilao.propoe(new Lance(usuario, valor));
        return this;
    }

    public Leilao constroi() {
        return leilao;
    }
    
}
