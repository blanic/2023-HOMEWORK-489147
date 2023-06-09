package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.CaricatoreCostanti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza {
	
	final static private int SOGLIA_MAGICA_DEFAULT = CaricatoreCostanti.getSogliaMagicaDefault();

	private static final int INIZIALIZZAZIONE_CONTATORE = 0;
	
	private int sogliaMagica;
	private int contatoreAttrezziPosati;

	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT, INIZIALIZZAZIONE_CONTATORE);
	}
	
	public StanzaMagica(String nome, int sogliaMagica) {
		this(nome, sogliaMagica, 0);
	}
	
	public StanzaMagica(String nome, int sogliaMagica, int contatoreAttrezziPosati) {
		super(nome);
		this.sogliaMagica = sogliaMagica;
		this.contatoreAttrezziPosati = contatoreAttrezziPosati;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati>=this.sogliaMagica) {
			attrezzo = this.modificaAttrezzo(attrezzo);
			this.sogliaMagica += this.sogliaMagica;
		}
		return super.addAttrezzo(attrezzo);
	}

	public Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		String nomeAttrezzo = attrezzo.getNome();
		int pesoAttrezzo = attrezzo.getPeso();
		
		attrezzo.setNome(new StringBuffer(nomeAttrezzo).reverse().toString());
		attrezzo.setPeso(pesoAttrezzo*2);
		
		return attrezzo;
	}
	
	@Override
	public int hashCode() {
		return (super.hashCode() + sogliaMagica);
	}
	
	@Override
	public boolean equals(Object o) {
		if(this.getClass() != o.getClass()) {
			return false;
		}
		StanzaMagica that = (StanzaMagica)o;	
		return (super.equals(that) && 
				this.sogliaMagica == that.sogliaMagica);
	}
}
