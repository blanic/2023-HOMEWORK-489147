package it.uniroma3.diadia.attrezzi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AttrezzoTest {
	
	public final static String NOME_ATTREZZO_1 = "Attrezzo1";
	public final static String NOME_ATTREZZO_2 = "Attrezzo2";
	public final static int PESO_ATTREZZO_1 = 1;
	public final static int PESO_ATTREZZO_2 = 2;
	
	Attrezzo attrezzo;
	Attrezzo attrezzoUguale;
	Attrezzo attrezzoDiverso;
	Attrezzo attrezzoNomeUgualePesoDiverso;
	Attrezzo attrezzoNomeDiversoPesoUguale;
	
	@Before
	public void setUp() {
		this.attrezzo = new Attrezzo(NOME_ATTREZZO_1, PESO_ATTREZZO_1);
		this.attrezzoUguale = new Attrezzo(NOME_ATTREZZO_1, PESO_ATTREZZO_1);
		this.attrezzoDiverso = new Attrezzo(NOME_ATTREZZO_2, PESO_ATTREZZO_2);
		this.attrezzoNomeUgualePesoDiverso = new Attrezzo(NOME_ATTREZZO_1, PESO_ATTREZZO_2);
		this.attrezzoNomeDiversoPesoUguale = new Attrezzo(NOME_ATTREZZO_2, PESO_ATTREZZO_1);
	}

	@Test
	public void testAttrezzoEqualsAttrezziUguali() {
		assertEquals(attrezzo, attrezzoUguale);
	}
	
	@Test
	public void testAttrezzoEqualsAttrezziDiversi() {
		assertNotEquals(attrezzo, attrezzoDiverso);
	}
	
	@Test
	public void testAttrezzoEqualsAttrezziNomeUgualePesoDiverso() {
		assertEquals(attrezzo, attrezzoNomeUgualePesoDiverso);
	}
	
	@Test
	public void testAttrezzoEqualsAttrezziNomeDiversopesoUguale() {
		assertNotEquals(attrezzo, attrezzoNomeDiversoPesoUguale);
	}

	@Test
	public void testToString() {
		assertEquals("Attrezzo1 (1kg)", attrezzo.toString());
	}

}
