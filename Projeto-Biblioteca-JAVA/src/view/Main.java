//Cassiano henrique de oliveira RGM: 38029308
//Gustavo Henrique de Souza RGM: 38738261
//Samuel Nogueira de Lima RGM: 38660288

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
