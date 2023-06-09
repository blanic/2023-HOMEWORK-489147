package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class LabirintoBuilderTest {
	
	private static final String NOME_STANZA_1 = "stanza1";
	private static final String NOME_STANZA_2 = "stanza2";
	private static final String NOME_DIREZIONE_NORD = "nord";

	private LabirintoBuilder labirintobuilder;
	private Stanza stanza1;
	private Stanza stanza2;
	Direzione direzioneNord;

	@Before
	public void setUp() throws Exception {
		this.labirintobuilder = new LabirintoBuilder();
		this.stanza1 = new Stanza(NOME_STANZA_1);
		this.stanza2 = new Stanza(NOME_STANZA_2);
		direzioneNord = Direzione.valueOf(NOME_DIREZIONE_NORD);
	}
	

	@Test
	public void testLabirintoBuilderCreatoCorrettamente() {
		assertNotNull(this.labirintobuilder.getLabirinto());
	}

	@Test
	public void testAddStanza() {
		this.labirintobuilder.addStanza(NOME_STANZA_1);
		assertEquals(this.stanza1, this.labirintobuilder.getStanza(NOME_STANZA_1));
	}

	@Test
	public void testAddStanze() {
		List<String> stanze = List.of(NOME_STANZA_1,NOME_STANZA_2);
		this.labirintobuilder.addStanze(stanze);
		assertEquals(this.stanza1,this.labirintobuilder.getStanza(NOME_STANZA_1));
		assertEquals(this.stanza2,this.labirintobuilder.getStanza(NOME_STANZA_2));

	}

	@Test
	public void testAddStanzaMagica() {
		Stanza stanzaMagica = new StanzaMagica(NOME_STANZA_1);
		this.labirintobuilder.addStanzaMagica(NOME_STANZA_1);
		assertEquals(StanzaMagica.class, this.labirintobuilder.getStanza(NOME_STANZA_1).getClass());
		assertEquals(stanzaMagica, this.labirintobuilder.getStanza(NOME_STANZA_1));
	}

	@Test
	public void testAddStanzaBuia() {
		Stanza stanzaBuia = new StanzaBuia(NOME_STANZA_1, "Lanterna");
		this.labirintobuilder.addStanzaBuia(NOME_STANZA_1, "Lanterna");
		assertEquals(StanzaBuia.class, this.labirintobuilder.getStanza(NOME_STANZA_1).getClass());
		assertEquals(stanzaBuia, this.labirintobuilder.getStanza(NOME_STANZA_1));
	}

	@Test
	public void testAddStanzaBloccata() {
		Stanza stanzaBloccata = new StanzaBloccata(NOME_STANZA_1,direzioneNord,"chiave");
		this.labirintobuilder.addStanzaBloccata(NOME_STANZA_1,NOME_DIREZIONE_NORD,"chiave");
		assertEquals(StanzaBloccata.class, this.labirintobuilder.getStanza(NOME_STANZA_1).getClass());
		assertEquals(stanzaBloccata, this.labirintobuilder.getStanza(NOME_STANZA_1));
	}

	@Test
	public void testSetStanzaIniziale() {
		assertNull(this.labirintobuilder.getLabirinto().getStanzaIniziale());
		this.labirintobuilder.addStanza(NOME_STANZA_1);
		this.labirintobuilder.setStanzaIniziale(NOME_STANZA_1);
		assertEquals(this.stanza1,this.labirintobuilder.getLabirinto().getStanzaIniziale());
	}

	@Test
	public void testSetStanzaVincente() {
		assertNull(this.labirintobuilder.getLabirinto().getStanzaVincente());
		this.labirintobuilder.addStanza(NOME_STANZA_1);
		this.labirintobuilder.setStanzaVincente(NOME_STANZA_1);
		assertEquals(this.stanza1,this.labirintobuilder.getLabirinto().getStanzaVincente());
	}

	@Test
	public void testAddAdiacenza() {
		this.labirintobuilder.addStanze(List.of(NOME_STANZA_1,NOME_STANZA_2));
		Stanza stanza = this.labirintobuilder.getStanza(NOME_STANZA_1);
		Stanza stanzaAdiacente = this.labirintobuilder.getStanza(NOME_STANZA_2);		
		assertTrue(stanza.getDirezioni().isEmpty());
		assertNull(stanza.getStanzaAdiacente(direzioneNord));
		this.labirintobuilder.addAdiacenza(NOME_STANZA_1, NOME_STANZA_2, NOME_DIREZIONE_NORD);
		assertTrue(stanza.getDirezioni().contains(direzioneNord));
		assertEquals(stanzaAdiacente,stanza.getStanzaAdiacente(direzioneNord));
	}

	@Test
	public void testAddAttrezzoAStanza() {
		this.labirintobuilder.addStanza(NOME_STANZA_1);
		Stanza stanza = this.labirintobuilder.getStanza(NOME_STANZA_1);
		assertFalse(stanza.hasAttrezzo("attrezzo"));
		this.labirintobuilder.addAttrezzoAStanza(NOME_STANZA_1,"attrezzo", 0);
		assertTrue(stanza.hasAttrezzo("attrezzo"));
	}

	@Test
	public void testGetStanzaNonPresenteInLabirintoVuoto() {
		assertNull(this.labirintobuilder.getStanza(NOME_STANZA_1));
	}
	
	@Test
	public void testGetStanzaNonPresenteInLabirinto() {
		this.labirintobuilder.addStanze(List.of(NOME_STANZA_1,NOME_STANZA_2));
		assertNull(this.labirintobuilder.getStanza("STANZA_NON-PRESENTE"));
	}
	
	@Test
	public void testGetStanzaUnicaInLabirinto() {
		Stanza stanza = new Stanza(NOME_STANZA_1);
		this.labirintobuilder.addStanza(NOME_STANZA_1);
		assertNotNull(this.labirintobuilder.getStanza(NOME_STANZA_1));
		assertEquals(stanza,this.labirintobuilder.getStanza(NOME_STANZA_1));
	}
	
	@Test
	public void testGetStanzaInLabirinto() {
		this.labirintobuilder.addStanze(List.of(NOME_STANZA_1,NOME_STANZA_2));
		Stanza stanza = new Stanza(NOME_STANZA_1);
		assertNotNull(this.labirintobuilder.getStanza(NOME_STANZA_1));
		assertEquals(stanza,this.labirintobuilder.getStanza(NOME_STANZA_1));
	}

}



