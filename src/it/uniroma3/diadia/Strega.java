package it.uniroma3.diadia;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		return "La Strega osserva ";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "La Strega ha ricevuto " + attrezzo.getNome() + "ed è scoppiata a ridere";
	}

}
