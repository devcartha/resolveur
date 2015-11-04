/*
 * Created by JFormDesigner on Wed Nov 04 21:17:18 WAT 2015
 */

package tn.insat.presentation;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;
import tn.insat.base_de_connaissances.BCLoader;
import tn.insat.resolveur.ResolveurGeneral;
import tn.insat.structure.Etat;
import tn.insat.structure.Probleme;
import tn.insat.structure.Regle;

/**
 * @author Souhail Chaouechi
 */
public class UserInterface extends JFrame {

    private BCLoader bcLoader;
    private Probleme probleme;
    private ResolveurGeneral resolveurGeneral;

    public UserInterface() {
        initComponents();
        try{
            bcLoader = new BCLoader();
            bcLoader.setBaseDeRegles(new File("c://operateurs.txt"));
            resolveurGeneral = new ResolveurGeneral();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void algoLIBtnMouseClicked(MouseEvent e) {
        traceurJTA.setText("");
        probleme = new Probleme();
        probleme.setBaseDeRegles(new ArrayList<Regle>());
        probleme.setEspaceDEtats(new ArrayList<Etat>());
        resolveurGeneral.rechercheEnProfondeurLimiteeIterative(probleme,getEtatFromTF(etatInitialTF),getEtatFromTF(etatFinalTF),traceurJTA);
    }

    private Etat getEtatFromTF(JTextField etatInitialTF) {
        Etat etatFromTF = new Etat();
        String etat = etatInitialTF.getText();
        etatFromTF.setX(Integer.parseInt(etat.substring(etat.indexOf("(")+1,etat.indexOf(","))));
        etat=etat.substring(etat.indexOf(",")+1);
        etatFromTF.setY(Integer.parseInt(etat.substring(0,etat.indexOf(","))));
        etat=etat.substring(etat.indexOf(",")+1);
        etatFromTF.setP(Integer.parseInt(etat.substring(0,etat.indexOf(")"))));
        return etatFromTF;
    }

    private void algoASBtnMouseClicked(MouseEvent e) {
        traceurJTA.setText("");
        probleme = new Probleme();
        probleme.setBaseDeRegles(new ArrayList<Regle>());
        probleme.setEspaceDEtats(new ArrayList<Etat>());
        resolveurGeneral.rechercheAStar(probleme,getEtatFromTF(etatInitialTF),getEtatFromTF(etatFinalTF),traceurJTA);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Souhail Chaouechi
        label1 = new JLabel();
        etatInitialTF = new JTextField();
        label2 = new JLabel();
        etatFinalTF = new JTextField();
        algoLIBtn = new JButton();
        algoASBtn = new JButton();
        scrollPane1 = new JScrollPane();
        traceurJTA = new JTextArea();

        //======== this ========
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Etat Intitial");

        //---- label2 ----
        label2.setText("Etat Final");

        //---- algoLIBtn ----
        algoLIBtn.setText("Algorithme limit\u00e9 itrative");
        algoLIBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                algoLIBtnMouseClicked(e);
            }
        });

        //---- algoASBtn ----
        algoASBtn.setText("Algorithme A*");
        algoASBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                algoASBtnMouseClicked(e);
            }
        });

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(traceurJTA);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .add(GroupLayout.TRAILING, contentPaneLayout.createSequentialGroup()
                    .add(contentPaneLayout.createParallelGroup(GroupLayout.TRAILING)
                        .add(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .add(label1)
                            .addPreferredGap(LayoutStyle.UNRELATED)
                            .add(etatInitialTF, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.RELATED, 14, Short.MAX_VALUE)
                            .add(label2)
                            .addPreferredGap(LayoutStyle.RELATED)
                            .add(etatFinalTF, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                        .add(contentPaneLayout.createSequentialGroup()
                            .add(33, 33, 33)
                            .add(algoLIBtn, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .add(18, 18, 18)
                            .add(algoASBtn, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                            .add(26, 26, 26)))
                    .add(13, 13, 13))
                .add(scrollPane1, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .add(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .add(contentPaneLayout.createParallelGroup(GroupLayout.BASELINE)
                        .add(label1)
                        .add(etatInitialTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .add(label2)
                        .add(etatFinalTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.UNRELATED)
                    .add(contentPaneLayout.createParallelGroup(GroupLayout.BASELINE)
                        .add(algoLIBtn)
                        .add(algoASBtn))
                    .addPreferredGap(LayoutStyle.RELATED)
                    .add(scrollPane1, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Souhail Chaouechi
    private JLabel label1;
    private JTextField etatInitialTF;
    private JLabel label2;
    private JTextField etatFinalTF;
    private JButton algoLIBtn;
    private JButton algoASBtn;
    private JScrollPane scrollPane1;
    private JTextArea traceurJTA;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
