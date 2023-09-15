package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.app.exceptions.WorkNotBorrowedByUserException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Obra;
import atec.poo.mediateca.core.User;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;
import atec.poo.mediateca.core.exceptions.WorkNotFoundException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

import java.util.ArrayList;

/**
 * 4.4.2. Return a work.
 */
public class DoReturnWork extends Comando<LibraryManager> {

    private LerInteiro userID;
    private LerInteiro obraID;

    /**
     *
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
            User userObra = this.getReceptor().listUsers().get(userID.getValor());
            userObra.getObraID(obraID.getValor()); // Ver se tem obra emprestada pelo o utente
            String info = this.getReceptor().devolverObra(this.userID.getValor(),this.obraID.getValor()); // Executa o metodo devolverObra
            ui.escreveLinha(info); // Escreve na consola o metodo devolverObra
        } catch (Exception e) { // Ele
            throw new WorkNotBorrowedByUserException(obraID.getValor(), userID.getValor());
        }
    }

    /*
        try {
            double multa = this.getReceptor().devolverRequisicao(idUtente.getValor(), idObra.getValor());     //devolve a multa
            if (multa > 0) {
                ui.escreveLinha(Message.showFine(idUtente.getValor(), (int) multa));                           //mostra a multa
                ui.lerInput(request = new LerString(Message.requestFinePaymentChoice(), null));           //pergunta se quer pagar a multa

                switch (request.getValor().toLowerCase()) {
                    case "s" -> this.getReceptor().pagarMultalogo(idUtente.getValor(), (int) multa, "s"); //se sim, paga a multa
                    case "n" -> this.getReceptor().pagarMultalogo(idUtente.getValor(), (int) multa, "n"); //se nao, nao paga a multa
                }

            }
        } catch (Exception e) {
            throw new WorkNotBorrowedByUserException(idObra.getValor(), idUtente.getValor());
        }
    */

}
