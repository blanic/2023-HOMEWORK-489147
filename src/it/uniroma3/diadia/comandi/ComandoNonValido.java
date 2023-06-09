package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {


	@Override
	public void esegui(Partita partita) {
		DiaDia.io.mostraMessaggio("Comando non valido.");  // si desidera smettere

	}

}
