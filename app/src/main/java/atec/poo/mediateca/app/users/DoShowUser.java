package atec.poo.mediateca.app.users;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;
import atec.poo.ui.exceptions.DialogException;

/**
 * Conforme Enunciado
 * 4.2.2. Mostrar Utente.
 */
public class DoShowUser extends Comando<LibraryManager> {

    /**
     * @param receiver
     */
    public DoShowUser(LibraryManager receiver) {
        super(receiver, Label.SHOW_USER);

    }

    @Override
    public final void executar() throws DialogException {
        ui.escreveLinha("---> Mostra Utilizador");// A apagar. Só indicativo
    }

}
