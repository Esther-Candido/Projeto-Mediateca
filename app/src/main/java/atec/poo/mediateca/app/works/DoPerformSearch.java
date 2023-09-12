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
 * Conforme Enunciado
 * 4.3.3. Pesquisar Obras
 * Resultado da pesquisa ordenadas por ordem crescente do seu identificador OBRAS
 * Pesquisa deve buscar no DVD -> o realizador e o título
 * Pesquisa deve busca no BOOK -> o autor e o título
 */
public class DoPerformSearch extends Comando<LibraryManager> {

  /**
   * @param receiver
   */

  private LerString pesquisa;


  public DoPerformSearch(LibraryManager receiver) {
    super(receiver, Label.PERFORM_SEARCH);
    this.pesquisa=new LerString(Message.requestSearchTerm(), null);
  }

  @Override
  public final void executar() throws DialogException  {
    ui.lerInput(this.pesquisa);
    this.pesquisa.getValor();

    ArrayList<Obra> obras=this.getReceptor().listObras();
    // Ordena a lista de obras por ID
    Collections.sort(obras, new CompareObraByID()); //obras.sort(new CompareObraByID()); Outra forma de fazer

    // Procura pela palavra inserida no input(pesquisa) se tem na lista de array de obras em titulo e nomeCriador(Autor/Realizador)
    for ( Obra o: obras ) {
      if (o.getTitulo().toLowerCase().contains(this.pesquisa.getValor().toLowerCase()) || o.nomeCriador().toLowerCase().contains(this.pesquisa.getValor().toLowerCase())){
        ui.escreveLinha(o.toString());
      }
    }
  }

}

