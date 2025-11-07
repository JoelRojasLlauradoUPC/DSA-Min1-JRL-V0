package edu.upc.dsa;
import edu.upc.dsa.models.*;

import java.util.Date;


public interface Manager {

    //TODO: Aquí va la “declaració” de les funcions que implementarà el manager.
    /*
    EXEMPLE:
        public void clear();
        public int size();
    */


    public lector addLector(String id, String nom, String cognoms, String DNI, String dataNaixement, String llocNaixement, String domicili);
    public lector getLector(String id);
    public llibre addLlibre(String ISBN, String titol, String editorial, String anyPublicacio, String numEdicio, String autor, String tematica, int quantitatExemplarsDisponibles);
    public llibre catalogarLlibre();
    public int trobarPosicioPerISBN(String isbn);



    /*
    //NOTE: Gestió lectors
    //public lector updateLector(lector newLector);

    //NOTE: Gestió llibres
    public llibre updateLlibre(llibre newLlibre);
     public llibre getLlibre(String ISBN);

     //NOTE: Emmagatzemar/Catalogar llibres
     public void catalogarLlibre(String ISBN);

     //NOTE: Gestió prèstecs
     public prestec addPrestec(String idPrestec, String idLector, String ISBNLlibre, Date dataFinalPrestec);
     public prestec updatePrestec(prestec newPrestec);
     public prestec getPrestec(String ISBN);

     //NOTE: Finalitzar prèstecs
     public prestec finalitzarPrestec(String ISBN);

     //NOTE: Control servei

     public int size();
     */
    public void clear();
}


