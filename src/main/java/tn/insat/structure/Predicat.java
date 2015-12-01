package tn.insat.structure;

import java.util.ArrayList;

/**
 * Created by Devcartha on 11/30/2015.
 */
public class Predicat {

    private String nom;
    private ArrayList<String> variables;

    public Predicat() {
        variables = new ArrayList<String>();
    }

    public Predicat(String nom, ArrayList<String> variables) {
        this.nom = nom;
        this.variables = variables;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<String> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<String> variables) {
        //this.variables = variables;
        for (String var : variables) {
            this.variables.add(extraireFonction(var));
        }
    }

    public void setVariablesInstancies(ArrayList<String> variables) {
        this.variables = variables;
    }

    private String extraireFonction(String expr) {
        //System.out.println(expr);
        if (expr.contains("-")) {
            if (expr.contains("+")) {
                String fonctionPlus = "+(" + expr.substring(expr.indexOf("+") - 2, expr.indexOf("+")) + "," + expr.substring(expr.indexOf("+") + 1, expr.indexOf("+") + 2) + ")";
                String fonctionMoin = "-(" + expr.substring(0, expr.indexOf("-")) + "," + fonctionPlus + ")";
                return fonctionMoin;
            }
            if (expr.indexOf("?") == 0) {
                String fonctionMoin = "-(" + expr.substring(expr.indexOf("-") - 2, expr.indexOf("-")) + "," + expr.substring(expr.indexOf("-") + 1, expr.indexOf("-") + 2) + ")";
                return fonctionMoin;
            } else {
                String fonctionMoin = "-(" + expr.substring(expr.indexOf("-") - 1, expr.indexOf("-")) + "," + expr.substring(expr.indexOf("-") + 1, expr.indexOf("-") + 3) + ")";
                return fonctionMoin;
            }
        } else if (expr.contains("+")) {
            String fonctionPlus = "+(" + expr.substring(expr.indexOf("+"), expr.indexOf("+") + 1) + ")";
            return fonctionPlus;
        }
        return expr;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Predicat predicat = (Predicat) o;

        if (nom != null ? !nom.equals(predicat.nom) : predicat.nom != null) return false;
        return !(variables != null ? !variables.equals(predicat.variables) : predicat.variables != null);

    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (variables != null ? variables.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String predicat = nom + "(";
        for (String var : variables) {
            predicat += var + ",";
        }
        if (predicat.contains(","))
            predicat = predicat.substring(0, predicat.lastIndexOf(","));
        predicat += ")";
        return predicat;
    }
}
