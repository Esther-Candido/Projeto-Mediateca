package atec.poo.mediateca.core.exceptions;

public class UtilizadorAtivoException extends Exception{
    private int id;

    public UtilizadorAtivoException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
