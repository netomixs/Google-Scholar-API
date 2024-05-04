import controllers.AutorController;
import view.AutorView;

public class Main {
    public static void main(String[] args) {
        AutorController contolador = new AutorController();
        AutorView vista = new AutorView();
        vista.imprimirLista(contolador.obtenerAutores());
        contolador.actulizar();
        vista.imprimirLista(contolador.obtenerAutores());
    }
}