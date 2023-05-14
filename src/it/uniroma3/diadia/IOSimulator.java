package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class IOSimulator implements IO {
	
	List<String> messaggiIn;
	Map<String, List<String>> messaggiOut;
	private String istruzioneCorrente;
	
	public IOSimulator() {
		this.messaggiIn = new ArrayList<>();
		this.messaggiOut = new LinkedHashMap<>();
		this.istruzioneCorrente = "Inizio gioco";
		this.messaggiOut.put(istruzioneCorrente, null);
	}
	
	public void setMessaggiIn(List<String> messaggiIn) {
		this.messaggiIn = messaggiIn;
	}

	@Override
	public void mostraMessaggio(String msg) {
		List<String> listaMessaggi = this.messaggiOut.get(istruzioneCorrente);
		if(listaMessaggi==null) {
			listaMessaggi = new ArrayList<>(Arrays.asList(msg));
			this.messaggiOut.put(istruzioneCorrente, listaMessaggi);
		}
		else {
			listaMessaggi.add(msg);
		}
		
	}

	@Override
	public String leggiRiga() {
		if (!this.messaggiIn.isEmpty()) {
		String prossimaIstruzione = this.messaggiIn.get(0);
		this.istruzioneCorrente = prossimaIstruzione;
		this.messaggiIn.remove(prossimaIstruzione);
		return prossimaIstruzione;
		}
		else {
			return null;
		}
	}
	
	public String getProssimoComando() {
		if (!this.messaggiIn.isEmpty()) {
			String prossimaIstruzione = this.messaggiIn.get(0);
			return prossimaIstruzione;
			}
			else {
				return null;
			}
	}

	public Map<String, List<String>> getMessaggiOut() {
		return messaggiOut;
	}

	public void setMessaggiOut(Map<String, List<String>> messaggiOut) {
		this.messaggiOut = messaggiOut;
	}

	public List<String> getMessaggiIn() {
		return this.messaggiIn;
	}
	
	

}
