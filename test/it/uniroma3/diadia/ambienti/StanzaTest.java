package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	
	Stanza stanza;
	Stanza stanzaAdiacente;
	Attrezzo attrezzo;
	
	@Before
	public void setUp() {
		this.stanza = new Stanza("Stanza");
		this.stanzaAdiacente = new Stanza("StanzaAdiacente");
		this.attrezzo = new Attrezzo("Attrezzo", 1);
	}

	@Test
	public void testImpostaStanzaAdiacente() {
		this.stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
		assertEquals(stanzaAdiacente, this.stanza.getStanzaAdiacente("nord"));
	}

	@Test
	public void testGetStanzaAdiacenteNonPresente() {
		assertNull(this.stanza.getStanzaAdiacente("nord"));
	}

	@Test
	public void testGetAttrezzi() {
		this.stanza.addAttrezzo(attrezzo);
		Attrezzo[] attrezzi = new Attrezzo[10];
		attrezzi[0] = attrezzo;
		assertArrayEquals(attrezzi, this.stanza.getAttrezzi());
	}

	@Test
	public void testAddAttrezzo() {
		this.stanza.addAttrezzo(attrezzo);
		assertEquals(attrezzo, this.stanza.getAttrezzo(this.attrezzo.getNome()));
	}

	@Test
	public void testToString() {
		this.stanza.addAttrezzo(attrezzo);
		this.stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
		assertEquals("Stanza\nUscite:  nord\nAttrezzi nella stanza: Attrezzo (1kg) ", this.stanza.toString());
	}
	
	@Test
	public void testHasAttrezzoNonPresente() {
		assertFalse(this.stanza.hasAttrezzo(this.attrezzo.getNome()));
	}

	@Test
	public void testHasAttrezzoPresente() {
		this.stanza.addAttrezzo(attrezzo);
		assertTrue(this.stanza.hasAttrezzo(this.attrezzo.getNome()));
	}

	@Test
	public void testGetAttrezzoNonPresente() {
		assertNull(this.stanza.getAttrezzo(this.attrezzo.getNome()));
	}
	
	@Test
	public void testGetAttrezzoPresente() {
		this.stanza.addAttrezzo(attrezzo);
		assertEquals(attrezzo, this.stanza.getAttrezzo(this.attrezzo.getNome()));
	}

	@Test
	public void testRemoveAttrezzoPresente() {
		this.stanza.addAttrezzo(attrezzo);
		assertTrue(this.stanza.removeAttrezzo(attrezzo));
	}
	
	@Test
	public void testRemoveAttrezzoNonPresente() {
		assertFalse(this.stanza.removeAttrezzo(attrezzo));
	}


}
