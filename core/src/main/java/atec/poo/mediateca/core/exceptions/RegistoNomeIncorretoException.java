package atec.poo.mediateca.core.exceptions;

public class RegistoNomeIncorretoException extends Exception {
    private String nome;

    public RegistoNomeIncorretoException(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
