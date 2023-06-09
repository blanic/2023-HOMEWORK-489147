package it.uniroma3.diadia.personaggi;

import java.util.*;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_AZIONE = "Sei un vero simpaticone, " +
			"con una mia magica azione, ti troverai in un nuovo luogo, a mia discrezione.";

	private static final String MESSAGGIO_REGALO_RICEVUTO = "ahahahahahahahaha";

	private static final String NOME = "Beatrice";

	private static final String PRESENTAZIONE = "Ciao, io sono una strega!";

	Attrezzo attrezzo;
	
	public Strega() {
		this(NOME, PRESENTAZIONE);
	}
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		
		Collection<Stanza> stanzeLabirinto = partita.getLabirinto().getStanzeLabirinto().values();
		List<Stanza> stanze = new ArrayList<>(stanzeLabirinto);
		Collections.shuffle(stanze);
		Stanza nuovaStanzaCorrente = stanze.get(0);
		partita.setStanzaCorrente(nuovaStanzaCorrente);
		msg = MESSAGGIO_AZIONE;
	return msg;
}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg = MESSAGGIO_REGALO_RICEVUTO;
		return msg;
	}

}
