package com.mycompany.projetolpooe1_joaoarthur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Set;

public class ProjetoLPOOE1_JoaoArthur {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaLPOO");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Adicionar Usuários
            Usuario usuario1 = new Usuario("Nome 1", "12345678900", "usuario1@example.com");
            em.persist(usuario1);

            Usuario usuario2 = new Usuario("Nome 2", "98765432100", "usuario2@example.com");
            em.persist(usuario2);

            // Adicionar Funcionários
            Funcionario funcionario1 = new Funcionario("Funcionario 1", "11122233300", "funcionario1@example.com");
            em.persist(funcionario1);

            Funcionario funcionario2 = new Funcionario("Funcionario 2", "44455566600", "funcionario2@example.com");
            em.persist(funcionario2);
            
            //Inserindo Funcionário aqui para testar id
            Usuario usuario3 = new Usuario("Nome 3","02039627022","joao@gmail.com");
            em.persist(usuario3);

            // Adicionar Livros
            Livro livro1 = new Livro();
            livro1.setTitulo("Java 1");
            livro1.setAutor("Autor A");
            em.persist(livro1);

            Livro livro2 = new Livro();
            livro2.setTitulo("Java 2");
            livro2.setAutor("Autor B");
            em.persist(livro2);

            Livro livro3 = new Livro();
            livro3.setTitulo("Java 3");
            livro3.setAutor("Autor C");
            em.persist(livro3);

            // Adicionar Empréstimos Exemplos
            Emprestimo emprestimo1 = new Emprestimo();
            emprestimo1.setUsuario(usuario1);
            emprestimo1.setDataEmprestimo(LocalDate.now());
            emprestimo1.setDataDevolucao(LocalDate.now().plusDays(14));
            emprestimo1.setLivrosEmprestados(Set.of(livro1, livro2));
            em.persist(emprestimo1);

            Emprestimo emprestimo2 = new Emprestimo();
            emprestimo2.setFuncionario(funcionario2);
            // Campo nao permitido para ser null
            emprestimo2.setDataEmprestimo(LocalDate.now().minusDays(10));
            // Campo permitido para ser null
            // emprestimo2.setDataDevolucao(LocalDate.now().plusDays(4));
            emprestimo2.setLivrosEmprestados(Set.of(livro3));
            em.persist(emprestimo2);
            
            // Tenta encontrar usuario de código 6 (6L == 6 long)
            // Para outras consultas, precisa de query personalizada
            Usuario usuario6 = em.find(Usuario.class, 6L);
            if (usuario6 == null) {
            System.out.println("Usuário com ID 6 não existe.");
            }  
            else {
            em.remove(usuario6);
            System.out.println("Usuário com ID 6 removido com sucesso.");
            }

            // Outro teste
            em.find(Usuario.class, 2L);
            if (usuario2 == null) {
            System.out.println("Usuário com ID 2 não existe.");
            } 
            else {
            em.remove(usuario2);
            System.out.println("Usuário com ID 2 removido com sucesso.");
            }
            
            em.getTransaction().commit();

            System.out.println("Registros de teste adicionados com sucesso!");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                System.out.println("Problema na transação, revertendo...\n\n\n");
                em.getTransaction().rollback();
            }
            // Para observar log de erros
            // e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
