package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class CaricatoreLabirintoTest {
	
	/* Test per monolocale */

	@Test
	public void testCaricatoreLabirintoMonolocale() throws Exception {
		String specifica = "Stanze: atrio\n" + "Inizio: atrio\n" + "Vincente: atrio\n" + "Attrezzi:\n" + "Uscite:\n";
		
		CaricatoreLabirinto car = new CaricatoreLabirinto(new StringReader(specifica));
		
		car.carica();
		
		Labirinto labirinto = car.getLabirinto();
		
		assertEquals("atrio", labirinto.getStanzaIniziale().getNome());
	}
	
	/* Test per bilocale */
	
	@Test
	public void testCaricatoreLabirintoBilocale() throws Exception {
		String specifica = "Stanze: atrio, biblioteca\n" + "Inizio: atrio\n" + "Vincente: biblioteca\n" + "Attrezzi:\n" + "Uscite: atrio nord biblioteca\n";
		
		CaricatoreLabirinto car = new CaricatoreLabirinto(new StringReader(specifica));
		
		car.carica();
		
		Labirinto labirinto = car.getLabirinto();
		Stanza atrio = labirinto.getStanzaIniziale();
		
		assertEquals("biblioteca", atrio.getStanzaAdiacente(Direzione.NORD).getNome());
	}
	
	/* Test per attrezzo */
	
	@Test
	public void testCaricatoreLabirintoAttrezzo() throws Exception {
		String specifica = "Stanze: atrio\n" + "Inizio: atrio\n" + "Vincente: atrio\n" + "Attrezzi: lanterna 3 atrio\n" + "Uscite:\n";
		
		CaricatoreLabirinto car = new CaricatoreLabirinto(new StringReader(specifica));
		
		car.carica();
		
		Labirinto labirinto = car.getLabirinto();
		
		assertNotNull(labirinto.getStanzaIniziale().getAttrezzo("lanterna"));
	}

}
