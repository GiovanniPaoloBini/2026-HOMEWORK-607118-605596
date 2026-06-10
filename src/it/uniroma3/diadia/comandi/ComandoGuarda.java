package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoGuarda extends AbstractComando {
    static {
    	segnaComando("guarda");
    }

    @Override
    public void esegui(Partita partita) {
        this.io.mostraMessaggio("Stanza corrente: " + partita.getStanzaCorrente().getNome()); 
        this.io.mostraMessaggio("CFU residui: " + partita.getGiocatore().getCfu()); 
        this.io.mostraMessaggio(partita.getGiocatore().getBorsa().toString()); 
    }

    @Override public void setParametro(String parametro) {}
    @Override public String getNome() { return "guarda"; }
    @Override public String getParametro() { return null; }
    public void setIO(IO io) { this.io = io; }
}