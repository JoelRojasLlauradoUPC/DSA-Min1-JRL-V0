package edu.upc.dsa.exceptions;

//TODO: Afegir un arxiu per cada excepció
/*
    INFO:
        - El nom del arxiu ha de ser el mateix que el de l'excepció.
        - Copiar tal qual l'exemple canviant el nom de classe i funció, (que han de coincidir amb el del fitxer).
 */
/*
    EXEMPLE:
        public class UsuarioNoExisteException extends Exception //NOTE: Canviar nom
        {
            public UsuarioNoExisteException(String message) //NOTE: Canviar nom
            {
                super(message);
            }
        }
 */

public class noExisteixLlibre extends Exception //NOTE: Canviar nom
{
    public noExisteixLlibre(String message) //NOTE: Canviar nom
    {
        super(message);
    }
}


