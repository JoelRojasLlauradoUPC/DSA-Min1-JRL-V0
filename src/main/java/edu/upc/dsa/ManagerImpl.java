package edu.upc.dsa;

//import edu.upc.dsa.exceptions.TrackNotFoundException; //NOTE: en teoria no seria necessari

//NOTE: Importar els models
/*
    EXEMPLE:
        import edu.upc.dsa.models.Tracks;
 */

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import java.util.*;


import java.util.LinkedList;
import java.util.List;

import edu.upc.dsa.util.RandomUtils;
import org.apache.log4j.Logger;

public class ManagerImpl implements Manager {
    private static Manager instance;

    //TODO: Crear els elements de dades
    /*
        EXEMPLES:
            - protected List<Tracks> tracks;
            - private Map<String, Avion> aviones;
            - private Map<String, Vuelo> vuelos;
            - private Map<String, Usuario> usuarios;
            - List<Avion> AllAviones;
            - List<Vuelo> AllVuelos;
            - List<Usuario> AllUsuarios;
            List<Stack> llibresEmmagatzemats;
     */
    List<Stack<llibre>> magatzemLlibres;
    List<llibre> catalogatsLlibres;
    List<lector> lectors;
    List<prestec> prestecs;


    final static Logger logger = Logger.getLogger(ManagerImpl.class); //MANTENIR

    private ManagerImpl() {
        //TODO: Convertir a ArrayList o LinkedList
        /*
            INFO:
                - LinkedList actua com a Queue.
                - ArrayList actua com a Llista.
         */

        /*
            EXEMPLES:
                this.tracks = new LinkedList<>();
                this.aviones = new HashMap<>();
                this.AllVuelos = new ArrayList<>();
         */
        this.magatzemLlibres = new ArrayList<>();
        this.catalogatsLlibres = new ArrayList<>();
        this.lectors = new ArrayList<>();
        this.prestecs = new ArrayList<>();

    }

    public static Manager getInstance() {
        if (instance==null) instance = new ManagerImpl();
        return instance;
    }

    //TODO: Obtenir la mida d'una estructura de dades del tipus llista
    /*
        public int size()
        {
            int ret = this.tracks.size();
            logger.info("size " + ret);

            return ret;
        }
     */


    //TODO: Estructures per afegir elements

    public lector addLector(String id, String nom, String cognoms, String DNI, String dataNaixement, String llocNaixement, String domicili)
    {
        logger.info("addLector: id="+id+", nom=" + nom + ", cognoms=" + cognoms + ", DNI=" + DNI + ", dataNaixement=" + dataNaixement + ", llocNaixement=" + llocNaixement + ", domicili=" + domicili);

        return new lector(id, nom, cognoms, DNI, dataNaixement, llocNaixement, domicili);
    }


    public llibre addLlibre(String isbn, String titol, String editorial, String anyPublicacio, String numEdicio, String autor, String tematica, int quantitatExemplarsDisponibles)
    {

        llibre nouLlibre = new llibre(isbn, titol, editorial, anyPublicacio, numEdicio, autor, tematica, quantitatExemplarsDisponibles);
        logger.info("Afegint nou llibre amb isbn "+ isbn+" al magatzem...");

        int MAX_LL = 10; //nombre màxim de llibres per pila

        // Recorremos las pilas existentes
        for (Stack<llibre> pila : magatzemLlibres) {
            if (pila.size() < MAX_LL) {
                pila.push(nouLlibre);
                logger.info("Llibre amb isbn "+ isbn+ " afegit a una pila existent. Total llibres en la pila: " + pila.size());
                return nouLlibre;
            }
        }

        // Si cap pila existent té espais disponibles
        Stack<llibre> novaPila = new Stack<>();
        novaPila.push(nouLlibre);
        magatzemLlibres.add(novaPila);
        logger.info("Nova pila creada i llibre amb isbn "+ isbn+" afegit a la nova pila.");
        return nouLlibre;
    }


    public prestec addPrestec(String idPrestec, String idLector, String isbnLlibre, Date dataFinalPrestec) {

        return new prestec(idPrestec, idLector, isbnLlibre, dataFinalPrestec);
    }


    public lector getLector(String id) {
        for (lector l : this.lectors) {
            if (l.getId().equals(id)) {
                logger.info("Lector amb ID "+id+" ja existeix");
                return l; // se ha encontrado un lector con el mismo id
            }
        }
        logger.info("No existeix lector amb ID "+id);
        return null; // no se ha encontrado ningún lector con ese id
    }

    public llibre catalogarLlibre()
    {
        logger.info("Iniciant procediment per catalogar llibres...");

        try{
            if(this.magatzemLlibres.isEmpty()){
                throw new noLlibresPerCatalogar("No hi ha llibres pendents per catalogar.");
            }
            else{
                Stack<llibre> primeraPila = magatzemLlibres.get(0);
                llibre llibreACatalogar = primeraPila.pop();

                if(primeraPila.isEmpty()){ //si la primera pila ha quedat buida
                    magatzemLlibres.remove(0);
                }

                int posLlibreDuplicat = trobarPosicioPerISBN(llibreACatalogar.getIsbn());

                if (posLlibreDuplicat != -1){ //si hi ha un llibre amb el mateix isbn
                    logger.error("Ja existeix un llibre amb el mateix isbn. S'ha comptabilitzat un exemplar més disponible.");
                    int exemplarsDisponibles = this.catalogatsLlibres.get(posLlibreDuplicat).getQuantitatExemplarsDisponibles();
                    exemplarsDisponibles++;
                    this.catalogatsLlibres.get(posLlibreDuplicat).setQuantitatExemplarsDisponibles(exemplarsDisponibles);
                    return this.catalogatsLlibres.get(posLlibreDuplicat);
                }
                else //si no hi ha cap llibre amb el mateix isbn
                {
                    logger.error("Cap llibre té el mateix isbn. El llibre s'ha catalogat.");
                    this.catalogatsLlibres.add(llibreACatalogar);
                    return llibreACatalogar;
                }

            }

        }
        catch(noLlibresPerCatalogar ex){
            logger.error("El magatzem està buit, no hi ha llibres per catalogar.");
            return null;
        }
    }

    public int trobarPosicioPerISBN(String isbn) {
        for (int i = 0; i < this.catalogatsLlibres.size(); i++) {
            llibre l = this.catalogatsLlibres.get(i);
            if (l.getIsbn().equals(isbn)) {
                return i; // retorna la posició on s'ha trobat
            }
        }
        return -1; // si no s'ha trobat cap llibre amb aquest isbn
    }

    //TODO: Estructures per obtenir elements
    /*
        INFO:
            - No repetir el nom de les estructures per obtenir elements.
    */

    /*
        EXEMPLES:
            public Tracks getTrack(String id)
            {
                logger.info("getTrack("+id+")");

                for (Tracks t: this.tracks)
                {
                    if (t.getId().equals(id))
                    {
                        logger.info("getTrack("+id+"): "+t);

                        return t;
                    }
                }

                logger.warn("not found " + id);
                return null;
            }

            public Tracks getTrack2(String id) throws TrackNotFoundException
            {
                Tracks t = getTrack(id);
                if (t == null) throw new TrackNotFoundException();
                return t;
            }
    */


    //TODO: Retornar tots els elements
    /*
        INFO:
            - Si hem de filtrar, podem fer tractament de dades abans de retornar la llista.
     */

    /*
        EXEMPLE:
            public List<Tracks> findAll()
            {
                return this.tracks;
            }
     */

    //TODO: Eliminar un element en concret
    /*
        EXEMPLE:
            @Override
            public void deleteTrack(String id)
            {

                Tracks t = this.getTrack(id);
                if (t==null)
                {
                    logger.warn("not found " + t);
                }
                else logger.info(t+" deleted ");

                this.tracks.remove(t);
            }
     */

    //TODO: Actualitzar un element existent
    /*
        EXEMPLE:
         @Override
        public Tracks updateTrack(Tracks p)
        {
            Tracks t = this.getTrack(p.getId());

            if (t!=null)
            {
                logger.info(p+" rebut!!!! ");

                t.setSinger(p.getSinger());
                t.setTitle(p.getTitle());

                logger.info(t+" updated ");
            }
            else
            {
                logger.warn("not found "+p);
            }
            return t;
        }
     */


    //TODO: Eliminar tots els elements existents
    /*
        EXEMPLE:
            public void clear()
            {
                this.tracks.clear();
                logger.info("Sistema gestion cleared");
            }
     */
    public void clear()
    {
        this.magatzemLlibres.clear();
        this.catalogatsLlibres.clear();
        this.lectors.clear();
        this.prestecs.clear();
        logger.info("Sistema de gestió cleared");
    }
}