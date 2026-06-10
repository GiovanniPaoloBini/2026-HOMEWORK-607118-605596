package it.uniroma3.diadia;

import java.io.InputStream;
import java.util.Properties;

public class CaricatoreProperties {
	
	private static Properties proprieta = new Properties();
	
	static {
		try {
			InputStream in = CaricatoreProperties.class.getClassLoader().getResourceAsStream("diadia.properties");
			
			proprieta.load(in);
		} catch(Exception e) {
			throw new RuntimeException("Errore", e);
		}
	}
	
	public static int getPesoMaxBorsa() {
		return Integer.parseInt(proprieta.getProperty("peso_max_borsa"));
	}
	
	public static int getCfuIniziali() {
		return Integer.parseInt(proprieta.getProperty("cfu_iniziali"));
	}
	
	public static int getSogliaDefault() {
		return Integer.parseInt(proprieta.getProperty("soglia_default"));
	}
}
