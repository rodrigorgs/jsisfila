# JSISFILA

O JSISFILA é uma biblioteca em Java para gerenciar filas de atendimento de alunos na Universidade Federal da Bahia (UFBA). Ele foi criado com o propósito de servir de código base para exercícios de programação.

## Requisitos

- O JSISFILA atende a apenas um colegiado
- O colegiado é identificado unicamente por uma string formada por exclusivamente por letras maiúsculas (ex.: BCC, BSI, LC...), e por um nome (obrigatório)
    - O sistema deve permitir que o usuário digite letras minúsculas, mas estas devem ser convertidas para maiúsculas
- O colegiado possui alunos
- Todo aluno possui um número de matrícula, que é um número de 9 dígitos que o identifica unicamente
- Durante a matrícula presencial, o aluno pode digitar seu número de matrícula em um telão, para entrar no final da fila de atendimento
  - Se o número de matrícula for inválido, o sistema deve informar que a matrícula é inválida
  - Se o número de matrícula for válido, mas não estiver na lista do colegiado, o sistema deve informar que não está na lista do colegiado
  - Se o aluno estiver sendo atendido no momento, ele não pode entrar na fila (o sistema deve informar que ele já está sendo atendido)
  - Se o aluno já estiver na fila e não tiver sido atendido ainda, ele não pode entrar na fila novamente
- Pode-se consultar a posição na fila de qualquer pessoa através de seu número de matrícula. Possíveis resultados:
  - "Está na sua vez"
  - "Você é o Xº da fila"
  - "Você já foi atendido"
  - "Você não está na fila"
- O sistema deve informar a quantidade de atendimentos realizados (ou em andamento) e o número de atendimentos a serem realizados.
- O sistema deve informar o total de alunos atendidos.
- O colegiado pode ver quem é o aluno atual na fila e chamar o próximo aluno da fila
