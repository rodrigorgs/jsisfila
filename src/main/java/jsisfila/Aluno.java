package jsisfila;

public class Aluno {
	String nome;
	String matricula;
	Colegiado colegiado = null;
	
	public Aluno() {
		
	}
	public Aluno(String nome, String matricula) {
		super();
		this.nome = nome;
		this.matricula = matricula;
	}
	public Colegiado getColegiado() {
		return colegiado;
	}
	public void setColegiado(Colegiado colegiado) {
		this.colegiado = colegiado;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;	
	}
	
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isValido() {
		return !StringUtils.isEmpty(matricula) &&
				matricula.matches("^[0-9]{9}$");
	}
}
