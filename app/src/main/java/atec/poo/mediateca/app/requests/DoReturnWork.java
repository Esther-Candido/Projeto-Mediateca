package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.app.exceptions.WorkNotBorrowedByUserException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.User;
import atec.poo.mediateca.core.exceptions.BorrowException;
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
        this.lerMulta = new LerBoolean(Message.requestFinePaymentChoice());
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
            this.getReceptor().ver_utente_obra(userID.getValor(), obraID.getValor()); // Executa o metodo devolverObra
        } catch (BorrowException e) { // Ele
            throw new WorkNotBorrowedByUserException(obraID.getValor(), userID.getValor());
        }

        this.getReceptor().devolverObra(userID.getValor(), obraID.getValor()); // Executa o metodo devolverObra

        int multa = this.getReceptor().mostraMulta(userID.getValor());
        if (multa > 0) {
            ui.escreveLinha(Message.showFine(userID.getValor(), multa));
            ui.lerInput(lerMulta);

            try {
                if (lerMulta.getValor()) {
                    this.getReceptor().pagarMulta(userID.getValor());
                }
            } catch (Exception e) {
                throw new UserIsActiveException(userID.getValor());
            }
        }
    }
}