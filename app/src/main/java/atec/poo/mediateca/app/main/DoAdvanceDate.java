package atec.poo.mediateca.app.main;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;



/**
 * Conforme Enunciado
 * 4.1.4. Avançar data atual
 */
public class DoAdvanceDate extends Comando<LibraryManager> {

  /**
   * @param receiver
   */
  public DoAdvanceDate(LibraryManager receiver) {
    super( receiver,Label.ADVANCE_DATE);

  }


  @Override
  public final void executar(){
    ui.escreveLinha("---> Avançar Data");// A apagar. Só indicativo
  }
  
}
