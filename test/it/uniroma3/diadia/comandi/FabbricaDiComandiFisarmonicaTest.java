package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandiFisarmonicaTest {

	FabbricaDiComandi fabbrica;
	
	@Before
	public void setUp() throws Exception {
		this.fabbrica = new FabbricaDiComandiFisarmonica();
	}

	@Test
	public void testCostruisciComandoVai() {
		Comando comandoVai = this.fabbrica.costruisciComando("vai sud");
		assertEquals(ComandoVai.class.toString(), comandoVai.getNome());
		assertEquals("sud", comandoVai.getParametro());
	}
	
	@Test
	public void testCostruisciComandoPosa() {
		Comando comandoPosa = this.fabbrica.costruisciComando("posa lanterna");
		assertEquals(ComandoPosa.class.toString(), comandoPosa.getNome());
		assertEquals("lanterna", comandoPosa.getParametro());
	}
	
	@Test
	public void testCostruisciComandoPrendi() {
		Comando comandoPrendi = this.fabbrica.costruisciComando("prendi lanterna");
		assertEquals(ComandoPrendi.class.toString(), comandoPrendi.getNome());
		assertEquals("lanterna", comandoPrendi.getParametro());
	}

	@Test
	public void testCostruisciComandoAiuto() {
		Comando comandoAiuto = this.fabbrica.costruisciComando("aiuto");
		assertEquals(ComandoAiuto.class.toString(), comandoAiuto.getNome());
		assertNull(comandoAiuto.getParametro());
	}
	
	@Test
	public void testCostruisciComandoFine() {
		Comando comandoFine = this.fabbrica.costruisciComando("fine");
		assertEquals(ComandoFine.class.toString(), comandoFine.getNome());
		assertNull(comandoFine.getParametro());
	}
	
	@Test
	public void testCostruisciComandoNonValido() {
		Comando comandoNonValido = this.fabbrica.costruisciComando("blabla");
		assertEquals(ComandoNonValido.class.toString(), comandoNonValido.getNome());
		assertNull(comandoNonValido.getParametro());
	}





}
