package atec.poo.mediateca.core.exceptions;

public class BorrowException extends Exception{

    private int userId;
    private int obraId;

    public BorrowException(int userId, int obraID) {
        this.userId = userId;
        this.obraId = obraId;

    }

    public int getUserId() {
        return userId;
    }

    public int getObraId() {
        return obraId;
    }

}
