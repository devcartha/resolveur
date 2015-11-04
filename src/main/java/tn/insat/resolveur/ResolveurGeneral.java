package tn.insat.resolveur;

import tn.insat.base_de_connaissances.BCLoader;
import tn.insat.structure.Etat;
import tn.insat.structure.Predicat;
import tn.insat.structure.Probleme;
import tn.insat.structure.Regle;
import tn.insat.util.Interpreteur;
import tn.insat.util.Unificateur;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by Devcartha on 12/1/2015.
 */
public class ResolveurGeneral {

    public void rechercheEnProfondeurLimiteeIterative(Probleme probleme, Etat etatInitial, Etat etatFinal,JTextArea traceur) {
        if (etatInitial.isValide()){
        Stack<Etat> pile = new Stack<Etat>();
        pile.push(etatInitial);
        traceur.append("Etat initial "+etatInitial+"\n");
        System.out.println("Etat initial "+etatInitial);
        int iteration = 0;
        int niveau = 0;
        int limite = 2;
        while (!pile.contains(etatFinal)){
            pile=new Stack<Etat>();
            pile.push(etatInitial);
            probleme.setEspaceDEtats(new ArrayList<Etat>());
            probleme.getEspaceDEtats().add(etatInitial);
            niveau=recherecheEnProfondeurLimitee(probleme,etatInitial,etatFinal,pile,iteration,iteration,niveau,traceur);
            iteration++;
        }
        }else{
            traceur.append("Etat initial non valide !! \n");
            System.out.println("Etat initial non valide !!");
        }

    }

    public int recherecheEnProfondeurLimitee(Probleme probleme, Etat etatInitial, Etat etatFinal,Stack<Etat> pile , int limite,int iteration,int niveau,JTextArea traceur) {
        return recherche(probleme, etatFinal, limite, pile,iteration,niveau,traceur);
    }

    private int recherche(Probleme probleme, Etat etatFinal, int limite, Stack<Etat> pile,int iteration,int niveau ,JTextArea traceur) {
        int i=0;
        while(!pile.isEmpty()&&i<iteration){
            Etat etat = pile.pop();
            ArrayList<Regle> operateursApplicables = genereOperateursApplicables(probleme,etat);
            for (int j=operateursApplicables.size()-1;j>=0;j--){
                if(!probleme.getEspaceDEtats().contains(operateursApplicables.get(j).getConclusion().getEtat())&&operateursApplicables.get(j).getConclusion().getEtat().isValide()){
                    probleme.getEspaceDEtats().add(operateursApplicables.get(j).getConclusion().getEtat());
                    pile.push(operateursApplicables.get(j).getConclusion().getEtat());
                    System.out.println("Iteration : "+iteration+" Niveau : "+niveau+" : "+etat+" "+operateursApplicables.get(j).getNom().substring(0,operateursApplicables.get(j).getNom().indexOf("("))+
                            " -> "+operateursApplicables.get(j).getConclusion().getEtat());
                    traceur.append("Iteration : "+iteration+" Niveau : "+niveau+" : "+etat+" "+operateursApplicables.get(j).getNom().substring(0,operateursApplicables.get(j).getNom().indexOf("("))+
                            " -> "+operateursApplicables.get(j).getConclusion().getEtat()+"\n");
                }
            }
            //System.out.println(pile);
            if (pile.contains(etatFinal)){
                traceur.append("But trouvé ! \n");
                System.out.println("But trouvé !");
                break;
            }
            i++;
            niveau++;
        }
        if (!pile.contains(etatFinal)){
            traceur.append("Echec \n");
            System.out.println("Echec");
        }

        return niveau;
    }

    public void rechercheEnProfondeur(Probleme probleme, Etat etatInitial, Etat etatFinal, JTextArea traceur) {
        Stack<Etat> pile = new Stack<Etat>();
        probleme.getEspaceDEtats().add(etatInitial);
        pile.push(etatInitial);
        System.out.println("Etat initial "+etatInitial);
        recherche(probleme, etatFinal, 10, pile,0,0,traceur);
        if (!pile.contains(etatFinal))
            System.out.println("Echec");
    }

    public ArrayList<Regle> genereOperateursApplicables(Probleme probleme, Etat etat) {
        BCLoader bcLoader = new BCLoader();
        try {
            bcLoader.setBaseDeRegles(new File("c://operateurs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Regle> baseDeRegle = bcLoader.getBaseDeRegles();
        ArrayList<Regle> operateursApplicables = new ArrayList<Regle>();
        if (!Interpreteur.interpreter(etat.toString())) {
            //System.out.println(etat);
            return operateursApplicables;
        }
        for (Regle regle : baseDeRegle) {
            if (((etat.getP() == 1) && (regle.getNom().contains("D"))) || ((etat.getP() == 0) && (!regle.getNom().contains("D")))) {
                continue;
            }
            ArrayList<String> list1 = new ArrayList<String>();
            list1.add(etat.toString());
            ArrayList<String> list2 = new ArrayList<String>();
            list2.add(regle.getPremisses().get(0).toString());
            String unificateur = Unificateur.unifier(list1, list2);
            Regle r = Unificateur.instancier(regle, unificateur);
            Predicat conclusion = regle.getConclusion();
            ArrayList<String> variables = new ArrayList<String>();
            for (String variable : conclusion.getVariables()) {
                variable = Interpreteur.interpreterFonction(variable);
                variables.add(variable);
            }
            conclusion.setVariablesInstancies(variables);
            r.setConclusion(conclusion);
            if (operateurApplicable(Unificateur.instancier(regle, unificateur))) {
                operateursApplicables.add(r);
                probleme.getBaseDeRegles().add(r);
                //probleme.getEspaceDEtats().add(r.getConclusion().getEtat());
            }
        }
        return operateursApplicables;
    }

    private boolean operateurApplicable(Regle regle) {
        for (int i = 0; i < regle.getPremisses().size(); i++) {
            if (!Interpreteur.interpreter(regle.getPremisses().get(i).toString()))
                return false;
        }
        return true;
    }

    public void rechercheAStar(Probleme probleme, Etat etatInitial, Etat etatFinal, JTextArea traceur){
        if(etatInitial.isValide()){
            ArrayList<Etat> arbre = new ArrayList<Etat>();
            arbre.add(etatInitial);
            double cout=0;
            while(!arbre.isEmpty()){
                Collections.sort(arbre);
                Etat etat = arbre.get(0);
                if(!probleme.getEspaceDEtats().contains(etat)){
                    probleme.getEspaceDEtats().add(etat);
                    if (etat.equals(etatInitial)){
                        System.out.println("Etat initial : "+etat);
                        traceur.append("Etat initial : "+etat+"\n");
                    }
                    else{
                        for(Etat e : arbre){
                            traceur.append("h("+e+") = "+calculerHeuristique(e)+"\n");
                            System.out.println("h("+e+") = "+calculerHeuristique(e));
                        }

                        cout+=calculerHeuristique(etat);
                        traceur.append("choix de l'etat : "+etat+"\n");
                        System.out.println("choix de l'etat : "+etat);
                    }
                }
                arbre.remove(etat);
                ArrayList<Regle> operateursApplicables = genereOperateursApplicables(probleme,etat);
                for (Regle operateur : operateursApplicables){
                    if(!probleme.getEspaceDEtats().contains(operateur.getConclusion().getEtat())
                            &&operateur.getConclusion().getEtat().isValide()&&!arbre.contains(operateur.getConclusion().getEtat())){
                        arbre.add(operateur.getConclusion().getEtat());
                        System.out.println(etat+" "+operateur.getNom().substring(0,operateur.getNom().indexOf("("))+
                                " -> "+operateur.getConclusion().getEtat());
                        traceur.append(etat+" "+operateur.getNom().substring(0,operateur.getNom().indexOf("("))+
                                " -> "+operateur.getConclusion().getEtat()+"\n");
                    }
                }
                if (arbre.contains(etatFinal)){
                    traceur.append("But trouvé avec un cout de "+cout+"\n");
                    System.out.println("But trouvé avec un cout de "+cout);
                    break;
                }
            }
            if (!arbre.contains(etatFinal)){
                System.out.println("Echec");
                traceur.append("Echec \n");
            }

        }
    }

    private double calculerHeuristique(Etat etat) {
        return ((double) (etat.getX()+etat.getY()))/2;
    }
}
