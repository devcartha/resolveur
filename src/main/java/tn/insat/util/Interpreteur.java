package tn.insat.util;

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

        String operande1, operande2, resultat;
        if (predicatComparaison.indexOf("-") == 2) {
            operande1 = soustraction(predicatComparaison.substring(predicatComparaison.indexOf("-"), predicatComparaison.indexOf("),") + 1));
            predicatComparaison = predicatComparaison.substring(predicatComparaison.indexOf("),") + 1);
        } else if (predicatComparaison.indexOf("+") == 2) {
            operande1 = addition(predicatComparaison.substring(predicatComparaison.indexOf("+"), predicatComparaison.indexOf("),") + 1));
            predicatComparaison = predicatComparaison.substring(predicatComparaison.indexOf("),") + 1);
        } else {
            operande1 = predicatComparaison.substring(predicatComparaison.indexOf("(") + 1, predicatComparaison.indexOf(","));
            predicatComparaison = predicatComparaison.substring(predicatComparaison.indexOf(","));
        }

        if (predicatComparaison.contains("-")) {
            operande2 = soustraction(predicatComparaison.substring(predicatComparaison.indexOf("-"), predicatComparaison.indexOf(")") + 1));
        } else if (predicatComparaison.contains("+")) {
            operande2 = addition(predicatComparaison.substring(predicatComparaison.indexOf("+"), predicatComparaison.indexOf(")") + 1));
        } else {
            operande2 = predicatComparaison.substring(predicatComparaison.indexOf(",") + 1, predicatComparaison.indexOf(")"));
        }

        int op1 = Integer.parseInt(operande1);
        int op2 = Integer.parseInt(operande2);

        if (predicatComparaison.contains(">"))
            return (op1 >= op2);
        return (op1 == op2);
    }

    public static boolean valide(String etat) {
        String x = etat.substring(etat.indexOf("(")+1,etat.indexOf(","));
        String y = etat.substring(etat.indexOf(",")+1,etat.lastIndexOf(","));
        int m=Integer.parseInt(x),c=Integer.parseInt(y);
        return (m==0 || m==3 || (m>=c && 3-m>=3-c)) ;
    }


}
