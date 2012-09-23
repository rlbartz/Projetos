public class CalculaMediaAluno {

	public float caclularMedia(int notaPrimeiroBimestre,
			int notaSegundoBimestre, int notaTerceiroBimestre,
			int notaQuartoBimestre) {
		return (notaPrimeiroBimestre + notaSegundoBimestre
				+ notaTerceiroBimestre + notaQuartoBimestre) / 4.0f;
	}

	public String verificaStuacaoAluno(float media) {
		if (media < 70.0f && media >= 50.0f) {
			return "Em recuperação";
		}
		if (media >= 70) {
			return "Aprovado";
		}
		return "Reprovado";
	}

}
