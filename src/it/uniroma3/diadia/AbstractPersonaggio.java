package it.uniroma3.diadia;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	
	private String nome;
	private String presentazione;
	private boolean salutato;
	
	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome=nome;
		this.presentazione=presentazione;
		this.salutato=false;
	}
	
	public String saluta() {
		this.salutato=true;
		return "Ciao " + this.presentazione + "sono " + this.nome;
	}
	
	public boolean salutato() {
		return this.salutato;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public abstract String agisci(Partita partita);
	
	public abstract String riceviRegalo(Attrezzo attrezzo, Partita partita);
	
}
