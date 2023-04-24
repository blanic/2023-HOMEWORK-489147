package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	private static final String NOME_STANZA_INIZIALE = "Stanza di test";
	private static final String NOME_ADIACENTE_BLOCCATA = "Stanza bloccata";
	private static final String NOME_ADIACENTE_SBLOCCATA = "Stanza sbloccata";
	private static final String DIREZIONE_BLOCCATA = "nord";
	private static final String DIREZIONE_LIBERA = "sud";
	private static final String ATTREZZO_SBLOCCANTE = "Chiave";
	private static final String ATTREZZO_INUTILE = "Piuma";
	private static final int PESO_ATTREZZO = 1;

	DiaDia diadia;
	IO io;
	Stanza stanzaIniziale;
	Stanza stanzaAdiacenteBloccata;
	Stanza stanzaAdiacenteNonBloccata;
	Attrezzo attrezzoSbloccante;
	Attrezzo attrezzoInutile;

	@Before
	public void setUp() throws Exception {
		
		this.io = new IOConsole();
		this.diadia = new DiaDia(io);
		
		this.stanzaIniziale = new StanzaBloccata(NOME_STANZA_INIZIALE, DIREZIONE_BLOCCATA, ATTREZZO_SBLOCCANTE);
		this.stanzaAdiacenteBloccata = new Stanza(NOME_ADIACENTE_BLOCCATA);
		this.stanzaAdiacenteNonBloccata = new Stanza(NOME_ADIACENTE_SBLOCCATA);

		this.stanzaIniziale.impostaStanzaAdiacente(DIREZIONE_BLOCCATA, stanzaAdiacenteBloccata);
		this.stanzaIniziale.impostaStanzaAdiacente(DIREZIONE_LIBERA, stanzaAdiacenteNonBloccata);
		
		this.attrezzoSbloccante = new Attrezzo(ATTREZZO_SBLOCCANTE, PESO_ATTREZZO);
		this.attrezzoInutile = new Attrezzo(ATTREZZO_INUTILE, PESO_ATTREZZO);
		
	}

	@Test
	public void testGetStanzaAdiacenteBloccataAttrezzoNonPresente() {
		Stanza stanza = this.stanzaIniziale.getStanzaAdiacente(DIREZIONE_BLOCCATA);
		assertSame(stanzaIniziale, stanza);
	}
	
	@Test
	public void testGetStanzaAdiacenteBloccataAttrezzoSbloccantePresente() {
		this.stanzaIniziale.addAttrezzo(attrezzoSbloccante);
		Stanza stanza = this.stanzaIniziale.getStanzaAdiacente(DIREZIONE_BLOCCATA);
		assertSame(stanzaAdiacenteBloccata, stanza);
	}
	
	@Test
	public void testGetStanzaAdiacenteBloccataAttrezzoNonSbloccantePresente() {
		this.stanzaIniziale.addAttrezzo(attrezzoInutile);
		Stanza stanza = this.stanzaIniziale.getStanzaAdiacente(DIREZIONE_BLOCCATA);
		assertSame(stanzaIniziale, stanza);
	}
	
	@Test
	public void testGetStanzaAdiacenteNonBloccata() {
		Stanza stanza = this.stanzaIniziale.getStanzaAdiacente(DIREZIONE_LIBERA);
		assertSame(stanzaAdiacenteNonBloccata, stanza);
	}
}
