package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosaTest {
	
	private static final String NOME_ATTREZZO_1 = "Attrezzo1";
	private static final int PESO_ATTREZZO_1 = 1;
	
	ComandoPosa comandoPosa;
	Partita partita;
	Borsa borsaGiocatore;
	Labirinto labirinto;
	Attrezzo attrezzoDaPosare;
	Stanza stanzaCorrente;
	DiaDia diadia;
	IO io;
	
	@Before
	public void setUp() {
		this.comandoPosa = new ComandoPosa();
		List<String> stanze = Arrays.asList("stanza unica");
		this.labirinto = new LabirintoBuilder()
				.addStanze(stanze)
				.setStanzaIniziale("stanza unica")
			    .getLabirinto();
		this.io = new IOConsole();
		this.diadia = new DiaDia(io, labirinto);
		this.partita = this.diadia.getPartita();
		this.stanzaCorrente = this.partita.getStanzaCorrente();
		this.borsaGiocatore = this.partita.getGiocatore().getBorsa();
		this.attrezzoDaPosare = new Attrezzo(NOME_ATTREZZO_1, PESO_ATTREZZO_1);
	}
	
	@Test
	public void testEseguiComandoPosaGiocatoreNonPossiedeAttrezzo() {
		Collection<Attrezzo> attrezziInStanza = this.stanzaCorrente.getAttrezzi().values();
		Collection<Attrezzo> attrezziInBorsaGiocatore = this.borsaGiocatore.getAttrezzi().values();
		comandoPosa.setParametro(NOME_ATTREZZO_1);
		comandoPosa.esegui(this.partita);
		Collection<Attrezzo> attrezziInStanzaDopoEsecuzioneComando = stanzaCorrente.getAttrezzi().values();
		Collection<Attrezzo> attrezziInBorsaGiocatoreDopoEsecuzioneComando = borsaGiocatore.getAttrezzi().values();
		assertEquals(attrezziInBorsaGiocatore, attrezziInBorsaGiocatoreDopoEsecuzioneComando);
		assertEquals(attrezziInStanza, attrezziInStanzaDopoEsecuzioneComando);
	}
	
	@Test
	public void testEseguiComandoPosaVieneEseguitoCorrettamente() {
		this.borsaGiocatore.addAttrezzo(attrezzoDaPosare);
		comandoPosa.setParametro(NOME_ATTREZZO_1);
		comandoPosa.esegui(this.partita);
		assertFalse(this.borsaGiocatore.hasAttrezzo(NOME_ATTREZZO_1));
		assertTrue(this.stanzaCorrente.hasAttrezzo(NOME_ATTREZZO_1));
	}


	@Test
	public void testGetNomeSuccesso() {
		assertEquals(ComandoPosa.class.toString(), comandoPosa.getClass().toString());
	}
	
	@Test
	public void testGetNomeFallimento() {
		Comando comando = new ComandoPosa();
		assertNotEquals(Comando.class, comando.getClass().toString());
	}

	@Test
	public void testGetParametroSuccesso() {
		this.comandoPosa.setParametro(NOME_ATTREZZO_1);
		assertEquals(NOME_ATTREZZO_1, this.comandoPosa.getParametro());
	}
	
	@Test
	public void testGetParametroFallimento() {
		this.comandoPosa.setParametro("Non Spada");
		assertNotEquals(NOME_ATTREZZO_1, this.comandoPosa.getParametro());
		
	}
	
	@Test
	public void testGetParametroIsNull() {
		assertNull(this.comandoPosa.getParametro());
		
	}

	@Test
	public void testSetParametroSuccesso() {
		this.comandoPosa.setParametro(NOME_ATTREZZO_1);
		assertEquals(NOME_ATTREZZO_1, this.comandoPosa.getParametro());
	}
	
	@Test
	public void testSetParametroFallimento() {
		this.comandoPosa.setParametro("Non Attrezzo1");
		assertNotEquals(NOME_ATTREZZO_1, this.comandoPosa.getParametro());
	}

}
