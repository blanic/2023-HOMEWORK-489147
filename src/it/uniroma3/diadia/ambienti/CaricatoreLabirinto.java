package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	private static final String STANZE_BLOCCATE_MARKER = "Stanze Bloccate:";

	private static final String STANZE_BUIE_MARKER = "Stanze Buie:";

	private static final String STANZE_MAGICHE_MARKER = "Stanze Magiche:";

	private static final String MAGHI_MARKER = "Maghi:";

	private static final String STREGHE_MARKER = "Streghe:";

	private static final String CANI_MARKER = "Cani:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(StringReader stringReader) {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(stringReader);	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeMagiche();
			this.leggiECollocaMaghi();
			this.leggiECollocaStreghe();
			this.leggiECollocaCani();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}


	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}


	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String stanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);

		for(String specificaStanzaBloccata : separaStringheAlleVirgole(stanzeBloccate)) {
			String nomeStanzaBloccata = null;
			Direzione direzioneBloccata = null;
			String nomeAttrezzoSbloccante = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBloccata)) {
				while(scannerLinea.hasNext()) {
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza Bloccata."));
					nomeStanzaBloccata = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione bloccata"));
					direzioneBloccata = Direzione.valueOf(scannerLinea.next());
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("Il nome dell'attrezzo sbloccante."));
					nomeAttrezzoSbloccante = scannerLinea.next();

					Stanza stanzaBloccata = new StanzaBloccata(nomeStanzaBloccata,direzioneBloccata,nomeAttrezzoSbloccante);
					this.nome2stanza.put(nomeStanzaBloccata, stanzaBloccata);
				}
			}
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String stanzeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String specificaStanzaMagica : separaStringheAlleVirgole(stanzeMagiche)) {
			String nomeStanzaMagica = null;
			int sogliaMagica = 0;
			try (Scanner scannerLinea = new Scanner(specificaStanzaMagica)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza Magica."));
				nomeStanzaMagica = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("Il valore della soglia magica"));
				sogliaMagica = Integer.valueOf(scannerLinea.next());
			}				
			Stanza stanzaMagica = new StanzaMagica(nomeStanzaMagica,sogliaMagica);
			this.nome2stanza.put(nomeStanzaMagica, stanzaMagica);
		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String stanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String specificaStanzaBuia : separaStringheAlleVirgole(stanzeBuie)) {
			String nomeStanzaBuia = null;
			String nomeAttrezzoPerVedere = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBuia)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza Buia."));
				nomeStanzaBuia = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("Il nome dell'attrezzo per vedere"));
				nomeAttrezzoPerVedere = scannerLinea.next();
			}				
			Stanza stanzaBuia = new StanzaBuia(nomeStanzaBuia,nomeAttrezzoPerVedere);
			this.nome2stanza.put(nomeStanzaBuia, stanzaBuia);
		}
	}

	private void leggiECollocaMaghi() throws FormatoFileNonValidoException {
		String specificheMaghi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);

		for(String specificaMago : separaStringheAlleVirgole(specificheMaghi)) {
			String nomeMago = null;
			String presentazioneMago = null;
			String nomeAttrezzo = null; 
			int pesoAttrezzo = 0;
			String nomeStanza= null; 

			try (Scanner scannerLinea = new Scanner(specificaMago)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un mago."));
				nomeMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("La presentazione di un mago."));
				presentazioneMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("Il nome dell'attrezzo che il mago regala."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("Il peso dell'attrezzo che il mago regala."));
				pesoAttrezzo = Integer.valueOf(scannerLinea.next());
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il mago."));
				nomeStanza = scannerLinea.next();
			}				
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, pesoAttrezzo);
			AbstractPersonaggio mago = new Mago(nomeMago, presentazioneMago, attrezzo);
			Stanza stanza = this.nome2stanza.get(nomeStanza);
			stanza.setPersonaggio(mago);
		}
	}

	private void leggiECollocaStreghe() throws FormatoFileNonValidoException {
		String specificheStreghe = this.leggiRigaCheCominciaPer(STREGHE_MARKER);

		for(String specificaStrega : separaStringheAlleVirgole(specificheStreghe)) {
			String nomeStrega = null;
			String presentazioneStrega = null;
			String nomeStanza= null; 

			try (Scanner scannerLinea = new Scanner(specificaStrega)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una strega."));
				nomeStrega = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("La presentazione di una strega."));
				presentazioneStrega = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare la strega."));
				nomeStanza = scannerLinea.next();
			}				
			AbstractPersonaggio strega = new Strega(nomeStrega, presentazioneStrega);
			Stanza stanza = this.nome2stanza.get(nomeStanza);
			stanza.setPersonaggio(strega);
		}
	}

	private void leggiECollocaCani() throws FormatoFileNonValidoException {
		String specificheCani = this.leggiRigaCheCominciaPer(CANI_MARKER);

		for(String specificaCane : separaStringheAlleVirgole(specificheCani)) {
			String nomeCane = null;
			String presentazioneCane = null;
			String ciboPreferito = null;
			String nomeStanza= null; 

			try (Scanner scannerLinea = new Scanner(specificaCane)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un cane."));
				nomeCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("La presentazione di un cane."));
				presentazioneCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il cane."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il cibo preferito del cane."));
				ciboPreferito = scannerLinea.next();
			}				
			AbstractPersonaggio cane = new Cane(nomeCane, presentazioneCane, ciboPreferito);
			Stanza stanza = this.nome2stanza.get(nomeStanza);
			stanza.setPersonaggio(cane);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext()) {
				result.add(scannerDiParole.next());
			}
		}
		return result;
	}



	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);
		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(this.isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {
			String stanzaPartenza = null;
			Direzione direzione = null;
			String stanzaDestinazione = null; 
			try (Scanner scannerLinea = new Scanner(specificaUscita)) {			
				while (scannerLinea.hasNext()) {
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("La stanza di partenza a cui collegare un'altra stanza è" +stanzaPartenza+"."));
					stanzaPartenza = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("La direzione dell'uscita da "+stanzaPartenza));
					direzione = Direzione.valueOf(scannerLinea.next());
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+direzione));
					stanzaDestinazione = scannerLinea.next();

					impostaUscita(stanzaPartenza, direzione, stanzaDestinazione);
				}
			} 
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, Direzione dir, String nomeA) throws FormatoFileNonValidoException {
		check(this.isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(this.isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public Map<String, Stanza> getNome2stanza() {
		return this.nome2stanza;
	}
}