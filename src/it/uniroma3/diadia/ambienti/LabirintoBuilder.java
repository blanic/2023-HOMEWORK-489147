package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {

	private Labirinto labirinto;
	private Map<String,Stanza> stanzeLabirinto; 
	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.stanzeLabirinto = new HashMap<>();
	}

	public LabirintoBuilder addStanza(String nomeStanza) {
		Stanza nuovaStanza = new Stanza(nomeStanza);
		this.stanzeLabirinto.put(nomeStanza, nuovaStanza);
		return this;
	}

	public LabirintoBuilder addStanze(List<String> nomiStanze) {
		for(String nomeStanza : nomiStanze) {
			Stanza stanza = new Stanza(nomeStanza);
			this.stanzeLabirinto.put(nomeStanza, stanza);
		}
		return this;
	}

	public LabirintoBuilder addStanzaMagica(String nomeStanza) {
		Stanza stanza = new StanzaMagica(nomeStanza);
		this.stanzeLabirinto.put(nomeStanza, stanza);
		return this;
	}

	public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzoPerVedere) {
		Stanza stanza = new StanzaBuia(nomeStanza, attrezzoPerVedere);
		this.stanzeLabirinto.put(nomeStanza, stanza);
		return this;
	}

	public LabirintoBuilder addStanzaBloccata(String nomeStanza, String direzioneBloccata, String attrezzoSbloccante) {
		StanzaBloccata stanza = new StanzaBloccata(nomeStanza, direzioneBloccata, attrezzoSbloccante);
		this.stanzeLabirinto.put(nomeStanza, stanza);
		return this;
	}

	public LabirintoBuilder setStanzaIniziale(String nomeStanzaIniziale) {
		Stanza stanzaIniziale = this.stanzeLabirinto.get(nomeStanzaIniziale);
		this.labirinto.setStanzaIniziale(stanzaIniziale);
		return this;
	}

	public LabirintoBuilder setStanzaVincente(String nomeStanzaVincente) {
		Stanza stanzaVincente = this.stanzeLabirinto.get(nomeStanzaVincente);
		this.labirinto.setStanzaVincente(stanzaVincente);
		return this;
	}

	public LabirintoBuilder addAdiacenza(String nomeStanza, String nomeStanzaAdiacente, String direzione) {
		Stanza stanza = this.stanzeLabirinto.get(nomeStanza);
		Stanza stanzaAdiacente = this.stanzeLabirinto.get(nomeStanzaAdiacente);
		stanza.impostaStanzaAdiacente(direzione, stanzaAdiacente);
		return this;
	}

	public LabirintoBuilder addAttrezzoAStanza(String nomeStanza, String nomeAttrezzo, int pesoAttrezzo) {
		Stanza stanza = this.stanzeLabirinto.get(nomeStanza);
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, pesoAttrezzo);
		stanza.addAttrezzo(attrezzo);
		return this;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public Stanza getStanza(String nomeStanza) {
		return this.stanzeLabirinto.get(nomeStanza);
	}

}
