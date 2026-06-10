package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {

    static {
    	segnaComando("vai");
    }
    
    @Override
    public void esegui(Partita partita) {

        String param = this.getParametro();

        if (param == null) {
            io.mostraMessaggio("Dove vuoi andare?");
            return;
        }

        Direzione direzione;

        try {
            direzione = Direzione.valueOf(param.toUpperCase());
        } catch (IllegalArgumentException e) {
            io.mostraMessaggio("Direzione non valida");
            return;
        }

        Stanza stanzaCorrente = partita.getStanzaCorrente();
        Stanza prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);

        if (prossimaStanza == null) {
            io.mostraMessaggio("Direzione inesistente");
            return;
        }

        partita.setStanzaCorrente(prossimaStanza);

        int cfu = partita.getGiocatore().getCfu();
        partita.getGiocatore().setCfu(cfu - 1);

        io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
    }

    @Override
    public String getNome() { return "vai"; }
}