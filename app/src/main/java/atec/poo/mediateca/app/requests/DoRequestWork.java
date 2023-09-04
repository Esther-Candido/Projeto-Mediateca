package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.ObraNaoExisteException;
import atec.poo.mediateca.core.exceptions.UtilizadorNaoExisteException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;


/**
 * Conforme enunciado
 * 4.4.1. Rquisitar uma obra
 */
public class DoRequestWork extends Comando<LibraryManager> {
    private LerInteiro id_utente;
    private LerInteiro id_obra;


    /**
     * @param receiver
     */
    public DoRequestWork(LibraryManager receiver) {
        super(receiver, Label.REQUEST_WORK);
        this.id_obra = new LerInteiro(Message.requestWorkId());
        this.id_utente = new LerInteiro(Message.requestUserId());

    }

    /**
     * Manda id_user e id_obra para LibraryManeger
     * @throws DialogException
     */
    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.id_utente);
        ui.lerInput(this.id_obra);
        try {
            this.getReceptor().requisitar_obra(id_utente.getValor(),id_obra.getValor());
        } catch (ObraNaoExisteException e) {
            throw new NoSuchWorkException(e.getId_obra());
        } catch (UtilizadorNaoExisteException e) {
            throw new NoSuchUserException(e.getUser_id());
        }
    }
}
