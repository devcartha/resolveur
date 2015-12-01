package tn.insat.resolveur;

import tn.insat.structure.Etat;
import tn.insat.structure.Predicat;
import tn.insat.structure.Probleme;
import tn.insat.structure.Regle;
import tn.insat.util.Interpreteur;
import tn.insat.util.Unificateur;

import java.util.ArrayList;

/**
 * Created by Devcartha on 12/1/2015.
 */
public class ResolveurGeneral {
    public ArrayList<Regle> genereOperateursApplicables (Probleme probleme, Etat etat){
        ArrayList<Regle> operateursApplicables = new ArrayList<Regle>();
        for (Regle regle : probleme.getBaseDeRegles()){
            ArrayList<String> list1 = new ArrayList<String>();
            list1.add(etat.toString());
            ArrayList<String> list2 = new ArrayList<String>();
            list2.add(regle.getPremisses().get(0).toString());
            String unificateur = Unificateur.unifier(list1,list2);
            Regle r = Unificateur.instancier(regle,unificateur);
            Predicat conclusion = regle.getConclusion();
            ArrayList<String> variables = new ArrayList<String>();
            for (String variable : conclusion.getVariables()){
                variable = Interpreteur.interpreterFonction(variable);
                variables.add(variable);
            }
            conclusion.setVariablesInstancies(variables);
            r.setConclusion(conclusion);
            if (operateurApplicable(Unificateur.instancier(regle,unificateur))){
                operateursApplicables.add(r);
            }
        }
        return operateursApplicables;
    }

    private boolean operateurApplicable(Regle regle){
        for (int i=0; i<regle.getPremisses().size(); i++){
            if (!Interpreteur.interpreter(regle.getPremisses().get(i).toString()))
                return false;
        }
        return true;
    }
}
