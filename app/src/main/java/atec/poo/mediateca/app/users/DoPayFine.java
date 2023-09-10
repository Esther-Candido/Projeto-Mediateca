package atec.poo.mediateca.app.users;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;
import atec.poo.ui.exceptions.DialogException;

/**
 * Conforme enunciado
 * 4.2.5. Pagar Multa
 */
public class DoPayFine extends Comando<LibraryManager> {


    /**
     * @param receiver
     */
    public DoPayFine(LibraryManager receiver) {
        super(receiver, Label.PAY_FINE);

    }

    @Override
    public final void executar() throws DialogException {
        ui.escreveLinha("---> Pagar Multa");// A apagar. Só indicativo
    }

}
