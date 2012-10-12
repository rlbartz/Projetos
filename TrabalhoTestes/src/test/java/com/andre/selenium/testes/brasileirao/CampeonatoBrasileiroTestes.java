package com.andre.selenium.testes.brasileirao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.andre.selenium.SeleniumTest;
import com.andre.selenium.SeleniumWebDriver;
import com.andre.selenium.telas.brasileirao.TelaTabelaBrasileirao;

public class CampeonatoBrasileiroTestes extends SeleniumTest {

	SeleniumWebDriver selenium;
	TelaTabelaBrasileirao telaBrasileiro;

	@Before
	public void prepararBrasileiro() {
		this.selenium = new SeleniumWebDriver(driver);
		telaBrasileiro = new TelaTabelaBrasileirao(selenium);
		telaBrasileiro.abrir();
	}
	@Test
	public void testarEstatisticasTimesLibertadores(){
		List<String> times;
		times = telaBrasileiro.getEstatisticasTimeLibertadores();
		for (String time : times) {
			System.out.println(time);
		}
	}
	@Test
	public void testarTimesRebaixados(){
		List<String> times;
		times = telaBrasileiro.getTimesRebaixados();
		for (String time : times) {
			System.out.println(time);
		}
	}
	@Test
	public void testarPalmeirasRebaixado(){
		List<String> times;
		times = telaBrasileiro.getTimesRebaixados();
		for (String time : times) {
			if(time.contains("Palmeiras")){
				System.out.println("O palmeiras ser� rebaixado para libertadores!");
				return;
			}
		}
		System.out.println("O palmeiras n�o ser� rebaixado para libertadores!");
	} 
	
	@Test
	public void testarPosicaoCorinthiansBrasileir�o(){
		String resultado = telaBrasileiro.getClassificacaoCorinthians();
		System.out.println(resultado);
	}
	
	@Test
	public void testarCAPPrimeiraDivisao(){
		if (telaBrasileiro.verificaCAPPrimeiraDivisao()){
			System.out.println("O CAP est� listado entre os times da primeira divis�o!");
		}else{
			System.out.println("O CAP n�o est� listado entre os times da primeira divis�o!");
		}		
	}
	
	@Test
	public void testarArtilheiroBrasileirao(){
		List<String> artilheiros = telaBrasileiro.getListaArtilheiros();
		System.out.println("------------------ Lista de artilheiros -----------------");
		for (String artilheiro : artilheiros){
			System.out.println(artilheiro);
		}
		System.out.println("---------------------------------------------------------");
	}
}
