package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	final static private int SOGLIA_MAGICA = 3;
	final static private String NOME_STANZA_MAGICA = "Stanza Magica";
	final static private String NOME_ATTREZZO = "Attrezzo";
	final static private int PESO_ATTREZZO = 1;

	private int sogliaMagica;
	private Stanza stanzaMagica;
	private Attrezzo attrezzo;
	private Attrezzo attrezzoCopia;

	@Before
	public void setUp() throws Exception {
		this.stanzaMagica = new StanzaMagica(NOME_STANZA_MAGICA, SOGLIA_MAGICA, 0);
		this.attrezzo = new Attrezzo(NOME_ATTREZZO, PESO_ATTREZZO);
		this.attrezzoCopia = new Attrezzo(NOME_ATTREZZO, PESO_ATTREZZO);
	}

	@Test
	public void testAddAttrezzoSogliaNonRaggiunta() {
		this.stanzaMagica.addAttrezzo(attrezzo);
		assertEquals(attrezzoCopia, attrezzo);
	}
	
	@Test
	public void testAddAttrezzoSogliaRaggiunta() {
		for(int i=0; i<=sogliaMagica+1; i++) {
			this.stanzaMagica.addAttrezzo(attrezzo);
			this.stanzaMagica.removeAttrezzo(attrezzo);
		}
		assertEquals(attrezzoCopia, attrezzo);
		
		this.stanzaMagica.addAttrezzo(attrezzo);
		assertNotEquals(attrezzoCopia, attrezzo);
	}

	@Test
	public void testModificaAttrezzoModificaCorrettamente() {
		String nomeAttrezzoInvertito =  new StringBuffer(NOME_ATTREZZO).reverse().toString();
		int pesoAttrezzoRaddoppiato = PESO_ATTREZZO*2;
		attrezzo = ((StanzaMagica) this.stanzaMagica).modificaAttrezzo(attrezzo);
		assertEquals(nomeAttrezzoInvertito, this.attrezzo.getNome());
		assertSame(pesoAttrezzoRaddoppiato, this.attrezzo.getPeso());
	}
	
	@Test
	public void testModificaAttrezzoModificaStessoOggettoNonCreandoneUnoNuovo() {
		Attrezzo attrezzoModificato = ((StanzaMagica) this.stanzaMagica).modificaAttrezzo(attrezzo);
		assertSame(attrezzo, attrezzoModificato);
	}
}
