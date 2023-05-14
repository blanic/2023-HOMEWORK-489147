package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertSame;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	private static final String NOME_STANZA_INIZIALE = "stanza iniziale";
	private static final String NOME_ADIACENTE_BLOCCATA = "stanza bloccata";
	private static final String NOME_ADIACENTE_SBLOCCATA = "stanza sbloccata";
	private static final String DIREZIONE_BLOCCATA = "nord";
	private static final String DIREZIONE_LIBERA = "ovest";
	private static final String ATTREZZO_SBLOCCANTE = "chiave";
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
		
		List<String> stanze = Arrays.asList(NOME_STANZA_INIZIALE, NOME_ADIACENTE_SBLOCCATA);
		LabirintoBuilder labbuilder = new LabirintoBuilder()
				.addStanze(stanze)
				.addStanzaBloccata(NOME_STANZA_INIZIALE, DIREZIONE_BLOCCATA, ATTREZZO_SBLOCCANTE)
				.setStanzaIniziale(NOME_STANZA_INIZIALE)
				.addAdiacenza(NOME_STANZA_INIZIALE,NOME_ADIACENTE_BLOCCATA,DIREZIONE_BLOCCATA)
				.addAdiacenza(NOME_STANZA_INIZIALE,NOME_ADIACENTE_SBLOCCATA,DIREZIONE_LIBERA);
		
		Labirinto labirinto = labbuilder.getLabirinto();
		
		this.io = new IOConsole();
		this.diadia = new DiaDia(io, labirinto);
		
		this.stanzaIniziale = labbuilder.getStanza(NOME_STANZA_INIZIALE);
		this.stanzaAdiacenteBloccata = labbuilder.getStanza(NOME_ADIACENTE_BLOCCATA);
		this.stanzaAdiacenteNonBloccata = labbuilder.getStanza(NOME_ADIACENTE_SBLOCCATA);
		
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
