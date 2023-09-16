package atec.poo.mediateca.core.exceptions;

import java.io.Serial;

/**
 * Exception for unknown import file entries.
 */
public class BadEntrySpecificationException extends Exception {

    /**
     * Serial number for serialization.
     */
    @Serial
    private static final long serialVersionUID = 201901101348L;

    /**
     * Bad bad entry specification.
     */
    private final String _entrySpecification;

    /**
     * @param entrySpecification;
     */
    public BadEntrySpecificationException(String entrySpecification) {
        _entrySpecification = entrySpecification;
    }

    /**
     * @param entrySpecification;
     * @param cause;
     */
    public BadEntrySpecificationException(String entrySpecification, Exception cause) {
        super(cause);
        _entrySpecification = entrySpecification;
    }

    /**
     * @return the bad entry specification.
     */
    public String getEntrySpecification() {
        return _entrySpecification;
    }
}

