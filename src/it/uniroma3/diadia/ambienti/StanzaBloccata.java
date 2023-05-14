package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.DiaDia;

public class StanzaBloccata extends Stanza {

	//	private static final String DIREZIONE_BLOCCATA = "sud";
	//	private static final String ATTREZZO_SBLOCCANTE = "Piede di porco";
	private static final String MESSAGGIO_DIREZIONE_BLOCCATA = "Il Passaggio è bloccato!";

	private String direzioneBloccata;
	private String attrezzoSbloccante;

	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}


	//	public StanzaBloccata(String nome) {
	//		this(nome, DIREZIONE_BLOCCATA, ATTREZZO_SBLOCCANTE);
	//	}
	//
	//	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(!direzione.equals(this.direzioneBloccata) || super.hasAttrezzo(attrezzoSbloccante)) {
			if (direzione.equals(this.direzioneBloccata)) {
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
		descrizione = new StringBuffer(descrizione).append("\nIl passaggio a "+ this.direzioneBloccata +" è bloccato. Usa " + this.attrezzoSbloccante + " per sbloccarlo.").toString();
		return descrizione;
	}



	public String getAttrezzoSbloccante() {
		return attrezzoSbloccante;
	}


	public void setAttrezzoSbloccante(String attrezzoSbloccante) {
		this.attrezzoSbloccante = attrezzoSbloccante;
	}


	public void setDirezioneBloccata(String direzioneBloccata) {
		this.direzioneBloccata = direzioneBloccata;
	}


	public String getDirezioneBloccata() {
		return direzioneBloccata;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + direzioneBloccata.hashCode() + attrezzoSbloccante.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(this.getClass() != o.getClass()) {
			return false;
		}
		StanzaBloccata that = (StanzaBloccata)o;
		return (super.equals(that) && 
				this.getDirezioneBloccata().equals(that.getDirezioneBloccata()) && 
						this.getAttrezzoSbloccante().equals(that.getAttrezzoSbloccante()));
	}


}
