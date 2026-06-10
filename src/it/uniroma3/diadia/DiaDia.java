package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.comandi.FabbricaDiComandiIntrospettiva;
import it.uniroma3.diadia.Comando;
/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	//static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "regala"};

	private Partita partita;
	private Scanner scannerDiLinee;
	
	private IO io;

	public DiaDia(IO io) {
		this.io = io;
		this.partita = new Partita();
		this.scannerDiLinee = new Scanner(System.in);
	}
	
	public DiaDia(Labirinto labirinto, IO io) {
		this.io = io;
		this.partita = new Partita(labirinto);
		this.scannerDiLinee = new Scanner(System.in);
	}

	public void gioca() {
		String istruzione; 

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
		*/
	private boolean processaIstruzione(String istruzione) {
		// 1. Chiediamo alla fabbrica di costruirci il comando giusto a partire dalla stringa
		FabbricaDiComandiIntrospettiva fabbrica = new FabbricaDiComandiIntrospettiva(this.io);
		Comando comandoDaEseguire = fabbrica.costruisciComando(istruzione);


		
		comandoDaEseguire.esegui(this.partita);

		// 3. Controlliamo se la partita è finita o vinta
		if (this.partita.vinta()) {
			this.io.mostraMessaggio("Hai vinto!");
			return true;
		}
    
    return this.partita.isFinita();
}

	
	

	public static void main(String[] argc) throws FileNotFoundException, FormatoFileNonValidoException {
		Scanner scanner = new Scanner(System.in);
		IO io = new IOConsole(scanner);
		Labirinto labirinto = new Labirinto("Labirinto.txt");
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		scanner.close();
	}
}