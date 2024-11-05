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

## Estrutura das Classes e Relacionamentos

![diagramaLPOO](https://github.com/user-attachments/assets/23075a98-3dd6-49aa-8a77-c7a42d55965e)

O projeto é estruturado em classes que utilizam herança e associações para modelar as entidades e suas interações:

### Classes Principais

- **Pessoa** (`@MappedSuperclass`): Classe abstrata que serve como base para `Usuario` e `Funcionario`, contendo atributos comuns como `idPessoa`, `nome`, `cpf` e `email`. O identificador `idPessoa` é gerado automaticamente utilizando uma sequência com `@SequenceGenerator`.

- **Usuario**: Representa um usuário comum da biblioteca que pode realizar empréstimos. Possui uma relação de um para muitos com `Emprestimo`, permitindo que um usuário tenha múltiplos empréstimos registrados.

- **Funcionario**: Representa um funcionário da biblioteca. Além dos atributos herdados, possui um campo adicional `cargo`, que é único e obrigatório.

- **Livro**: Representa os livros disponíveis na biblioteca, com atributos `idLivro`, `titulo` (limitado a 100 caracteres) e `autor` (limitado a 50 caracteres).

- **Emprestimo**: Representa um empréstimo realizado. Contém atributos como `idEmprestimo`, `dataEmprestimo` e `dataDevolucao`. Um `Emprestimo` pode estar associado a um `Usuario` ou a um `Funcionario`, bem como a múltiplos `Livro`, estabelecendo uma relação de muitos-para-muitos.

### Relacionamentos

- **Pessoa e Emprestimo**: A relação entre `Pessoa` (que inclui `Usuario` e `Funcionario`) e `Emprestimo` é de um para muitos. Um `Usuario` ou `Funcionario` pode ter vários `Emprestimos` registrados, representando a associação `@OneToMany`.

- **Livro e Emprestimo**: A relação entre `Livro` e `Emprestimo` é de muitos para muitos. Cada `Emprestimo` pode incluir vários `Livros`, e cada `Livro` pode estar em múltiplos `Emprestimos`, utilizando uma tabela associativa `@ManyToMany`.

## Estrutura de Tabelas

Após a configuração, as tabelas geradas pelo JPA representam as entidades e suas relações:

- **tb_pessoa**: Contém informações de `Pessoa` com `idPessoa` como chave primária.
- **tb_usuario**: Herdada de `tb_pessoa`, contém usuários.
- **tb_funcionario**: Herdada de `tb_pessoa`, contém funcionários, incluindo o campo `cargo`.
- **tb_livro**: Representa os livros da biblioteca.
- **tb_emprestimo**: Registra os empréstimos, associados a `Usuario` ou `Funcionario`.
- **tb_emprestimo_livro**: Tabela associativa para a relação muitos-para-muitos entre `Emprestimo` e `Livro`.
