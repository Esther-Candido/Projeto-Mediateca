package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.BadEntrySpecificationException;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Biblioteca implements Serializable {
    private static final long serialVersionUID = 2L;

    private HashMap<Integer, User> users;
    private HashMap<Integer, Obra> obras;
    private int nextUserID;
    private int nextObraID;
    private int data;

    public Biblioteca() {
        this.users = new HashMap<>();
        this.obras = new HashMap<>();
        this.nextUserID = 1;
        this.nextObraID = 1;
        this.data = 0;
    }

    public void setData(int data) {
        if (data > 0)
            this.data += data;
    }

    public int getData() {
        return data;
    }


    public int registarUser(String nome, String email) {
        User u = new User(this.nextUserID, nome, email);
        this.users.put(u.getId(), u);
        this.nextUserID++;
        return u.getId();
    }

    public int registarLivro(String titulo, String autor, Double preco, String categoria, String isbn, Integer exemplares) {
        Livro l = new Livro(this.nextObraID, titulo, autor, preco, categoria, isbn, exemplares);
        this.obras.put(l.getId(), l);
        this.nextObraID++;
        return l.getId();
    }

    public int registarDVD(String titulo, String realizador, Double preco, String categoria, String igac, Integer exemplares) {
        DVD d = new DVD(this.nextObraID, titulo, realizador, preco, categoria, igac, exemplares);
        this.obras.put(d.getId(), d);
        this.nextObraID++;
        return d.getId();
    }

    public ArrayList<User> listUsers() {
        ArrayList<User> users_array = new ArrayList<>(this.users.values());
        Collections.sort(users_array);
        return users_array;
    }

    public String mostrarUtente(int id) throws UserNotFoundException {
        if (this.users.containsKey(id))
            return this.users.get(id).toString();
        throw new UserNotFoundException(id);
    }

    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename of the file to load
     * @throws BadEntrySpecificationException A especificação do ficheiro não é correta
     * @throws IOException                    Erro na abertura e/ou Leitura do ficheiro
     */
    void importFile(String filename) throws BadEntrySpecificationException, IOException {
        Scanner s = new Scanner(new File(filename));
        while (s.hasNextLine()) {
            String line = s.nextLine();
            //System.out.println(line);
            String[] elementos = line.split(":", 0);
            switch (elementos[0]) {

                case "USER":
                    this.registarUser(elementos[1], elementos[2]);
                    break;
                case "BOOK":
                    this.registarLivro(elementos[1],elementos[2],elementos[3],elementos[4],elementos[5],elementos[6]);
                    break;
                case "DVD":
                    this.registarDVD(elementos[1],elementos[2],elementos[3],elementos[4],elementos[5],elementos[6]);
                    break;
                default:
                    throw new BadEntrySpecificationException("Unknow type of category");
            }
        }
        s.close();
    }
}

