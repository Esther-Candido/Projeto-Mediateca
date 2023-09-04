package atec.poo.mediateca.core.exceptions;

public class ObraNaoExisteException extends Exception{
    private int id_obra;

    public ObraNaoExisteException(int id_obra) {
        this.id_obra = id_obra;
    }

    public int getId_obra() {
        return id_obra;
    }
}
