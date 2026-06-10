package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

class FabbricaComandiIntrospettivaTest {

	private FabbricaDiComandiIntrospettiva fabbrica;
	private IO io;
	
	@BeforeEach
	public void setUp() {
		io = new IOConsole(new Scanner(System.in));
		fabbrica = new FabbricaDiComandiIntrospettiva(io);
	}
	
/* Test per Comando Non Esistente */
	
	@Test
	public void testComandoNonEsistente() {
		Comando c = fabbrica.costruisciComando("corri");
		
		assertTrue(c instanceof ComandoNonValido);
	}
	
	@Test
	public void testComandoNonEsistenteVuoto() {
		Comando c = fabbrica.costruisciComando("");
		
		assertTrue(c instanceof ComandoNonValido);
	}
	
	@Test
	public void testComandoVaiSenzaParametro() {
		Comando c = fabbrica.costruisciComando("vai");
		
		assertTrue(c instanceof ComandoVai);
		assertNull(c.getParametro());
	}
	
	/* Test per ComandoVai */
	
	@Test
	public void testComandoVai() {
		Comando c = fabbrica.costruisciComando("vai nord");
		
		assertTrue(c instanceof ComandoVai);
		assertEquals("nord", c.getParametro());
	}

}
