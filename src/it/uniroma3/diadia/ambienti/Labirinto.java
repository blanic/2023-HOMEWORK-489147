package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class Labirinto {
	
	Stanza stanzaIniziale;
	Stanza stanzaVincente;
	Map<String,Stanza> stanzeLabirinto;

	/**
	 * Crea il labirinto, tutte le stanze e le porte di collegamento
	 */
	public Labirinto() {

//		List<String> stanze = Arrays.asList("Aula N10","Biblioteca");
//		LabirintoBuilder lB = new LabirintoBuilder()
//				.addStanze(stanze)
//				.addStanzaBloccata("Atrio", "nord", "chiave")
//				.addStanzaBuia("Aula N11", "lanterna")
//				.addStanzaMagica("Laboratorio")
//				.setStanzaIniziale("Atrio")
//				.setStanzaVincente("Biblioteca")
//				.addAdiacenza("Atrio","Biblioteca","nord")
//				.addAdiacenza("Atrio","Aula N11","est")
//				.addAdiacenza("Atrio","Aula N10","sud")
//				.addAdiacenza("Atrio","Laboratorio","ovest")
//				.addAdiacenza("Aula N11","Laboratorio","est")
//				.addAdiacenza("Aula N11","Atrio","ovest")
//				.addAdiacenza("Aula N10","Atrio","nord")
//				.addAdiacenza("Aula N10","Aula N11","est")
//				.addAdiacenza("Aula N10","Laboratorio","ovest")
//				.addAdiacenza("Laboratorio","Atrio","est")
//				.addAdiacenza("Laboratorio","Aula N11","ovest")
//				.addAdiacenza("Biblioteca","Atrio","sud")
//				.addAttrezzoAStanza("Aula N10", "lanterna", 5)
//				.addAttrezzoAStanza("Laboratorio", "osso", 8)
//				.addAttrezzoAStanza("Aula N11", "chiave", 1);
//		
//		Labirinto labirintoCreatoDaBuilder = lB.getLabirinto();
//		this.stanzaIniziale = labirintoCreatoDaBuilder.getStanzaIniziale();
//		this.stanzaVincente = labirintoCreatoDaBuilder.getStanzaVincente();
//		this.stanzeLabirinto = labirintoCreatoDaBuilder.getStanzeLabirinto();

	}
	
	private Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(nomeFile);
		caricatore.carica();
		this.stanzaVincente = caricatore.getStanzaVincente();
		this.stanzaIniziale = caricatore.getStanzaIniziale();
		this.stanzeLabirinto = caricatore.getNome2stanza();
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	public Map<String, Stanza> getStanzeLabirinto() {
		return stanzeLabirinto;
	}

	public void setStanzeLabirinto(Map<String, Stanza> stanzeLabirinto) {
		this.stanzeLabirinto = stanzeLabirinto;
	}
	
	
	
	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Map<String,Stanza> stanzeLabirinto; 
		
		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.stanzeLabirinto = new HashMap<>();
			this.labirinto.setStanzeLabirinto(stanzeLabirinto);
		}
		
		public LabirintoBuilder(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
			this.labirinto = new Labirinto(nomeFile);
			this.stanzeLabirinto = this.labirinto.getStanzeLabirinto();
		}
		
		public Labirinto getLabirinto() {
			return this.labirinto;
		}
		
	/* TUTTO QUELLO CHE SEGUE UTILE SOLO AI FINI DEL TESTING - 
	 * ORA SI OCCUPA CARICATORE LABIRINTO DI QUESTE FUNZIONI	
	 */

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
			StanzaBloccata stanza = new StanzaBloccata(nomeStanza, Direzione.valueOf(direzioneBloccata), attrezzoSbloccante);
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
			stanza.impostaStanzaAdiacente(Direzione.valueOf(direzione), stanzaAdiacente);
			return this;
		}

		public LabirintoBuilder addAttrezzoAStanza(String nomeStanza, String nomeAttrezzo, int pesoAttrezzo) {
			Stanza stanza = this.stanzeLabirinto.get(nomeStanza);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, pesoAttrezzo);
			stanza.addAttrezzo(attrezzo);
			return this;
		}
		
		public LabirintoBuilder addPersonaggioAStanza(String nomeStanza, String nomePersonaggio, String descrizionePersonaggio) {
			Stanza stanza = this.stanzeLabirinto.get(nomeStanza);
			
			try {
				String nomeClasse = "it.uniroma3.diadia.personaggi.";
				nomeClasse += Character.toUpperCase(nomePersonaggio.charAt(0));
				nomeClasse += nomeClasse.substring(1);
				@SuppressWarnings("deprecation")
				AbstractPersonaggio personaggio = (AbstractPersonaggio)Class.forName(nomeClasse).newInstance();
				stanza.setPersonaggio(personaggio);
				} catch (Exception e) {
				DiaDia.io.mostraMessaggio("Personaggio inesistente");
				}
			return this;
		}

		

		public Stanza getStanza(String nomeStanza) {
			return this.stanzeLabirinto.get(nomeStanza);
		}

	}
	
	

}
