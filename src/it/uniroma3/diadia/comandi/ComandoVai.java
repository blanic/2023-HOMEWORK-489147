package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {

	private Direzione direzione;

	@Override
	public void esegui(Partita partita) {
		if (direzione==null) {
			DiaDia.io.mostraMessaggio("Dove vuoi andare ? \nDevi specificare una direzione.");
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
	public void setParametro(String direzione) {
		this.direzione = Direzione.valueOf(direzione);
	}

	@Override
	public String getParametro() {
		return this.direzione.toString();
	}

}
