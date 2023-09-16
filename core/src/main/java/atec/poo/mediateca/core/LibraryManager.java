package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.*;

import java.io.*;
import java.util.ArrayList;

public class LibraryManager{

    private Biblioteca _biblioteca;

    public LibraryManager() {
        this._biblioteca =new Biblioteca();
    }

    /**
     * Mostra a data atual
     * @return Data atual
     */
    public int getData() {
        return this._biblioteca.getData();
    }

    /**
     * Define a data atual
     * @param dias dias
     */
    public void setData(int dias) {
        this._biblioteca.setData(dias);
    }

    /**
     * Registra um novo utente
     * @param nome nome utente
     * @param email emial utente
     * @return Cria um novo utente
     */
    public int registarUser(String nome, String email){
        return this._biblioteca.registarUser(nome,email);
    }

    /**
     * Obtém informações sobre um utente especifico
     * @param id id utente
     * @return Informações do utente pretendido
     * @throws UserNotFoundException Verificar se o utente existe ou não
     */
    public String mostrarUtente(int id) throws UserNotFoundException {
        return this._biblioteca.mostrarUtente(id);
    }

    /**
     * Mostra informações sobre todos os utentes
     * @return Informações de todos os utentes
     */
    public ArrayList<User> listUsers(){
        return this._biblioteca.listUsers();
    }

    /** AINDA POR FAZER!!!!!!!!!!!!!!!!!!!
     * Obtém notificações de um utente específico
     * @param id id utente
     * @return Notificações do utente pretendido
     * @throws UserNotFoundException Verificar se o utente existe ou não
     */
    public  String mostrarNotificacao(int id) throws UserNotFoundException {
        return this._biblioteca.mostrarNotificacao(id);
    }

    /**
     * Paga a multa de um utente especifico
     * @param userID id utente
     */
    public void pagarMulta(int userID) throws ActiveUserException {this._biblioteca.pagarMulta(userID);}

    /**
     * Obtém informações sobre uma obra específica
     * @param id id obra
     * @return Informações da obra pretendido
     * @throws WorkNotFoundException Verificar se a obra existe ou não
     */
    public String mostrarObra(int id) throws  WorkNotFoundException{
        return this._biblioteca.mostrarObra(id);
    }

    /**
     * Mostra informações sobre todas as obras por ordem crescente do ID da obra
     * @return Informações de todas as obras (ordem crescente id obra)
     */
    public ArrayList<Obra> listObrasByID(){
        return this._biblioteca.listObrasByID();
    }

    /**
     * Requisita obra especifica para um utente especifico
     * @param userID id utente
     * @param obraID id obra
     * @throws RuleException Mostra cada erro especifico
     */
    public void requisitarObra(int userID, int obraID) throws RuleException{
        this._biblioteca.requisitarObra(userID, obraID);
    }

    /**
     * COMENTAR ISTO!!
     * @param userID id utente
     * @param obraID id obra
     * @return VER UM NOME PARA DAR A ISTO!!!!
     */
    public int requisicaoDias(int userID, int obraID) {
        return this._biblioteca.requisicaoMaxDias(userID, obraID);
    }

    /**
     * Faz a devolução da Obra que foi requisitada
     * @param userID id utente
     * @param obraID id obra
     */
    public void devolverObra(int userID, int obraID) throws BorrowException{
        this._biblioteca.devolverObra(userID, obraID);
    }

    /**
     * Verificar se o Utente possui aquela Obra
     * @param userID id utente
     * @param obraID id obra
     */
    public void verificarUtenteObra(int userID, int obraID) {
        this._biblioteca.verificarUtenteObra(userID, obraID);
    }

    /**
     * Procura a multa de um utente especifico
     * @param userID utente id
     * @return Retorna a multa do utente
     */
    public int mostraMulta(int userID){
        return this._biblioteca.mostraMulta(userID);
    }

    /**
     * COMENTAR ISTO!!
     * @param ficheiro ficheiro
     * @throws IOException NÂO SEI!
     */
    public void save(String ficheiro) throws IOException {
        ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(ficheiro)));
        oos.writeObject(this._biblioteca);
        oos.close();
    }

    /**
     * COMENTAR ISTO!!
     * @param ficheiro ficheiro
     * @throws IOException NÃO SEI
     * @throws ClassNotFoundException NÃO SEI
     */
    public void load(String ficheiro) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(ficheiro)));
        this._biblioteca = ((Biblioteca) ois.readObject());
        ois.close();
    }

    /**
     * Recebe ficheiro de entrada
     * @param datafile Ficheiro de dados
     * @throws ImportFileException A importação do ficheiro deu erro
     */
    public void importFile(String datafile) throws ImportFileException {
        try {
            this._biblioteca.importFile(datafile);
        } catch (IOException | BadEntrySpecificationException e) {
            throw new ImportFileException(e);
        }
    }
}