package model;

import java.util.Date;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private Date dataEmprestimo;
    private Date dataPrevista;
    private Date dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario, Date dataEmprestimo, Date dataPrevista) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataPrevista;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void registrarDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public long calcularDiasAtraso() {
        if (dataDevolucao == null || !dataDevolucao.after(dataPrevista)) return 0;
        long diff = dataDevolucao.getTime() - dataPrevista.getTime();
        return diff / (1000 * 60 * 60 * 24);
    }

    @Override
    public String toString() {
        return livro.getTitulo() + " emprestado para " + usuario.getNome() +
                " em " + dataEmprestimo + " (previsto: " + dataPrevista + ")";
    }
}