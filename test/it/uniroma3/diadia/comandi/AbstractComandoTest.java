package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AbstractComandoTest {
	
	AbstractComando comando;

	@Before
	public void setUp() throws Exception {
		comando = new FakeComando();
	}

	@Test
	public void testGetNome() {
		assertEquals(FakeComando.class, this.comando.getClass());
	}

	@Test
	public void testSetParametro() {
		this.comando.setParametro("fakeParametro");
		assertNull(this.comando.getParametro());
	}

	@Test
	public void testGetParametro() {
		assertNull(this.comando.getParametro());
	}

}
