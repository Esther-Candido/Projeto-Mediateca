package atec.poo.mediateca.core;

public class DVD extends Obra {

    private String realizador;

    private String igac;

    public DVD(int id, String titulo, String realizador, Double preco, String categoria, String igac, String exemplares) {
        super(id, titulo, preco, categoria, exemplares);
        this.realizador = realizador;
        this.igac = igac;
    }

    public String getRealizador() {
        return realizador;
    }

    public void setRealizador(String realizador) {
        this.realizador = realizador;
    }

    public String getIgac() {
        return igac;
    }

    public void setIgac(String igac) {
        this.igac = igac;
    }

    @Override
    public String toString() {
        return super.toString()+" - " +this.realizador+" - "+this.igac;
    }
}