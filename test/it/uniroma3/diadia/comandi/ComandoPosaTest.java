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

class ComandoPosaTest {

	private Partita partita;
	private ComandoPosa comando;
	private Attrezzo attrezzo;
	private IO io;
	
	@BeforeEach
	public void setUp() {
	    Labirinto labirinto = Labirinto.newBuilder()
	            .addStanzaIniziale("atrio")
	            .addStanzaVincente("biblioteca")
	            .getLabirinto();

	    partita = new Partita(labirinto);

	    comando = new ComandoPosa();
	    attrezzo = new Attrezzo("osso", 1);

	    io = new IOConsole(new Scanner(System.in));
	    comando.setIO(io);
	}
	
	/* Test per esegui */
	
	@Test
	public void testEseguiConAttrezzoPresente() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comando.setParametro("osso");
		comando.esegui(partita);
		
		assertNotNull(partita.getStanzaCorrente().getAttrezzo("osso"));
	}
	
	@Test
	public void testEseguiConAttrezzoDaPosare() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comando.setParametro("osso");
		comando.esegui(partita);
		
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("osso"));
	}
	
	@Test
	public void testEseguiConAttrezzoNonEsistente() {
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		comando.setParametro("osso");
		comando.esegui(partita);
		
		assertNotEquals("torcia", partita.getStanzaCorrente().getAttrezzo("osso"));
	}

	/* Test per setParametro */
	
	@Test
	public void testSetParametroValido() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comando.setParametro("osso");
		comando.esegui(partita);
		
		assertNotNull(partita.getStanzaCorrente().getAttrezzo("osso"));
	}
	
	@Test
	public void testSetParametroNonDaPosare() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		
		comando.setParametro(null);
		comando.esegui(partita);
		
		assertNotNull(partita.getGiocatore().getBorsa().getAttrezzo("osso"));
	}
	
	@Test
	public void testSetParametroNonEsistente() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		
		comando.setParametro(null);
		comando.esegui(partita);
		
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("luce"));
	}

}
