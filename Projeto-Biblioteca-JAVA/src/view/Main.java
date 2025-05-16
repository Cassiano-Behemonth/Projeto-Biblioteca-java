package view;

import controller.BibliotecaController;
import utils.PreCargaDados;

public class Main {
    public static void main(String[] args) {
        BibliotecaController controller = new BibliotecaController();
        PreCargaDados.carregar(controller);

        MenuConsole menu = new MenuConsole(controller);
        menu.exibir();
    }
}