package model;

public class Livro {
    private static int contador = 1;
    private int id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String categoria;
    private int totalExemplares;
    private int exemplaresDisponiveis;

    public Livro(String titulo, String autor, int anoPublicacao, String categoria, int totalExemplares) {
        this.id = contador++;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.categoria = categoria;
        this.totalExemplares = totalExemplares;
        this.exemplaresDisponiveis = totalExemplares;
    }

    public void emprestar() {
        exemplaresDisponiveis--;
    }

    public void devolver() {
        exemplaresDisponiveis++;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public String getCategoria() { return categoria; }
    public int getExemplaresDisponiveis() { return exemplaresDisponiveis; }

    @Override
    public String toString() {
        return id + " - " + titulo + " por " + autor + " (" + exemplaresDisponiveis + "/" + totalExemplares + ")";
    }
}