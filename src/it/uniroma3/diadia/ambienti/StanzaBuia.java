package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza {

	private static final String MESSAGGIO_STANZA_BUIA = "Qui c'è un buio pesto";
	private static final String ATTREZZO_PER_VEDERE = "lanterna";

	private String messaggioStanzaBuia;
	private String attrezzoPerVedere;

	public StanzaBuia(String nome) {
		this(nome, ATTREZZO_PER_VEDERE);
	}

	public StanzaBuia(String nome, String attrezzoPerVedere) {
		super(nome);
		this.attrezzoPerVedere = attrezzoPerVedere;
		this.messaggioStanzaBuia = MESSAGGIO_STANZA_BUIA;
	}

	@Override
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(!super.hasAttrezzo(this.attrezzoPerVedere)) {
			return null;
		}
		else {
			return super.getAttrezzo(nomeAttrezzo);
		}
	}

	@Override
	public String getDescrizione() {
		if (super.hasAttrezzo(attrezzoPerVedere)) {
			return super.getDescrizione();
		}

		return this.messaggioStanzaBuia;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + attrezzoPerVedere.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(this.getClass() != o.getClass()) {
			return false;
		}
		StanzaBuia that = (StanzaBuia)o;
		return (super.equals(that) &&
				this.attrezzoPerVedere.equals(that.attrezzoPerVedere));
	}
}
