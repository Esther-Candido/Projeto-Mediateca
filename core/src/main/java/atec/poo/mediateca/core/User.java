package atec.poo.mediateca.core;

import java.io.Serializable;

public class User implements Comparable<User>, Serializable {
    private static final long serialVersionUID = 1L;
    private final int id;
    private final String nome;
    private final String email;
    private Estado estado;
    private Comportamento comportamento;
    private Double multa;


    public User(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.estado=Estado.ACTIVO;
        this.comportamento=Comportamento.NORMAL;
        this.multa= (double) 0;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setComportamento(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    public Estado getEstado() {
        return estado;
    }

    public Comportamento getComportamento() {
        return comportamento;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getMulta() {
        return multa;
    }

    public void setMulta(Double multa) {
        this.multa = multa;
    }

    @Override
    public String toString() {
        if (multa > 0)
            return this.id+" - "+this.nome+" - "+this.email+" - "+this.comportamento+" - "+this.estado+" - "+this.multa;
        else
            return this.id+" - "+this.nome+" - "+this.email+" - "+this.comportamento+" - "+this.estado;
    }

    @Override
    public int compareTo(User o) {
        if(this.nome.equals(o.getNome()))
            return this.id-o.getId();
        return this.nome.compareTo(o.getNome());
    }
}
