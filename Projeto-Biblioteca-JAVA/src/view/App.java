package biblioteca;

import biblioteca.controller.BibliotecaController;
import biblioteca.utils.PreCargaDados;
import biblioteca.view.MenuConsole;

public class Main {
    public static void main(String[] args) {
        BibliotecaController controller = new BibliotecaController();
        PreCargaDados.carregar(controller);

        MenuConsole menu = new MenuConsole(controller);
        menu.exibir();
    }
}