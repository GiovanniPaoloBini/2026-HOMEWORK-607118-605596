package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {
	
	private Partita partita;
	private ComandoVai comando;
	private Stanza primaStanza;
	private Stanza secondaStanza;
	private IOSimulator io;
	
	@BeforeEach
	public void setUp() {
		Labirinto labirinto = Labirinto.newBuilder().addStanzaIniziale("Aula N11").addStanzaVincente("Laboratorio").addAdiacenza("Aula N11", "Laboratorio", Direzione.EST).getLabirinto();
		partita = new Partita(labirinto);
		
		primaStanza = labirinto.getStanzaIniziale();
		secondaStanza = labirinto.getStanzaVincente();
		
		comando = new ComandoVai();
		
		io = new IOSimulator(List.of());
		comando.setIO(io);
	}
	
	/* Test per setParametro */
	
	@Test
	public void testSetParametroOvest() {
		comando.setParametro("ovest");
		comando.esegui(partita);
		assertEquals(primaStanza, partita.getStanzaCorrente());
	}
	
	@Test
	public void testSetParametroEst() {
		comando.setParametro("est");
		comando.esegui(partita);
		assertEquals(secondaStanza, partita.getStanzaCorrente());
	}
	
	@Test
	public void testSetParametroNord() {
		comando.setParametro("nord");
		comando.esegui(partita);
		assertEquals(primaStanza, partita.getStanzaCorrente());
	}
	
	/* Test per esegui */
	
	@Test
	public void testEseguiUnCfu() {
		partita.getGiocatore().setCfu(1);
		comando.setParametro("est");
		
		comando.esegui(partita);
		
		assertEquals(0, partita.getGiocatore().getCfu());
	}

	@Test
	public void testEseguiValido() {
		int cfuIniziali = partita.getGiocatore().getCfu();
		comando.setParametro("est");
		
		comando.esegui(partita);
		
		assertEquals(cfuIniziali-1, partita.getGiocatore().getCfu());
	}
	
	@Test
	public void testEseguiNonValido() {
		partita.getGiocatore().setCfu(1);
		comando.setParametro("sud");
		
		comando.esegui(partita);
		
		assertEquals(1, partita.getGiocatore().getCfu());
	}

	/* Test per monolocale */
	
	@Test
	public void testComandoVaiMonolocale() {
		Labirinto labirinto = Labirinto.newBuilder().addStanzaIniziale("Aula N11").addStanzaVincente("Aula N11").getLabirinto();
		
		Partita partita = new Partita(labirinto);
		
		comando.setParametro("est");
		comando.esegui(partita);
		
		assertEquals("Aula N11", partita.getStanzaCorrente().getNome());
	}
	
	/* Test per bilocale */
	
	@Test
	public void testComandoVaiBilocale() {
		Labirinto labirinto = Labirinto.newBuilder().addStanzaIniziale("Aula N11").addStanzaVincente("Laboratorio").addAdiacenza("Aula N11", "Laboratorio", Direzione.EST).getLabirinto();
		
		Partita partita = new Partita(labirinto);
		
		comando.setParametro("est");
		comando.esegui(partita);
		
		assertEquals("Laboratorio", partita.getStanzaCorrente().getNome());
	}
	
	/* Test per trilocale */
	
	@Test
	public void testComandoVaiTrilocale() {
		Labirinto labirinto = Labirinto.newBuilder().addStanzaIniziale("Aula N11").addStanza("Laboratorio").addStanzaVincente("Biblioteca").addAdiacenza("Aula N11", "Laboratorio", Direzione.EST).addAdiacenza("Aula N11", "Biblioteca", Direzione.NORD).getLabirinto();
		
		Partita partita = new Partita(labirinto);
		
		comando.setParametro("nord");
		comando.esegui(partita);
		
		assertEquals("Biblioteca", partita.getStanzaCorrente().getNome());
	}
}
