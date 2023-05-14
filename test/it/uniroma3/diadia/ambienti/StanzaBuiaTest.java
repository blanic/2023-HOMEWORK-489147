package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

	private static final String NOME_STANZA = "Stanza Di Test";
	private static final String ATTREZZO_PER_VEDERE = "Lanterna";
	private static final String MESSAGGIO_STANZA_BUIA = "Qui c'è un buio pesto";


	Stanza stanzaBuia;
	Stanza stanza;
	Attrezzo attrezzoPerVedere;
	Attrezzo attrezzoInutile;

	@Before
	public void setUp() throws Exception {
		this.stanza = new Stanza(NOME_STANZA);
		this.stanzaBuia = new StanzaBuia(NOME_STANZA, ATTREZZO_PER_VEDERE);
		this.attrezzoPerVedere = new Attrezzo(ATTREZZO_PER_VEDERE, 1);
		this.attrezzoInutile = new Attrezzo("Attrezzo qualunque", 1);
	}

	@Test
	public void testGetDescrizioneAttrezzoPerVedereNonPresente() {
		assertEquals(MESSAGGIO_STANZA_BUIA, this.stanzaBuia.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneAttrezzoPerVederePresente() {
		this.stanzaBuia.addAttrezzo(attrezzoPerVedere);
		this.stanza.addAttrezzo(attrezzoPerVedere);
		assertEquals(this.stanza.getDescrizione(), this.stanzaBuia.getDescrizione());
	}
	
	@Test
	public void testGetDescrizionePresenteSoloAttrezzoGenerico() {
		this.stanzaBuia.addAttrezzo(attrezzoInutile);
		assertEquals(MESSAGGIO_STANZA_BUIA, this.stanzaBuia.getDescrizione());
	}

}
