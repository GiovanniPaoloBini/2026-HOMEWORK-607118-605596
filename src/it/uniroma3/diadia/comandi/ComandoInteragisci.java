package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.AbstractPersonaggio;
import it.uniroma3.diadia.Partita;

public class ComandoInteragisci extends AbstractComando {

	static {
    	segnaComando("interagisci");
    }
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		
		if(personaggio == null) {
			io.mostraMessaggio("Non c'è nessuno con cui interagire");
			return;
		}
		
		io.mostraMessaggio(personaggio.agisci(partita));
	}

	@Override
	public String getNome() {
		return "interagisci";
	}
}
