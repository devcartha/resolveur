package tn.insat.util;

import tn.insat.structure.Regle;

/**
 * Created by Devcartha on 12/1/2015.
 */
public class Interpreteur {

    public static String addition(String fonctionAddition) {
        String operande1 = fonctionAddition.substring(fonctionAddition.indexOf("(") + 1, fonctionAddition.indexOf(","));
        String operande2 = fonctionAddition.substring(fonctionAddition.indexOf(",") + 1, fonctionAddition.indexOf(")"));
        String resultat = Integer.parseInt(operande1) + Integer.parseInt(operande2) + "";
        return resultat;
    }

    public static String soustraction(String fonctionSoustraction) {
        String operande1, operande2, resultat;
        if (fonctionSoustraction.indexOf("+") == 2) {
            operande1 = addition(fonctionSoustraction.substring(fonctionSoustraction.indexOf("+"), fonctionSoustraction.indexOf(")") + 1));
            fonctionSoustraction = fonctionSoustraction.substring(fonctionSoustraction.indexOf(")") + 1, fonctionSoustraction.lastIndexOf(")") + 1);
        } else {
            operande1 = fonctionSoustraction.substring(fonctionSoustraction.indexOf("(") + 1, fonctionSoustraction.indexOf(","));
            fonctionSoustraction = fonctionSoustraction.substring(fonctionSoustraction.indexOf(","), fonctionSoustraction.lastIndexOf(")") + 1);
        }

        if (fonctionSoustraction.contains("+")) {
            operande2 = addition(fonctionSoustraction.substring(fonctionSoustraction.indexOf("+"), fonctionSoustraction.indexOf(")") + 1));
        } else {
            operande2 = fonctionSoustraction.substring(fonctionSoustraction.indexOf(",") + 1, fonctionSoustraction.indexOf(")"));
        }
        resultat = Integer.parseInt(operande1) - Integer.parseInt(operande2) + "";
        return resultat;
    }

    //>(-(3,+(?x,1)),-(3,+(?y,1)))
    public static boolean comparer(String predicatComparaison) {

        String operande1, operande2, operateurDeComparaison=predicatComparaison.substring(0,1);
        if (predicatComparaison.indexOf("-") == 2) {
            operande1 = soustraction(predicatComparaison.substring(predicatComparaison.indexOf("-"), predicatComparaison.indexOf("),") + 1));
            predicatComparaison = predicatComparaison.substring(predicatComparaison.indexOf("),") + 1);
        } else if (predicatComparaison.indexOf("+") == 2) {
            operande1 = addition(predicatComparaison.substring(predicatComparaison.indexOf("+"), predicatComparaison.indexOf("),") + 1));
            predicatComparaison = predicatComparaison.substring(predicatComparaison.indexOf("),") + 1);
        } else {
            operande1 = predicatComparaison.substring(predicatComparaison.indexOf("(") + 1, predicatComparaison.indexOf(","));
            predicatComparaison = predicatComparaison.substring(predicatComparaison.indexOf(","));
            //System.out.println(operande1);
        }

        if (predicatComparaison.contains("-")) {
            operande2 = soustraction(predicatComparaison.substring(predicatComparaison.indexOf("-"), predicatComparaison.indexOf(")") + 1));
        } else if (predicatComparaison.contains("+")) {
            operande2 = addition(predicatComparaison.substring(predicatComparaison.indexOf("+"), predicatComparaison.indexOf(")") + 1));
        } else {
            operande2 = predicatComparaison.substring(predicatComparaison.indexOf(",") + 1, predicatComparaison.indexOf(")"));
            //System.out.println(operande2);
        }

        int op1 = Integer.parseInt(operande1);
        int op2 = Integer.parseInt(operande2);

        if (operateurDeComparaison.contains(">"))
            return (op1 >= op2);
        return (op1 == op2);
    }

    public static boolean valide(String etat) {
        //System.out.println(etat);
        String x = etat.substring(etat.indexOf("(")+1,etat.indexOf(","));
        String y = etat.substring(etat.indexOf(",")+1,etat.lastIndexOf(","));
        int m=Integer.parseInt(x),c=Integer.parseInt(y);
        return (m==0 || m==3 || (m>=c && 3-m>=3-c)) ;
    }

    public static boolean interpreter(String predicat){
        if (predicat.contains("=")|| predicat.contains(">"))
        return comparer(predicat);
        return valide(predicat);
    }

    public static String interpreterFonction(String fonction){
        if(fonction.indexOf("+")==0)
            return addition(fonction);
        if(fonction.indexOf("-")==0)
            return soustraction(fonction);
        return fonction;
    }

}
