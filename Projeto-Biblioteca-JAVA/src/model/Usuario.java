package model;

import java.util.*;

public class Usuario {
    private static int contador = 1;
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private Emprestimo emprestimo;

    public Usuario(String nome, String telefone, String email, String endereco) {
        this.id = contador++;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public void adicionarEmprestimo(Emprestimo e) {
        this.emprestimo = e;
    }

    public void removerEmprestimo() {
        this.emprestimo = null;
    }

    public Emprestimo getEmprestimos() {
        return emprestimo;
    }

    public String getNome() { return nome; }
    public int getId() { return id; }
    @Override
    public String toString() {
        return id + " - " + nome;
    }
}