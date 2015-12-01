package tn.insat.base_de_connaissances;

import tn.insat.structure.Predicat;
import tn.insat.structure.Regle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Devcartha on 11/30/2015.
 */
public class BCLoader {

    private ArrayList<Regle> baseDeRegles;

    public ArrayList<Regle> getBaseDeRegles() {
        return baseDeRegles;
    }

    public void setBaseDeRegles(File f) throws IOException {

        this.baseDeRegles = new ArrayList<Regle>();
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String ligne;
        int i = 1;
        while ((ligne = br.readLine()) != null) {
            //System.out.println(ligne);
            while (ligne.contains("(")) {
                String predicat = ligne.substring(ligne.indexOf("("), ligne.indexOf(")") + 1);
                if (predicat.contains(",")) {
                    Predicat p = new Predicat();
                    p.setNom("p");
                    ArrayList<String> variables = new ArrayList<String>();
                    variables.add(predicat.substring(1, predicat.indexOf(",")));
                    predicat = predicat.substring(predicat.indexOf(",") + 1);
                    while (predicat.lastIndexOf(",") > 0) {
                        variables.add(predicat.substring(0, predicat.indexOf(",")));
                        predicat = predicat.substring(predicat.indexOf(",") + 1);
                    }
                    variables.add(predicat.substring(0, 1));
                    p.setVariables(variables);
                    System.out.println(p);
                }else if(predicat.contains(">=")){
                    Predicat p = new Predicat();
                    p.setNom(">");
                    ArrayList<String> variables = new ArrayList<String>();
                    String variable;
                    String fonctionPlus = null;
                    String fonctionMoin = null;
                    if(predicat.contains("+")){
                        fonctionPlus = "+("+predicat.substring(predicat.indexOf("?"),predicat.indexOf("?")+2)+",";
                        fonctionPlus+=predicat.substring(predicat.indexOf("+")+1,predicat.indexOf("+")+2)+")";
                            System.out.println(fonctionPlus);
                    }
                    if(predicat.contains("-")){
                            if (predicat.contains("+")){
                                //if(predicat.indexOf("?")==0)
                                fonctionMoin="-("+predicat.substring(predicat.indexOf("?"),predicat.indexOf("?")+2)+","+fonctionPlus+")";
                                System.out.println(fonctionMoin);
                                variables.add(fonctionMoin);
                            }
                    }else{
                        variable = predicat.substring(1,predicat.indexOf(">="));
                        variables.add(variable);
                        variable = predicat.substring(predicat.indexOf(">=")+2,predicat.indexOf(")"));
                        variables.add(variable);
                    }
                    p.setVariables(variables);
                    System.out.println(p);
                }else if (predicat.contains("=")){
                    if(predicat.contains("-")){

                    }else if(predicat.contains("+")){

                    }else{

                    }
                }

                ligne = ligne.substring(ligne.indexOf(")") + 1);
            }
            i++;
        }
        br.close();
    }

}
