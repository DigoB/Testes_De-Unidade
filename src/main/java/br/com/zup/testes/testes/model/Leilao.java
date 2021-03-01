package br.com.zup.testes.testes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	// Logica para verificar se o mesmo usuario esta fazendo dois lances seguidos
	public void propoe(Lance lance) {
        if(lances.isEmpty() || podeDarLance(lance.getUsuario())) {
            lances.add(lance);
        }
    }

    private boolean podeDarLance(Usuario usuario) {
        return !ultimoLanceDado().getUsuario().equals(usuario) 
            && qtdDelancesDo(usuario) < 5;
    }

    private int qtdDelancesDo(Usuario usuario) {
        int total = 0;
        for(Lance lance : lances) {
            if(lance.getUsuario().equals(usuario)) total++;
        }
        return total;
    }

    private Lance ultimoLanceDado() {
        return lances.get(lances.size()-1);
    }

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}	
}