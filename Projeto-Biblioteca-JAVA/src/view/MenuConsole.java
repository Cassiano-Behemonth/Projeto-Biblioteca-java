package view;

import controllers.BibliotecaController;
import model.*;

import java.util.*;

public class MenuConsole {
    private Scanner scanner = new Scanner(System.in);
    private BibliotecaController controller;

    public MenuConsole(BibliotecaController controller) {
        this.controller = controller;
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Cadastrar Usuário");
            System.out.println("3. Pesquisar Livro");
            System.out.println("4. Realizar Empréstimo");
            System.out.println("5. Registrar Devolução");
            System.out.println("6. Relatório de Atrasos");
            System.out.println("0. Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> cadastrarUsuario();
                case 3 -> pesquisarLivro();
                case 4 -> realizarEmprestimo();
                case 5 -> registrarDevolucao();
                case 6 -> mostrarAtrasos();
            }
        } while (opcao != 0);
    }

    private void cadastrarLivro() {
        System.out.println("Título:");
        String titulo = scanner.nextLine();
        System.out.println("Autor:");
        String autor = scanner.nextLine();
        System.out.println("Ano:");
        int ano = scanner.nextInt(); scanner.nextLine();
        System.out.println("Categoria:");
        String categoria = scanner.nextLine();
        System.out.println("Total de exemplares:");
        int total = scanner.nextInt(); scanner.nextLine();

        controller.adicionarLivro(new Livro(titulo, autor, ano, categoria, total));
        System.out.println("Livro cadastrado!");
    }

    private void cadastrarUsuario() {
        System.out.println("Nome:");
        String nome = scanner.nextLine();
        System.out.println("Telefone:");
        String tel = scanner.nextLine();
        System.out.println("Email:");
        String email = scanner.nextLine();
        System.out.println("Endereço:");
        String end = scanner.nextLine();

        controller.adicionarUsuario(new Usuario(nome, tel, email, end));
        System.out.println("Usuário cadastrado!");
    }

    private void pesquisarLivro() {
        System.out.println("Digite o título ou autor:");
        String termo = scanner.nextLine();
        List<Livro> resultado = controller.pesquisarLivro(termo);
        resultado.forEach(System.out::println);
    }

    private void realizarEmprestimo() {
        System.out.println("ID do usuário:");
        int idUser = scanner.nextInt();
        System.out.println("ID do livro:");
        int idLivro = scanner.nextInt(); scanner.nextLine();

        Usuario usuario = controller.getUsuarios().stream().filter(u -> u.getId() == idUser).findFirst().orElse(null);
        Livro livro = controller.getLivros().stream().filter(l -> l.getId() == idLivro).findFirst().orElse(null);

        if (usuario != null && livro != null) {
            Date hoje = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(hoje);
            cal.add(Calendar.DATE, 7);
            controller.realizarEmprestimo(new Emprestimo(livro, usuario, hoje, cal.getTime()));
            System.out.println("Empréstimo realizado!");
        } else {
            System.out.println("Usuário ou livro não encontrado.");
        }
    }

    private void registrarDevolucao() {
        System.out.println("ID do usuário:");
        int idUser = scanner.nextInt(); scanner.nextLine();
        Usuario usuario = controller.getUsuarios().stream().filter(u -> u.getId() == idUser).findFirst().orElse(null);
        if (usuario != null && usuario.getEmprestimos() != null) {
            controller.registrarDevolucao(usuario.getEmprestimos());
            System.out.println("Devolução registrada!");
        } else {
            System.out.println("Usuário não possui empréstimos.");
        }
    }

    private void mostrarAtrasos() {
        List<Emprestimo> atrasos = controller.listarAtrasos();
        atrasos.forEach(e -> {
            System.out.println(e + " - Atraso: " + e.diasDeAtraso() + " dias");
        });
    }
}