package jsisfila;

public class EnfileiraException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private SituacaoNaFila situacao;

	public EnfileiraException(SituacaoNaFila situacao) {
		super();
		this.situacao = situacao;
	}

	public SituacaoNaFila getSituacao() {
		return situacao;
	}
	
	@Override
	public String getMessage() {
		return "Erro ao adicionar à fila: " + situacao.toString();
	}
}
