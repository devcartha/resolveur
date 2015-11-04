package tn.insat;

import tn.insat.presentation.UserInterface;

import javax.swing.*;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {

        UserInterface userInterface = new UserInterface();
        userInterface.setVisible(true);
        userInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      /*  // MC(3,3,1)
        BCLoader bcLoader = new BCLoader();
        bcLoader.setBaseDeRegles(new File("c://operateurs.txt"));
        Probleme probleme = new Probleme();
        probleme.setBaseDeRegles(new ArrayList<Regle>());
        probleme.setEspaceDEtats(new ArrayList<Etat>());
        ResolveurGeneral resolveurGeneral = new ResolveurGeneral();

       // resolveurGeneral.rechercheEnProfondeurLimiteeIterative(probleme,new Etat(0,3,1),new Etat(0,0,0));
        resolveurGeneral.rechercheAStar(probleme,new Etat(3,3,1),new Etat(0,0,0));

*/
    }
}
