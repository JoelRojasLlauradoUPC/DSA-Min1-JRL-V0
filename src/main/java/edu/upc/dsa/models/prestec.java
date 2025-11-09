package edu.upc.dsa.models;

import java.util.Date;

public class prestec {

    String idPrestec;
    String idLector;
    String isbnLlibre;
    String dataIniciPrestec;
    String dataFinalPrestec;
    int estatPrestec; //0 = prèstec finalitzat, 1 = prèstec en curs


    public prestec() {

    }


    public prestec(String idPrestec, String idLector, String isbnLlibre, String dataFinalPrestec) {
        this.idPrestec = idPrestec;
        this.idLector = idLector;
        this.isbnLlibre = isbnLlibre;
        this.dataIniciPrestec = new Date().toString(); //data actual
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


    public String getIsbnLlibre() {
        return this.isbnLlibre;
    }


    public void setIsbnLlibre(String nouIsbnLlibre) {
        this.isbnLlibre = nouIsbnLlibre;
    }


    public String getDataIniciPrestec() {
        return this.dataIniciPrestec;
    }


    public void setDataIniciPrestec(String novaDataInici) {
        this.dataIniciPrestec = novaDataInici;
    }


    public String getDataFinalPrestec() {
        return this.dataFinalPrestec;
    }


    public void setDataFinalPrestec(String novaDataFinal) {
        this.dataFinalPrestec = novaDataFinal;
    }


    public int getEstatPrestec() {
        return this.estatPrestec;
    }


    public void setEstatPrestec(int nouEstatPrestec) {
        this.estatPrestec = nouEstatPrestec;
    }

    public void marcarFinalPrestec() {
        this.estatPrestec = 0;
    }


    @Override //TODO: Sempre fer aquesta estructura
    public String toString() {
        return "Prestec [idPrestec=" + idPrestec + ", idLector=" + idLector +
                ", ISBNLlibre=" + isbnLlibre + ", dataIniciPrestec=" + dataIniciPrestec +
                ", dataFinalPrestec=" + dataFinalPrestec + ", estatPrestec=" + estatPrestec + "]";
    }

}