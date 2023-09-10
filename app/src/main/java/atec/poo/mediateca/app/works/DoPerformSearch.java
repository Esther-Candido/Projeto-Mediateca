package atec.poo.mediateca.app.works;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;

/**
 * Conforme Enunciado
 * 4.3.3. Pesquisar Obras
 */
public class DoPerformSearch extends Comando<LibraryManager> {

  /**
   * @param receiver
   */
  public DoPerformSearch(LibraryManager receiver) {
    super(receiver, Label.PERFORM_SEARCH);

  }

  @Override
  public final void executar() {
    ui.escreveLinha("---> Pesquisar Obras");// A apagar. Só indicativo
  }

}

