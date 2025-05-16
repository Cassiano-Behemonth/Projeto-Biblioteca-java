package view;

import controller.BibliotecaController;
import model.*;

import java.util.*;

public class MenuConsole {
    private BibliotecaController controller;
    private Scanner sc = new Scanner(System.in);

    public MenuConsole(BibliotecaController controller) {
        this.controller = controller;
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n===== MENU BIBLIOTECA =====");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Buscar Livro");
            System.out.println("3. Cadastrar Usuário");
            System.out.println("4. Realizar Empréstimo");
            System.out.println("5. Registrar Devolução");
            System.out.println("6. Relatório de Atrasos");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> buscarLivro();
                case 3 -> cadastrarUsuario();
                case 4 -> emprestarLivro();
                case 5 -> devolverLivro();
                case 6 -> listarAtrasos();
            }
        } while (opcao != 0);
    }

    private void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Ano: ");
        int ano = sc.nextInt();
        sc.nextLine();
        System.out.print("Categoria: ");
        String categoria = sc.nextLine();
        System.out.print("Exemplares: ");
        int ex = sc.nextInt();
        sc.nextLine();

        controller.adicionarLivro(new Livro(titulo, autor, ano, categoria, ex));
    }

    private void buscarLivro() {
        System.out.print("Buscar por título/autor/categoria: ");
        String termo = sc.nextLine();
        controller.buscarLivro(termo).forEach(System.out::println);
    }

    private void cadastrarUsuario() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String tel = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Endereço: ");
        String end = sc.nextLine();
        controller.adicionarUsuario(new Usuario(nome, tel, email, end));
    }

    private void emprestarLivro() {
        System.out.print("ID do Livro: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nome do Usuário: ");
        String nome = sc.nextLine();

        Livro livro = controller.buscarLivro("").stream().filter(l -> l.getId() == id).findFirst().orElse(null);
        Usuario usuario = controller.listarEmprestimos().stream().map(Emprestimo::getUsuario).filter(u -> u.getNome().equalsIgnoreCase(nome)).findFirst().orElse(null);

        if (livro != null && usuario != null) {
            Date hoje = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(hoje);
            cal.add(Calendar.DATE, 7);
            Date devolucao = cal.getTime();

            controller.realizarEmprestimo(new Emprestimo(livro, usuario, hoje, devolucao));
        } else {
            System.out.println("Livro ou usuário não encontrado.");
        }
    }

    private void devolverLivro() {
        System.out.println("Lista de empréstimos:");
        List<Emprestimo> emps = controller.listarEmprestimos();
        for (int i = 0; i < emps.size(); i++) {
            System.out.println(i + ": " + emps.get(i));
        }
        System.out.print("Escolha o número do empréstimo: ");
        int idx = sc.nextInt();
        sc.nextLine();

        Emprestimo e = emps.get(idx);
        controller.registrarDevolucao(e, new Date());
    }

    private void listarAtrasos() {
        List<Emprestimo> atrasos = controller.listarAtrasos();
        for (Emprestimo e : atrasos) {
            System.out.println(e + " - Atraso: " + e.calcularDiasAtraso() + " dias");
        }
    }
}