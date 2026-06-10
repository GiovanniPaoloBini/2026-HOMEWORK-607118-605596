package it.uniroma3.diadia;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	
	private String ciboPreferito;
	private Attrezzo attrezzo;

	public Cane(String nome, String presentazione, Attrezzo attrezzo, String ciboPreferito) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
		this.ciboPreferito = ciboPreferito;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
		return "Il cane ti ha morso, ora hai un cfu in meno";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome().equals(this.ciboPreferito)) {
			
			if(this.attrezzo!=null) {
				partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
				
				Attrezzo attrezzoLasciato = this.attrezzo;
				this.attrezzo=null;
				
				return "Il cane ha accettato " + attrezzoLasciato.getNome();
			}
			return "Il cane ha ricevuto il regalo";
		}
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
		return "Il cane ti ha morso, ora hai un cfu in meno";
	}
}
