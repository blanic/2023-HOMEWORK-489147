package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {

	private String direzione;

	@Override
	public void esegui(Partita partita) {
		if (direzione==null) {
			DiaDia.io.mostraMessaggio("Dove vuoi andare ? \n Devi specificare una direzione.");
			return;
		}
		Stanza prossimaStanza = null;
		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			DiaDia.io.mostraMessaggio("Direzione inesistente.");
		else {
			partita.setStanzaCorrente(prossimaStanza);
			int cfu = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(--cfu);
		}
		DiaDia.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	@Override
	public String getNome() {
		return this.getClass().toString();		
	}

	@Override
	public void setParametro(String direzione) {
		this.direzione = direzione;

	}

	@Override
	public String getParametro() {
		return this.direzione;
	}

}
