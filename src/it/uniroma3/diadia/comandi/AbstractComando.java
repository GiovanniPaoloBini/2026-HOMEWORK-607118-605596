package it.uniroma3.diadia.comandi;

import java.util.ArrayList;
import java.util.List;

import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.IO;

public abstract class AbstractComando implements Comando {
	
	private String parametro;
	protected IO io;
	private static final List<String> comandiDisponibili = new ArrayList<>();
	
	protected static void segnaComando(String nome) {
		comandiDisponibili.add(nome);
	}
	public static List<String> getComandiDisponibili() {
		return comandiDisponibili;
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}
	
}
