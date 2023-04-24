package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	private String nomeAttrezzo;


	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if(nomeAttrezzo==null) {
			DiaDia.io.mostraMessaggio("Cosa vuoi prendere ?");
		}
		else if(!stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
			DiaDia.io.mostraMessaggio("Attrezzo non presente nella stanza.");
		}
		else {
			Attrezzo attrezzoDaPrendere = stanzaCorrente.getAttrezzo(nomeAttrezzo);
			boolean preso = partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere);
			if(preso) {
				stanzaCorrente.removeAttrezzo(attrezzoDaPrendere);
				DiaDia.io.mostraMessaggio("Attrezzo risposto nella borsa!");
			}
			else DiaDia.io.mostraMessaggio("Impossibile prendere l'attrezzo.");
		}
	}

	@Override
	public String getNome() {
		return this.getClass().toString();
	}

	@Override
	public void setParametro(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;		
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;	
	}


}
