package edu.upc.dsa.models;

import java.util.Date;

public class prestec {

    String idPrestec;
    String idLector;
    String ISBNLlibre;
    Date dataIniciPrestec;
    Date dataFinalPrestec;
    int estatPrestec; //0 = prèstec finalitzat, 1 = prèstec en curs

    public prestec() {

    }
    public prestec(String idPrestec, String idLector, String ISBNLlibre, Date dataFinalPrestec) {
        this.idPrestec = idPrestec;
        this.idLector = idLector;
        this.ISBNLlibre = ISBNLlibre;
        this.dataIniciPrestec = new Date(); //data actual
        this.dataFinalPrestec = dataFinalPrestec;
        this.estatPrestec = 1; //prèstec en curs
    }

    public String getIdPrestec() {
        return this.idPrestec;
    }

    public void setIdPrestec(String nouIdPrestec) {
        this.idPrestec = nouIdPrestec;
    }

    public String getIdLector() {
        return this.idLector;
    }

    public void setIdLector(String nouIdLector) {
        this.idLector = nouIdLector;
    }

    public String getISBNLlibre() {
        return this.ISBNLlibre;
    }

    public void setISBNLlibre(String nouISBNLlibre) {
        this.ISBNLlibre = nouISBNLlibre;
    }

    public Date getDataIniciPrestec() {
        return this.dataIniciPrestec;
    }

    public void setDataIniciPrestec(Date novaDataInici) {
        this.dataIniciPrestec = novaDataInici;
    }

    public Date getDataFinalPrestec() {
        return this.dataFinalPrestec;
    }

    public void setDataFinalPrestec(Date novaDataFinal) {
        this.dataFinalPrestec = novaDataFinal;
    }

    public int getEstatPrestec() {
        return this.estatPrestec;
    }

    public void setEstatPrestec(int nouEstatPrestec) {
        this.estatPrestec = nouEstatPrestec;
    }


    @Override //TODO: Sempre fer aquesta estructura
    public String toString() {
        return "Prestec [idPrestec=" + idPrestec + ", idLector=" + idLector +
                ", ISBNLlibre=" + ISBNLlibre + ", dataIniciPrestec=" + dataIniciPrestec +
                ", dataFinalPrestec=" + dataFinalPrestec + ", estatPrestec=" + estatPrestec + "]";
    }

}