package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
	

	@Override
	public void esegui(Partita partita) {
		DiaDia.io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere

	}
}
