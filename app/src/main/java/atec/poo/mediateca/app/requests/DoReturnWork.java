package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;
import atec.poo.ui.exceptions.DialogException;

/**
 * 4.4.2. Return a work.
 */
public class DoReturnWork extends Comando<LibraryManager> {

    /**
     * @param receiver
     */
    public DoReturnWork(LibraryManager receiver) {
        super(receiver, Label.RETURN_WORK);
    }

    @Override
    public final void executar() throws DialogException {
        ui.escreveLinha("---> Entregar Obra");// A apagar. Só indicativo
    }

}
