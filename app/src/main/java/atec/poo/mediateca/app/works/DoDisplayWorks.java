package atec.poo.mediateca.app.works;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;

/**
 * 4.3.2. Listar Obras
 */
public class DoDisplayWorks extends Comando<LibraryManager> {

    /**
     * @param receiver
     */
    public DoDisplayWorks(LibraryManager receiver) {
        super(receiver, Label.SHOW_WORKS);
    }

    @Override
    public final void executar() {
        ui.escreveLinha("---> Mostrar Obras");// A apagar. Só indicativo
    }
}