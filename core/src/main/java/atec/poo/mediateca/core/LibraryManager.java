package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.BadEntrySpecificationException;
import atec.poo.mediateca.core.exceptions.ImportFileException;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;

import java.io.*;
import java.util.ArrayList;

public class LibraryManager{

    private Biblioteca _biblioteca;

    public LibraryManager() {
        this._biblioteca =new Biblioteca();
    }

    public int registarUser(String nome, String email){
        return this._biblioteca.registarUser(nome,email);
    }

    public String mostrarUtente(int id) throws UserNotFoundException {
        return this._biblioteca.mostrarUtente(id);
    }

    public  String mostrarNotificacao(int id) throws UserNotFoundException {
        return this._biblioteca.mostrarNotificacao(id);
    }

    public  String pagarMulta(int id) throws UserNotFoundException {
        return this._biblioteca.pagarMulta(id);
    }

    public ArrayList<User> listUsers(){
        return this._biblioteca.listUsers();
    }

    public void save(String ficheiro) throws IOException {
        ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(ficheiro+".import")));
        //ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("imports/"+ficheiro+".import")));
        oos.writeObject(this._biblioteca);
        oos.close();
    }

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

    /**
     * Mostra a data
     * @return data atual
     */

    public int getData() {
        return this._biblioteca.getData();
    }

    /**
     * Atualiza a data
     * @param dias
     * @return dias avançar
     */

    public void setData(int dias) {
        this._biblioteca.setData(dias);
    }

}
