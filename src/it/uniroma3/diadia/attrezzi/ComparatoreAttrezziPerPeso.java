package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatoreAttrezziPerPeso implements Comparator<Attrezzo> {


	@Override
	public int compare(Attrezzo o1, Attrezzo o2) {
		int cmp = o1.getPeso()-o2.getPeso();
		
		if(cmp==0) {
			cmp = o1.getNome().compareTo(o2.getNome());
		}
		
		return cmp;
	}

}
