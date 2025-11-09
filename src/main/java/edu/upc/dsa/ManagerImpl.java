package edu.upc.dsa;

//NOTE: Importar els models

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import java.util.*;

import java.util.LinkedList;
import java.util.List;

import edu.upc.dsa.util.RandomUtils;
import org.apache.log4j.Logger;

public class ManagerImpl implements Manager
{
    private static Manager instance;

    //NOTE: Creació dels elements de dades
    List<lector> lectors;

    List<Stack<llibre>> magatzemLlibres;
    List<llibre> catalogatsLlibres;

    List<prestec> prestecs;

    final static Logger logger = Logger.getLogger(ManagerImpl.class); //MANTENIR


    private ManagerImpl()
    {
        this.magatzemLlibres = new ArrayList<>();
        this.catalogatsLlibres = new ArrayList<>();
        this.lectors = new ArrayList<>();
        this.prestecs = new ArrayList<>();
    }


    public static Manager getInstance()
    {
        if (instance==null) instance = new ManagerImpl();
        return instance;
    }


    //NOTE: Métodes add----------()


    public lector addLector(String id, String nom, String cognoms, String dni, String dataNaixement, String llocNaixement, String domicili)
    {
        logger.info("===== Afegint lector =====");
        logger.info("addLector: id="+id+", nom=" + nom + ", cognoms=" + cognoms + ", DNI=" + dni + ", dataNaixement=" + dataNaixement + ", llocNaixement=" + llocNaixement + ", domicili=" + domicili);
        lector nouLector = new lector(id, nom, cognoms, dni, dataNaixement, llocNaixement, domicili);
        logger.info("===== Lector afegit =====");
        this.lectors.add(nouLector);

        return nouLector;
    }


    public llibre addLlibre(String isbn, String titol, String editorial, String anyPublicacio, String numEdicio, String autor, String tematica, int quantitatExemplarsDisponibles)
    {
        logger.info("===== Afegint nou llibre amb isbn "+ isbn+" al magatzem... =====");
        llibre nouLlibre = new llibre(isbn, titol, editorial, anyPublicacio, numEdicio, autor, tematica, quantitatExemplarsDisponibles);

        int MAX_LL = 10; //nombre màxim de llibres per pila

        for (Stack<llibre> pila : magatzemLlibres) //per cada pila
        {
            if (pila.size() < MAX_LL) //si la pila té espais disponibles
            {
                pila.push(nouLlibre);
                logger.info("===== Llibre amb isbn "+ isbn+ " afegit a una pila existent. Total llibres en la pila: " + pila.size()+". =====");

                return nouLlibre;
            }

        }

        // Si cap pila existent té espais disponibles
        Stack<llibre> novaPila = new Stack<>();
        novaPila.push(nouLlibre);
        magatzemLlibres.add(novaPila);
        logger.info("===== Nova pila creada i llibre amb isbn "+ isbn+" afegit a la nova pila. =====");

        return nouLlibre;
    }


    public prestec addPrestec(String idLector, String IsbnLlibre, String dataFinalPrestec)
    {
        logger.info("===== Iniciant procediment per prestar llibre =====");
        logger.info("- ISBN del llibre a prestar: "+IsbnLlibre);
        logger.info("- ID del lector al que es realitzarà el prèstec: "+idLector);

        try
        {
            if (getLector(idLector) == null) //si no existeix lector amb id facilitat
            {
                throw new noExisteixLector("El lector amd l'ID: "+idLector+" no està donat d'alta!");
            }
            else
            {
                int posLlibreEnIndex = trobarPosicioPerISBN(IsbnLlibre);
                if (posLlibreEnIndex== -1) //si no s'ha trobat el llibre amb l'isbn = no existeix
                {
                    throw new noExisteixLlibre("El llibre amb ISBN: "+IsbnLlibre+" no consta a la biblioteca!");
                }
                else //En cas que existeixi el llibre amb l'isbn
                {
                    if(this.catalogatsLlibres.get(posLlibreEnIndex).getQuantitatExemplarsDisponibles()==0) //si no hi ha unitats disponibles
                    {
                        throw new noUnitatsDisponibles("El llibre amb ISBN: "+IsbnLlibre+" no té unitats disponibles!");
                    }
                    else //si hi ha unitats disponibles
                    {
                        this.catalogatsLlibres.get(posLlibreEnIndex).prestarUnLlibre();

                        String propostaIDPrestec = RandomUtils.getId(); //generar proposta inicial de ID
                        while (getIdPrestec(propostaIDPrestec) != null) //mentre hi hagi algun prestec amb el mateix ID de prestec
                        {
                            propostaIDPrestec = RandomUtils.getId(); //generem nou ID
                        }

                        prestec nouPrestec = new prestec(propostaIDPrestec, idLector, IsbnLlibre, dataFinalPrestec);
                        this.prestecs.add(nouPrestec);
                        logger.info("===== Prèstec realitzat correctament! =====");

                        return nouPrestec;

                    }

                }

            }

        }
        catch (noExisteixLector | noExisteixLlibre | noUnitatsDisponibles ex)
        {
            logger.info("===== ATENCIÓ: Error realitzant prèstec: "+ex.getMessage()+"=====");

            return null;
        }

    }


    //NOTE: Métodes get/obtenir----------()


    public prestec getIdPrestec(String id)
    {
        logger.error("===== Iniciant procediment per verificar existència d'un prèstec amb el mateix ID... =====");

        for (prestec prestecActual : this.prestecs) //per cada prèstec dins el vector de prèstecs
        {
            if (prestecActual.getIdPrestec().equals(id)) //si coincideixen les ids
            {
                logger.info("===== Prestec amb ID "+id+" ja existeix. =====");

                return prestecActual; //s'ha trobat un prestec amb el mateix id
            }

        }

        logger.info("===== No existeix prestec amb ID "+id+". =====");

        return null; // si no es troba cap prestec amb el mateix id

    }


    public lector getLector(String id)
    {
        logger.error("===== Iniciant procediment per verificar existència d'un lector amb el mateix ID... =====");

        for (lector l : this.lectors) //per cada lector
        {
            if (l.getId().equals(id)) //si coincideixen les ids
            {
                logger.info("Lector amb ID "+id+" ja existeix");

                return l; //s'ha trobat un lector amb el mateix id
            }

        }

        logger.info("No existeix lector amb ID "+id);

        return null; //no s'ha trobat cap lector amb el mateix id
    }


    public int trobarPosicioPerISBN(String isbn)
    {
        logger.error("===== Iniciant procediment per trobar la posició per ISBN "+isbn+"... =====");

        for (int i = 0; i < this.catalogatsLlibres.size(); i++) //per cada llibre dins la llista de catalogats
        {
            llibre l = this.catalogatsLlibres.get(i);
            if (l.getIsbn().equals(isbn)) //si coincideixen els isbn
            {
                logger.error("===== El llibre amb ISBN "+isbn+" s'ha trobat a la posició "+i+". =====");

                return i; //retorna la posició on s'ha trobat l'isbn
            }

        }

        logger.error("===== No s'ha trobat l'ISBN "+isbn+" a la llista de llibres. =====");

        return -1; //si no s'ha trobat cap llibre amb aquest isbn

    }


    public List<llibre> obtenirLlibresMagatzem()
    {
        logger.error("===== Iniciant procediment per obtenir tots els llibres disponibles al magatzem... =====");

        List<llibre> llibresMagatzem = new ArrayList<>();

        for (Stack<llibre> pilaActual : this.magatzemLlibres) //per cada llibre que hi hagi al magatzem
        {
            for (llibre llibreActual : pilaActual)
            {
                llibresMagatzem.add(llibreActual);
            }

        }

        if (llibresMagatzem.isEmpty()) //si el magatzem està buit
        {
            logger.error("===== No hi ha cap llibre al magatzem. =====");

            return null;
        }
        else //si hi ha llibres al magatzem
        {
            logger.error("===== Hi ha un total de "+llibresMagatzem.size()+" llibres disponibles al magatzem. =====");

            return llibresMagatzem;
        }

    }


    public List<lector> obtenirTotsUsuaris()
    {
        logger.error("===== Iniciant procediment per obtenir tots els usuaris donats d'alta... =====");

        List<lector> usuarisAlta = new ArrayList<>();

        for (lector lectorActual : this.lectors) //per cada usuari
        {
            usuarisAlta.add(lectorActual);
        }

        if (usuarisAlta.isEmpty()) //si no hi ha usuaris donats d'alta
        {
            logger.error("===== No hi ha cap usuari donat d'alta. =====");

            return null;
        }
        else //si hi ha usuaris donats d'alta
        {
            logger.error("===== Hi ha un total de "+usuarisAlta.size()+" usuaris donats d'alta. =====");

            return usuarisAlta;
        }

    }


    public List<llibre> obtenirCatalegBiblioteca()
    {
        logger.error("===== Iniciant procediment per obtenir tots els llibres que consten en el registre de catalogats... =====");

        List<llibre> llibresCatalogats = new ArrayList<>();

        for (llibre llibreActual : this.catalogatsLlibres) //per cada llibre
        {
            llibresCatalogats.add(llibreActual);
        }

        if (llibresCatalogats.isEmpty()) //si no hi ha llibres catalogats
        {
            logger.error("===== No hi ha cap llibre catalogat. =====");

            return null;
        }
        else //si hi ha llibres catalogats
        {
            logger.error("===== Hi ha un total de "+llibresCatalogats.size()+" llibres catalogats. =====");

            return llibresCatalogats;
        }

    }

    public List<prestec> prestecsUsuari(String idUsuari)
    {
        logger.error("===== Iniciant procediment per obtenir tots els préstecs realitzats a l'usuari amd ID: "+idUsuari+"... =====");

        List<prestec> prestecsRealitzats = new ArrayList<>();

        for (prestec prestecActual : this.prestecs) //per cada préstec
        {
            if (prestecActual.getIdLector().equals(idUsuari)) //revisar si l id de l'usuari coincideix amb l'associada al préstec
            {
                prestecsRealitzats.add(prestecActual);
            }

        }

        if (prestecsRealitzats.isEmpty()) //si no s'han realitzat prestecs
        {
            logger.error("===== L'usuari encara no ha realitzat cap préstec. =====");

            return null;
        }
        else //si s'han realitzat prestecs
        {
            logger.error("===== L'usuari ha realitzat un total de "+prestecsRealitzats.size()+" préstecs. =====");

            return prestecsRealitzats;
        }
    }


    public List<prestec> obtenirPrestecs()
    {
        logger.error("===== Iniciant procediment per obtenir tots els préstecs que consten en el registre... =====");

        List<prestec> prestecsRealitzats = new ArrayList<>();

        for (prestec prestecActual : this.prestecs) //per cada préstec
        {
            prestecsRealitzats.add(prestecActual);
        }

        if (prestecsRealitzats.isEmpty()) //si no s'han realitzat prestecs
        {
            logger.error("===== Encara no s'ha realitzat cap préstec. =====");

            return null;
        }
        else //si s'han realitzat prestecs
        {
            logger.error("===== S'han realitzat un total de "+prestecsRealitzats.size()+" préstecs. =====");

            return prestecsRealitzats;
        }

    }


    //NOTE: Funcionalitats específiques de biblioteca


    public llibre catalogarLlibre()
    {
        logger.info("===== Iniciant procediment per catalogar llibres... =====");

        try
        {
            if(this.magatzemLlibres.isEmpty()) //si no hi ha cap llibre al magatzem pendent de ser catalogat
            {
                throw new noLlibresPerCatalogar("No hi ha llibres pendents per catalogar.");
            }
            else
            {
                llibre llibreACatalogar = magatzemLlibres.get(0).pop(); //marquem el llibre de dalt de tot de la pila en la pos 0


                if(magatzemLlibres.get(0).isEmpty()) //si la pila de la pos 0 ha quedat buida l'eliminem
                {
                    magatzemLlibres.remove(0);
                }

                int posLlibreDuplicat = trobarPosicioPerISBN(llibreACatalogar.getIsbn()); //revisem si ja hi ha exemplars catalogats

                if (posLlibreDuplicat != -1) //si ja hi ha exemplars catalogats amb el mateix isbn
                {
                    logger.error("Ja existeix un llibre amb el mateix isbn("+llibreACatalogar.getIsbn()+"). S'ha comptabilitzat un exemplar més disponible.");
                    int exemplarsDisponibles = this.catalogatsLlibres.get(posLlibreDuplicat).getQuantitatExemplarsDisponibles();
                    exemplarsDisponibles++;
                    this.catalogatsLlibres.get(posLlibreDuplicat).setQuantitatExemplarsDisponibles(exemplarsDisponibles);
                    logger.error("===== Ha finalitzat el procés de catalogació amb èxit =====");

                    return this.catalogatsLlibres.get(posLlibreDuplicat);
                }
                else //si no hi ha cap llibre amb el mateix isbn ja catalogat
                {
                    logger.error("Cap llibre té el mateix isbn("+llibreACatalogar.getIsbn()+"). El llibre de la pila de més amunt de la pila 0 s'ha catalogat amb èxit!");
                    this.catalogatsLlibres.add(llibreACatalogar);
                    logger.error("===== Ha finalitzat el procés de catalogació amb èxit =====");

                    return llibreACatalogar;
                }

            }

        }
        catch(noLlibresPerCatalogar ex)
        {
            logger.error("===== El magatzem està buit, no hi ha llibres per catalogar. =====");

            return null;
        }

    }


    public prestec finalitzarPrestec(String idPrestec)
    {
        logger.error("===== Iniciant procediment per finalitzar prèstec... =====");

        for (prestec prestecActual : this.prestecs) //per cada prèstec dins el vector de prèstecs
        {
            if (prestecActual.getIdPrestec().equals(idPrestec)) //si coincideixen les ids
            {
                logger.info("===== Prestec amb ID "+idPrestec+" trobat! =====");
                prestecActual.marcarFinalPrestec();

                int posicioCataleg = trobarPosicioPerISBN(prestecActual.getIsbnLlibre());
                this.catalogatsLlibres.get(posicioCataleg).retornarUnLlibre();

                return prestecActual; //s'ha trobat un prestec amb el mateix id
            }

        }

        logger.info("===== No existeix prestec amb ID "+idPrestec+" =====");

        return null; // si no es troba cap prestec amb el mateix id

    }


    public lector modificarDadesLector(lector nouLectorActualitzat)
    {
        logger.info("===== Iniciat procediment d'actualització de dades d'usuari =====");
        try
        {
            if (this.getLector(nouLectorActualitzat.getId())==null) //si no hi ha cap lector amb l'id facilitada
            {
                throw new noExisteixLector("El lector amd l'ID: "+nouLectorActualitzat.getId()+" no està donat d'alta!");
            }
            else
            {
                int i = 0;

                while (i < this.lectors.size()) //per cada lector fins que es trobi l'id
                {
                    if (this.lectors.get(i).getId().equals(nouLectorActualitzat.getId())) //quan es trobi l'id
                    {

                        this.lectors.set(i, nouLectorActualitzat);
                        logger.info("===== Dades actualitzades correctament. =====");

                        return nouLectorActualitzat;
                    }
                    i++;
                }

                return null;

            }
        }
        catch (noExisteixLector ex)
        {
            logger.info("===== ATENCIÓ: Error actualitzant dades: "+ex.getMessage()+"=====");

            return null;
        }
    }


    //NOTE: Métodes de control


    public int size(){
        logger.info("===== S'ha executat el procediment per verificar la presència de llibres en el sistema. =====");

        return  this.magatzemLlibres.size() + this.catalogatsLlibres.size();
    }


    public void clear()
    {
        logger.info("===== Iniciant procediment per buidar el sistema de gestió... =====");

        this.magatzemLlibres.clear();
        this.catalogatsLlibres.clear();
        this.lectors.clear();
        this.prestecs.clear();

        logger.info("===== El procediment per buidar el sistema de gestió ha finalitzat amb èxit!");
    }

}