package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	
	private Partita partita;
	
	@Before
	public void setup() {
		List<String> stanze = Arrays.asList("Atrio","Biblioteca");
		Labirinto labirinto = new LabirintoBuilder()
				.addStanze(stanze)
				.setStanzaIniziale("Atrio")
				.setStanzaVincente("Biblioteca")
				.getLabirinto();
		this.partita = new Partita(labirinto);
		
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
