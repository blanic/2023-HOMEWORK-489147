package it.uniroma3.diadia.testAccettazione;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class AccettazioneTest {

	private static final String NOME_STANZA_1 = "stanza1";
	private static final String NOME_STANZA_2 = "stanza2";
	private static final String NOME_STANZA_3 = "stanza3";

	DiaDia diadia;
	Partita partita;
	IOSimulator io;

	@Before
	public void setUp() throws Exception {
		this.io = new IOSimulator();
		List<String> stanze = Arrays.asList("Aula N10","Biblioteca");
		Labirinto labirinto = new LabirintoBuilder()
				.addStanze(stanze)
				.addStanzaBloccata("Atrio", "nord", "chiave")
				.addStanzaBuia("Aula N11", "lanterna")
				.addStanzaMagica("Laboratorio")
				.setStanzaIniziale("Atrio")
				.setStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio","Biblioteca","nord")
				.addAdiacenza("Atrio","Aula N11","est")
				.addAdiacenza("Atrio","Aula N10","sud")
				.addAdiacenza("Atrio","Laboratorio","ovest")
				.addAdiacenza("Aula N11","Laboratorio","est")
				.addAdiacenza("Aula N11","Atrio","ovest")
				.addAdiacenza("Aula N10","Atrio","nord")
				.addAdiacenza("Aula N10","Aula N11","est")
				.addAdiacenza("Aula N10","Laboratorio","ovest")
				.addAdiacenza("Laboratorio","Atrio","est")
				.addAdiacenza("Laboratorio","Aula N11","ovest")
				.addAdiacenza("Biblioteca","Atrio","sud")
				.addAttrezzoAStanza("Aula N10", "lanterna", 5)
				.addAttrezzoAStanza("Laboratorio", "osso", 8)
				.addAttrezzoAStanza("Aula N11", "chiave", 1)
				.getLabirinto();

	}
	@Test
	public void testPartitaConMonolocale () {
		Labirinto monolocale = new LabirintoBuilder() 
				.addStanza(NOME_STANZA_1)
				.setStanzaIniziale(NOME_STANZA_1)
				.setStanzaVincente(NOME_STANZA_1)
				.getLabirinto();
		DiaDia diadia = new DiaDia(io,monolocale);
		diadia.gioca();
//		Map<String, List<String>> messaggiOut = this.io.getMessaggiOut();
//		for(String messaggioIn : messaggiOut.keySet()) {
//			System.out.println(messaggioIn);
//			for(String messaggioOut : this.io.getMessaggiOut().get(messaggioIn))
//				System.out.println(messaggioOut);
//		}
		assertTrue(diadia.getPartita().isFinita());
	}


	@Test
	public void testPartitaConBilocale () {
		Labirinto bilocale = new LabirintoBuilder() 
				.addStanza(NOME_STANZA_1)
				.addStanza(NOME_STANZA_2)
				.setStanzaIniziale(NOME_STANZA_1)
				.setStanzaVincente(NOME_STANZA_2)
				.addAttrezzoAStanza(NOME_STANZA_2, "letto", 10)
				.addAdiacenza(NOME_STANZA_1, NOME_STANZA_2,"nord")
				.getLabirinto();
		DiaDia diadia = new DiaDia(io,bilocale);
		this.io.setMessaggiIn(new ArrayList<>(List.of("vai nord")));
		diadia.gioca();
//		Map<String, List<String>> messaggiOut = this.io.getMessaggiOut();
//		for(String messaggioIn : messaggiOut.keySet()) {
//			System.out.println("\n\n");
//			System.out.println(messaggioIn);
//			System.out.println("\n");
//			for(String messaggioOut : this.io.getMessaggiOut().get(messaggioIn))
//				System.out.println(messaggioOut);
//		}
	}
	
	@Test
	public void testPartitaConTrilocale () {
		Labirinto bilocale = new LabirintoBuilder() 
				.addStanza(NOME_STANZA_1)
				.addStanza(NOME_STANZA_2)
				.addStanza(NOME_STANZA_3)
				.setStanzaIniziale(NOME_STANZA_1)
				.setStanzaVincente(NOME_STANZA_3)
				.addAdiacenza(NOME_STANZA_1, NOME_STANZA_2,"nord")
				.addAdiacenza(NOME_STANZA_2, NOME_STANZA_3, "est")
				.getLabirinto();
		DiaDia diadia = new DiaDia(io,bilocale);
		this.io.setMessaggiIn(new ArrayList<>(List.of("vai nord","vai est")));
		diadia.gioca();
//		Map<String, List<String>> messaggiOut = this.io.getMessaggiOut();
//		for(String messaggioIn : messaggiOut.keySet()) {
//			System.out.println("\n\n");
//			System.out.println(messaggioIn);
//			System.out.println("\n");
//			for(String messaggioOut : this.io.getMessaggiOut().get(messaggioIn))
//				System.out.println(messaggioOut);
//		}
	}

}
