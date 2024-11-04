# ProjetoLPOOE1_JoaoArthur
Projeto da Disciplina LPOO com meu tema escolhido, utilizando DB PostgreSQL, JPA e JDBC

Abaixo, segue diagrama para orientação das conexões entre as entidades do projeto.

![diagramaLPOO](https://github.com/user-attachments/assets/23075a98-3dd6-49aa-8a77-c7a42d55965e)

*** Explicação das Relações ***

--> Pessoa é uma classe abstrata contendo os atributos comuns (nome, cpf, email, ehFuncionario).

--> Usuario e Funcionario herdam de Pessoa, sendo Funcionario a única classe com o atributo adicional "cargo".

--> Emprestimo representa o empréstimo de livros, com atributos como dataEmprestimo, dataDevolucao e uma coleção livrosEmprestados.

--> A associação entre Emprestimo e Livro é de muitos para muitos, e entre Emprestimo e as classes Usuario ou Funcionario é de um para muitos.

