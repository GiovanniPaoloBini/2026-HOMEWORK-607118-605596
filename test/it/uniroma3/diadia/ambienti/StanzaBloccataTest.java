package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private StanzaBloccata stanza;
    private Stanza adiacenteNord;
    private Stanza adiacenteSud;
    private Attrezzo chiave;

    @BeforeEach
    public void setUp() {

        stanza = new StanzaBloccata("Laboratorio", Direzione.NORD, "chiave");

        adiacenteNord = new Stanza("Biblioteca");
        adiacenteSud = new Stanza("Aula");

        chiave = new Attrezzo("chiave", 1);

        stanza.impostaStanzaAdiacente(Direzione.NORD, adiacenteNord);
        stanza.impostaStanzaAdiacente(Direzione.SUD, adiacenteSud);
    }
    
    /* Test per getStanzaAdiacente */
    
    @Test
    public void testGetStanzaAdiacenteDirezioneBloccataSenzaAttrezzo() {

        assertEquals(stanza, stanza.getStanzaAdiacente(Direzione.NORD));
    }

    @Test
    public void testGetStanzaAdiacenteDirezioneBloccataConAttrezzo() {
        stanza.addAttrezzo(chiave);

        assertEquals(adiacenteNord, stanza.getStanzaAdiacente(Direzione.NORD));
    }

    @Test
    public void testGetStanzaAdiacenteDirezioneNonBloccata() {

        assertEquals(adiacenteSud, stanza.getStanzaAdiacente(Direzione.SUD));
    }
    
    /* Test per getDescrizione */
    
    @Test
    public void testGetDescrizioneSenzaAttrezzoConMessaggioBlocco() {
        String descrizione = stanza.getDescrizione();

        assertTrue(descrizione.contains("bloccata"));
    }

    @Test
    public void testGetDescrizioneSenzaAttrezzoConNomeAttrezzo() {
        String descrizione = stanza.getDescrizione();

        assertTrue(descrizione.contains("chiave"));
    }

    @Test
    public void testGetDescrizioneConAttrezzoSenzaMessaggioBlocco() {
        stanza.addAttrezzo(chiave);

        String descrizione = stanza.getDescrizione();

        assertFalse(descrizione.contains("bloccata"));
    }

}
