package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.WorkNotFoundException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * Conforme enunciado
 * 4.4.1. Rquisitar uma obra
 */
public class DoRequestWork extends Comando<LibraryManager> {
    private LerInteiro userID;
    private LerInteiro obraID;

    /**
     * Requisita a obra
     * @param receiver
     *
     */
    public DoRequestWork(LibraryManager receiver) {
        super(receiver, Label.REQUEST_WORK);
        this.userID = new LerInteiro(Message.requestUserId());
        this.obraID = new LerInteiro(Message.requestWorkId());
    }


    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.userID);
        ui.lerInput(this.obraID);

        try {
            String info=this.getReceptor().requisitarObra(this.userID.getValor(),this.obraID.getValor());
            ui.escreveLinha(info);
        } catch (WorkNotFoundException e) {
            throw new NoSuchWorkException(e.getId());
        }

    }
}
