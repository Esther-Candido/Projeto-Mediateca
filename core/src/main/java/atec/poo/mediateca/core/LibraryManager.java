package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.BadEntrySpecificationException;
import atec.poo.mediateca.core.exceptions.ImportFileException;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;

import java.io.*;
import java.util.ArrayList;

public class LibraryManager{

    private Biblioteca _biblioteca;

    /**
     * Cria uma nova Biblioteca
     */
    public LibraryManager() {
        this._biblioteca =new Biblioteca();
    }

    /**
     * Mostra a Data
     * @return Data Atual
     */
    public int getData() {
        return this._biblioteca.getData();
    }

    /**
     * Atualiza a Data
     * @param dias
     * @return Dias Avançar
     */
    public void setData(int dias) {
        this._biblioteca.setData(dias);
    }

    /**
     * Cria um novo Utente
     * @param nome
     * @param email
     * @return Novo Utente
     */
    public int registarUser(String nome, String email){
        return this._biblioteca.registarUser(nome,email);
    }

    /**
     *
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    public String mostrarUtente(int id) throws UserNotFoundException {
        return this._biblioteca.mostrarUtente(id);
    }

    /**
     *
     * @return
     */
    public ArrayList<User> listUsers(){
        return this._biblioteca.listUsers();
    }

    /**
     *
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    public  String mostrarNotificacao(int id) throws UserNotFoundException {
        return this._biblioteca.mostrarNotificacao(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    public  String pagarMulta(int id) throws UserNotFoundException {
        return this._biblioteca.pagarMulta(id);
    }

    /**
     *
     * @param id
     * @return
     */
    public String mostrarObra(int id) {
        return this._biblioteca.mostrarObra(id);
    }

    /**
     *
     * @return
     */
    public ArrayList<Obra> listObras(){
        return this._biblioteca.listObras();
    }

    /**
     *
     * @param idUser
     * @param idObra
     */
    public void requisitarObra(int idUser,int idObra){
        this._biblioteca.requisitarObra(idUser, idObra);
    }

    /**
     *
     * @param ficheiro
     * @throws IOException
     */
    public void save(String ficheiro) throws IOException {
        ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(ficheiro+".import")));
        //ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("imports/"+ficheiro+".import")));
        oos.writeObject(this._biblioteca);
        oos.close();
    }

    /**
     *
     * @param ficheiro
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void load(String ficheiro) throws IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new BufferedInputStream(new FileInputStream(ficheiro)));
        this._biblioteca=((Biblioteca) ois.readObject());
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
