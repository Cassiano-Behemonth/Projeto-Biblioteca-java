package model;

public class Livro {
    private static int contador = 1;
    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private String categoria;
    private int exemplaresDisponiveis;

    public Livro(String titulo, String autor, int ano, String categoria, int exemplares) {
        this.id = contador++;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.categoria = categoria;
        this.exemplaresDisponiveis = exemplares;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getExemplaresDisponiveis() {
        return exemplaresDisponiveis;
    }

    public void emprestar() {
        exemplaresDisponiveis--;
    }

    public void devolver() {
        exemplaresDisponiveis++;
    }

    @Override
    public String toString() {
        return id + ": " + titulo + " por " + autor + " [" + exemplaresDisponiveis + " disp.]";
    }
}