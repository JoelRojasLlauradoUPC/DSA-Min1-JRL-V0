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
    Manager tm;

    @Before
    public void setUp() {
        this.tm = ManagerImpl.getInstance();
        //TODO: "Constructor" automàtic per omplir dades al inicialitzar el servei.
        /*
            EXEMPLE:
                this.tm.addTrack("T1", "La Barbacoa", "Georgie Dann");
                this.tm.addTrack("T2", "Despacito", "Luis Fonsi");
                this.tm.addTrack("T3", "Ent3r S4ndm4n", "Metallica");
         */
    }

    //TODO: Clear.

    @After
    public void tearDown() {
        // És un Singleton
       this.tm.clear();
    }

    //TODO: Verificar els ADD
    /*
        EXEMPLE TEST ADD:

            @Test
            public void addTrackTest() { //NOTE: Canviar nom
                Assert.assertEquals(3, tm.size()); //NOTE: Condició que s'ha de complir x superar el Test

                this.tm.addTrack("La Vereda De La Puerta De Atrás", "Extremoduro"); //NOTE: Afegim un element

                Assert.assertEquals(4, tm.size()); //NOTE: Condició que s'ha de complir x superar el Test
            }
     */

    //TODO: Verificar els GET d'un ÚNIC element
    /*
        EXEMPLE TEST GET:

            @Test
            public void getTrackTest() throws Exception { //NOTE: Canviar nom
                Assert.assertEquals(3, tm.size());

                Tracks t = this.tm.getTrack("T2");
                Assert.assertEquals("Despacito", t.getTitle());
                Assert.assertEquals("Luis Fonsi", t.getSinger());

                t = this.tm.getTrack2("T2");
                Assert.assertEquals("Despacito", t.getTitle());
                Assert.assertEquals("Luis Fonsi", t.getSinger());

                Assert.assertThrows(TrackNotFoundException.class, () ->
                        this.tm.getTrack2("XXXXXXX"));

            }
     */

    //TODO: Verificar els GET de llistes ÚNIC element
    /*
        EXEMPLE TEST GET LLISTES:

            @Test
            public void getTracksTest() {
                Assert.assertEquals(3, tm.size());
                List<Tracks> tracks  = tm.findAll();

                Tracks t = tracks.get(0);
                Assert.assertEquals("La Barbacoa", t.getTitle());
                Assert.assertEquals("Georgie Dann", t.getSinger());

                t = tracks.get(1);
                Assert.assertEquals("Despacito", t.getTitle());
                Assert.assertEquals("Luis Fonsi", t.getSinger());

                t = tracks.get(2);
                Assert.assertEquals("Ent3r S4ndm4n", t.getTitle());
                Assert.assertEquals("Metallica", t.getSinger());

                Assert.assertEquals(3, tm.size());

            }
     */

    //TODO: Verificar els updates
    /*
        EXEMPLE UPATES:

            @Test
            public void updateTrackTest() {
                Assert.assertEquals(3, tm.size());
                Tracks t = this.tm.getTrack("T3");
                Assert.assertEquals("Ent3r S4ndm4n", t.getTitle());
                Assert.assertEquals("Metallica", t.getSinger());

                t.setTitle("Enter Sandman");
                this.tm.updateTrack(t);

                t = this.tm.getTrack("T3");
                Assert.assertEquals("Enter Sandman", t.getTitle());
                Assert.assertEquals("Metallica", t.getSinger());
            }
     */

    //TODO: Verificar el delete sencer
    /*
        EXEMPLE D'ELIMINAR TOTA LA LLISTA
                @Test
                public void deleteTrackTest() {
                    Assert.assertEquals(3, tm.size());
                    this.tm.deleteTrack("T3");
                    Assert.assertEquals(2, tm.size());

                    Assert.assertThrows(TrackNotFoundException.class, () ->
                            this.tm.getTrack2("T3"));

                }
    }
     */
