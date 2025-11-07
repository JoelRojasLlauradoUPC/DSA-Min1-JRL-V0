package edu.upc.dsa.services;


import edu.upc.dsa.Manager;
import edu.upc.dsa.ManagerImpl;

//NOTE: Importar els models
/*
    EXEMPLE:
        import edu.upc.dsa.models.Tracks;
 */

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
@Api(value = "/offeredService", description = "Endpoint to the service")
@Path("/offeredService")
public class Service {

    private Manager myManager;

    public Service() {
        this.myManager = ManagerImpl.getInstance();
        //TODO: "Constructor" automàtic per omplir dades al inicialitzar el servei.
        /*
            EXEMPLE:
            if (myManager.size()==0)
            {
                this.myManager.addTrack("La Barbacoa", "Georgie Dann");
                this.myManager.addTrack("Despacito", "Luis Fonsi");
                this.myManager.addTrack("Enter Sandman", "Metallica");
            }
            //NOTE: Seria bó pre-inicialitzar el servei amb altres funcions d'entre les que s'implementen per tal que no es retornin dades buides
         */



    }

    //NOTE: INICI DELS SERVEIS REST
    /*
        INFO:
            - Necessari afegir aquí tots els serveis REST que es vulguin establir.
     */

    //TODO: Mètodes GET
    /*
        EXEMPLE MÈTODE GET per una LLISTA:

            @GET
            @ApiOperation(value = "get all Track", notes = "asdasd") //NOTE: Modificar la descripció
            @ApiResponses(value = {
                    @ApiResponse(code = 201, message = "Successful", response = Tracks.class, responseContainer="List"),
            })
            @Path("/") //NOTE: S'hauria de modificar el Path
            @Produces(MediaType.APPLICATION_JSON)
            public Response getTracks() {

                List<Tracks> tracks = this.tm.findAll();

                GenericEntity<List<Tracks>> entity = new GenericEntity<List<Tracks>>(tracks) {};
                return Response.status(201).entity(entity).build()  ;

            }
     */

    /*
        EXEMPLE MÈTODE GET per un ÚNIC ELEMENT:

            @GET
            @ApiOperation(value = "get a Track", notes = "asdasd") //NOTE: Modificar la descripció
            @ApiResponses(value = {
                    @ApiResponse(code = 201, message = "Successful", response = Tracks.class),
                    @ApiResponse(code = 404, message = "Not found")
            })
            @Path("/{id}") //NOTE: Si es vol modificar el Path
            @Produces(MediaType.APPLICATION_JSON)
            public Response getTrack(@PathParam("id") String id) {
                Tracks t = this.tm.getTrack(id);
                if (t == null) return Response.status(404).build();
                else  return Response.status(201).entity(t).build();
            }
     */

    //TODO: Mètodes DELETE
    /*
        EXEMPLE:

            @DELETE
            @ApiOperation(value = "delete a Track", notes = "asdasd") //NOTE: Modificar la descripció
            @ApiResponses(value = {
                    @ApiResponse(code = 201, message = "Successful"),
                    @ApiResponse(code = 404, message = "Not found")
            })
            @Path("/{id}") //NOTE: Si es vol modificar el Path
            public Response deleteTrack(@PathParam("id") String id) {
                Tracks t = this.tm.getTrack(id);
                if (t == null) return Response.status(404).build();
                else this.tm.deleteTrack(id);
                return Response.status(201).build();
            }
     */

    //TODO: Mètodes PUT (per actualitzar elements existents)
    /*
        EXEMPLE MÈTODE PUT:

            @PUT
            @ApiOperation(value = "update a Track", notes = "asdasd") //NOTE: Modificar la descripció
            @ApiResponses(value = {
                    @ApiResponse(code = 201, message = "Successful"),
                    @ApiResponse(code = 404, message = "Not found")
            })
            @Path("/") //NOTE: S'hauria de modificar el Path
            public Response updateTrack(Tracks track) {

                Tracks t = this.tm.updateTrack(track);

                if (t == null) return Response.status(404).build();

                return Response.status(201).build();
            }
     */

    //TODO: Mètodes POST (per crear un nou element)
    /*
        EXEMPLE MÈTODE POST:

            @POST
            @ApiOperation(value = "create a new Avion", notes = "asdasd") //NOTE: Modificar descripció
            @ApiResponses(value = { //NOTE: Modificar classe a la que fan referència
                    @ApiResponse(code = 201, message = "Successful, creado", response = Avion.class),
                    @ApiResponse(code = 200, message = "Successful, actualizado", response = Avion.class),
                    @ApiResponse(code = 500, message = "Validation Error"),
                    @ApiResponse(code = 409, message = "Parámetros duplicados")
            })
            @Path("/avion") //NOTE: Modificar path
            @Consumes(MediaType.APPLICATION_JSON)
            public Response newAvion(Avion avion)
            {
                if (avion.getId() == null || avion.getModelo() == null || avion.getCompania() == null)
                {
                    return Response.status(500).entity(avion).build();
                }

                boolean existiaPreviamente = sistemaGestion.getAvion(avion.getId()) != null;
                Avion avionResultado = sistemaGestion.addAvion(
                        avion.getId(),
                        avion.getModelo(),
                        avion.getCompania()
                );
                if (avionResultado == null)
                {
                    return Response.status(409).entity("Mismo ID con mismos parámetros").build();
                }
                if (existiaPreviamente)
                {
                    return Response.status(200).entity(avionResultado).build();
                } else
                {
                    return Response.status(201).entity(avionResultado).build();
                }
            }
     */

}