package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa extends AbstractComando {

    static {
        segnaComando("posa");
    }

    private String nomeAttrezzo;

    @Override
    public void esegui(Partita partita) {

        if(nomeAttrezzo==null) {
        	this.io.mostraMessaggio("Cosa vuoi posare?");
        	return;
        }

        Borsa borsa = partita.getGiocatore().getBorsa();
        Attrezzo attrezzo = borsa.removeAttrezzo(nomeAttrezzo);

        if(attrezzo==null) {
        	this.io.mostraMessaggio("L'attrezzo non è nella borsa");
        	return;
        }

        partita.getStanzaCorrente().addAttrezzo(attrezzo);
        this.io.mostraMessaggio("Hai posato l'attrezzo " + nomeAttrezzo);
    }

    @Override
    public void setParametro(String parametro) {
        this.nomeAttrezzo = parametro;
    }

    @Override
    public String getNome() {
        return "posa";
    }

    @Override
    public String getParametro() {
        return this.nomeAttrezzo;
    }
}