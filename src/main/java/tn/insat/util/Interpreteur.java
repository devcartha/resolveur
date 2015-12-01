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
        String operande1,operande2,resultat;
        if (fonctionSoustraction.indexOf("+")==2) {
            operande1 = addition(fonctionSoustraction.substring(fonctionSoustraction.indexOf("+"), fonctionSoustraction.indexOf(")")+1));
            fonctionSoustraction=fonctionSoustraction.substring(fonctionSoustraction.indexOf(")")+2,fonctionSoustraction.lastIndexOf(")")+1);
        } else {
            operande1 = fonctionSoustraction.substring(fonctionSoustraction.indexOf("(") + 1, fonctionSoustraction.indexOf(","));
            fonctionSoustraction=fonctionSoustraction.substring(fonctionSoustraction.indexOf(",")+1,fonctionSoustraction.lastIndexOf(")")+1);
        }
        System.out.println(fonctionSoustraction);
        if (fonctionSoustraction.contains("+")) {
            operande2 = addition(fonctionSoustraction.substring(fonctionSoustraction.indexOf("+"), fonctionSoustraction.indexOf(")")+1));
        } else {
            operande2 = fonctionSoustraction.substring(fonctionSoustraction.indexOf(",") + 1, fonctionSoustraction.indexOf(")"));
        }
        resultat = Integer.parseInt(operande1) - Integer.parseInt(operande2) + "";
        return resultat;
    }

    public static boolean comparer(String predicatComparaison) {
       return false;
    }

    public static boolean valide(String etat) {
        return false;
    }


}
