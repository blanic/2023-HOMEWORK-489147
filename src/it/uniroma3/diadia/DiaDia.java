package it.uniroma3.diadia;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoFine;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	public static IO io;

	public DiaDia(IO io, Labirinto labirinto) {
		this.partita = new Partita(labirinto);
		DiaDia.io = io;
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

//		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
//		scannerDiLinee = new Scanner(System.in);		
//		do		
//			istruzione = scannerDiLinee.nextLine();
//		while (!processaIstruzione(istruzione));
		
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	public boolean processaIstruzione(String istruzione) {
		
		if(istruzione == null) {
			return true;
		}
		FabbricaDiComandi fabbricaComandi = new FabbricaDiComandiFisarmonica();
		Comando comandoDaEseguire = fabbricaComandi.costruisciComando(istruzione);
		
		comandoDaEseguire.esegui(this.partita);
		
		if (comandoDaEseguire.getClass().equals(ComandoFine.class)) {
			return true;
		}
		
		if (this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	} 

	
	
	public Partita getPartita() {
		return this.partita;
	}

	public static void main(String[] argc) {
		IO io = new IOConsole();
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
		
		DiaDia gioco = new DiaDia(io, labirinto);
		gioco.gioca();
	}
}