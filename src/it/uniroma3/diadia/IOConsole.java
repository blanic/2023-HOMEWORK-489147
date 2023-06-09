package it.uniroma3.diadia;
import java.util.Scanner;

public class IOConsole implements IO {
	
	Scanner scannerLinee;
	
	public IOConsole(Scanner scanner) {
		this.scannerLinee = scanner;
	}
	
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	public String leggiRiga() {
		//Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerLinee.nextLine();
		return riga;
	}
}