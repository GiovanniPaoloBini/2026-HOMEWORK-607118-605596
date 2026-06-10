package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica fabbrica;
	private IO io;
	
	@BeforeEach
	public void setUp() {
		io = new IOConsole(new Scanner(System.in));
		fabbrica = new FabbricaDiComandiFisarmonica(io);
	}
	
	/* Test per vai */
	
	@Test
	public void testComandoVai() {
		Comando comando = fabbrica.costruisciComando("vai ovest");
		
		assertEquals("vai", comando.getNome());
		assertEquals("ovest", comando.getParametro());
	}
	
	/* Test per aiuto */
	
	@Test
	public void testComandoAiuto() {
		Comando comando = fabbrica.costruisciComando("aiuto");
		
		assertEquals("aiuto", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	/* Test per fine */
	
	@Test
	public void testComandoFine() {
		Comando comando = fabbrica.costruisciComando("fine");
		
		assertEquals("fine", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	/* Test per prendi */
	
	@Test
	public void testComandoPrendi() {
		Comando comando = fabbrica.costruisciComando("prendi lanterna");
		
		assertEquals("prendi", comando.getNome());
		assertEquals("lanterna", comando.getParametro());
	}
	
	/* Test per posa */
	
	@Test
	public void testComandoPosa() {
		Comando comando = fabbrica.costruisciComando("posa lanterna");
		
		assertEquals("posa", comando.getNome());
		assertEquals("lanterna", comando.getParametro());
	}
	
	/* Test per guarda */
	
	@Test
	public void testComandoGuarda() {
		Comando comando = fabbrica.costruisciComando("guarda");
		
		assertEquals("guarda", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	/* Test per ComandoNonValido */
	
	@Test
	public void testComandoNonValido() {
		Comando comando = fabbrica.costruisciComando("go");
		
		assertNull(comando.getParametro());
	}

}