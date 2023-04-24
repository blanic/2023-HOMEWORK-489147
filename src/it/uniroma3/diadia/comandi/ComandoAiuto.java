package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine"};

	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			DiaDia.io.mostraMessaggio(elencoComandi[i]+" ");
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
