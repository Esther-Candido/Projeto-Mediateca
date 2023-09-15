package atec.poo.mediateca.app.users;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * Conforme enunciado
 * 4.2.3. Mostrar Notificações do Utente
 *
 */
public class DoShowUserNotifications extends Comando<LibraryManager> {
  private LerInteiro id;
  /**
   * @param receiver
   */
  public DoShowUserNotifications(LibraryManager receiver) {
    super(receiver, Label.SHOW_USER_NOTIFICATIONS);
    this.id=new LerInteiro(Message.requestUserId());
  }

  @Override
  public final void executar() throws DialogException {
    // Por fazer
    ui.lerInput(this.id);
    try{
      String notificacao=this.getReceptor().mostrarNotificacao(this.id.getValor());
      ui.escreveLinha(notificacao);
    }
    catch (UserNotFoundException e) {
      throw new NoSuchUserException(e.getId());
    }
  }
}
