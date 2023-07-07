package atec.poo.mediateca.app.users;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;
import atec.poo.ui.exceptions.DialogException;

/**
 * 4.2.1. Registar novo Utente.
 */
public class DoRegisterUser extends Comando<LibraryManager> {

    /**
     * @param receiver
     */
    public DoRegisterUser(LibraryManager receiver) {
        super(receiver, Label.REGISTER_USER);
    }

    @Override
    public final void executar() throws DialogException {
        ui.escreveLinha("---> Registar Utilizador");// A apagar. Só indicativo
    }

}
