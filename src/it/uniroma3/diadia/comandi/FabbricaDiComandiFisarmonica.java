package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {

	@Override
	public Comando costruisciComando(String istruzione) {
		Comando comando = null;
		String nomeComando = null;
		String parametroComando = null;
		Scanner scannerDiParole = new Scanner(istruzione);


		// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); 

		// seconda parola: eventuale parametro
		if (scannerDiParole.hasNext())
			parametroComando = scannerDiParole.next();


		if(nomeComando ==null) {
			comando = new ComandoNonValido();
		}
		else if (nomeComando.equals("fine")) {
			comando = new ComandoFine();
		} else if (nomeComando.equals("vai")) {
			comando = new ComandoVai();
			comando.setParametro(parametroComando);
		}
		else if (nomeComando.equals("prendi")) {
			comando = new ComandoPrendi();
			comando.setParametro(parametroComando);
		}
		else if (nomeComando.equals("posa")) {
			comando = new ComandoPosa();
			comando.setParametro(parametroComando);
		}
		else if (nomeComando.equals("guarda")) {
			comando = new ComandoGuarda();
			comando.setParametro(parametroComando);
		}
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto();
		else
			comando = new ComandoNonValido();


		return comando;
	}



}
