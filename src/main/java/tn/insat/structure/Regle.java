package tn.insat.structure;

import java.util.ArrayList;

/**
 * Created by Devcartha on 11/30/2015.
 */
public class Regle {

    private String nom;
    private ArrayList<Predicat> premisses;
    private Predicat conclusion;

    public Regle() {
    }

    public Regle(String nom, ArrayList<Predicat> premisses, Predicat conclusion) {
        this.nom = nom;
        this.premisses = premisses;
        this.conclusion = conclusion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Predicat> getPremisses() {
        return premisses;
    }

    public void setPremisses(ArrayList<Predicat> premisses) {
        this.premisses = premisses;
    }

    public Predicat getConclusion() {
        return conclusion;
    }

    public void setConclusion(Predicat conclusion) {
        this.conclusion = conclusion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Regle regle = (Regle) o;

        if (nom != null ? !nom.equals(regle.nom) : regle.nom != null) return false;
        if (premisses != null ? !premisses.equals(regle.premisses) : regle.premisses != null) return false;
        return !(conclusion != null ? !conclusion.equals(regle.conclusion) : regle.conclusion != null);

    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (premisses != null ? premisses.hashCode() : 0);
        result = 31 * result + (conclusion != null ? conclusion.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Regle{" +
                "nom='" + nom + '\'' +
                ", premisses = " + premisses +
                ", conclusion = " + conclusion +
                '}';
    }
}
