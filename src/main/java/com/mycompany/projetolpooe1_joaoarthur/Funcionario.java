package com.mycompany.projetolpooe1_joaoarthur;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario extends Pessoa {
    public Funcionario(String nome, String cpf, String email) {
        super(nome, cpf, email, true);
    }

    public Funcionario() {
        super();
    }
}
