package com.mycompany.projetolpooe1_joaoarthur;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario extends Pessoa {
    
    @Column(nullable = false)
    private String cargo;
    public Funcionario(String nome, String cpf, String email) {
        super(nome, cpf, email, true);
    }
    public Funcionario() {
        super();
    }
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
