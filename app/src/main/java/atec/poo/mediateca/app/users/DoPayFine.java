package atec.poo.mediateca.app.users;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.app.exceptions.UserIsActiveException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.UtilizadorAtivoException;
import atec.poo.mediateca.core.exceptions.UtilizadorNaoExisteException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * Conforme enunciado
 * 4.2.5. Pagar Multa
 */
public class DoPayFine extends Comando<LibraryManager> {
    private LerInteiro id;


    /**
     * Construtor para a ação de pagar multa de um utente.
     * @param receiver O gerenciador da biblioteca responsável por executar a ação.
     */
    public DoPayFine(LibraryManager receiver) {
        super(receiver, Label.PAY_FINE);
        this.id = new LerInteiro(Message.requestUserId());

    }

    /**
     * Executa a ação de pagar multa de um utente.
     * @throws DialogException Se ocorrer um erro de diálogo durante a execução.
     */
    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.id);
        int userId = this.id.getValor();
        try {
            ui.escreveLinha(this.getReceptor().PagarMulta(userId));
        } catch (UtilizadorNaoExisteException e) {
            throw new NoSuchUserException(e.getUser_id());
        } catch (UtilizadorAtivoException e) {
            throw new UserIsActiveException(e.getId());
        }
    }

}
