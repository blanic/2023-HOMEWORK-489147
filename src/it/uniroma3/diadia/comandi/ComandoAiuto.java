package it.uniroma3.diadia.comandi;

import java.util.Arrays;
import java.util.List;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	
	static final private List<String> elencoComandi = Arrays.asList("vai", "prendi", "posa", "guarda", "aiuto", "fine");

	@Override
	public void esegui(Partita partita) {
		for(String comando : elencoComandi)
			DiaDia.io.mostraMessaggio(comando+" ");
//		io.mostraMessaggio();

	}

	@Override
	public String getNome() {
		return this.getClass().toString();
	}

	@Override
	public void setParametro(String parametro) {
		return;
	}

	@Override
	public String getParametro() {
		return null;
	}

}
