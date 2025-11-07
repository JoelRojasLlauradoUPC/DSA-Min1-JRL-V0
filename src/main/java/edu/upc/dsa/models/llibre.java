package edu.upc.dsa.models;

public class llibre {

    String isbn;
    String titol;
    String editorial;
    String anyPublicacio;
    String numEdicio;
    String autor;
    String tematica;
    int quantitatExemplarsDisponibles;

    public llibre() {

    }
    public llibre(String isbn, String titol, String editorial, String anyPublicacio, String numEdicio, String autor, String tematica, int quantitatExemplarsDisponibles) {
        this.isbn = isbn;
        this.titol = titol;
        this.editorial = editorial;
        this.anyPublicacio = anyPublicacio;
        this.numEdicio = numEdicio;
        this.autor = autor;
        this.tematica = tematica;
        this.quantitatExemplarsDisponibles = quantitatExemplarsDisponibles;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String nouisbn) {
        this.isbn = nouisbn;
    }

    public String getTitol() {
        return this.titol;
    }

    public void setTitol(String nouTitol) {
        this.titol = nouTitol;
    }

    public String getEditorial() {
        return this.editorial;
    }

    public void setEditorial(String novaEditorial) {
        this.editorial = novaEditorial;
    }

    public String getAnyPublicacio() {
        return this.anyPublicacio;
    }

    public void setAnyPublicacio(String nouAnyPublicacio) {
        this.anyPublicacio = nouAnyPublicacio;
    }

    public String getNumEdicio() {
        return this.numEdicio;
    }

    public void setNumEdicio(String nouNumEdicio) {
        this.numEdicio = nouNumEdicio;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String nouAutor) {
        this.autor = nouAutor;
    }

    public String getTematica() {
        return this.tematica;
    }

    public void setTematica(String novaTematica) {
        this.tematica = novaTematica;
    }

    public int getQuantitatExemplarsDisponibles() {
        return this.quantitatExemplarsDisponibles;
    }

    public void setQuantitatExemplarsDisponibles(int novaQuantitat) {
        this.quantitatExemplarsDisponibles = novaQuantitat;
    }


    @Override //TODO: Sempre fer aquesta estructura
    public String toString() {
        return "Llibre [isbn=" + isbn + ", titol=" + titol + ", editorial=" + editorial +
                ", anyPublicacio=" + anyPublicacio + ", numEdicio=" + numEdicio +
                ", autor=" + autor + ", tematica=" + tematica +
                ", quantitatExemplarsDisponibles=" + quantitatExemplarsDisponibles + "]";
    }

}