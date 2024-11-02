# ProjetoLPOOE1_JoaoArthur
Projeto da Disciplina LPOO com meu tema escolhido, utilizando DB PostgreSQL, JPA e JDBC

Abaixo, segue diagrama para orientação das conexões entre as entidades do projeto.
![diagramaLPOO](https://github.com/user-attachments/assets/d78f1de3-b24a-41a6-92d6-773dc5997da6)

Explicação das Relações:
--> Funcionario herda de Usuario, indicando que todos os atributos de Usuario estão disponíveis para Funcionario.
--> Emprestimo possui uma relação de um-para-um com Funcionario e Usuario, significando que cada empréstimo está associado a um funcionário ou a um usuário específico.
--> Emprestimo possui uma relação de muitos-para-muitos com Livro, o que permite que um empréstimo contenha múltiplos livros e um livro esteja em múltiplos empréstimos.

