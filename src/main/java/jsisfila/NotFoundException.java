package jsisfila;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Não encontrado.";
	}
}
