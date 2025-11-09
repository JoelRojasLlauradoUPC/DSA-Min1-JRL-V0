package edu.upc.dsa.services;


import edu.upc.dsa.Manager;
import edu.upc.dsa.ManagerImpl;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import java.util.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
//NOTE: Podria ser interessant canviar el Endpoint del servei, però si es canvia s'hauria de canviar a Api i Path.
@Api(value = "/biblioteca", description = "Endpoint to the service")
@Path("/biblioteca")
public class Service {

    private Manager myManager;

    public Service() {
        this.myManager = ManagerImpl.getInstance();
        //TODO: "Constructor" automàtic per omplir dades al inicialitzar el servei.
        //NOTE: Seria bó pre-inicialitzar el servei amb altres funcions d'entre les que s'implementen per tal que no es retornin dades buides

        if (myManager.size() == 0)
        {
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

    }

    //NOTE: INICI DELS SERVEIS REST

    /*
        INFO:
            - Necessari afegir aquí tots els serveis REST que es vulguin establir.
     */

    //NOTE: Mètodes POST

    @POST
    @ApiOperation(value = "Afegir nou lector", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful, creat", response = lector.class),
            @ApiResponse(code = 500, message = "Validation Error"),
    })
    @Path("/lectors/alta")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newLector(lector nouLector)
    {

        if (this.myManager.getLector(nouLector.getId()) == null) //si no hi ha cap lector amb el mateix id
        {
            lector lectorAAfegir = myManager.addLector(
                    nouLector.getId(),
                    nouLector.getNom(),
                    nouLector.getCognoms(),
                    nouLector.getDni(),
                    nouLector.getDataNaixement(),
                    nouLector.getLlocNaixement(),
                    nouLector.getDomicili()
            );
            return Response.status(201).entity(lectorAAfegir).build();
        }
        else
        {
            return Response.status(500).entity("Ja existeix un lector amb aquest ID").build();
        }

    }

    @POST
    @ApiOperation(value = "Afegir nou llibre al magatzem", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful, afegit al magatzem", response = lector.class),
            @ApiResponse(code = 500, message = "Validation Error"),
    })
    @Path("/magatzem/afegirLlibre")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newLlibre(llibre nouLlibre)
    {

        if (nouLlibre.getIsbn() == null || nouLlibre.getTitol() == null || nouLlibre.getEditorial() == null || nouLlibre.getAutor() == null)
        { //Revisar si s'han emplenat els camps obligatoris
            return Response.status(500).entity(nouLlibre).build();
        }
        llibre llibreAAfegir = myManager.addLlibre(
                nouLlibre.getIsbn(),
                nouLlibre.getTitol(),
                nouLlibre.getEditorial(),
                nouLlibre.getAnyPublicacio(),
                nouLlibre.getNumEdicio(),
                nouLlibre.getAutor(),
                nouLlibre.getTematica(),
                nouLlibre.getQuantitatExemplarsDisponibles()

        );
        return Response.status(201).entity(llibreAAfegir).build();

    }

    @POST
    @ApiOperation(value = "Catalogar el 1r llibre disponible", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful, catalogat", response = llibre.class),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/magatzem/catalogar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newCatalogacio()
    {
        llibre llibreCatalogat = myManager.catalogarLlibre();

        if (llibreCatalogat == null)
        {
            return Response.status(404).entity("No hi ha llibres per catalogar").build();
        }
        else{
            return Response.status(201).entity(llibreCatalogat).build();
        }

    }


    @POST
    @ApiOperation(value = "Realitzar un préstec", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful, creat", response = prestec.class),
            @ApiResponse(code = 500, message = "Validation Error"),
    })
    @Path("/prestecs/nou")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPrestec(prestec nouPrestec)
    {
        prestec prestecARealitzar = myManager.addPrestec(
                nouPrestec.getIdLector(),
                nouPrestec.getIsbnLlibre(),
                nouPrestec.getDataFinalPrestec()

        );
        if (prestecARealitzar == null)
        {
            return Response.status(500).entity("No s'ha pogut realitzar el prèstec...").build();
        }
        else
        {
            return Response.status(201).entity(prestecARealitzar).build();
        }

    }


    //NOTE: Mètodes GET


    @GET
    @ApiOperation(value = "Obtenir tots els llibres pendents de catalogar del magatzem", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = llibre.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/magatzem/disponibles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLlibresMagatzem() {

        List<llibre> llibresMagatzem = this.myManager.obtenirLlibresMagatzem();
        if (llibresMagatzem == null){
            return Response.status(404).entity("No hi ha llibres al magatzem").build();
        }
        else
        {
            GenericEntity<List<llibre>> entity = new GenericEntity<List<llibre>>(llibresMagatzem) {};
            return Response.status(201).entity(entity).build()  ;
        }

    }

    @GET
    @ApiOperation(value = "Obtenir tots els usuaris", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = lector.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/lectors")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {

        List<lector> lectorsDonatsAlta = this.myManager.obtenirTotsUsuaris();
        if (lectorsDonatsAlta == null){
            return Response.status(404).entity("No hi ha usuaris donats d'alta").build();
        }
        else
        {
            GenericEntity<List<lector>> entity = new GenericEntity<List<lector>>(lectorsDonatsAlta) {};
            return Response.status(201).entity(entity).build()  ;
        }

    }

    @GET
    @ApiOperation(value = "Obtenir tots els llibres que estan catalogats", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = llibre.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/cataleg")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLlibres() {

        List<llibre> llibresCatalogats = this.myManager.obtenirCatalegBiblioteca();
        if (llibresCatalogats == null){
            return Response.status(404).entity("No hi ha llibres catalogats").build();
        }
        else
        {
            GenericEntity<List<llibre>> entity = new GenericEntity<List<llibre>>(llibresCatalogats) {};
            return Response.status(201).entity(entity).build()  ;
        }

    }

    @GET
    @ApiOperation(value = "Obtenir tots els prestecs", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = prestec.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/prestecs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPrestecs() {

        List<prestec> prestecsRealitzats = this.myManager.obtenirPrestecs();
        if (prestecsRealitzats == null){
            return Response.status(404).entity("Encara no s'ha realitzat cap préstec").build();
        }
        else
        {
            GenericEntity<List<prestec>> entity = new GenericEntity<List<prestec>>(prestecsRealitzats) {};
            return Response.status(201).entity(entity).build();
        }

    }


    @GET
    @ApiOperation(value = "Obtenir els préstecs que ha realitzat un usuari concret", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = prestec.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("prestecs/lector/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPrestecsUsuari(@PathParam("id") String idUsuari) {
        List<prestec> prestecsUsuari = this.myManager.prestecsUsuari(idUsuari);

        if (prestecsUsuari == null)
        {
            return Response.status(404).entity("L'usuari encara no ha realitzat cap prèstec...").build();
        }
        else
        {
            GenericEntity<List<prestec>> entity = new GenericEntity<List<prestec>>(prestecsUsuari) {};
            return Response.status(201).entity(entity).build();
        }
    }


    //NOTE: Mètodes PUT


    @PUT
    @ApiOperation(value = "Finalitzar un préstec", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/prestecs/finalitzar/{id}")
    public Response finalitzarPrestec(@PathParam("id") String idPrestec) {

        prestec prestecAFinalitzar = this.myManager.finalitzarPrestec(idPrestec);

        if (prestecAFinalitzar == null)
        {
            return Response.status(500).entity("No s'ha trobat el préstec amb l'id de préstec facilitada").build();
        }
        else
        {
            return Response.status(200).entity(prestecAFinalitzar).build();
        }
    }


    @PUT
    @ApiOperation(value = "Modificar les dades d'un lector existent", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Validation error")
    })
    @Path("/lectors/modificar/{id}")
    public Response modificarDades(@PathParam("id") String idLector, lector lectorActualitzat) {

        if (idLector.equals(lectorActualitzat.getId()))
        {
            lector nouLector = this.myManager.modificarDadesLector(lectorActualitzat);

            if (nouLector == null)
            {
                return Response.status(500).entity("No s'ha trobat cap lector amb l'id facilitat").build();
            }
            else
            {
                return Response.status(200).entity(nouLector).build();
            }
        }
        else
        {
            return Response.status(500).entity("L'id del lector ha de coincidir amb el PathParam").build();
        }

    }

}