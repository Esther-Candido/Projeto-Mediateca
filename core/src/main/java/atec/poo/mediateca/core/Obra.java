package atec.poo.mediateca.core;

import java.io.Serializable;

public class Obra implements Comparable<Obra>, Serializable {
    private static final long serialVersionUID = 1L;
    private final int id;
    private final String titulo;
    private final Double preco;
    private String categoria;
    private String exemplares;

     Obra(int id, String titulo, Double preco, String categoria, String exemplares) {
        this.id = id;
        this.titulo = titulo;
        this.preco = preco;
        this.categoria = categoria;
        this.exemplares = exemplares;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Double getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getExemplares() {
        return exemplares;
    }

    public void setExemplares(String exemplares) {
        this.exemplares = exemplares;
    }

    @Override
    public String toString() {
        return this.id+" - "+this.exemplares+" - DVD/LIVRO - "+this.titulo+" - "+this.preco+" - "+this.categoria;
    }

    @Override
    public int compareTo(Obra o) {
        if(this.titulo.equals(o.getTitulo()))
            return this.id-o.getId();
        return this.titulo.compareTo(o.getTitulo());
    }
}
