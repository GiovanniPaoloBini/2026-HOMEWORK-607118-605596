package it.uniroma3.diadia;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {

	private Attrezzo attrezzo;

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		if(this.attrezzo!=null) {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			this.attrezzo=null;
			return "Il mago lascia l'attrezzo nella stanza";
		}
		return "Il mago non fa nulla";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		Attrezzo attrezzoDimezzato = new Attrezzo(attrezzo.getNome(), attrezzo.getPeso()/2);
		partita.getStanzaCorrente().addAttrezzo(attrezzoDimezzato);
		
		return "Il mago ha ricevuto il regalo, ne ha dimezzato il peso e lo ha lasciato cadere";
	}

}
