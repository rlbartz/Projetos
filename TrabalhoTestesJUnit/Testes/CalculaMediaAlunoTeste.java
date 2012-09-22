import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class CalculaMediaAlunoTeste {

	@Test
	public void testaCalculoDeMedia(){
		CalculaMediaAluno calcMedia = new CalculaMediaAluno();
		float media = calcMedia.caclularMedia(15, 15, 10, 30);
		assertEquals(17.5, media, 0);
	}
	
	@Test
	public void testaSituacaoAlunoAprovadoNota70(){
		CalculaMediaAluno calcMedia = new CalculaMediaAluno();
		String situacao = calcMedia.verificaStuacaoAluno(70);
		assertEquals("Aprovado", situacao);
	}
	
	@Test
	public void testaSituacaoAlunoAprovadoNotaMaior70(){
		CalculaMediaAluno calcMedia = new CalculaMediaAluno();
		String situacao = calcMedia.verificaStuacaoAluno(70.1f);
		assertEquals("Aprovado", situacao);
	}
	
	@Test
	public void testaSituacaoAlunoEmRecuperacaoNota50(){
		CalculaMediaAluno calcMedia = new CalculaMediaAluno();
		String situacao = calcMedia.verificaStuacaoAluno(50.0f);
		assertEquals("Em recuperação", situacao);
	}
	
	
	@Test
	public void testaSituacaoAlunoEmRecuperacaoNotaMenor70(){
		CalculaMediaAluno calcMedia = new CalculaMediaAluno();
		String situacao = calcMedia.verificaStuacaoAluno(69.9f);
		assertEquals("Em recuperação", situacao);
	}
	
	@Test
	public void testaSituacaoAlunoReprovado(){
		CalculaMediaAluno calcMedia = new CalculaMediaAluno();
		String situacao = calcMedia.verificaStuacaoAluno(49.99f);
		assertEquals("Reprovado", situacao);
	}
}
