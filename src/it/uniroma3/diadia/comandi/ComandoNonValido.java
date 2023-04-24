package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {


	@Override
	public void esegui(Partita partita) {
		DiaDia.io.mostraMessaggio("Comando non valido.");  // si desidera smettere

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