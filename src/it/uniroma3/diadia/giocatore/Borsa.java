package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.CaricatoreProperties;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = CaricatoreProperties.getPesoMaxBorsa();
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}
	
	public int getPeso() {
		int peso = 0;
		
		for(Attrezzo a : this.attrezzi.values()) {
			peso += a.getPeso();
		}
		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> lista = new ArrayList<>();
		
		lista.addAll(this.attrezzi.values());
		
		Collections.sort(lista, new Comparator<Attrezzo>() {
			
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				if(a1.getPeso()==a2.getPeso()) {
					return a1.getNome().compareTo(a2.getNome());
				}
				return a1.getPeso()-a2.getPeso();
			}
		});
		return lista;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> insieme = new TreeSet<>(new Comparator<Attrezzo>() {
			
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				return a1.getNome().compareTo(a2.getNome());
			}
		});
		
		insieme.addAll(this.attrezzi.values());
		
		return insieme;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> mappa = new HashMap<>();
		
		for(Attrezzo a : this.attrezzi.values()) {
			int peso = a.getPeso();
			
			if(!mappa.containsKey(peso)) {
				mappa.put(peso, new HashSet<>());
			}
			
			mappa.get(peso).add(a);
		}
		return mappa;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> insiemeOrdinato = new TreeSet<>(new Comparator<Attrezzo>() {
			
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				if(a1.getPeso()!=a2.getPeso()) {
					return a1.getPeso()-a2.getPeso();
				}
				return a1.getNome().compareTo(a2.getNome());
			}
		});
		insiemeOrdinato.addAll(this.attrezzi.values());
		
		return insiemeOrdinato;
	}
	
	public String toString() {
		if(this.isEmpty()) {
			return "Borsa vuota";
		}
		
		StringBuilder s = new StringBuilder();

		s.append("Contenuto Borsa: ");
		s.append(this.getPeso());
		s.append("kg");
		s.append(this.getPesoMax());
		s.append("kg");
		
		for(Attrezzo a : this.attrezzi.values()) {
			s.append("a");
		}
		
		return s.toString();
	}
}