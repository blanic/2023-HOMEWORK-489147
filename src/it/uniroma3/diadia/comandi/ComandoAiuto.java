package it.uniroma3.diadia.comandi;

import java.util.Arrays;
import java.util.List;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	
	static final private List<String> elencoComandi = Arrays.asList("vai", "prendi", "posa", "guarda", "saluta" , "interagisci" , "regala" , "aiuto", "fine");

	@Override
	public void esegui(Partita partita) {
		
		
		for(String comando : elencoComandi)
			DiaDia.io.mostraMessaggio(comando+" ");
	}

}
