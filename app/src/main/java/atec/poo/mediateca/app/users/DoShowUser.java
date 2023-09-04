package atec.poo.mediateca.app.users;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Utente;
import atec.poo.mediateca.core.exceptions.UtilizadorNaoExisteException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * Conforme Enunciado
 * 4.2.2. Mostrar Utente.
 */


public class DoShowUser extends Comando<LibraryManager> {

    private LerInteiro id;

    /**
     * Construtor para a ação de mostrar informações de um usuário.
     * @param receiver O gerenciador da biblioteca responsável por executar a ação.
     */
    public DoShowUser(LibraryManager receiver) {
        super(receiver, Label.SHOW_USER);
        this.id= new LerInteiro(Message.requestUserId());
    }

    /**
     * Executa a ação de mostrar informações de um usuário na biblioteca.
     * @throws DialogException Se ocorrer algum erro durante a interação com o diálogo.
     */
    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.id);
        int userId = this.id.getValor();
        try {
            ui.escreveLinha(this.getReceptor().getUserInformacao(userId));
        } catch (UtilizadorNaoExisteException e) {
            throw new NoSuchUserException(e.getUser_id());
        }
    }

}
