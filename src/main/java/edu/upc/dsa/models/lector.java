package edu.upc.dsa.models;
import edu.upc.dsa.util.RandomUtils;
import org.glassfish.grizzly.http.io.NIOOutputStream;

public class lector {

    String id;
    String nom;
    String cognoms;
    String DNI;
    String dataNaixement;
    String llocNaixement;
    String domicili;

    public lector() {

    }
    public lector(String id, String nom, String cognoms, String DNI, String dataNaixement, String llocNaixement, String domicili) {
        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
        this.DNI = DNI;
        this.dataNaixement = dataNaixement;
        this.llocNaixement = llocNaixement;
        this.domicili = domicili;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id=id;
    }


    public String getNom() {
        return this.nom;
    }

    public void setNom(String nouNom) {
        this.nom = nouNom;
    }

    public String getCognoms() {
        return this.cognoms;
    }

    public void setDNI(String nouDNI) {
        this.DNI = nouDNI;
    }

    public String getDNI() {
        return this.DNI;
    }

    public String getDataNaixement() {
        return this.dataNaixement;
    }

    public void setDataNaixement(String novaDataNaixement) {
        this.dataNaixement = novaDataNaixement;
    }

    public String getLlocNaixement() {
        return this.llocNaixement;
    }

    public void setLlocNaixement(String nouLlocNaixement) {
        this.llocNaixement = nouLlocNaixement;
    }

    public String getDomicili() {
        return this.domicili;
    }

    public void setDomicili(String nouDomicili) {
        this.domicili = nouDomicili;
    }


    @Override //TODO: Sempre fer aquesta estructura
    public String toString() {
        return "Lector [id=" + id + ", nom=" + nom + ", cognoms=" + cognoms +
                ", DNI=" + DNI + ", dataNaixement=" + dataNaixement +
                ", llocNaixement=" + llocNaixement + ", domicili=" + domicili + "]";
    }

}