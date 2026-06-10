package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.AbstractPersonaggio;
import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando {
	
	static {
    	segnaComando("saluta");
    }

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		
		if(personaggio == null) {
			io.mostraMessaggio("Non c'è nessuno qui");
			return;
		}
		
		io.mostraMessaggio(personaggio.saluta());
	}

	@Override
	public String getNome() {
		return "saluta";
	}
}
