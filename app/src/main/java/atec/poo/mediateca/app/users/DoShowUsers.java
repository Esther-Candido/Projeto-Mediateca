package atec.poo.mediateca.app.users;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;


/**
 * Conforme enunciado
 * 4.2.4. Mostrar Todos os utentes.
 */
public class DoShowUsers extends Comando<LibraryManager> {

  /**
   * @param receiver
   */
  public DoShowUsers(LibraryManager receiver) {
    super(receiver,Label.SHOW_USERS);
  }

  @Override
  public final void executar() {
    ui.escreveLinha("---> Mostrar Utilizadores");// A apagar. Só indicativo
  }
}
