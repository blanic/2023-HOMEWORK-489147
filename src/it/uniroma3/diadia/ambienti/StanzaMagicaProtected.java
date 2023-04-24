package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected {
	
	final static private int SOGLIA_MAGICA = 3;
	
	private int sogliaMagica;
	private int contatoreAttrezziPosati;

	public StanzaMagicaProtected(String nome) {
		super(nome);
		this.sogliaMagica = SOGLIA_MAGICA;
		this.contatoreAttrezziPosati = 0;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati>=this.sogliaMagica) {
			attrezzo = this.modificaAttrezzo(attrezzo);
			this.sogliaMagica += this.sogliaMagica;
		}
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
        	this.attrezzi[numeroAttrezzi] = attrezzo;
        	this.numeroAttrezzi++;
        	return true;
        }
        else {
        	return false;
        }
	}

	public Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		String nomeAttrezzo = attrezzo.getNome();
		int pesoAttrezzo = attrezzo.getPeso();
		
		attrezzo.setNome(new StringBuffer(nomeAttrezzo).reverse().toString());
		attrezzo.setPeso(pesoAttrezzo*2);
		
		return attrezzo;
	}
}
