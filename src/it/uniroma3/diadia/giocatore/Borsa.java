package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.CaricatoreCostanti;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerNome;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = CaricatoreCostanti.getPesoMax();
	private Map<String,Attrezzo> attrezzi;
	private int pesoMax;


	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
	}


	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo==null)
			return false;
		else {
			if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
				return false;
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;
		}
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = this.attrezzi.get(nomeAttrezzo);
		return a;
	}

	public int getPeso() {
		int peso = 0;

		for(String nomeAttrezzo : this.attrezzi.keySet())
			peso += this.attrezzi.get(nomeAttrezzo).getPeso();

		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}

	public Map<String,Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for(Attrezzo attrezzo : this.attrezzi.values())
				s.append(attrezzo.toString()+" ");
		}
		else {
			s.append("Borsa vuota");
		}
		return s.toString();
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> attrezziInBorsaOrdinatiPerPeso = new ArrayList<>(this.attrezzi.values());
		attrezziInBorsaOrdinatiPerPeso.sort(new ComparatoreAttrezziPerPeso());
		return attrezziInBorsaOrdinatiPerPeso;
	}

	SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> attrezziInBorsaOrdinatiPerNome = new TreeSet<>(new ComparatoreAttrezziPerNome());
		attrezziInBorsaOrdinatiPerNome.addAll(this.attrezzi.values());
		return attrezziInBorsaOrdinatiPerNome;

	}

	Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> attrezziInBorsaRaggruppatiPerPeso = new HashMap<>();

		for(Attrezzo attrezzo : this.attrezzi.values()) {
			boolean presente = attrezziInBorsaRaggruppatiPerPeso.containsKey(attrezzo.getPeso());
			if(!presente) {
				attrezziInBorsaRaggruppatiPerPeso.put(attrezzo.getPeso(), new HashSet<>(Collections.singleton(attrezzo)));
			}
			else {
				attrezziInBorsaRaggruppatiPerPeso.get(attrezzo.getPeso()).add(attrezzo);
			}
		}
		return attrezziInBorsaRaggruppatiPerPeso;
	}












}