package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	private static final String MESSAGGIO_CON_CHI = "A chi dovrei regalarlo?...";

	String messaggio;
	String nomeAttrezzoDaRegalare;

	@Override
	public void esegui(Partita partita) {

		Attrezzo attrezzoDaRegalare = null;
		Borsa borsa = partita.getGiocatore().getBorsa();

		if(nomeAttrezzoDaRegalare==null) {
			DiaDia.io.mostraMessaggio("Cosa vuoi regalare ?");
			return;
		}
		else if(!borsa.hasAttrezzo(nomeAttrezzoDaRegalare)) {
			DiaDia.io.mostraMessaggio("Non puoi regalare questo attrezzo, non lo possiedi.");
			return;
		}
		else {
			attrezzoDaRegalare = borsa.getAttrezzo(nomeAttrezzoDaRegalare);
			if(attrezzoDaRegalare != null) {
				borsa.removeAttrezzo(nomeAttrezzoDaRegalare);
				DiaDia.io.mostraMessaggio("Attrezzo rimosso dalla borsa!");
			}
			else {
				DiaDia.io.mostraMessaggio("Impossibile prendere l'attrezzo.");
				return;
			}
		}

		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.riceviRegalo(attrezzoDaRegalare,partita);
			DiaDia.io.mostraMessaggio(this.messaggio);

		} else DiaDia.io.mostraMessaggio(MESSAGGIO_CON_CHI);
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzoDaRegalare;
	}

	@Override
	public void setParametro(String nomeAttrezzoDaRegalare) {
		this.nomeAttrezzoDaRegalare = nomeAttrezzoDaRegalare;

	}

}