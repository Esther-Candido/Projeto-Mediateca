package atec.poo.mediateca.core.exceptions;

public class RegistroEmail_incorretoException extends Exception{

    private String email ;

    public RegistroEmail_incorretoException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
