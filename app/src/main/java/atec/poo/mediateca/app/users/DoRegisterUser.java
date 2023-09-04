package atec.poo.mediateca.app.users;

import atec.poo.mediateca.app.exceptions.UserRegistrationFailedException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.RegistoNomeIncorretoException;
import atec.poo.mediateca.core.exceptions.RegistroEmail_incorretoException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerString;
import atec.poo.ui.exceptions.DialogException;

/**
 * 4.2.1. Registar novo Utente.
 */
public class DoRegisterUser extends Comando<LibraryManager> {
    private LerString nome;
    private LerString email;

    /**
     * Construtor para a ação de registro de usuário.
     * @param receiver O gerenciador da biblioteca responsável por executar a ação.
     */
    public DoRegisterUser(LibraryManager receiver) {
        super(receiver, Label.REGISTER_USER);
        this.nome = new LerString(Message.requestUserName(),null);
        this.email = new LerString(Message.requestUserEMail(), null);
    }

    /**
     * Executa a ação de registro de um novo usuário na biblioteca.
     * @throws DialogException Se ocorrer algum erro durante a interação com o diálogo.
     */
    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.nome);
        ui.lerInput(this.email);
        int id = this.getReceptor().generateNewUserId();
        try {
            this.getReceptor().registrarUtente(id, nome.getValor(), email.getValor());
            ui.escreveLinha(Message.userRegistrationSuccessful(id));
        } catch (RegistoNomeIncorretoException | RegistroEmail_incorretoException e) {
            throw new UserRegistrationFailedException(nome.getValor(), email.getValor());
        }
    }
}
