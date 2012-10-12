package com.andre.selenium.testes.cotacao;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import com.andre.selenium.SeleniumTest;
import com.andre.selenium.SeleniumWebDriver;
import com.andre.selenium.dominio.Moeda;
import com.andre.selenium.telas.cotacao.TelaCotacoesUOL;
import com.andre.selenium.telas.cotacao.TelaHistoricoMoeda;

public class CotacaoMoedasTeste extends SeleniumTest {

	TelaCotacoesUOL telaCotacoes;
	SeleniumWebDriver selenium;

	@Before
	public void prepararCotacoes() {
		this.selenium = new SeleniumWebDriver(driver);
		this.telaCotacoes = new TelaCotacoesUOL(selenium);
		this.telaCotacoes.abrir();
	}

	/**
	 * Compara a cota��o do Dolar, Peso e Euro e verifica se o Euro � a que
	 * possui a cota��o mais alta.
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testeCotacaoDiariaDolarPesoEuro() throws ParseException {
		Moeda peso = telaCotacoes.consultarCotacaoPesoArgentino();
		Moeda dolar = telaCotacoes.consultarCotacaoDolarComercial();
		Moeda euro = telaCotacoes.consultarCotacaoEuro();
		assertTrue(euroCotacaoMaisAlta(peso, dolar, euro));
	}

	private boolean euroCotacaoMaisAlta(Moeda peso, Moeda dolar, Moeda euro)
			throws ParseException {

		NumberFormat nf = NumberFormat.getInstance(Locale.US);
		float vendaPeso = nf.parse(peso.getVenda()).floatValue();
		float vendaDolar = nf.parse(dolar.getVenda()).floatValue();
		float vendaEuro = nf.parse(euro.getVenda()).floatValue();

		if ((vendaEuro > vendaPeso) && (vendaEuro > vendaDolar))
			return true;
		else
			return false;
	}

	/**
	 * Verifica a cota��o do d�lar comercial (Venda) mais baixa e mais alta dos
	 * �ltimos 50 dias
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testeCotacaoMaisBaixaAltaDolarUltimos50dias() {
		telaCotacoes.consultarCotacaoDolarComercial();
		TelaHistoricoMoeda historicoMoeda = new TelaHistoricoMoeda(selenium);
		ArrayList cotacaoMaisBaixaMaisAlta = historicoMoeda
				.getCotacaoMoedaMaisBaixaMaisAlta();

		selenium.takeScreenshot();

		assertEquals("14/09/2012 2,0111", cotacaoMaisBaixaMaisAlta.get(0));
		assertEquals("02/08/2012 2,0507", cotacaoMaisBaixaMaisAlta.get(1));
		System.out.println("Cota��o mais BAIXA: "
				+ cotacaoMaisBaixaMaisAlta.get(0));
		System.out.println("Cota��o mais ALTA:  "
				+ cotacaoMaisBaixaMaisAlta.get(1));
	}

	/**
	 * Dado que a cota para viagens para o exterior � de $500. Consulte a cota
	 * em R$ (reais) utilizando o "Conversor de Moedas"
	 */
	@Test
	public void testeConsultaLimiteCotaViagemExteriorEmReais() {
		float resultado = telaCotacoes.converterMoeda("Estados Unidos - D�lar Americano", "Brasil - Real", 1000.00f);
		System.out.println("Cota��o para viagem = "+resultado);
	}
}
