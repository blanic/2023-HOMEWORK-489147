package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {
	
	private static final int CFU_INIZIALI = 20;
	
	private Giocatore giocatore;
	
	
	@Before
	public void setUp() {
		this.giocatore = new Giocatore();
	}

	@Test
	public void testGiocatoreAppenaCreatoNonENull() {
		assertNotNull(giocatore);
	}
	
	@Test
	public void testGiocatoreAppenaCreatoPossiedeBorsa() {
		assertNotNull(giocatore.getBorsa());
	}
	
	@Test
	public void testGiocatoreAppenaCreatoPossiedeCFUMaggioriZero() {
		assertTrue(giocatore.getCfu()>0);
	}
	
	@Test
	public void testGiocatoreAppenaCreatoPossiedeGiustoNumeroCFU() {
		assertSame(CFU_INIZIALI, giocatore.getCfu());
	}
	
	@Test
	public void testGetCfu() {
		giocatore.setCfu(9);
		assertSame(9, giocatore.getCfu());
	}

	@Test
	public void testSetCfu() {
		giocatore.setCfu(3);
		assertSame(3, giocatore.getCfu());
	}

	@Test
	public void testGetBorsaNotNull() {
		assertNotNull(giocatore.getBorsa());
	}
	

	@Test
	public void testSetBorsa() {
		Borsa borsa = giocatore.getBorsa();
		Borsa altraBorsa = new Borsa();
		giocatore.setBorsa(altraBorsa);
		assertNotSame(borsa, altraBorsa);
	}

}
