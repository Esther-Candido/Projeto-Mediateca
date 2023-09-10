package atec.poo.mediateca.app.works;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;


/**
 * 4.3.1. Mostrar Obra.
 */
public class DoDisplayWork extends Comando<LibraryManager> {

    /**
     * @param receiver
     */
    public DoDisplayWork(LibraryManager receiver) {
        super(receiver, Label.SHOW_WORK);


    }


    @Override
    public final void executar() {
        ui.escreveLinha("---> Mostrar Obra");// A apagar. Só indicativo
    }

}

