package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	
	private Partita partita;
	
	@Before
	public void setup() {
		this.partita = new Partita();
	}

	@Test
	public void testVintaIsNotVinta() {
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testVintaIsVinta() {
		Stanza stanzaVincente = this.partita.getStanzaVincente();
		this.partita.setStanzaCorrente(stanzaVincente);
		assertTrue(this.partita.vinta());
	}

	@Test
	public void testIsFinitaNonFinita() {
		assertFalse(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinitaFinita() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinitaIsVinta() {
		Stanza stanzaVincente = this.partita.getStanzaVincente();
		this.partita.setStanzaCorrente(stanzaVincente);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinitaCfuFiniti() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}

}
