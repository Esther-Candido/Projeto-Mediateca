package atec.poo.mediateca.core.exceptions;

public class RuleException extends Exception {

   private int userId;
   private int obraId;
   private int ruleId;

    public RuleException(int userId, int obraId, int ruleId) {
        this.userId = userId;
        this.obraId = obraId;
        this.ruleId = ruleId;
    }

    public int getUserId() {
        return userId;
    }

    public int getObraId() {
        return obraId;
    }

    public int getRuleId() {
        return ruleId;
    }
}

