package model;

import java.util.*;

public class Emprestimo {
    private static int contador = 1;
    private int id;
    private Livro livro;
    private Usuario usuario;
    private Date dataEmprestimo;
    private Date dataPrevistaDevolucao;
    private Date dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario, Date dataEmprestimo, Date dataPrevistaDevolucao) {
        this.id = contador++;
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public void setDataDevolucao(Date data) {
        this.dataDevolucao = data;
    }

    public int diasDeAtraso() {
        if (dataDevolucao == null || !dataDevolucao.after(dataPrevistaDevolucao)) return 0;
        long diff = dataDevolucao.getTime() - dataPrevistaDevolucao.getTime();
        return (int)(diff / (1000 * 60 * 60 * 24));
    }

    public Livro getLivro() { return livro; }
    public Usuario getUsuario() { return usuario; }
    public Date getDataPrevistaDevolucao() { return dataPrevistaDevolucao; }
    public Date getDataDevolucao() { return dataDevolucao; }
    @Override
    public String toString() {
        return "Emprestimo ID " + id + " - " + livro.getTitulo() + " para " + usuario.getNome();
    }
}