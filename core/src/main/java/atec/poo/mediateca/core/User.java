package atec.poo.mediateca.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Comparable<User>, Serializable {
    private static final long serialVersionUID = 1L;
    private final int id;
    private final String nome;
    private final String email;
    private Estado estado;
    private Comportamento comportamento;
    private double multa;
    List<Integer> requisicao=new ArrayList<Integer>();
    public int numRequisicoes;

    public User(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.estado=Estado.ACTIVO;
        this.comportamento=Comportamento.NORMAL;
        this.multa=0;
        this.numRequisicoes=0;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Comportamento getComportamento() {
        return comportamento;
    }

    public void setComportamento(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public boolean getObraID(int id) { // Arranjar um melhor nome pra isto
        if (requisicao != null) {
            for (Integer valor : requisicao) {
                if (valor == id) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (multa > 0)
            return this.id+" - "+this.nome+" - "+this.email+" - "+this.comportamento+" - "+this.estado+" - EUR "+this.multa;
        else
            return this.id+" - "+this.nome+" - "+this.email+" - "+this.comportamento+" - "+this.estado;
    }

    @Override
    public int compareTo(User o) {
        if(this.nome.equalsIgnoreCase(o.getNome()))
            return this.id-o.getId();
        return this.nome.toLowerCase().compareTo(o.getNome().toLowerCase());
    }
}
