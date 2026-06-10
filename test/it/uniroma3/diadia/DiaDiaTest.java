package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

class DiaDiaTest {

    private Labirinto labirinto;

    @BeforeEach
    void setUp() {
        labirinto = Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .addStanzaVincente("Biblioteca")
                .addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
                .getLabirinto();
    }

    @Test
    void testPartitaConVittoriaRapida() {
        List<String> comandi = Arrays.asList("vai nord", "fine");
        IOSimulator io = new IOSimulator(comandi);

        DiaDia gioco = new DiaDia(labirinto, io);
        gioco.gioca();

        assertTrue(messaggioPresente(io.getMessaggiProdotti(), "Hai vinto!"));
    }

    private boolean messaggioPresente(List<String> messaggi, String target) {
        for (String m : messaggi) {
            if (m.contains(target))
                return true;
        }
        return false;
    }
}