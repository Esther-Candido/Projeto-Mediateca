package atec.poo.mediateca.app.users;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;

import java.util.List;


/**
 * Conforme enunciado
 * 4.2.4. Mostrar Todos os utentes.
 */
public class DoShowUsers extends Comando<LibraryManager> {

  /**
   * Construtor para a ação de mostrar todas as informações dos utentes.
   * @param receiver O gerenciador da biblioteca responsável por executar a ação.
   */
  public DoShowUsers(LibraryManager receiver) {
    super(receiver,Label.SHOW_USERS);
  }

  /**
   * Executa a ação de mostrar todas as informações dos utentes na biblioteca.
   */
  @Override
  public final void executar() {
    List<String> utentesOrdenadosInfo = this.getReceptor().getUtentesOrdenadosInfo();
    for (String utenteInfo : utentesOrdenadosInfo) {
      ui.escreveLinha(utenteInfo);
    }
  }
}
