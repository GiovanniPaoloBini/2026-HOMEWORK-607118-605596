package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {

	private StanzaMagica stanza;
	private Attrezzo osso;
	private Attrezzo lanterna;
	
	@BeforeEach
	public void setUp() {
		this.stanza = new StanzaMagica("atrio", 1);
		
		this.osso = new Attrezzo("osso", 1);
		this.lanterna = new Attrezzo("lanterna", 3);
	}
	
	/* Test per addAttrezzo */
	
	@Test
	public void testAddAttrezzoLanterna() {
		this.stanza.addAttrezzo(lanterna);
		
		assertTrue(this.stanza.hasAttrezzo("lanterna"));		
	}
	
	@Test
	public void testAddAttrezzoPeso() {
		this.stanza.addAttrezzo(osso);
		
		assertTrue(this.stanza.hasAttrezzo("osso"));
		assertEquals(1, osso.getPeso());
	}
	
	@Test
	public void testAddAttrezzoInseritoEquals() {
		this.stanza.addAttrezzo(lanterna);
		
		assertEquals(this.lanterna, this.stanza.getAttrezzo("lanterna"));
	}

}
