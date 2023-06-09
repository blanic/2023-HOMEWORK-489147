package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	
	private String nome;
	private String presentazione;
	private boolean haSalutato;
	
	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
	}

	public String getNome() {
		return this.nome;
	}

	public String getPresentazione() {
		return this.presentazione;
	}
	
	public boolean haSalutato() {
		return this.haSalutato;
	}
	
	public abstract String agisci(Partita partita);
	
	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, ");
		
		if(haSalutato) {
			risposta.append("ci siamo già salutati!");
		}
		else {
			risposta.append(" io sono ");
			risposta.append(this.getNome()+".");
			this.haSalutato = true;
		}
		return risposta.toString();
	}
	
	public abstract String riceviRegalo(Attrezzo attrezzo, Partita partita);
	
	public String toString() {
		return this.getNome();
	}
}
