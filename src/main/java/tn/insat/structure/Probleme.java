package tn.insat.structure;

import java.util.ArrayList;

/**
 * Created by Devcartha on 12/1/2015.
 */
public class Probleme {
    private String nom;
    private ArrayList<Regle> baseDeRegles;
    private ArrayList<Etat> espaceDEtats;

    public Probleme() {
    }

    public Probleme(String nom, ArrayList<Regle> baseDeRegles, ArrayList<Etat> espaceDEtats) {
        this.nom = nom;
        this.baseDeRegles = baseDeRegles;
        this.espaceDEtats = espaceDEtats;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Regle> getBaseDeRegles() {
        return baseDeRegles;
    }

    public void setBaseDeRegles(ArrayList<Regle> baseDeRegles) {
        this.baseDeRegles = baseDeRegles;
    }

    public ArrayList<Etat> getEspaceDEtats() {
        return espaceDEtats;
    }

    public void setEspaceDEtats(ArrayList<Etat> espaceDEtats) {
        this.espaceDEtats = espaceDEtats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Probleme probleme = (Probleme) o;

        if (nom != null ? !nom.equals(probleme.nom) : probleme.nom != null) return false;
        if (baseDeRegles != null ? !baseDeRegles.equals(probleme.baseDeRegles) : probleme.baseDeRegles != null)
            return false;
        return !(espaceDEtats != null ? !espaceDEtats.equals(probleme.espaceDEtats) : probleme.espaceDEtats != null);

    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (baseDeRegles != null ? baseDeRegles.hashCode() : 0);
        result = 31 * result + (espaceDEtats != null ? espaceDEtats.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Probleme{" +
                "nom='" + nom + '\'' +
                ", baseDeRegles=" + baseDeRegles +
                ", espaceDEtats=" + espaceDEtats +
                '}';
    }
}
