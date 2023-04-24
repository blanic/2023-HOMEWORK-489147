package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

	private static final String MESSAGGIO_STANZA_BUIA = "qui c'è un buio pesto";
	private static final String ATTREZZO_PER_VEDERE = "Lanterna";

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
	public String getDescrizione() {
		if (super.hasAttrezzo(attrezzoPerVedere)) {
			return super.getDescrizione();
		}

		return this.messaggioStanzaBuia;
	}
}
