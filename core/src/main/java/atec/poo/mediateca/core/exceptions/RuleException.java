package atec.poo.mediateca.core.exceptions;

import java.io.Serializable;

public class RuleException extends Exception implements Serializable {

    private int id;

    public RuleException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

