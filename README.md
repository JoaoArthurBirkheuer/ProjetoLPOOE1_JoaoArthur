# ProjetoLPOOE1_JoaoArthur
Projeto da Disciplina LPOO com meu tema escolhido, utilizando DB PostgreSQL, JPA e JDBC

Abaixo, segue diagrama para orientação das conexões entre as entidades do projeto.

![diagramaLPOO](https://github.com/user-attachments/assets/e2de7b2d-0452-44ca-bf01-2679336e8e8c)

--> Explicação das Relações:

Herança (Pessoa -> Usuario, Funcionario): Pessoa é uma superclasse abstrata que define propriedades comuns para Usuario e Funcionario.

Um para um (Emprestimo -> Funcionario/Usuario): Cada empréstimo é associado a um único funcionário ou usuário.

Muitos para muitos (Emprestimo -> Livro): Cada empréstimo pode incluir múltiplos livros, e cada livro pode estar em múltiplos empréstimos.

