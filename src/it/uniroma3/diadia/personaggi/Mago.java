package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {

	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";

	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";

	private static final String MESSAGGIO_REGALO_RICEVUTO = "Grazie del regalo! Ti ho lasciato l'attrezzo modificato nella stanza";

	private static final String PRESENTAZIONE = "Ciao, io sono un mago!";

	private static final String NOME = "Merlino";

	Attrezzo attrezzoDaRegalare;
	
	public Mago() {
		this(NOME, PRESENTAZIONE, new Attrezzo("chiave",1));
	}

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzoDaRegalare = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzoDaRegalare!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzoDaRegalare);
			this.attrezzoDaRegalare = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		int pesoAttrezzo = attrezzo.getPeso();
		attrezzo.setPeso(pesoAttrezzo/2);
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		String msg = MESSAGGIO_REGALO_RICEVUTO;
		return msg;
	}

}
