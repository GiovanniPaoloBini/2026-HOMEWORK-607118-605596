package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	 private StanzaBuia stanzaBuia;
	 private Attrezzo lanterna;
	 private Attrezzo osso;
	    
	@BeforeEach
	public void setUp() {
		stanzaBuia = new StanzaBuia("Laboratorio", " Lanterna");
		lanterna = new Attrezzo("lanterna", 3);
		osso = new Attrezzo("osso", 1);
	}
	
	/* Test per getDescrizione */
	
	@Test
	public void testGetDescrizioneSenzaAttrezzo() {
		
		assertEquals("qui c'è un buio pesto", stanzaBuia.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneConAttrezzo() {
		stanzaBuia.addAttrezzo(lanterna);
		
		assertEquals("qui c'è un buio pesto", stanzaBuia.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneConAltroAttrezzo() {
		stanzaBuia.addAttrezzo(osso);
		
		assertEquals("qui c'è un buio pesto", stanzaBuia.getDescrizione());
	}

}
