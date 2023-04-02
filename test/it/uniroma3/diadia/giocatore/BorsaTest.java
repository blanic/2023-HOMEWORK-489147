package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	public final static int DEFAULT_LUNGHEZZA_ARRAY_ATTREZZI = 10;
	public final static String NOME_ATTREZZO1 = "Attrezzo1";
	public final static int PESO_ATTREZZO1 = 1;
	
	Borsa borsa;
	Attrezzo attrezzo;
	
	@Before 
	public void setUp() {
		this.borsa = new Borsa();
	}

	@Test
	public void testBorsaAppenaCreataNotNull() {
		assertNotNull(this.borsa);
	}
	
	@Test
	public void testBorsaAppenaCreataHaGiustoPesoMassimo() {
		assertSame(DEFAULT_PESO_MAX_BORSA, this.borsa.getPesoMax());
	}
	
	@Test
	public void testBorsaAppenaCreataHaGiustaLunghezzaArrayAttrezzi() {
		Attrezzo[] attrezzi = new Attrezzo[10];
		assertArrayEquals(attrezzi, this.borsa.getAttrezzi());
	}
	
	@Test
	public void testBorsaAppenaCreataEVuota() {
		Attrezzo[] attrezzi = this.borsa.getAttrezzi();
		for(int i=0; i<attrezzi.length; i++) {
			assertNull(attrezzi[i]);
		}
	}
	
	@Test
	public void testBorsaAppenaCreataNumeroAttrezziEZero() {
		int numeroAttrezzi = this.borsa.getNumeroAttrezzi();
		assertSame(0,numeroAttrezzi);
	}

	@Test
	public void testAddAttrezzoBorsaVuota() {
		this.attrezzo = new Attrezzo(NOME_ATTREZZO1,PESO_ATTREZZO1);
		this.borsa.addAttrezzo(this.attrezzo);
		assertSame(1,this.borsa.getNumeroAttrezzi());
		Attrezzo[] attrezzi = this.borsa.getAttrezzi();
		assertSame(this.attrezzo,attrezzi[0]);
		assertSame(1,this.borsa.getNumeroAttrezzi());
	}
	
	@Test
	public void testAddAttrezzoBorsaPiena() {
		for(int i=0; i<DEFAULT_LUNGHEZZA_ARRAY_ATTREZZI; i++) {
			this.borsa.addAttrezzo(new Attrezzo("i", i));
		}
		Attrezzo attrezzo = new Attrezzo(NOME_ATTREZZO1, PESO_ATTREZZO1);
		assertFalse(this.borsa.addAttrezzo(attrezzo));
	}
	
	@Test
	public void testAddAttrezzoTroppoPesante() {
		Attrezzo attrezzoTroppoPesante = new Attrezzo ("Pesantissimo", 100);
		assertFalse(this.borsa.addAttrezzo(attrezzoTroppoPesante));
		
	}

	@Test
	public void testBorsaVuotaIsVuota() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	public void testBorsaNonVuotaIsVuota() {
		Attrezzo attrezzo = new Attrezzo(NOME_ATTREZZO1, PESO_ATTREZZO1);
		this.borsa.addAttrezzo(attrezzo);
		assertFalse(this.borsa.isEmpty());
	}
	
	

	@Test
	public void testHasAttrezzoPresente() {
		Attrezzo attrezzo = new Attrezzo(NOME_ATTREZZO1, DEFAULT_PESO_MAX_BORSA);
		this.borsa.addAttrezzo(attrezzo);
		assertTrue(this.borsa.hasAttrezzo(NOME_ATTREZZO1));
		
	}
	
	@Test
	public void testHasAttrezzoPresenteBorsaConAttrezziMultipli() {
		for(int i=0; i<3; i++) {
			this.borsa.addAttrezzo(new Attrezzo("i", i));
		}
		Attrezzo attrezzo = new Attrezzo(NOME_ATTREZZO1, PESO_ATTREZZO1);
		this.borsa.addAttrezzo(attrezzo);
		assertTrue(this.borsa.hasAttrezzo(NOME_ATTREZZO1));
		
	}
	
	@Test
	public void testHasAttrezzoAssenteBorsaVuota() {
		Attrezzo attrezzo = new Attrezzo(NOME_ATTREZZO1, DEFAULT_PESO_MAX_BORSA);
		assertFalse(this.borsa.hasAttrezzo("Attrezzo non presente"));
		
	}
	
	@Test
	public void testHasAttrezzoAssenteBorsaNonVuota() {
		Attrezzo attrezzo = new Attrezzo(NOME_ATTREZZO1, DEFAULT_PESO_MAX_BORSA);
		this.borsa.addAttrezzo(attrezzo);
		assertFalse(this.borsa.hasAttrezzo("Attrezzo non presente"));
		
	}

	@Test
	public void testRemoveAttrezzoUnicoPresente() {
		Attrezzo attrezzo = new Attrezzo(NOME_ATTREZZO1, PESO_ATTREZZO1);
		this.borsa.addAttrezzo(attrezzo);
		assertTrue(this.borsa.hasAttrezzo(NOME_ATTREZZO1));
		assertEquals(attrezzo, this.borsa.removeAttrezzo(NOME_ATTREZZO1));
		assertFalse(this.borsa.hasAttrezzo(NOME_ATTREZZO1));
	}
	
	@Test
	public void testRemoveAttrezzoPresenteInBorsaConAttrezziMultipli() {
		for(int i=0; i<3; i++) {
			this.borsa.addAttrezzo(new Attrezzo("i", i));
		}
		Attrezzo attrezzo = new Attrezzo(NOME_ATTREZZO1, PESO_ATTREZZO1);
		this.borsa.addAttrezzo(attrezzo);
		assertTrue(this.borsa.hasAttrezzo(NOME_ATTREZZO1));
		assertEquals(attrezzo, this.borsa.removeAttrezzo(NOME_ATTREZZO1));
		assertFalse(this.borsa.hasAttrezzo(NOME_ATTREZZO1));
	}

	@Test
	public void testRemoveAttrezzoNonPresenteInBorsaVuota() {
		Attrezzo attrezzo = new Attrezzo(NOME_ATTREZZO1, PESO_ATTREZZO1);
		assertNull(this.borsa.removeAttrezzo(NOME_ATTREZZO1));
	}
	@Test
	public void testRemoveAttrezzoNonPresenteInBorsaConAttrezziMultipli() {
		for(int i=0; i<3; i++) {
			this.borsa.addAttrezzo(new Attrezzo("i", i));
		}
		Attrezzo attrezzo = new Attrezzo(NOME_ATTREZZO1, PESO_ATTREZZO1);
		assertNull(this.borsa.removeAttrezzo(NOME_ATTREZZO1));
	}

	@Test
	public void testToString() {
		Attrezzo attrezzo = new Attrezzo(NOME_ATTREZZO1, PESO_ATTREZZO1);
		assertEquals("Attrezzo1 (1kg)", attrezzo.toString());
	}
	


}
