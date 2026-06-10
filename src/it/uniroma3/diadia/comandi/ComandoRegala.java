package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.AbstractPersonaggio;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando {

    static {
        segnaComando("regala");
    }

    private String parametro;

    @Override
    public void esegui(Partita partita) {

        if(parametro == null) {
        	io.mostraMessaggio("Cosa vuoi regalare?");
        	return;
        }

        AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();

        if (personaggio == null) {
            io.mostraMessaggio("Non c'è nessuno qui");
            return;
        }

        Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(this.parametro);

        if (attrezzo == null) {
            io.mostraMessaggio("Non hai questo attrezzo");
            return;
        }

        partita.getGiocatore().getBorsa().removeAttrezzo(this.parametro);

        io.mostraMessaggio(personaggio.riceviRegalo(attrezzo, partita));
    }

    @Override
    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    @Override
    public String getNome() {
        return "regala";
    }

    @Override
    public String getParametro() {
        return this.parametro;
    }
}