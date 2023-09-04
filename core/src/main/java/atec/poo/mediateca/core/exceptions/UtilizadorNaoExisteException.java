package atec.poo.mediateca.core.exceptions;

public class UtilizadorNaoExisteException extends Exception{
    private int user_id;

    public UtilizadorNaoExisteException(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }
}
