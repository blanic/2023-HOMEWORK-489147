package it.uniroma3.diadia.comandi;

import java.util.List;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoGuarda extends AbstractComando {

	private String parametro;

	@Override
	public void esegui(Partita partita) {
		if(parametro==null) {
			DiaDia.io.mostraMessaggio("Cosa vuoi guardare?");
		}
		else {
			if(this.parametro.equals("borsa")) {
				List<Attrezzo> contenutoBorsa = partita.getGiocatore().getBorsa().getContenutoOrdinatoPerPeso();
				if(contenutoBorsa.isEmpty()) {
					DiaDia.io.mostraMessaggio("Borsa vuota"+" CFU:"+partita.getGiocatore().getCfu());
				}
				else {
						DiaDia.io.mostraMessaggio(partita.getGiocatore().getBorsa()+"\n"+"CFU:"+partita.getGiocatore().getCfu());
				}
				
			}
			else {
				if(this.parametro.equals("stanza")) {
					DiaDia.io.mostraMessaggio(partita.getStanzaCorrente().toString());
				}
				else {
					DiaDia.io.mostraMessaggio("Richiesta non valida");
				}
			}
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}

}
