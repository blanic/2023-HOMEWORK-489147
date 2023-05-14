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

public class ComandoPrendiTest {
	
	private static final String NOME_ATTREZZO_1 = "Attrezzo1";
	private static final int PESO_ATTREZZO_1 = 1;
	
	private ComandoPrendi comandoPrendi;
	private Partita partita;
	private Labirinto labirinto;
	private Borsa borsaGiocatore;
	private Stanza stanzaCorrente;
	private Attrezzo attrezzoDaPrendere;
	private DiaDia diadia;
	private IO io;
	
	@Before
	public void setUp() {
		this.comandoPrendi = new ComandoPrendi();
		List<String> stanze = Arrays.asList("stanza unica");
		this.labirinto = new LabirintoBuilder()
				.addStanze(stanze)
				.setStanzaIniziale("stanza unica")
			    .getLabirinto();
		this.io = new IOConsole();
		this.diadia = new DiaDia(io, labirinto);
		this.partita = this.diadia.getPartita();
		this.borsaGiocatore = this.partita.getGiocatore().getBorsa();
		this.attrezzoDaPrendere = new Attrezzo(NOME_ATTREZZO_1, PESO_ATTREZZO_1);
		this.stanzaCorrente = this.partita.getStanzaCorrente();

		
	}
	
	@Test
	public void testEseguiComandoPrendiStanzaNonContieneAttrezzo() {
		Collection<Attrezzo> attrezziInStanza = this.stanzaCorrente.getAttrezzi().values();
		Collection<Attrezzo> attrezziInBorsaGiocatore = this.borsaGiocatore.getAttrezzi().values();
		comandoPrendi.setParametro(NOME_ATTREZZO_1);
		comandoPrendi.esegui(this.partita);
		Collection<Attrezzo> attrezziInStanzaDopoEsecuzioneComando = stanzaCorrente.getAttrezzi().values();
		Collection<Attrezzo> attrezziInBorsaGiocatoreDopoEsecuzioneComando = borsaGiocatore.getAttrezzi().values();
		assertEquals(attrezziInBorsaGiocatore, attrezziInBorsaGiocatoreDopoEsecuzioneComando);
		assertEquals(attrezziInStanza, attrezziInStanzaDopoEsecuzioneComando);
	}
	
	@Test
	public void testEseguiComandoPrendiVieneEseguitoCorrettamente() {
		this.stanzaCorrente.addAttrezzo(attrezzoDaPrendere);
		comandoPrendi.setParametro(NOME_ATTREZZO_1);
		comandoPrendi.esegui(this.partita);
		assertTrue(this.borsaGiocatore.hasAttrezzo(NOME_ATTREZZO_1));
		assertFalse(this.stanzaCorrente.hasAttrezzo(NOME_ATTREZZO_1));
	}

	@Test
	public void testGetNomeSuccesso() {
		assertEquals(ComandoPrendi.class.toString(), comandoPrendi.getClass().toString());
	}
	
	@Test
	public void testGetNomeFallimento() {
		Comando comando = new ComandoPrendi();
		assertNotEquals(Comando.class, comando.getClass().toString());
	}

	@Test
	public void testGetParametroSuccesso() {
		this.comandoPrendi.setParametro(NOME_ATTREZZO_1);
		assertEquals(NOME_ATTREZZO_1, this.comandoPrendi.getParametro());
	}
	
	@Test
	public void testGetParametroFallimento() {
		this.comandoPrendi.setParametro("Non Attrezzo1");
		assertNotEquals(NOME_ATTREZZO_1, this.comandoPrendi.getParametro());
		
	}
	
	@Test
	public void testGetParametroIsNull() {
		assertNull(this.comandoPrendi.getParametro());
		
	}

	@Test
	public void testSetParametroSuccesso() {
		this.comandoPrendi.setParametro(NOME_ATTREZZO_1);
		assertEquals(NOME_ATTREZZO_1, this.comandoPrendi.getParametro());
	}
	
	@Test
	public void testSetParametroFallimento() {
		this.comandoPrendi.setParametro("Non Spada");
		assertNotEquals(NOME_ATTREZZO_1, this.comandoPrendi.getParametro());
	}

}
