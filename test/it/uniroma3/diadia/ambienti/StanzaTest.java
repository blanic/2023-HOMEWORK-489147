package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	
	Stanza stanza;
	Stanza stanzaAdiacente;
	Attrezzo attrezzo;
	Direzione direzioneNord;
	
	@Before
	public void setUp() {
		this.stanza = new Stanza("Stanza");
		this.stanzaAdiacente = new Stanza("StanzaAdiacente");
		this.attrezzo = new Attrezzo("Attrezzo", 1);
		this.direzioneNord = Direzione.valueOf("nord");
	}

	@Test
	public void testImpostaStanzaAdiacente() {
		this.stanza.impostaStanzaAdiacente(direzioneNord, stanzaAdiacente);
		assertEquals(stanzaAdiacente, this.stanza.getStanzaAdiacente(direzioneNord));
	}

	@Test
	public void testGetStanzaAdiacenteNonPresente() {
		assertNull(this.stanza.getStanzaAdiacente(direzioneNord));
	}

	@Test
	public void testGetAttrezzi() {
		this.stanza.addAttrezzo(attrezzo);
		Map<String,Attrezzo> attrezziTest = new HashMap<>();
		attrezziTest.put(attrezzo.getNome(), attrezzo);
		assertEquals(attrezziTest, this.stanza.getAttrezzi());
	}

	@Test
	public void testAddAttrezzo() {
		this.stanza.addAttrezzo(attrezzo);
		assertEquals(attrezzo, this.stanza.getAttrezzo(this.attrezzo.getNome()));
	}

	@Test
	public void testToString() {
		this.stanza.addAttrezzo(attrezzo);
		this.stanza.impostaStanzaAdiacente(direzioneNord, stanzaAdiacente);
		assertEquals("Stanza\nUscite:  nord\nAttrezzi nella stanza: Attrezzo (1kg) \nPersonaggio nella stanza: Nessun personaggio nella stanza", this.stanza.toString());
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
