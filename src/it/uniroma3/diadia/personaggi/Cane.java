package it.uniroma3.diadia.personaggi;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	private static final String MESSAGGIO = "Woff Woff";

	private static final String CIBO_PREFERITO = "osso";

	private static final String PRESENTAZIONE = "Woff Woff";

	private static final String NOME = "Fuffi";

	String ciboPreferito;
	
	public Cane() {
		this(NOME, PRESENTAZIONE, CIBO_PREFERITO);
	}

	public Cane(String nome, String presentazione, String ciboPreferito) {
		super(nome, presentazione);
		this.ciboPreferito = ciboPreferito;
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		int cfuAttualiGiocatore = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(--cfuAttualiGiocatore);
		msg = MESSAGGIO;
		
	return msg;
}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		
		if(this.ciboPreferito.equals(attrezzo.getNome())) {
			Attrezzo regalo = new Attrezzo("chiave",1);
			partita.getStanzaCorrente().addAttrezzo(regalo);
		}
		else {
			this.agisci(partita);
		}
		String msg = MESSAGGIO;
		return msg;
	}


}
