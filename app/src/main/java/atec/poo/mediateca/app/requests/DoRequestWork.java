package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;
import atec.poo.ui.exceptions.DialogException;


/**
 * Conforme enunciado
 * 4.4.1. Rquisitar uma obra
 */
public class DoRequestWork extends Comando<LibraryManager> {


    /**
     * @param receiver
     */
    public DoRequestWork(LibraryManager receiver) {
        super(receiver, Label.REQUEST_WORK);

    }


    @Override
    public final void executar() throws DialogException {
        ui.escreveLinha("---> Requisitar Obra");// A apagar. Só indicativo
    }
}
