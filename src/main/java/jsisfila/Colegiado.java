package jsisfila;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Colegiado {
	String codigo;
	String nome;
	Map<String, Aluno> alunos = new HashMap<>();
	
	List<Aluno> fila = new ArrayList<>();
	int posicao = -1;
	

	public Colegiado() {
	}
	
	public Colegiado(String codigo, String nome) {
		setCodigo(codigo);
		setNome(nome);
	}

	/**
	 * Vincula aluno a este colegiado
	 * @param aluno
	 */
	public void insereAluno(Aluno aluno) {
//		if (aluno != null && aluno.getMatricula() != null) {
			alunos.put(aluno.getMatricula(), aluno);
			aluno.setColegiado(this);
//		}
	}
	/**
	 * Retorna o aluno vinculado ao colegiado que possui a matrícula fornecida
	 * @param matricula Número de matrícula do aluno
	 * @return Objeto aluno ou null se não encontrar
	 */
	public Aluno findAluno(String matricula) {
		return alunos.get(matricula);
	}
	
	/**
	 * @return true se o código do colegiado é válido,
	 * ou false caso contrário
	 */
	public boolean isValido() {
		return !StringUtils.isEmpty(this.codigo) &&
				!StringUtils.isEmpty(this.nome) &&
				this.codigo.matches("^[A-Z]+$");
	}
	
	/**
	 * Indica a situação de um aluno na fila de atendimento
	 * @param matricula Número de matrícula do aluno
	 * @return
	 */
	public SituacaoNaFila situacaoNaFila(String matricula) {
		int pos = posicaoAluno(matricula);
		if (pos == 0) {
			return SituacaoNaFila.EM_ATENDIMENTO;
		} else if (pos > 0) {
			return SituacaoNaFila.AGUARDANDO_ATENDIMENTO;
		} else {
			for (int i = 0; i < posicao; i++) {
				String m = fila.get(i).getMatricula();
				if (m.equals(matricula)) {
					return SituacaoNaFila.JA_FOI_ATENDIDO;
				}
			}
			return SituacaoNaFila.NAO_ESTA_NA_FILA;
		}
	}

	/**
	 * Adiciona aluno no final da fila
	 * @param matricula
	 * @throws EnfileiraException O aluno não puder ser adicionado à fila;
	 * use getSituacao() para saber a causa.
	 * @throws IllegalArgumentException O número de matrícula é inválido
	 * @throws NotFoundException O aluno não está vinculado a um colegiado
	 */
	public void enfileira(String matricula) throws EnfileiraException, IllegalArgumentException, NotFoundException {
		if (!(new Aluno("asd", matricula).isValido())) {
			throw new IllegalArgumentException();
		}
		Aluno aluno = findAluno(matricula);
		if (aluno == null) {
			throw new NotFoundException();
		}
		SituacaoNaFila situacao = situacaoNaFila(matricula);
		if (situacao == SituacaoNaFila.EM_ATENDIMENTO || situacao == SituacaoNaFila.AGUARDANDO_ATENDIMENTO) {
			throw new EnfileiraException(situacao);
		}
		
		fila.add(aluno);
	}
	/**
	 * Indica se a fila está vazia, i.e., se todos os alunos que estavam
	 * na fila já foram atendidos.
	 * @return
	 */
	public boolean filaVazia() {
		return fila.isEmpty() || posicao >= fila.size() - 1;
	}
	/**
	 * Indica a posição de um aluno na fila de atendimento
	 * @param matricula Número de matrícula do aluno
	 * @return 0, se o aluno está sendo atendido;
	 * número maior que 0, se o aluno está aguardando atendimento;
	 * -1 se o aluno já foi atendido ou nunca entrou na fila.
	 */
	public int posicaoAluno(String matricula) {
		if (posicao >= fila.size()) {
			return -1;
		}
		if (posicao >= 0 && fila.get(posicao).getMatricula().equals(matricula)) {
			return 0;
		}
		
		for (int i = posicao + 1; i < fila.size(); i++) {
			String m = fila.get(i).getMatricula();
			if (m.equals(matricula)) {
				return i - posicao;
			}
		}
		
		return -1;
	}
	/**
	 * Chama a próximo aluno da fila
	 * @return O aluno que era o próximo da fila ou
	 * null se todos já haviam sido atendidos
	 */
	public Aluno chamaProximo() {
		posicao++;
		return posicao < fila.size() ? fila.get(posicao) : null;
	}
	/**
	 * @return O aluno que está sendo atendido ou
	 * null se todos os alunos já foram atendidos.
	 */
	public Aluno alunoEmAtendimento() {
//		System.out.println("BRANCH: " + (posicao >= 0) + ", " + (posicao < fila.size()));
		if (posicao >= 0 && posicao < fila.size()) {
			return fila.get(posicao);
		} else {
			return null;
		}
	}

	/**
	 * @return Quantidade de alunos atendidos ou em atendimento
	 */
	public int totalAlunosAtendidos() {
		Set<String> alunos = new HashSet<>();
		for (int i = 0; i <= Math.min(posicao, fila.size() - 1); i++) {
			alunos.add(fila.get(i).getMatricula());
		}
		return alunos.size();
	}
	/**
	 * @return Quantidade de atendimentos realizados ou em andamento
	 */
	public int totalAtendimentosRealizados() {
		if (posicao < 0) {
			return 0;
		} else if (posicao < fila.size()) {
			return posicao + 1;
		} else {
			return posicao;
		}
	}
	/**
	 * @return Quantidade de alunos na fila aguardando atendimento
	 */
	public int totalAtendimentosFuturos() {
		if (posicao < 0) {
			return fila.size();
		} else if (posicao < fila.size()) {
			return fila.size() - posicao - 1;
		} else {
			return 0;
		}
	}
	
	///////// getters e setters //////////////
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
		if (this.codigo != null) {
			this.codigo = this.codigo.toUpperCase();
		}
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
