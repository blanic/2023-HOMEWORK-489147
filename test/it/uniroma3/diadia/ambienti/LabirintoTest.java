package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	
	Labirinto labirinto;
	
	@Before
	public void setup(){
		this.labirinto = new Labirinto();
		Stanza stanzaVincente = new Stanza("stanzaVincente");
		Stanza stanzaIniziale = new Stanza("stanzaIniziale");
		this.labirinto.setStanzaIniziale(stanzaIniziale);
		this.labirinto.setStanzaVincente(stanzaVincente);
	}

	@Test
	public void testLabirintoVieneCreato() {
		assertNotNull(this.labirinto);
	}

	@Test
	public void testGetStanzaIniziale() {
		assertNotNull(this.labirinto.getStanzaIniziale());
	}

	@Test
	public void testGetStanzaVincente() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}

}
