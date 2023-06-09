package it.uniroma3.diadia.comandi;

import java.util.Scanner;
import it.uniroma3.diadia.*;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	
	@SuppressWarnings("deprecation")
	public Comando costruisciComando(String istruzione){
		
		Scanner scannerDiParole = new Scanner(istruzione); // es. ‘vai sud’
		String nomeComando = null; // es. ‘vai’
		String parametro = null; // es. ‘sud’
		Comando comando = null;
		
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();//prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();//seconda parola: eventuale parametro
		
//		StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
//		nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) );
//		// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoV’
//		nomeClasse.append( nomeComando.substring(1) ) ;
//		// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoVai’
//		comando = (Comando)Class.forName(nomeClasse.toString()).newInstance();
//		comando.setParametro(parametro);
		
		try {
			String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
			nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
			nomeClasse += nomeComando.substring(1);
			comando = (Comando)Class.forName(nomeClasse).newInstance();
			comando.setParametro(parametro);
			} catch (Exception e) {
			comando = new ComandoNonValido();
			DiaDia.io.mostraMessaggio("Comando inesistente");
			}
		
		scannerDiParole.close();
		return comando;
	}
}