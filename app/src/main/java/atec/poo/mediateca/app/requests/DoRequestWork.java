package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.app.exceptions.RuleFailedException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.RuleException;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;
import atec.poo.mediateca.core.exceptions.WorkNotFoundException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * Conforme enunciado
 * 4.4.1. Rquisitar uma obra
 */
public class DoRequestWork extends Comando<LibraryManager> {
    private final LerInteiro userID;
    private final LerInteiro obraID;

    /**
     * Requisita a obra
     *
     * @param receiver;
     */
    public DoRequestWork(LibraryManager receiver) {
        super(receiver, Label.REQUEST_WORK);
        this.userID = new LerInteiro(Message.requestUserId());
        this.obraID = new LerInteiro(Message.requestWorkId());
    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(userID);
        ui.lerInput(obraID);

        try {
            this.getReceptor().mostrarUtente(userID.getValor());
            this.getReceptor().mostrarObra(obraID.getValor());
        } catch (UserNotFoundException e) {
            throw new NoSuchUserException(e.getUserID());
        } catch (WorkNotFoundException e) {
            throw new NoSuchWorkException(e.getObraID());
        }

        int tempoEntrega = this.getReceptor().calcularDataEntrega(userID.getValor(), obraID.getValor()) + this.getReceptor().getData();

        try {
            this.getReceptor().requisitarObra(this.userID.getValor(), this.obraID.getValor());
            ui.escreveLinha(Message.workReturnDay(obraID.getValor(), tempoEntrega));
        } catch (RuleException e) {
            throw new RuleFailedException(e.getUserID(), e.getObraID(), e.getRuleID());
        }
    }
}