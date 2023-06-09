package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class CaricatoreCostanti {

	private static final String DIADIA_PROPERTIES = "diadia.properties";
	private static final String PESO_MAX = "pesoMax";
	private static final String CFU = "cfu";
	private static final String SOGLIA_MAGICA_DEFAULT = "sogliaMagicaDefault";
	private static Properties properties = null;
	
	public static int getCFU() {
		if(properties == null)
			carica();
		return Integer.parseInt(properties.getProperty(CFU));
	}
	
	public static int getPesoMax() {
		if(properties == null)
			carica();
		return Integer.parseInt(properties.getProperty(PESO_MAX));
	}
	
	public static int getSogliaMagicaDefault() {
		if(properties == null)
			carica();
		return Integer.parseInt(properties.getProperty(SOGLIA_MAGICA_DEFAULT));
	}


	private static void carica() {
		properties = new Properties();
		try {
			FileInputStream input = new FileInputStream(DIADIA_PROPERTIES);
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}