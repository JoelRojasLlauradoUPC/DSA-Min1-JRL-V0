package edu.upc.dsa.exceptions;

//TODO: Afegir un arxiu per cada excepció
/*
    INFO:
        - El nom del arxiu ha de ser el mateix que el de l'excepció.
        - Copiar tal qual l'exemple canviant el nom de classe i funció, (que han de coincidir amb el del fitxer).
 */


public class noUnitatsDisponibles extends Exception
{
    public noUnitatsDisponibles(String message)
    {
        super(message);
    }
}