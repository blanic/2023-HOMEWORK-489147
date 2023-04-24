package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.DiaDia;

public class StanzaBloccata extends Stanza {

	private static final String DIREZIONE_BLOCCATA = "sud";
	private static final String ATTREZZO_SBLOCCANTE = "Piede di porco";
	private static final String MESSAGGIO_DIREZIONE_BLOCCATA = "Il Passaggio è bloccato!";

	private String direzioneBloccata;
	private String attrezzoSbloccante;

	public StanzaBloccata(String nome) {
		this(nome, DIREZIONE_BLOCCATA, ATTREZZO_SBLOCCANTE);
	}

	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione != this.direzioneBloccata || super.hasAttrezzo(attrezzoSbloccante)) {
			if (direzione == this.direzioneBloccata) {
				DiaDia.io.mostraMessaggio("Hai sbloccato il passaggio!");
			}
			return super.getStanzaAdiacente(direzione);
		}
		else {
			DiaDia.io.mostraMessaggio(MESSAGGIO_DIREZIONE_BLOCCATA);
			return this;
		}
	}
	
	@Override
	public String getDescrizione() {
		String descrizione = super.getDescrizione();
		descrizione = new StringBuffer(descrizione).append("\n Il passaggio a "+ DIREZIONE_BLOCCATA +" è bloccato. Usa" + ATTREZZO_SBLOCCANTE + " per sbloccarlo.").toString();
		return descrizione;
	}

}
