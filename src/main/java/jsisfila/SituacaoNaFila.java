package jsisfila;

public enum SituacaoNaFila {
	  JA_FOI_ATENDIDO {
		@Override
		public String toString() {
			return "O aluno já foi atendido";
		}
	  },
	  EM_ATENDIMENTO {
			@Override
			public String toString() {
				return "O aluno está sendo atendido";
			}
		  },
	  AGUARDANDO_ATENDIMENTO  {
			@Override
			public String toString() {
				return "O aluno está na fila aguardando ser atendido";
			}
		  },
	  NAO_ESTA_NA_FILA {
			@Override
			public String toString() {
				return "O aluno não está na fila de atendimento";
			}
		  },
}
