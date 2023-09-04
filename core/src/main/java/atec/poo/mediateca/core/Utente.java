package atec.poo.mediateca.core;

public class Utente {


    private String nome;
    private String email;
    private String classificacao; // Nova propriedade: NORMAL, PREMIUM, etc.
    private String estado; // Nova propriedade: ATIVO, SUSPENSO, etc.
    private Integer multas; // Nova propriedade: valor das multas acumuladas

    public Utente( String nome, String email) {

        this.nome = nome;
        this.email = email;
        this.classificacao = "NORMAL"; // Valor padrão para classificação
        this.estado = "ATIVO"; // Valor padrão para estado
        this.multas = 0;
    }

    // Getters e setters para os novos campos
    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getMultas() {
        return multas;
    }

    public void setMultas(Integer multas) {
        this.multas = multas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

