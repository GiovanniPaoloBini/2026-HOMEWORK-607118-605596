package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

class AbstractComandoTest {

	private ComandoTest comando;
	private IO io;
	
	private static class ComandoTest extends AbstractComando {
		@Override
		public void esegui(Partita partita) {
			
		}
		
		@Override
		public String getNome() {
			return "test";
		}
		
	}
	
	@BeforeEach
	public void setUp() {
		comando = new ComandoTest();
		
		io = new IO() {
			@Override
			public void mostraMessaggio(String messaggio) {
				
			}

			@Override
			public String leggiRiga() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	/* Test per setParametro */
	
	@Test
	public void testSetParametroLanterna() {
		comando.setParametro("Lanterna");
		
		assertEquals("Lanterna", comando.getParametro());
	}
	
	@Test
	public void testSetParametroOsso() {
		comando.setParametro("Osso");
		
		assertEquals("Osso", comando.getParametro());
	}
	
	@Test
	public void testSetParametroNull() {
		comando.setParametro(null);
		
		assertNull(comando.getParametro());
	}
	
	/* Test per setIO */
	
	@Test
	public void testSetIO() {
		comando.setIO(io);
		assertSame(io, comando.io);
	}
	
	@Test
	public void testSetIONull() {
		comando.setIO(null);
		assertNull(comando.io);
	}
	
}
