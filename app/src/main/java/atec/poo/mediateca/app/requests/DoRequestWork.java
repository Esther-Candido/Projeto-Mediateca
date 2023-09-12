package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Obra;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

import java.util.ArrayList;


/**
 * Conforme enunciado
 * 4.4.1. Rquisitar uma obra
 */
public class DoRequestWork extends Comando<LibraryManager> {

    private LerInteiro user_id;
    private LerInteiro obra_id;


    /**
     * Requisita a obra
     * @param receiver
     *
     */
    public DoRequestWork(LibraryManager receiver) {
        super(receiver, Label.REQUEST_WORK);
        this.user_id = new LerInteiro(Message.requestUserId());
        this.obra_id = new LerInteiro(Message.requestWorkId());

    }


    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.user_id);
        ui.lerInput(this.obra_id);

        this.getReceptor().requisitarObra(this.user_id.getValor(),this.obra_id.getValor());

    }
}
