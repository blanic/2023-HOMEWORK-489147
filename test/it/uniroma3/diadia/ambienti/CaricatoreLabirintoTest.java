package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.Test;

import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirintoTest {

	private String monolocale =
			"Stanze:N10\n"+
	        "Stanze Bloccate:\n"+
			"Stanze Buie:\n"+
	        "Stanze Magiche:\n"+
			"Maghi:\n"+
	        "Streghe:\n"+
			"Cani:\n"+
	        "Inizio:N10\n"+
			"Vincente:N10\n"+
	        "Attrezzi:\n"+
			"Uscite:\n";
	
	private String monolocaleBloccato =
			"Stanze:N10\n"+
	        "Stanze Bloccate:N10 ovest Martello\n"+
			"Stanze Buie:\n"+
	        "Stanze Magiche:\n"+
			"Maghi:\n"+
	        "Streghe:\n"+
			"Cani:\n"+
	        "Inizio:N10\n"+
			"Vincente:N10\n"+
	        "Attrezzi:\n"+
			"Uscite:\n";
	
	private String monolocaleBuio =
			"Stanze:N10\n"+
	        "Stanze Bloccate:\n"+
			"Stanze Buie:N10 lanterna\n"+
	        "Stanze Magiche:\n"+
			"Maghi:\n"+
	        "Streghe:\n"+
			"Cani:\n"+
	        "Inizio:N10\n"+
			"Vincente:N10\n"+
	        "Attrezzi:\n"+
			"Uscite:\n";
	
	private String monolocaleMagico =
			"Stanze:N10\n"+
	        "Stanze Bloccate:\n"+
			"Stanze Buie:\n"+
	        "Stanze Magiche:N10 1\n"+
			"Maghi:\n"+
	        "Streghe:\n"+
			"Cani:\n"+
	        "Inizio:N10\n"+
			"Vincente:N10\n"+
	        "Attrezzi:\n"+
			"Uscite:\n";
	
	private String monolocaleConMago =
			"Stanze:N10\n"+
	        "Stanze Bloccate:\n"+
			"Stanze Buie:\n"+
	        "Stanze Magiche:\n"+
			"Maghi:Merlino Ciao! bacchetta 1 N10\n"+
	        "Streghe:\n"+
			"Cani:\n"+
	        "Inizio:N10\n"+
			"Vincente:N10\n"+
	        "Attrezzi:\n"+
			"Uscite:\n";
	
	private String monolocaleConStrega =
			"Stanze:N10\n"+
	        "Stanze Bloccate:\n"+
			"Stanze Buie:\n"+
	        "Stanze Magiche:\n"+
			"Maghi:\n"+
	        "Streghe:Beatrice Ahaha N10\n"+
			"Cani:\n"+
	        "Inizio:N10\n"+
			"Vincente:N10\n"+
	        "Attrezzi:\n"+
			"Uscite:\n";
	
	private String monolocaleConCane =
			"Stanze:N10\n"+
	        "Stanze Bloccate:\n"+
			"Stanze Buie:\n"+
	        "Stanze Magiche:\n"+
			"Maghi:\n"+
	        "Streghe:\n"+
			"Cani:Fido Woff N10 Osso\n"+
	        "Inizio:N10\n"+
			"Vincente:N10\n"+
	        "Attrezzi:\n"+
			"Uscite:\n";
	
	private String monolocaleConAttrezzo =
			"Stanze:N10\n"+
	        "Stanze Bloccate:\n"+
			"Stanze Buie:\n"+
	        "Stanze Magiche:\n"+
			"Maghi:\n"+
	        "Streghe:\n"+
			"Cani:\n"+
	        "Inizio:N10\n"+
			"Vincente:N10\n"+
	        "Attrezzi:Osso 5 N10\n"+
			"Uscite:\n";
	
	
	private String bilocale =
			"Stanze:N10, N11\n"+
	        "Stanze Bloccate:\n"+
			"Stanze Buie:\n"+
	        "Stanze Magiche:\n"+
			"Maghi:\n"+
	        "Streghe:\n"+
			"Cani:\n"+
	        "Inizio:N10\n"+
			"Vincente:N11\n"+
	        "Attrezzi:\n"+
			"Uscite:N10 nord N11\n";
					

	private CaricatoreLabirinto caricatore;

	@Test
	public void testMonolocale() throws FormatoFileNonValidoException, FileNotFoundException {
		caricatore = new CaricatoreLabirinto(new StringReader(monolocale));
		caricatore.carica();
		assertEquals("N10", this.caricatore.getStanzaIniziale().getNome());
		assertEquals("N10", this.caricatore.getStanzaVincente().getNome());
		assertEquals(Stanza.class,this.caricatore.getNome2stanza().get("N10").getClass());

	}
	
	@Test
	public void testMonolocaleBloccato() throws FormatoFileNonValidoException, FileNotFoundException {
		caricatore = new CaricatoreLabirinto(new StringReader(monolocaleBloccato));
		caricatore.carica();
		assertEquals("N10", this.caricatore.getStanzaIniziale().getNome());
		assertEquals("N10", this.caricatore.getStanzaVincente().getNome());
		assertEquals(StanzaBloccata.class,this.caricatore.getNome2stanza().get("N10").getClass());
	}
	
	@Test
	public void testMonolocaleBuio() throws FormatoFileNonValidoException, FileNotFoundException {
		caricatore = new CaricatoreLabirinto(new StringReader(monolocaleBuio));
		caricatore.carica();
		assertEquals("N10", this.caricatore.getStanzaIniziale().getNome());
		assertEquals("N10", this.caricatore.getStanzaVincente().getNome());
		assertEquals(StanzaBuia.class,this.caricatore.getNome2stanza().get("N10").getClass());
	}
	
	@Test
	public void testMonolocaleMagico() throws FormatoFileNonValidoException, FileNotFoundException {
		caricatore = new CaricatoreLabirinto(new StringReader(monolocaleMagico));
		caricatore.carica();
		assertEquals("N10", this.caricatore.getStanzaIniziale().getNome());
		assertEquals("N10", this.caricatore.getStanzaVincente().getNome());
		assertEquals(StanzaMagica.class,this.caricatore.getNome2stanza().get("N10").getClass());
	}
	
	@Test
	public void testMonolocaleConMago() throws FormatoFileNonValidoException, FileNotFoundException {
		caricatore = new CaricatoreLabirinto(new StringReader(monolocaleConMago));
		caricatore.carica();
		Stanza unicaStanza = this.caricatore.getNome2stanza().get("N10");
		assertNotNull(unicaStanza.getPersonaggio());
		assertEquals(Mago.class,unicaStanza.getPersonaggio().getClass());
	}
	
	@Test
	public void testMonolocaleConStrega() throws FormatoFileNonValidoException, FileNotFoundException {
		caricatore = new CaricatoreLabirinto(new StringReader(monolocaleConStrega));
		caricatore.carica();
		Stanza unicaStanza = this.caricatore.getNome2stanza().get("N10");
		assertNotNull(unicaStanza.getPersonaggio());
		assertEquals(Strega.class,unicaStanza.getPersonaggio().getClass());
	}
	
	@Test
	public void testMonolocaleConCane() throws FormatoFileNonValidoException, FileNotFoundException {
		caricatore = new CaricatoreLabirinto(new StringReader(monolocaleConCane));
		caricatore.carica();
		Stanza unicaStanza = this.caricatore.getNome2stanza().get("N10");
		assertNotNull(unicaStanza.getPersonaggio());
		assertEquals(Cane.class,unicaStanza.getPersonaggio().getClass());
	}
	
	@Test
	public void testBilocaleStanzaInizialeEVincente() throws FormatoFileNonValidoException, FileNotFoundException {
		caricatore = new CaricatoreLabirinto(new StringReader(bilocale));
		caricatore.carica();
		assertSame(2,this.caricatore.getNome2stanza().size());
		assertEquals("N10", this.caricatore.getStanzaIniziale().getNome());
		assertEquals("N11", this.caricatore.getStanzaVincente().getNome());
	}

	@Test
	public void testMonolocaleConAttrezzo() throws FormatoFileNonValidoException, FileNotFoundException {
		caricatore = new CaricatoreLabirinto(new StringReader(monolocaleConAttrezzo));
		caricatore.carica();
		Stanza stanzaUnica = this.caricatore.getNome2stanza().get("N10");
		assertTrue(stanzaUnica.hasAttrezzo("Osso"));
	}

	@Test
	public void testBilocale() throws FormatoFileNonValidoException, FileNotFoundException {
		caricatore = new CaricatoreLabirinto(new StringReader(bilocale));
		caricatore.carica();
		Stanza stanza = this.caricatore.getNome2stanza().get("N10");
		Stanza stanzaAdiacente = this.caricatore.getNome2stanza().get("N11");
		Direzione nord = Direzione.valueOf("nord");
		assertTrue(stanza.getStanzaAdiacente(nord).equals(stanzaAdiacente));
	}
}
