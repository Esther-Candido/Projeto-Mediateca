package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.*;

import java.io.IOException;
import java.util.List;

public class LibraryManager{

    private Biblioteca _biblioteca;

    public LibraryManager() {
        this._biblioteca =new Biblioteca();
    }

    /**
     * Recebe ficheiro da entrada
     * @param datafile Ficheiro de dados
     * @throws ImportFileException A importaçao do ficheiro deu erro
     */
    public void importFile(String datafile) throws ImportFileException {
        try {
            this._biblioteca.importFile(datafile);
        } catch (IOException | BadEntrySpecificationException e) {
            throw new ImportFileException(e);
        }
    }

    /**
     * Obtém a data atual.
     * @return A data atual.
     */
    public int getData(){
        return this._biblioteca.getData();
    }

    /**
     * Atualiza a data da biblioteca.
     * @param dias O número de dias a serem atualizados.
     */
    public void setData(int dias){
        this._biblioteca.setData(dias);
    }

    /**
     * Registra um novo utente na biblioteca.
     * @param id   O ID do utente.
     * @param nome O nome do utente.
     * @param email O email do utente.
     * @throws RegistroEmail_incorretoException Se o email estiver incorreto.
     * @throws RegistoNomeIncorretoException Se o nome estiver incorreto.
     */
    public void registrarUtente(int id, String nome, String email) throws RegistroEmail_incorretoException, RegistoNomeIncorretoException {
          this._biblioteca.registrarUtente(id,nome,email);
    }

    /**
     * Obtém as informações de um utente.
     * @param id O ID do utente.
     * @return As informações do utente.
     * @throws UtilizadorNaoExisteException Se o utente não existir.
     */
    public String getUserInformacao(int id) throws UtilizadorNaoExisteException {
        return this._biblioteca.getUserInformacao(id);
    }

    /**
     * Obtém informações ordenadas de todos os utentes.
     * @return Lista de informações ordenadas dos utentes.
     */
    public List<String> getUtentesOrdenadosInfo() {
        return this._biblioteca.getUtentesOrdenadosInfo();
    }

    /**
     * Paga a multa de um utente.
     * @param id O ID do utente.
     * @return A mensagem de pagamento bem-sucedido.
     * @throws UtilizadorNaoExisteException Se o utente não existir.
     * @throws UtilizadorAtivoException Se o utente estiver ativo.
     */
    public String PagarMulta(int id) throws UtilizadorNaoExisteException, UtilizadorAtivoException {
        return this._biblioteca.PagarMulta(id);
    }

    /**
     * Gera um novo ID para um utente.
     * @return O novo ID gerado.
     */
    public int generateNewUserId() {
        return this._biblioteca.generateNewUserId();
    }

    /**
     * Requisita uma obra por um utente.
     * @param id_user O ID do utente.
     * @param id_obra O ID da obra.
     * @throws ObraNaoExisteException Se a obra não existir.
     * @throws UtilizadorNaoExisteException Se o utente não existir.
     */
    public void requisitar_obra(int id_user,int id_obra) throws ObraNaoExisteException, UtilizadorNaoExisteException {
        this._biblioteca.requisitar_obra(id_user,id_obra);
    }
}
