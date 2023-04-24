package it.uniroma3.diadia;

public class IOSimulator implements IO {
	
	String[] messaggiIn;
	String[] messaggiOut;
	int contatoreMessaggiIn;
	int contatoreMessaggiOut;

	public IOSimulator() {
		this.messaggiIn = new String[100];
		this.messaggiOut = new String[100];
		this.contatoreMessaggiIn = 0;
		this.contatoreMessaggiOut = 0;
	}
	
	public void setMessaggiIn(String[] messaggiIn) {
		this.messaggiIn = messaggiIn;
	}

	@Override
	public void mostraMessaggio(String msg) {
		this.messaggiOut[contatoreMessaggiOut] = msg;
		this.contatoreMessaggiOut++;
	}

	@Override
	public String leggiRiga() {
		if (contatoreMessaggiIn<messaggiIn.length) {
		String prossimaIstruzione = this.messaggiIn[contatoreMessaggiIn];
		contatoreMessaggiIn++;
		return prossimaIstruzione;
		}
		else {
			return null;
		}
	}
	
	public String getProssimoComando() {
		if (contatoreMessaggiIn<messaggiIn.length) {
		String prossimaIstruzione = this.messaggiIn[contatoreMessaggiIn];
		return prossimaIstruzione;
		}
		else {
			return null;
		}
	}


	public String[] getMessaggiOut() {
		return messaggiOut;
	}

	public void setMessaggiOut(String[] messaggiOut) {
		this.messaggiOut = messaggiOut;
	}

	public int getContatoreMessaggiIn() {
		return contatoreMessaggiIn;
	}

	public void setContatoreMessaggiIn(int contatoreMessaggiIn) {
		this.contatoreMessaggiIn = contatoreMessaggiIn;
	}

	public int getContatoreMessaggiOut() {
		return contatoreMessaggiOut;
	}

	public void setContatoreMessaggiOut(int contatoreMessaggiOut) {
		this.contatoreMessaggiOut = contatoreMessaggiOut;
	}

	public String[] getMessaggiIn() {
		return messaggiIn;
	}
	
	

}
