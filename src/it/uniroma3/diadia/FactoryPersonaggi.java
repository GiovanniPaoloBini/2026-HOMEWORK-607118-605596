package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class FactoryPersonaggi implements FabbricaPersonaggi {

	@Override
	public AbstractPersonaggio creaPersonaggio(String specifica) {
		Scanner scanner = new Scanner(specifica);
		
		String tipo = scanner.next();
		switch(tipo.toLowerCase()) {
		
		case "mago":
			String nomeMago = scanner.next();
			return new Mago(nomeMago, "", new Attrezzo(scanner.next(), 1));
			
		case "cane":
			String ciboPreferito = scanner.next();
			String nomeCane = scanner.next();
			return new Cane(nomeCane, "", new Attrezzo(scanner.next(), 1), ciboPreferito);
			
		case "strega":
			String nomeStrega = scanner.next();
			String pres = scanner.next();
			
			return new Strega(nomeStrega, pres);
			
		default:
			throw new IllegalArgumentException("Personaggio sconosciuto");
		}
		
	}

}
