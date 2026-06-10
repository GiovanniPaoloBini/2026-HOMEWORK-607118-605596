package it.uniroma3.diadia.ambienti;

import java.util.Scanner;

public class FactoryStanze implements FabbricaStanze {

	@Override
	public Stanza creaStanza(String specifica) {
		Scanner scanner = new Scanner(specifica);
		
		String tipo = scanner.next();

		switch(tipo.toLowerCase()) {
		    case "buia":
		        return new StanzaBuia(scanner.next(), scanner.next());

		    case "bloccata":
		        return new StanzaBloccata(
		            scanner.next(),
		            Direzione.valueOf(scanner.next().toUpperCase()),
		            scanner.next()
		        );

		    case "magica":
		        return new StanzaMagica(scanner.next());

		    default:
		        // 👇 se NON è un tipo, è già il nome stanza
		        return new Stanza(tipo);
		}
	}
}
