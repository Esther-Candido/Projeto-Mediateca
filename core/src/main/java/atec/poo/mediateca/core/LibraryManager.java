package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.BadEntrySpecificationException;
import atec.poo.mediateca.core.exceptions.ImportFileException;
import java.io.IOException;

public class LibraryManager{

    private Biblioteca _biblioteca;
    /**
     * Recebe ficheiro de entrada
     * @param datafile
     * @throws ImportFileException
     */
    public void importFile(String datafile) throws ImportFileException {
        try {
            this._biblioteca.importFile(datafile);
        } catch (IOException | BadEntrySpecificationException e) {
            throw new ImportFileException(e);
        }
    }

}
