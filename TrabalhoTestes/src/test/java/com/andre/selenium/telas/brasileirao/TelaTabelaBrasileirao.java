package com.andre.selenium.telas.brasileirao;

import java.util.List;
import java.util.ArrayList;

import com.andre.selenium.SeleniumWebDriver;

public class TelaTabelaBrasileirao {

	private SeleniumWebDriver selenium;
	private static final String URL = "http://esporte.uol.com.br/futebol/campeonatos/brasileiro/2012/serie-a/classificacao/classificacao.htm";

	public TelaTabelaBrasileirao(SeleniumWebDriver selenium) {
		this.selenium = selenium;
	}

	public List<String> getEstatisticasTimeLibertadores() {
		List<String> times = new ArrayList<String>();
		String estatistica = "";
		for (int i = 1; i <= 4; i++) {
			estatistica = "";
			estatistica = selenium.getTextTableCell("classificationTable", i, 1);
			estatistica = estatistica.replace("\n", " - ");
			for (int j = 2; j <= 10; j++) {
				estatistica = estatistica + " - " + selenium.getTextTableCell("classificationTable", i, j);
			}
			times.add(estatistica);
		}
		return times;
	}

	public List<String> getTimesRebaixados() {
		List<String> times = new ArrayList<String>();
		String estatistica = "";
		for (int i = 17; i <= 20; i++) {
			estatistica = "";
			estatistica = selenium.getTextTableCell("classificationTable", i, 1);
			estatistica = estatistica.replace("\n", " - ");
			times.add(estatistica);
		}
		return times;
	}

	public String getClassificacaoCorinthians() {
		String posicao = "";
		String time = "";
		String numeroPontos = "";
		for (int i = 1; i <= 20; i++) {
			time = selenium.getTextTableCell("classificationTable", i, 1).replace("\n", "").replaceAll("[0-9]", "");
			if (time.equals("Corinthians")) {
				posicao = selenium.getTextTableCell("classificationTable", i, 1).replace("\n", "").replaceAll("[^0-9]", "");
				numeroPontos = selenium.getTextTableCell("classificationTable", i, 2).replace("\n", "");
				return "O Corinthians esta na " + posicao + "º posição com " + numeroPontos + " pontos.";
			}
		}
		return "O Corinthians não esta na lista de classificados do campeonato.";
	}

	public boolean verificaCAPPrimeiraDivisao() {
		for (int i = 1; i <= 20; i++) {
			String time = selenium.getTextTableCell("classificationTable", i, 1).replace("\n", "").replaceAll("[0-9]", "");
			if (time.equals("Atlético-PR")) {
				return true;
			}
		}
		return false;
	}

	public List<String> getListaArtilheiros() {
		selenium.clickByLinkText("Artilharia");		
		List<String> times = new ArrayList<String>();
		for (int i = 1; i <= 20; i++) {
			String nomeJogador = selenium.getTextTableCell("marcados", i, 3);
			if (!nomeJogador.equals("JOGADOR"))
				times.add(nomeJogador);
		}
		return times;
	}

	public void abrir() {
		selenium.driver.get(URL);
		selenium.assertTitle("Classificação do Campeonato Brasileiro 2012 de Futebol - UOL Esporte");
	}
}
