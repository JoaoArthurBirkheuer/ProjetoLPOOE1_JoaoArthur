# Projeto Biblioteca - LPOO

Este projeto é um sistema de gerenciamento de biblioteca desenvolvido para a disciplina de **Linguagem de Programação Orientada a Objetos (LPOO)**. Ele permite o cadastro e a administração de usuários, funcionários, livros e empréstimos utilizando **Java**, **JPA** (Java Persistence API) e o banco de dados **PostgreSQL**. Este sistema demonstra o uso de herança, associações e persistência de dados em um contexto de gerenciamento de biblioteca.

## Estrutura do Projeto

O projeto utiliza **Apache Maven** para gerenciamento de dependências e estrutura. As configurações do banco de dados e o mapeamento de entidades são realizados com JPA, integrando o sistema ao PostgreSQL.

### Principais Dependências

O arquivo `pom.xml` inclui as dependências necessárias para o funcionamento do projeto:

- **javax.persistence**: Biblioteca para uso de JPA na persistência de entidades e transações.
- **org.postgresql.Driver**: Driver JDBC para conexão ao banco de dados PostgreSQL.

## Configuração do Banco de Dados

O projeto está configurado para utilizar um banco de dados PostgreSQL chamado `BibliotecaLPOO`. As configurações de conexão são definidas no arquivo `persistence.xml`, localizado em `src/main/resources/META-INF`.

```xml
<persistence version="2.2" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence           http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
  <persistence-unit name="BibliotecaLPOO" transaction-type="RESOURCE_LOCAL">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <properties>
      <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/ProjetoLPOOE1_JoaoArthur"/>
      <property name="javax.persistence.jdbc.user" value="postgres"/>
      <property name="hibernate.hbm2ddl.auto" value="create"/>
      <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
      <property name="javax.persistence.jdbc.password" value="jb12"/>
      <property name="javax.persistence.schema-generation.database.action" value="drop-and-create"/>
      <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
      <property name="hibernate.show_sql" value="true"/>
    </properties>
  </persistence-unit>
</persistence>
```

# Estrutura de Classes e Relacionamentos - Biblioteca

Este projeto utiliza **herança**, **associações** e o mapeamento ORM com JPA/Hibernate para modelar as entidades e suas interações em uma biblioteca.

![image](https://github.com/user-attachments/assets/43230b93-e437-4b1b-9fe4-55e5eb626559)

## Classes Principais

### **Pessoa** (`@MappedSuperclass`)
- Classe abstrata que serve como base para `Usuario` e `Funcionario`.
- **Não gera uma tabela própria no banco de dados**, pois utiliza a estratégia `InheritanceType.TABLE_PER_CLASS`. 
- Atributos comuns, herdados por `Usuario` e `Funcionario`:
  - `idPessoa`: identificador gerado automaticamente com `@SequenceGenerator`.
  - `nome`: obrigatório, limitado a 50 caracteres.
  - `cpf`: único, obrigatório, limitado a 11 caracteres.
  - `email`: único, obrigatório, limitado a 30 caracteres.
  - `ehFuncionario`: booleano que indica se a pessoa é um funcionário.

### **Usuario**
- Representa um usuário comum da biblioteca.
- Possui uma relação de um-para-muitos com `Emprestimo`, permitindo que um usuário tenha múltiplos empréstimos registrados.

### **Funcionario**
- Representa um funcionário da biblioteca.
- Além dos atributos herdados de `Pessoa`, possui:
  - `cargo`: obrigatório e único.
- Também pode realizar avaliações de livros.

### **Livro**
- Representa os livros disponíveis na biblioteca.
- Atributos:
  - `idLivro` (chave primária).
  - `titulo` (máximo de 100 caracteres).
  - `autor` (máximo de 50 caracteres).
- Possui uma lista de avaliações (`avaliacoes`) relacionadas ao livro.

### **Emprestimo**
- Representa um empréstimo realizado.
- Atributos:
  - `idEmprestimo` (chave primária).
  - `dataEmprestimo` e `dataDevolucao`.
- Relacionamentos:
  - Está associado a um `Usuario` ou a um `Funcionario`.
  - Pode conter múltiplos `Livro`, estabelecendo uma relação muitos-para-muitos.

### **Avaliacao**
- Representa as avaliações que podem ser feitas sobre um livro.
- Atributos:
  - `idAvaliacao` (chave primária).
  - `descricao`: texto da avaliação.
  - `nota`: uma nota de 0 a 10 (float).
  - `idLivro`: ID do livro avaliado.
  - `idPessoa`: ID da pessoa (usuário ou funcionário) que fez a avaliação.

---

## Relacionamentos e Mapeamento

### **Pessoa e Avaliacao**
- Uma `Pessoa` (seja `Usuario` ou `Funcionario`) pode fazer várias avaliações.
- Relacionamento de um-para-muitos entre `Pessoa` e `Avaliacao`.

### **Pessoa e Emprestimo**
- Um `Usuario` ou `Funcionario` pode ter vários empréstimos registrados.
- Relacionamento de um-para-muitos.

### **Livro e Avaliacao**
- Um livro pode ter várias avaliações.
- Relacionamento de um-para-muitos entre `Livro` e `Avaliacao`.

### **Livro e Emprestimo**
- Relacionamento muitos-para-muitos.
- Um `Emprestimo` pode incluir vários `Livro`, e cada `Livro` pode estar em múltiplos `Emprestimos`.

---

## Estrutura de Tabelas

As tabelas geradas pelo JPA representam as entidades concretas e suas relações:

- **`tb_usuario`**:
  - Representa os usuários da biblioteca.
  - Contém atributos herdados de `Pessoa`, como `idPessoa`, `nome`, `cpf` e `email`.

- **`tb_funcionario`**:
  - Representa os funcionários da biblioteca.
  - Contém atributos herdados de `Pessoa` e o campo adicional `cargo`.

- **`tb_livro`**:
  - Representa os livros disponíveis na biblioteca.
  - Contém atributos como `idLivro`, `titulo` e `autor`.

- **`tb_avaliacao`**:
  - Registra as avaliações realizadas.
  - Contém os campos `idAvaliacao`, `descricao`, `nota`, `idLivro` e `idPessoa`.

- **`tb_emprestimo`**:
  - Registra os empréstimos.
  - Contém os campos `idEmprestimo`, `dataEmprestimo` e `dataDevolucao`.

- **`tb_emprestimo_livro`**:
  - Tabela associativa para a relação muitos-para-muitos entre `Emprestimo` e `Livro`.

---

- **`tb_emprestimo`**:
  - Registra os empréstimos.
  - Contém os campos `idEmprestimo`, `dataEmprestimo` e `dataDevolucao`.

- **`tb_emprestimo_livro`**:
  - Tabela associativa para a relação muitos-para-muitos entre `Emprestimo` e `Livro`.
