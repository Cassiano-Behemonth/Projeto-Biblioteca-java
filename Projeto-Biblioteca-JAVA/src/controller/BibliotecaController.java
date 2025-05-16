package controller;

import model.Emprestimo;
import model.Livro;
import model.Usuario;

import java.util.*;

public class BibliotecaController {
    private List<Livro> livros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Livro> pesquisarLivro(String termo) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(termo.toLowerCase()) ||
                livro.getAutor().toLowerCase().contains(termo.toLowerCase())) {
                resultado.add(livro);
            }
        }
        return resultado;
    }

    public void realizarEmprestimo(Emprestimo emprestimo) {
        if (emprestimo.getLivro().getExemplaresDisponiveis() > 0 &&
            emprestimo.getUsuario().getEmprestimos().isEmpty()) {
            emprestimos.add(emprestimo);
            emprestimo.getLivro().emprestar();
            emprestimo.getUsuario().adicionarEmprestimo(emprestimo);
        } else {
            System.out.println("Não foi possível realizar o empréstimo.");
        }
    }

    public void registrarDevolucao(Emprestimo emprestimo) {
        emprestimo.setDataDevolucao(new Date());
        emprestimo.getLivro().devolver();
        emprestimo.getUsuario().removerEmprestimo();
    }

    public List<Emprestimo> listarAtrasos() {
        List<Emprestimo> atrasados = new ArrayList<>();
        Date hoje = new Date();
        for (Emprestimo e : emprestimos) {
            if (e.getDataDevolucao() != null && e.getDataDevolucao().after(e.getDataPrevistaDevolucao())) {
                atrasados.add(e);
            }
        }
        atrasados.sort((a, b) -> b.diasDeAtraso() - a.diasDeAtraso());
        return atrasados;
    }

    public List<Livro> getLivros() { return livros; }
    public List<Usuario> getUsuarios() { return usuarios; }
    public List<Emprestimo> getEmprestimos() { return emprestimos; }
}