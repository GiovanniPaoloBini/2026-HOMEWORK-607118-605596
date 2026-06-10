package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
    
    static {
    	segnaComando("aiuto");
    }
    
    @Override
    public void esegui(Partita partita) {
        io.mostraMessaggio("Comandi Disponibili");
        
        for(String comandoNome : getComandiDisponibili()) {
        	io.mostraMessaggio(comandoNome);
        }
    }

    @Override public String getNome() { return "aiuto"; }
}