package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	private Stanza stanza;
	private Stanza stanzaAdiacente;
	private Stanza altraStanza;
	private Attrezzo osso;
	private Attrezzo lanterna;
	
	@BeforeEach
	public void setUp() {
		stanza = new Stanza("stanza");
		stanzaAdiacente = new Stanza("stanzaAdiacente");
		altraStanza = new Stanza("altraStanza");
		
		osso = new Attrezzo("osso", 1);
		lanterna = new Attrezzo("lanterna", 3);
	}

	/* Test per impostaStanzaAdiacente */
	
	@Test
	public void testImpostaStanzaAdiacenteEst() {
		
		altraStanza.impostaStanzaAdiacente(Direzione.EST, stanzaAdiacente);
		assertEquals(altraStanza.getStanzaAdiacente(Direzione.EST), stanzaAdiacente);
	}
	
	@Test
	public void testImpostaStanzaAdiacenteSud() {
		
		stanza.impostaStanzaAdiacente(Direzione.SUD, altraStanza);
		assertEquals(stanza.getStanzaAdiacente(Direzione.SUD), altraStanza);
	}

	@Test
	public void testImpostaStanzaAdiacenteNull() {
		
		stanza.impostaStanzaAdiacente(Direzione.SUD, altraStanza);
		assertNull(stanza.getStanzaAdiacente(Direzione.EST));
	}
	
	/* Test per getStanzaAdiacente */
	
	@Test
	public void testGetStanzaAdiacenteEsistente() {
		
		stanza.impostaStanzaAdiacente(Direzione.OVEST, altraStanza);
		assertNotNull(stanza.getStanzaAdiacente(Direzione.OVEST));
	}
	
	@Test
	public void testGetStanzaAdiacenteNonEsistente() {
		
		stanza.impostaStanzaAdiacente(Direzione.SUD, altraStanza);
		assertNull(stanza.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	public void testGetStanzaAdiacenteNord() {
		
		stanza.impostaStanzaAdiacente(Direzione.NORD, altraStanza);
		assertEquals(stanza.getStanzaAdiacente(Direzione.NORD), altraStanza);
	}
	
	/* Test per addAttrezzo */
	
	@Test
	public void testAddAttrezzoOsso() {
	
		assertTrue(stanza.addAttrezzo(osso));
	}
	
	@Test
	public void testAddAttrezzoLanterna() {
	
		assertTrue(stanza.addAttrezzo(lanterna));
	}

	@Test
	public void testAddAttrezzoLanternaPesoDiverso() {
		Attrezzo lanterna = new Attrezzo("lanterna", 1);
		
		assertTrue(stanza.addAttrezzo(lanterna));
	}
	
	/*Test per hasAttrezzo */
	
	@Test
	public void testHasAttrezzo() {
		stanza.addAttrezzo(osso);
		
		assertTrue(stanza.hasAttrezzo("osso"));
	}
	
	@Test
	public void testHasAttrezzoNonPresente() {
		stanza.addAttrezzo(osso);
		
		assertFalse(stanza.hasAttrezzo("lanterna"));
	}
	
	@Test
	public void testHasAttrezzoEntrambiAttrezziPresenti() {
		stanza.addAttrezzo(osso);
		stanza.addAttrezzo(lanterna);
		
		assertTrue(stanza.hasAttrezzo("lanterna"));
		assertTrue(stanza.hasAttrezzo("osso"));
	}
	
	/* Test per getAttrezzo */
	
	@Test
	public void testGetAttrezzo() {
		stanza.addAttrezzo(lanterna);
		
		assertEquals(lanterna, stanza.getAttrezzo("lanterna"));
	}
	
	@Test
	public void testGetAttrezzoNonPresente() {
		stanza.addAttrezzo(lanterna);
		
		assertNull(stanza.getAttrezzo("osso"));
	}
	
	@Test
	public void testGetAttrezzoEntrambiGliAttrezziPresenti() {
		stanza.addAttrezzo(lanterna);
		stanza.addAttrezzo(osso);
		
		assertEquals(lanterna, stanza.getAttrezzo("lanterna"));
		assertNotNull(stanza.getAttrezzo("osso"));
	}
	
	/* Test per removeAttrezzo */
	
	@Test
	public void testRemoveAttrezzo() {
		stanza.addAttrezzo(lanterna);
		stanza.removeAttrezzo(lanterna);
		
		assertFalse(stanza.hasAttrezzo("lanterna"));
	}
	
	@Test
	public void testRemoveAttrezzoNonPresente() {
		stanza.addAttrezzo(lanterna);
		
		assertFalse(stanza.removeAttrezzo(osso));
	}
	
	@Test
	public void testRemoveAttrezzoConDueAttrezzi() {
		stanza.addAttrezzo(lanterna);
		stanza.addAttrezzo(osso);
		stanza.removeAttrezzo(lanterna);
		
		assertFalse(stanza.hasAttrezzo("lanterna"));
		assertTrue(stanza.hasAttrezzo("osso"));
	}
	
	/* Test per getDirezioni */
	
	@Test
	public void testGetDirezioniZeroDirezioni() {
		
		assertEquals(0, stanza.getDirezioni().size());
	}
	
	@Test
	public void testGetDirezioniUnaDirezione() {
		
		stanza.impostaStanzaAdiacente(Direzione.EST, stanzaAdiacente);
		assertEquals(1, stanza.getDirezioni().size());
	}

	@Test
	public void testGetDirezioniDueDirezioni() {
		
		stanza.impostaStanzaAdiacente(Direzione.EST, altraStanza);
		stanza.impostaStanzaAdiacente(Direzione.SUD, stanzaAdiacente);
		assertEquals(2, stanza.getDirezioni().size());
	}
}