package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
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
	public void testBorsaAppenaCreataHaMappaAttrezzi() {
		assertNotNull(this.borsa.getAttrezzi());
	}
	
	@Test
	public void testBorsaAppenaCreataEVuota() {
		assertTrue(this.borsa.getAttrezzi().isEmpty());
	}
	
	@Test
	public void testBorsaAppenaCreataNumeroAttrezziEZero() {
		assertTrue(this.borsa.getAttrezzi().size()==0);
	}

	@Test
	public void testAddAttrezzoBorsaVuota() {
		this.attrezzo = new Attrezzo(NOME_ATTREZZO1,PESO_ATTREZZO1);
		this.borsa.addAttrezzo(this.attrezzo);
		assertSame(1,this.borsa.getAttrezzi().size());
		assertSame(this.attrezzo,this.borsa.getAttrezzi().get(NOME_ATTREZZO1));
	}
	
	@Test
	public void testAddAttrezzoPesoMaxBorsaRaggiunto() {
		for(int i=0; i<DEFAULT_PESO_MAX_BORSA; i++) {
			this.borsa.addAttrezzo(new Attrezzo("i"+i, i));
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
			this.borsa.addAttrezzo(new Attrezzo("i"+i, i));
		}
		Attrezzo attrezzo = new Attrezzo(NOME_ATTREZZO1, PESO_ATTREZZO1);
		this.borsa.addAttrezzo(attrezzo);
		assertTrue(this.borsa.hasAttrezzo(NOME_ATTREZZO1));
		assertEquals(attrezzo, this.borsa.removeAttrezzo(NOME_ATTREZZO1));
		assertFalse(this.borsa.hasAttrezzo(NOME_ATTREZZO1));
	}

	@Test
	public void testRemoveAttrezzoNonPresenteInBorsaVuota() {
		assertNull(this.borsa.removeAttrezzo(NOME_ATTREZZO1));
	}
	
	@Test
	public void testRemoveAttrezzoNonPresenteInBorsaConAttrezziMultipli() {
		for(int i=0; i<3; i++) {
			this.borsa.addAttrezzo(new Attrezzo("i", i));
		}
		assertNull(this.borsa.removeAttrezzo(NOME_ATTREZZO1));
	}
	
	@Test 
	public void testDueAttrezziAggiuntiConStessoNomeNonPossonoEssereAggiuntiInBorsa() {
		Attrezzo attrezzo = new Attrezzo("Attrezzo", 1);
		Attrezzo attrezzoStessoNome = new Attrezzo("Attrezzo", 1);
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(1, this.borsa.getAttrezzi().values().size());
		this.borsa.addAttrezzo(attrezzoStessoNome);
		assertEquals(1, this.borsa.getAttrezzi().values().size());
	}
	
	@Test 
	public void testDueAttrezziAggiuntiConStessoNomeNonPossonoEssereAggiuntiInBorsaAncheConPesoDiverso() {
		Attrezzo attrezzo = new Attrezzo("Attrezzo", 1);
		Attrezzo attrezzoStessoNome = new Attrezzo("Attrezzo", 2);
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(1, this.borsa.getAttrezzi().values().size());
		this.borsa.addAttrezzo(attrezzoStessoNome);
		assertEquals(1, this.borsa.getAttrezzi().values().size());
	}

	@Test
	public void testToString() {
		Attrezzo attrezzo = new Attrezzo(NOME_ATTREZZO1, PESO_ATTREZZO1);
		assertEquals("Attrezzo1 (1kg)", attrezzo.toString());
	}
	
	
	//TEST DEL METODO getContenutoOrdinatoPerPeso
	
	@Test
	public void testGetContenutoOrdinatoPerPesoENomeDueAttrezziStessoPesoNomeDiversoGiustoOrdine() {
		Attrezzo ancora = new Attrezzo("Ancora", 1);
		this.borsa.addAttrezzo(ancora);
		Attrezzo bastone = new Attrezzo("Bastone", 1);
		this.borsa.addAttrezzo(bastone);
		List<Attrezzo> attrezziInBorsa = new ArrayList<Attrezzo>(this.borsa.getContenutoOrdinatoPerPeso());
		assertEquals(Arrays.asList(ancora,bastone), attrezziInBorsa);
	}

	@Test
	public void testGetContenutoOrdinatoPerPesoENomeDueAttrezziStessoPesoNomeDiversoOrdineSbagliato() {
		Attrezzo bastone = new Attrezzo("Bastone", 1);
		this.borsa.addAttrezzo(bastone);
		Attrezzo ancora = new Attrezzo("Ancora", 1);
		this.borsa.addAttrezzo(ancora);
		List<Attrezzo> attrezziInBorsa = new ArrayList<Attrezzo>(this.borsa.getContenutoOrdinatoPerPeso());
		assertEquals(Arrays.asList(ancora,bastone), attrezziInBorsa);
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPesoENomeDueAttrezziPesoDiversoGiustoOrdine() {
		Attrezzo ancora = new Attrezzo("Ancora", 1);
		this.borsa.addAttrezzo(ancora);
		Attrezzo bastone = new Attrezzo("Bastone", 2);
		this.borsa.addAttrezzo(bastone);
		List<Attrezzo> attrezziInBorsa = new ArrayList<Attrezzo>(this.borsa.getContenutoOrdinatoPerPeso());
		assertEquals(Arrays.asList(ancora,bastone), attrezziInBorsa);
	}

	
	@Test
	public void testGetContenutoOrdinatoPerPesoENomeDueAttrezziPesoDiversoOrdineSbagliato() {
		Attrezzo bastone = new Attrezzo("Bastone", 2);
		this.borsa.addAttrezzo(bastone);
		Attrezzo ancora = new Attrezzo("Ancora", 1);
		this.borsa.addAttrezzo(ancora);
		List<Attrezzo> attrezziInBorsa = new ArrayList<Attrezzo>(this.borsa.getContenutoOrdinatoPerPeso());
		assertEquals(Arrays.asList(ancora,bastone), attrezziInBorsa);
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPesoENomeDueAttrezziPesoDiversoOrdineSbagliato2() {
		Attrezzo ancora = new Attrezzo("Ancora", 2);
		this.borsa.addAttrezzo(ancora);
		Attrezzo bastone = new Attrezzo("Bastone", 1);
		this.borsa.addAttrezzo(bastone);	
		List<Attrezzo> attrezziInBorsa = new ArrayList<Attrezzo>(this.borsa.getContenutoOrdinatoPerPeso());
		assertEquals(Arrays.asList(bastone,ancora), attrezziInBorsa);
	}
	
	
	//TEST DEL METODO getContenutoOrdinatoPerNome
	
	@Test
	public void testGetContenutoOrdinatoPerNomeDueAttrezziGiustoOrdine() {
		Attrezzo ancora = new Attrezzo("Ancora", 1);
		this.borsa.addAttrezzo(ancora);
		Attrezzo bastone = new Attrezzo("Bastone", 1);
		this.borsa.addAttrezzo(bastone);
		List<Attrezzo> attrezziInBorsa = new ArrayList<>(this.borsa.getContenutoOrdinatoPerNome());
		assertEquals(Arrays.asList(ancora,bastone), attrezziInBorsa);
	}

	@Test
	public void testGetContenutoOrdinatoPerNomeDueAttrezziOrdineSbagliato() {
		Attrezzo bastone = new Attrezzo("Bastone", 1);
		this.borsa.addAttrezzo(bastone);
		Attrezzo ancora = new Attrezzo("Ancora", 1);
		this.borsa.addAttrezzo(ancora);
		List<Attrezzo> attrezziInBorsa = new ArrayList<>(this.borsa.getContenutoOrdinatoPerNome());
		assertEquals(Arrays.asList(ancora, bastone), attrezziInBorsa);
	}
	
	
	//TEST DEL METODO getContenutoRaggruppatoPerPeso
	
	
	@Test
	public void testGetContenutoRaggruppatoPerPesoDueAttrezziPesoDiverso() {
		Attrezzo ancora = new Attrezzo("Ancora", 1);
		this.borsa.addAttrezzo(ancora);
		Attrezzo bastone = new Attrezzo("Bastone", 2);
		this.borsa.addAttrezzo(bastone);
		Map<Integer, Set<Attrezzo>> attrezziInBorsa = this.borsa.getContenutoRaggruppatoPerPeso();
		assertEquals(2, attrezziInBorsa.size());
		assertEquals(Map.of(ancora.getPeso(), Set.of(ancora), bastone.getPeso(), Set.of(bastone)), attrezziInBorsa);
	
		//assertEquals(Arrays.asList(ancora, bastone), attrezziInBorsa);
		
		
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPesoDueAttrezziPesoUguale() {
		Attrezzo ancora = new Attrezzo("Ancora", 1);
		this.borsa.addAttrezzo(ancora);
		Attrezzo bastone = new Attrezzo("Bastone", 1);
		this.borsa.addAttrezzo(bastone);
		Map<Integer, Set<Attrezzo>> attrezziInBorsa = this.borsa.getContenutoRaggruppatoPerPeso();
		assertEquals(1, attrezziInBorsa.size());
		assertEquals(Map.of(ancora.getPeso(), Set.of(ancora,bastone)), attrezziInBorsa);		
	}
	
	

}
