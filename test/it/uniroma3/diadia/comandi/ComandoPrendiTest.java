package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {

	private Partita partita;
	private ComandoPrendi comando;
	private Attrezzo attrezzo;
	private IO io;
	
	@BeforeEach
	public void setUp() {

	    Labirinto labirinto = Labirinto.newBuilder()
	            .addStanzaIniziale("atrio")
	            .addStanzaVincente("biblioteca")
	            .getLabirinto();

	    partita = new Partita(labirinto);

	    comando = new ComandoPrendi();
	    attrezzo = new Attrezzo("lanterna", 3);

	    io = new IOConsole(new Scanner(System.in));
	    comando.setIO(io);
	}
	
	/* Test per esegui */
	
	@Test
	public void testEseguiConAttrezzoValido() {
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		comando.setParametro("lanterna");
		comando.esegui(partita);
		
		assertNull(partita.getStanzaCorrente().getAttrezzo("lanterna"));
	}

	@Test
	public void testEseguiConAttrezzoNonValido() {
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		comando.setParametro("lanterna");
		comando.esegui(partita);
		
		assertNull(partita.getStanzaCorrente().getAttrezzo("lampada"));
	}
	
	@Test
	public void testEseguiConAttrezzoNull() {
		comando.setParametro(null);
		comando.esegui(partita);
		
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("lanterna"));
	}
	
	/* Test per setParametro */
	
	@Test
	public void testSetParametroConAttrezzo() {
		comando.setParametro("lanterna");
		comando.esegui(partita);
		
		assertNull(partita.getStanzaCorrente().getAttrezzo("lanterna"));
	}
	
	@Test
	public void testSetParametroSenzaAttrezzo() {
		comando.setParametro("lanterna");
		comando.esegui(partita);
		
		assertNull(partita.getStanzaCorrente().getAttrezzo("lanterna"));
	}
	
	@Test
	public void testSetParametroCambioAttrezzo() {
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		comando.setParametro(null);
		comando.setParametro("lanterna");
		comando.esegui(partita);
		
		assertNull(partita.getStanzaCorrente().getAttrezzo("lanterna"));
	}
	
}
