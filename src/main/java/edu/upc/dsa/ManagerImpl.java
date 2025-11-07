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
     */


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
    /*
        INFO:
            - Important tenir diverses maneres d'afegir amb el mateix nom per tal de poder
            atacar els diferents constructors
    */

    /*
        EXEMPLES:
            public Tracks addTrack(Tracks t)
            {
                logger.info("new Track " + t);

                this.tracks.add (t);
                logger.info("new Track added");
                return t;
            }

            public Tracks addTrack(String title, String singer)
            {
                return this.addTrack(null, title, singer);
            }

            public Tracks addTrack(String id, String title, String singer)
            {
                return this.addTrack(new Tracks(id, title, singer));
            }

        EXEMPLE COMPLET

            public Avion addAvion(String id, String modelo, String compania)
            {
            logger.info("addAvion: id=" + id + ", modelo=" + modelo + ", compania=" + compania);
            try
            {
                if (aviones.containsKey(id))
                {
                    Avion avion = aviones.get(id);
                    if (avion.getModelo().equals(modelo) && avion.getCompania().equals(compania))
                    {
                        logger.error("No se pueden usar los mismos valores para modelo y compañía en un ID existente");
                        throw new MismosParamConMismoIdException("El avión con ID " + id + " ya tiene esos valores.");
                    }
                    else
                    {
                        avion.setModelo(modelo);
                        avion.setCompania(compania);
                        logger.info("Avion actualizado: " + avion);
                    }

                }
                else
                {
                    Avion avion = new Avion(id, modelo, compania);
                    aviones.put(id, avion);
                    logger.info("Avion añadido: " + avion);
                }
            }
            catch (MismosParamConMismoIdException ex) //NOTE: Aquí van totes les excepcions que poden saltar excepcio1 | excepcio2 ex
            {
                logger.error("Excepción mismos parametros con mismo id: ", ex);
                return null;
            }
            return aviones.get(id);
        }
     */


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

}