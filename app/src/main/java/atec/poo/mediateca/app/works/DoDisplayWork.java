package atec.poo.mediateca.app.works;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;


/**
 * 4.3.1. Mostrar Obra.
 */
public class DoDisplayWork extends Comando<LibraryManager> {

    private LerInteiro id;
    /**
     * @param receiver
     */
    public DoDisplayWork(LibraryManager receiver) {
        super(receiver, Label.SHOW_WORK);
        this.id=new LerInteiro(Message.requestWorkId());
    }


    @Override
    public final void executar() {
        ui.lerInput(this.id);
        String info = this.getReceptor().mostrarObra(this.id.getValor());
        ui.escreveLinha(info);
    }

}

