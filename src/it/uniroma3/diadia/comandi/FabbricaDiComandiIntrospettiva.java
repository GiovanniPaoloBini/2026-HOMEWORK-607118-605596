package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.IO;

public class FabbricaDiComandiIntrospettiva {

	private IO io;
	
	public FabbricaDiComandiIntrospettiva(IO io) {
        this.io = io;
        
        try {
            Class.forName("it.uniroma3.diadia.comandi.ComandoAiuto");
            Class.forName("it.uniroma3.diadia.comandi.ComandoVai");
            Class.forName("it.uniroma3.diadia.comandi.ComandoGuarda");
            Class.forName("it.uniroma3.diadia.comandi.ComandoPrendi");
            Class.forName("it.uniroma3.diadia.comandi.ComandoPosa");
            Class.forName("it.uniroma3.diadia.comandi.ComandoFine");
            Class.forName("it.uniroma3.diadia.comandi.ComandoInteragisci");
            Class.forName("it.uniroma3.diadia.comandi.ComandoRegala");
            Class.forName("it.uniroma3.diadia.comandi.ComandoSaluta");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
	
	public Comando costruisciComando(String istruzione) {
		Scanner scanner = new Scanner(istruzione);
		
		String nomeComando = null;
        String parametro = null;
        
        if (scanner.hasNext())
            nomeComando = scanner.next(); 
        if (scanner.hasNext())
            parametro = scanner.next(); 
        scanner.close();
        
        if(nomeComando==null) {
        	return new ComandoNonValido();
        }
        
        try {
        	String nomeClasse = "it.uniroma3.diadia.comandi.Comando" + Character.toUpperCase(nomeComando.charAt(0)) + nomeComando.substring(1);
        	
        	Class<?> classe = Class.forName(nomeClasse);
        	
        	Comando comando = (Comando) classe.getDeclaredConstructor().newInstance();
        	comando.setParametro(parametro);
            comando.setIO(this.io);
            
            return comando;
        }
        catch (Exception e){
        	Comando comando = new ComandoNonValido();
        	comando.setIO(io);
        	return comando;
        }
	}
}
