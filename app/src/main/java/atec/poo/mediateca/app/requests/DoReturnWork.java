package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.app.exceptions.WorkNotBorrowedByUserException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.User;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;
import atec.poo.mediateca.core.exceptions.WorkNotFoundException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * 4.4.2. Return a work.
 */
public class DoReturnWork extends Comando<LibraryManager> {
    private LerInteiro userID;
    private LerInteiro obraID;

    /**
     *
     * @param receiver
     */
    public DoReturnWork(LibraryManager receiver) {
        super(receiver, Label.RETURN_WORK);
        this.userID = new LerInteiro(Message.requestUserId());
        this.obraID = new LerInteiro(Message.requestWorkId());
    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(userID);
        ui.lerInput(obraID);

        try {
            this.getReceptor().mostrarUtente(this.userID.getValor());
        } catch (UserNotFoundException e) {
            throw new NoSuchUserException(e.getId());
        }

        try {
            this.getReceptor().mostrarObra(this.obraID.getValor());
        } catch (WorkNotFoundException e) {
            throw new NoSuchWorkException(e.getId());
        }

        try {
            // Arranjar uma melhor variavel pra o userObra (Procura se o User requisitou aquela Obra)
            User userObra = this.getReceptor().listUsers().get(userID.getValor());
            userObra.getObraID(obraID.getValor());
            String info = this.getReceptor().devolverObra(this.userID.getValor(),this.obraID.getValor());
            ui.escreveLinha(info);
        } catch (Exception e) {
            throw new WorkNotBorrowedByUserException(obraID.getValor(), userID.getValor());
        }
    }
}
