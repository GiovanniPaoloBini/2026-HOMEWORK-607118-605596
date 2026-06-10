package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.CaricatoreLabirinto;
import java.io.StringReader;

class LabirintoTest {
	
	private Labirinto labirinto;
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	
	@BeforeEach
	public void setUp() throws Exception {

		String specifica = "Stanze: Atrio, AulaN10, AulaN11\n" + "Inizio: Atrio\n" + "Vincente: AulaN11\n" + "Attrezzi: lanterna 3 Atrio\n" + "Uscite: Atrio est AulaN11 Atrio ovest AulaN10";

	    CaricatoreLabirinto car = new CaricatoreLabirinto(new StringReader(specifica));

	    car.carica();

	    Labirinto labirinto = car.getLabirinto();

	    stanzaIniziale = labirinto.getStanzaIniziale();
	    stanzaVincente = labirinto.getStanzaVincente();
	}
	
	/* Test per Stanza Iniziale  e Stanza Vincente*/
	
	@Test
	public void testStanzaInizialeEsistente() {
		assertNotNull(stanzaIniziale);
		assertEquals("Atrio", stanzaIniziale.getNome());
	}
	
	@Test
	public void testStanzaInizialeFalsa() {
		
		assertNotEquals("Laboratorio Campus", stanzaIniziale.getNome());
	}
	
	@Test
	public void testStanzaVincente() {
		assertNotNull(stanzaVincente);
		assertEquals("AulaN11", stanzaVincente.getNome());
	}
	
	/* Test per stanze vicine */
	
	@Test
	public void testStanzaEstdiStanzaIniziale() {
		
		assertNotNull(stanzaIniziale.getStanzaAdiacente(Direzione.EST));
		assertEquals("AulaN11", stanzaIniziale.getStanzaAdiacente(Direzione.EST).getNome());
		
	}
	
	@Test
	public void testStanzaOvestdiStanzaIniziale() {
		
		assertNotNull(stanzaIniziale.getStanzaAdiacente(Direzione.OVEST));
		assertEquals("AulaN10", stanzaIniziale.getStanzaAdiacente(Direzione.OVEST).getNome());
		
	}
	
	@Test
	public void testStanzaSuddiStanzaVincente() {
		
		assertNull(stanzaVincente.getStanzaAdiacente(Direzione.SUD));
		
	}
	
	/* Test per verificare presenza degli attrezzi nelle stanze */
	
	@Test
	public void testAttrezziPresentiInAtrio() {
		
		assertTrue(stanzaIniziale.hasAttrezzo("lanterna"));
		assertFalse(stanzaIniziale.hasAttrezzo("osso"));
		
	}
	
	@Test
	public void testAttrezziPresentiInAulaN10() {
		Stanza aulaN10 = stanzaIniziale.getStanzaAdiacente(Direzione.OVEST);
		
		assertFalse(aulaN10.hasAttrezzo("lanterna"));
		assertFalse(aulaN10.hasAttrezzo("osso"));
	}
	
	@Test
	public void testAttrezziPresenti() {
		Stanza aulaN10 = stanzaIniziale.getStanzaAdiacente(Direzione.OVEST);
		
		assertNull(aulaN10.getAttrezzo("lanterna"));
	}
	
	/* Test aggiuntivi dopo modifiche */
	
	@Test
	public void testStanzaVincenteSenzaAttrezzi() {
		assertNull(stanzaVincente.getAttrezzo("lanterna"));
	}
	
	@Test
	public void testStanzaVincenteDiversaDaStanzaIniziale() {
		assertNotEquals(stanzaVincente, stanzaIniziale);
	}
	
	@Test
	public void testAttrezzoGetPeso() {
		assertEquals(3, stanzaIniziale.getAttrezzo("lanterna").getPeso());
	}

}
