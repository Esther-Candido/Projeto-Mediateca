package atec.poo.mediateca.core;

public class Livro extends Obra{

    private String autor;

    private String isbn;

    public Livro(int id, String titulo, String autor, Double preco, String categoria,String isbn, String exemplares) {
        super(id, titulo, preco, categoria, exemplares);
        this.autor = autor;
        this.isbn = isbn;
        this.setTipo(Tipo.BOOK);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return super.toString()+" - " +this.autor+ " - "+this.isbn;
    }

    @Override
    public String nomeautor_realizador() {
        return this.autor;
    }

}
