package utils;

import controller.BibliotecaController;
import model.*;
import java.util.*;

public class PreCargaDados {
    public static void carregar(BibliotecaController controller) {
        Livro l1 = new Livro("Dom Casmurro", "Machado de Assis", 1899, "Romance", 5);
        Livro l2 = new Livro("O Alienista", "Machado de Assis", 1882, "Novela", 3);

        Usuario u1 = new Usuario("Maria", "9999-9999", "maria@email.com", "Rua A");
        Usuario u2 = new Usuario("João", "8888-8888", "joao@email.com", "Rua B");

        controller.adicionarLivro(l1);
        controller.adicionarLivro(l2);
        controller.adicionarUsuario(u1);
        controller.adicionarUsuario(u2);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -5);
        Date emprestimo = cal.getTime();
        cal.add(Calendar.DATE, 7);
        Date devolucao = cal.getTime();

        Emprestimo e1 = new Emprestimo(l1, u1, emprestimo, devolucao);
        controller.realizarEmprestimo(e1);
    }
}
