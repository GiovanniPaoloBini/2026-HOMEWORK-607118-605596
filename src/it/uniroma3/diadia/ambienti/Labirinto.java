package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.AbstractPersonaggio;
import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	private Labirinto() {
		
	}
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	
	public static class LabirintoBuilder {
		private Labirinto labirinto;
		private Map<String, Stanza> listaStanze;
		private Stanza stanzaAggiuntaPerUltima;
		
		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.listaStanze = new HashMap<>();
		}
		
		public LabirintoBuilder addStanza(String nomeStanza) {
			if(!this.listaStanze.containsKey(nomeStanza)) {
				Stanza stanza = new Stanza(nomeStanza);
				this.listaStanze.put(nomeStanza, stanza);
				this.stanzaAggiuntaPerUltima = stanza;
			}
			else {
				this.stanzaAggiuntaPerUltima = this.listaStanze.get(nomeStanza);
			}
			return this;
		}
		
		public LabirintoBuilder addStanza(Stanza stanza) {
			this.listaStanze.put(stanza.getNome(), stanza);
			
			return this;
		}
		
		public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
			this.addStanza(nomeStanza);
			this.labirinto.setStanzaIniziale(this.listaStanze.get(nomeStanza));
			
			return this;
		}
		
		public LabirintoBuilder addStanzaVincente(String nomeStanza) {
			this.addStanza(nomeStanza);
			this.labirinto.setStanzaVincente(this.listaStanze.get(nomeStanza));
			
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nome, int peso, String nomeStanza) {
			Stanza stanza = this.listaStanze.get(nomeStanza);
			if(stanza!=null) {
				stanza.addAttrezzo(new Attrezzo(nome, peso));
			}
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String primaStanza, String altraStanza, Direzione direzione) {
			Stanza stanza1 = this.listaStanze.get(primaStanza);
			Stanza stanza2 = this.listaStanze.get(altraStanza);
			
			if(stanza1 != null && stanza2 != null) {
				stanza1.impostaStanzaAdiacente(direzione, stanza2);
			}
			
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoSbloccante) {
			if(!this.listaStanze.containsKey(nome)) {
				StanzaBloccata stanza = new StanzaBloccata(nome, direzioneBloccata, attrezzoSbloccante);
				this.listaStanze.put(nome, stanza);
			}
			this.stanzaAggiuntaPerUltima = this.listaStanze.get(nome);
			
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String nome, String attrezzoLuminoso) {
			if(!this.listaStanze.containsKey(nome)) {
				StanzaBuia stanza = new StanzaBuia(nome, attrezzoLuminoso);
				this.listaStanze.put(nome, stanza);
			}
			this.stanzaAggiuntaPerUltima = this.listaStanze.get(nome);
			
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
			if(!this.listaStanze.containsKey(nome)) {
				StanzaMagica stanza = new StanzaMagica(nome, soglia);
				this.listaStanze.put(nome, stanza);
			}
			this.stanzaAggiuntaPerUltima = this.listaStanze.get(nome);
			
			return this;
		}
		
		public LabirintoBuilder addPersonaggio(String nomeStanza, AbstractPersonaggio p) {
			this.listaStanze.get(nomeStanza).setPersonaggio(p);
			
			return this;
		}
		
		public boolean containsStanza(String nome) {
			return this.listaStanze.containsKey(nome);
		}
		
		public Map<String, Stanza> getListaStanze() {
			return this.listaStanze;
		}
		
		public Labirinto getLabirinto() {
			return this.labirinto;
		}
	}
	
	public Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(nomeFile);
		
		caricatore.carica();
		
		Labirinto lab = caricatore.getLabirinto();
		
		this.stanzaIniziale = lab.getStanzaIniziale();
		this.stanzaVincente = lab.getStanzaVincente();
	}
	
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente=stanzaVincente;
	}
    
	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale=stanzaIniziale;
	}
	
    public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
    
    public Stanza getStanzaIniziale() {
    	return stanzaIniziale;
	}

	
}