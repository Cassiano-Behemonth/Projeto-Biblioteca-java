package controller;

import model.*;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<Livro> buscarLivro(String termo) {
        return livros.stream().filter(l ->
            l.getTitulo().equalsIgnoreCase(termo) ||
            l.getAutor().equalsIgnoreCase(termo) ||
            l.getCategoria().equalsIgnoreCase(termo)
        ).collect(Collectors.toList());
    }

    public void realizarEmprestimo(Emprestimo e) {
        if (e.getLivro().getExemplaresDisponiveis() > 0) {
            emprestimos.add(e);
            e.getLivro().emprestar();
        } else {
            System.out.println("Sem exemplares disponíveis.");
        }
    }

    public void registrarDevolucao(Emprestimo e, Date data) {
        e.registrarDevolucao(data);
        e.getLivro().devolver();
    }

    public List<Emprestimo> listarEmprestimos() {
        return emprestimos;
    }

    public List<Emprestimo> listarAtrasos() {
        return emprestimos.stream()
            .filter(e -> e.getDataDevolucao() != null && e.calcularDiasAtraso() > 0)
            .sorted((a, b) -> Long.compare(b.calcularDiasAtraso(), a.calcularDiasAtraso()))
            .collect(Collectors.toList());
    }
}