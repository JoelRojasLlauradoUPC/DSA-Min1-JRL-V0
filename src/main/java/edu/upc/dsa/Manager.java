package edu.upc.dsa;
import edu.upc.dsa.models.*;
import java.util.*;


import java.util.Date;


public interface Manager
{
    //NOTE: Aquí va la “declaració” de les funcions que implementarà el manager.

    //LECTOR
    public lector addLector(String id, String nom, String cognoms, String dni, String dataNaixement, String llocNaixement, String domicili);
    public lector modificarDadesLector(lector nouLectorActualitzat);
    public lector getLector(String id);
    public List<lector> obtenirTotsUsuaris();

    //LLIBRE
    public llibre addLlibre(String ISBN, String titol, String editorial, String anyPublicacio, String numEdicio, String autor, String tematica, int quantitatExemplarsDisponibles);
    public llibre catalogarLlibre();
    public int trobarPosicioPerISBN(String isbn);
    public List<llibre> obtenirLlibresMagatzem();
    public List<llibre> obtenirCatalegBiblioteca();

    //PRÉSTEC
    public prestec addPrestec(String idLector, String ISBNLlibre, String dataFinalPrestec);
    public prestec finalitzarPrestec(String idPrestec);
    public List<prestec> obtenirPrestecs();
    public List<prestec> prestecsUsuari(String idUsuari);

    //CONTROL
    public int size();
    public void clear();
}


