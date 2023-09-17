package atec.poo.mediateca.app.users;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Obra;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Conforme enunciado
 * 4.2.3. Mostrar Notificações do Utente
 */
public class DoShowUserNotifications extends Comando<LibraryManager> {
    private final LerInteiro id;



    /**
     * @param receiver;
     */
    public DoShowUserNotifications(LibraryManager receiver) {
        super(receiver, Label.SHOW_USER_NOTIFICATIONS);
        this.id = new LerInteiro(Message.requestUserId());

    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.id);

        // verificar se o user existe
        try {
            this.getReceptor().mostrarUtente(this.id.getValor());
        } catch (UserNotFoundException e) {
            throw new NoSuchUserException(e.getUserID()); //se n existe entra nessa excecao
        }

        ArrayList<String> obra_notificacao = this.getReceptor().mostrarNotificacao(this.id.getValor());

        if (!obra_notificacao.isEmpty()){
            // Imprimindo cada elemento do arraylist criado para mostrar cada registro de requisicao e devolucao
            for (String elemento : obra_notificacao) {
                ui.escreveLinha(elemento);
            }
        }

    }
}