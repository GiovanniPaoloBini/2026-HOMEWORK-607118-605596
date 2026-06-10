package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	
	static {
		segnaComando("sconosciuto");
	}

    @Override
    public void esegui(Partita partita) {
        this.io.mostraMessaggio("Comando sconosciuto"); 
    }

    @Override public String getNome() { return "sconosciuto"; }
}