package atec.poo.mediateca.core.exceptions;

public class RuleException extends Exception {

    private int id;

    public RuleException(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }
}

