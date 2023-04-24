package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Comando {
	
	private String nomeAttrezzo;


	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		if(nomeAttrezzo==null) {
			DiaDia.io.mostraMessaggio("Cosa vuoi posare ?");
		}
		else if(!borsa.hasAttrezzo(nomeAttrezzo)) {
			DiaDia.io.mostraMessaggio("Non possiedi questo oggetto.");
		}
		else {
			Attrezzo attrezzoDaPosare = borsa.getAttrezzo(nomeAttrezzo);
			boolean posato = partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare);
			if(posato) {
				borsa.removeAttrezzo(nomeAttrezzo);
				DiaDia.io.mostraMessaggio("Attrezzo posato nella stanza!");
			}
			else DiaDia.io.mostraMessaggio("Impossibile posare l'attrezzo.");
		}

	}

	@Override
	public String getNome() {
		return this.getClass().toString();
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public void setParametro(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;
		
	}
		

}
