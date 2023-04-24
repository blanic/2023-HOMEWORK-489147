package it.uniroma3.diadia.testAccettazione;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class AccettazioneTest {

	DiaDia diadia;
	Partita partita;
	IOSimulator io;

	@Before
	public void setUp() throws Exception {
		this.io = new IOSimulator();
		this.diadia = new DiaDia(io);
		this.partita = this.diadia.getPartita();
	}

	@Test 
	public void testPartitaCreataCorrettamente(){
		assertNotNull(this.partita!=null);
		Labirinto labirinto = this.partita.getLabirinto();
		assertNotNull(labirinto);
		assertNotNull(this.partita.getStanzaCorrente()==labirinto.getStanzaIniziale());
		assertNotNull(labirinto.getStanzaVincente());
		assertEquals(this.partita.getStanzaVincente(), labirinto.getStanzaVincente());
		assertNotNull(this.partita.getGiocatore());
	}

	@Test
	public void testVinciPartitaInUnaMossa() {
		String[] istruzioni = {"vai nord"};
		this.io.setMessaggiIn(istruzioni);
		assertEquals("vai nord", this.io.getProssimoComando());
		Stanza prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente("nord");
		assertTrue(this.diadia.processaIstruzione(this.io.leggiRiga()));
		assertEquals(prossimaStanza, this.partita.getStanzaCorrente());
		assertEquals(this.partita.getStanzaCorrente(), this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testFaiUnaMossaEPoiFinisciDiGiocare() {
		String[] istruzioni = {"vai sud", "fine"};
		this.io.setMessaggiIn(istruzioni);
		assertEquals("vai sud", this.io.getProssimoComando());
		Stanza prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente("sud");
		assertFalse(this.diadia.processaIstruzione(this.io.leggiRiga()));
		assertEquals(prossimaStanza, this.partita.getStanzaCorrente());
		assertEquals("fine", this.io.getProssimoComando());
		assertTrue(this.diadia.processaIstruzione(this.io.leggiRiga()));
	}
	
	@Test
	public void testFaiTreMosseEPoiVinci() {
		String[] istruzioni = {"vai sud", "vai nord", "vai nord"};
		this.io.setMessaggiIn(istruzioni);
		assertEquals("vai sud", this.io.getProssimoComando());
		Stanza prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente("sud");
		assertFalse(this.diadia.processaIstruzione(this.io.leggiRiga()));
		assertEquals(prossimaStanza, this.partita.getStanzaCorrente());
		assertEquals("vai nord", this.io.getProssimoComando());
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente("nord");
		assertFalse(this.diadia.processaIstruzione(this.io.leggiRiga()));
		assertEquals(prossimaStanza, this.partita.getStanzaCorrente());
		assertEquals("vai nord", this.io.getProssimoComando());
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente("nord");
		assertTrue(this.diadia.processaIstruzione(this.io.leggiRiga()));
		assertEquals(prossimaStanza, this.partita.getStanzaCorrente());
		assertEquals(this.partita.getStanzaCorrente(), this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}
}
