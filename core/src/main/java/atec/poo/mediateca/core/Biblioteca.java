package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.BadEntrySpecificationException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Biblioteca {

    public Biblioteca(){
        //TODO: Inicialização dos atributos biblioteca
    }

    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename of the file to load
     * @throws BadEntrySpecificationException
     * @throws IOException
     */
    void importFile(String filename) throws BadEntrySpecificationException, IOException {
        Scanner s =new Scanner(new File(filename));
        while(s.hasNextLine()){
            String line = s.nextLine();
            String[] elementos= line.split(":",0);
            switch(elementos[0]){
                case "USER":
                    //this.registerUser(elementos[1],elementos[2]);
                    //TODO: A implementar pelos alunos dependente dos métodos que implementarem
                    break;
                case "BOOK":
                    //this.registerLivro(elementos[1],elementos[2],elementos[3],elementos[4],elementos[5],elementos[6]);
                    //TODO: A implementar pelos alunos dependente dos métodos que implementarem
                    break;
                case "DVD":
                    //this.registerDVD(elementos[1],elementos[2],elementos[3],elementos[4],elementos[5],elementos[6]);
                    //TODO: A implementar pelos alunos dependente dos métodos que implementarem
                    break;
                default:
                    throw new BadEntrySpecificationException("Unknow type of category");
            }
        }
        s.close();
    }
}
