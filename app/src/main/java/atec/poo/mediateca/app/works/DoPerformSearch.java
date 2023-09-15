package atec.poo.mediateca.app.works;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Obra;
import atec.poo.mediateca.core.Tipo;
import atec.poo.mediateca.core.utilidades.CompareObraByID;
import atec.poo.ui.Comando;
import atec.poo.ui.Constantes;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.LerString;
import atec.poo.ui.exceptions.DialogException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 4.3.3. Pesquisar Obras
 */
public class DoPerformSearch extends Comando<LibraryManager> {
    private LerString pesquisa;

    /**
     * @param receiver
     */
    public DoPerformSearch(LibraryManager receiver) {
        super(receiver, Label.PERFORM_SEARCH);
        this.pesquisa = new LerString(Message.requestSearchTerm(), null);
    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.pesquisa);
        this.pesquisa.getValor(); // VER ISTO

        ArrayList<Obra> obras = this.getReceptor().listObrasByID(); // Ordena a lista de obras por ID

        for (Obra o : obras) {  // Procura pela palavra inserida no input(pesquisa) se tem na lista de array de obras em titulo e nomeCriador(Autor/Realizador)
            if (o.getTitulo().toLowerCase().contains(this.pesquisa.getValor().toLowerCase()) || o.nomeCriador().toLowerCase().contains(this.pesquisa.getValor().toLowerCase())) {
                ui.escreveLinha(o.toString());
            }
        }
    }
}

