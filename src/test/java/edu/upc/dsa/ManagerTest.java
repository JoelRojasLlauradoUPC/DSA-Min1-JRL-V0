package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;

//NOTE: Importar els models
/*
    EXEMPLE:
        import edu.upc.dsa.models.Tracks;
 */

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import java.util.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ManagerTest {
    Manager myManager;

    @Before
    public void setUp() {
        this.myManager = ManagerImpl.getInstance();
        this.myManager.clear();
        //TODO: "Constructor" automàtic per omplir dades al inicialitzar el servei.

        lector lector1 = this.myManager.addLector("001", "Joel", "Rojas Llauradó", "39953195X", "24/07/2004", "Salou", "Carrer de Salou, 247");

        lector lector2 = this.myManager.addLector("002", "Dolores", "Fuertes de Rodilla", "45897635G", "14/08/2004", "Reus", "Carrer de Reus, 33");

        llibre llibre1 = this.myManager.addLlibre("9788497592208", "El nombre del viento", "Plaza & Janés",
                "2007", "1", "Patrick Rothfuss", "Fantasía", 1);

        llibre llibre2 = this.myManager.addLlibre("9788466344388", "Sapiens: De animales a dioses", "Debate",
                "2014", "3", "Yuval Noah Harari", "Historia", 1);

        llibre llibre3 = this.myManager.addLlibre("9788420432124", "1984", "Debolsillo",
                "1949", "2", "George Orwell", "Distopía", 1);

        llibre llibre4 = this.myManager.addLlibre("9788498383621", "Los juegos del hambre", "RBA",
                "2008", "1", "Suzanne Collins", "Ciencia ficción", 1);

        llibre llibre5 = this.myManager.addLlibre("9788413141799", "La sombra del viento", "Planeta",
                "2001", "5", "Carlos Ruiz Zafón", "Misterio", 1);

        llibre llibre6 = this.myManager.addLlibre("9788467035636", "Don Quijote de la Mancha", "Alfaguara",
                "1605", "7", "Miguel de Cervantes", "Clásico", 1);

        llibre llibre7 = this.myManager.addLlibre("9788490324863", "Cien años de soledad", "Sudamericana",
                "1967", "4", "Gabriel García Márquez", "Realismo mágico", 1);

        llibre llibre8 = this.myManager.addLlibre("9788497593798", "Harry Potter y la piedra filosofal", "Salamandra",
                "1997", "2", "J.K. Rowling", "Fantasía", 1);

        llibre llibre9 = this.myManager.addLlibre("9788437604947", "La casa de Bernarda Alba", "Cátedra",
                "1936", "1", "Federico García Lorca", "Teatro", 1);

        llibre llibre10 = this.myManager.addLlibre("9788408172178", "It", "Debolsillo",
                "1986", "3", "Stephen King", "Terror", 1);

        llibre llibre11 = this.myManager.addLlibre("9788420471833", "El principito", "Salamandra",
                "1943", "10", "Antoine de Saint-Exupéry", "Infantil", 1);

        llibre llibre12 = this.myManager.addLlibre("9788445071416", "Dune", "Debolsillo",
                "1965", "1", "Frank Herbert", "Ciencia ficción", 1);

    }


    @After
    public void tearDown()
    {
        this.myManager.clear();
    }


    @Test
    public void testAddLector()
    {
        this.myManager.addLector("001", "Joel", "Rojas Llauradó", "39953195X", "24/07/2004", "Salou", "Carrer de Salou, 247");

        lector lectorTest = ((ManagerImpl) this.myManager).getLector("001");
        Assert.assertEquals("Joel", lectorTest.getNom());
        Assert.assertEquals("Rojas Llauradó", lectorTest.getCognoms());
        Assert.assertEquals("39953195X", lectorTest.getDni());
        Assert.assertEquals("24/07/2004", lectorTest.getDataNaixement());
        Assert.assertEquals("Salou", lectorTest.getLlocNaixement());
        Assert.assertEquals("Carrer de Salou, 247", lectorTest.getDomicili());
    }


    @Test
    public void testModificarLector()
    {

        lector nouLectorActualitzat = this.myManager.addLector("002", "Carme", "Roig", "45897635G", "14/08/2004", "Salou", "Carrer de Reus, 33");

        lector lectorTest = ((ManagerImpl) this.myManager).modificarDadesLector(nouLectorActualitzat);
        Assert.assertEquals("Carme", lectorTest.getNom());
        Assert.assertEquals("Roig", lectorTest.getCognoms());
        Assert.assertEquals("45897635G", lectorTest.getDni());
        Assert.assertEquals("14/08/2004", lectorTest.getDataNaixement());
        Assert.assertEquals("Salou", lectorTest.getLlocNaixement());
        Assert.assertEquals("Carrer de Reus, 33", lectorTest.getDomicili());
    }


    @Test
    public void testAddLlibre()
    {
        this.myManager.addLlibre("9781780895159", "Star Wars: Thrawn", "Del Rey",
                "2017", "1", "Timothy Zahn", "Ciencia ficción", 3);

        List<llibre> llibreTest = ((ManagerImpl) this.myManager).obtenirLlibresMagatzem();
        llibre llibreAfegit = llibreTest.get(llibreTest.size() - 1);

        Assert.assertEquals("9781780895159", llibreAfegit.getIsbn());
        Assert.assertEquals("Star Wars: Thrawn", llibreAfegit.getTitol());
        Assert.assertEquals("Del Rey", llibreAfegit.getEditorial());
        Assert.assertEquals("2017", llibreAfegit.getAnyPublicacio());
        Assert.assertEquals("1", llibreAfegit.getNumEdicio());
        Assert.assertEquals("Timothy Zahn", llibreAfegit.getAutor());
        Assert.assertEquals("Ciencia ficción", llibreAfegit.getTematica());
        Assert.assertEquals(3, llibreAfegit.getQuantitatExemplarsDisponibles());
    }


    @Test
    public void testCatalogarLlibre()
    {
        this.myManager.catalogarLlibre(); //test catalogar llibre de més a munt 1a pila

        llibre llibreCatalogat = ((ManagerImpl) this.myManager).catalogatsLlibres.get(((ManagerImpl) this.myManager).catalogatsLlibres.size()-1);

        Assert.assertEquals("9788408172178", llibreCatalogat.getIsbn());
        Assert.assertEquals("It", llibreCatalogat.getTitol());
        Assert.assertEquals("Debolsillo", llibreCatalogat.getEditorial());
        Assert.assertEquals("1986", llibreCatalogat.getAnyPublicacio());
        Assert.assertEquals("3", llibreCatalogat.getNumEdicio());
        Assert.assertEquals("Stephen King", llibreCatalogat.getAutor());
        Assert.assertEquals("Terror", llibreCatalogat.getTematica());
        Assert.assertEquals(1, llibreCatalogat.getQuantitatExemplarsDisponibles());

        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();

        llibreCatalogat = this.myManager.catalogarLlibre(); //cataloguem 11 llibres en total, en teoria l'últim catalogat hauria de ser el de més amunt de la 2a pila

        Assert.assertEquals("9788445071416", llibreCatalogat.getIsbn());
        Assert.assertEquals("Dune", llibreCatalogat.getTitol());
        Assert.assertEquals("Debolsillo", llibreCatalogat.getEditorial());
        Assert.assertEquals("1965", llibreCatalogat.getAnyPublicacio());
        Assert.assertEquals("1", llibreCatalogat.getNumEdicio());
        Assert.assertEquals("Frank Herbert", llibreCatalogat.getAutor());
        Assert.assertEquals("Ciencia ficción", llibreCatalogat.getTematica());
        Assert.assertEquals(1, llibreCatalogat.getQuantitatExemplarsDisponibles());
    }


    @Test
    public void prestarLlibre()
    {
        this.myManager.catalogarLlibre(); //cataloguem tots els llibres
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();

        int posLlistaCatalogats = this.myManager.trobarPosicioPerISBN("9788420432124");
        int quantitatLlibresAbansPrestec = this.myManager.obtenirCatalegBiblioteca().get(posLlistaCatalogats).getQuantitatExemplarsDisponibles();

        this.myManager.addPrestec("001", "9788420432124","24/07/2026");
        prestec prestecRealitzat = ((ManagerImpl) this.myManager).prestecs.get(((ManagerImpl) this.myManager).prestecs.size()-1);

        int quantitatLlibresDespresPrestec = this.myManager.obtenirCatalegBiblioteca().get(posLlistaCatalogats).getQuantitatExemplarsDisponibles();

        Assert.assertEquals(quantitatLlibresAbansPrestec-1, quantitatLlibresDespresPrestec);

        Assert.assertEquals("001", prestecRealitzat.getIdLector());
        Assert.assertEquals("9788420432124", prestecRealitzat.getIsbnLlibre());
        Assert.assertEquals("24/07/2026", prestecRealitzat.getDataFinalPrestec());
    }


    @Test
    public void consultarPrestecsLector()
    {
        this.myManager.catalogarLlibre(); //cataloguem tots els llibres
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();
        this.myManager.catalogarLlibre();

        this.myManager.addPrestec("001", "9788497592208","24/07/2026");
        this.myManager.addPrestec("001", "9788466344388","24/07/2026");
        this.myManager.addPrestec("001", "9788408172178","24/07/2026");
        this.myManager.addPrestec("001", "9788498383621","24/07/2026");
        this.myManager.addPrestec("002", "9788413141799","24/07/2026");
        this.myManager.addPrestec("002", "9788445071416","24/07/2026");

        List<prestec> prestecsUser1= ((ManagerImpl) this.myManager).prestecsUsuari("001");
        List<prestec> prestecsUser2= ((ManagerImpl) this.myManager).prestecsUsuari("002");

        Assert.assertEquals("9788497592208", prestecsUser1.get(0).getIsbnLlibre());
        Assert.assertEquals("9788466344388", prestecsUser1.get(1).getIsbnLlibre());
        Assert.assertEquals("9788408172178", prestecsUser1.get(2).getIsbnLlibre());
        Assert.assertEquals("9788498383621", prestecsUser1.get(3).getIsbnLlibre());
        Assert.assertEquals("9788413141799", prestecsUser2.get(0).getIsbnLlibre());
        Assert.assertEquals("9788445071416", prestecsUser2.get(1).getIsbnLlibre());
    }


}


