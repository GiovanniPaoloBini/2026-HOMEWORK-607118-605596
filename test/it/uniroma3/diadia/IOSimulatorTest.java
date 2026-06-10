package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class IOSimulatorTest {

	@Test
	public void testIOSimulatorPartitaFinitaImmediatamente() {
		IOSimulator sim = new IOSimulator(List.of("fine"));
		
		DiaDia partita = new DiaDia(sim);
		partita.gioca();
		assertFalse(sim.getMessaggiProdotti().isEmpty());
	}

}
