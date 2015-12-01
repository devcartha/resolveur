package tn.insat;

import tn.insat.base_de_connaissances.BCLoader;
import tn.insat.structure.Predicat;
import tn.insat.util.Unificateur;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {

        BCLoader bcLoader = new BCLoader();
        bcLoader.setBaseDeRegles(new File("c://operateurs.txt"));

        /*ArrayList<String> variables = new ArrayList<String>();
        variables.add("?x");
        variables.add("?y");
        Predicat p = new Predicat("+",variables);
        System.out.println(p);

        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        list1.add(p.toString());
        list2.add("+(3,2)");
        System.out.println(Unificateur.unifier(list1,list2));

        /*System.out.println( "Hello World!" );
        ArrayList<String> list ;
        list = (ArrayList<String>) Unificateur.extractExpression("f(A,g(B,C),h(?x,?y),k(A,B,t(B,F)),K)");
        for (String element : list){
            System.out.println(element);
        }*/
        //Unifier(p(B,C,?x,?z,f(A,?z,B)), p(?y,?z,?y,C,?w))
        /*ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        list1.add("p(B,C,?x,?z,f(A,?z,B))");
        list2.add("p(?y,?z,?y,C,?w)");
        System.out.println(Unificateur.unifier(list1,list2));

        //Unifier (p(?x,f(g(?x)),a),p(B,?y,?z))
        ArrayList<String> list3 = new ArrayList<String>();
        ArrayList<String> list4 = new ArrayList<String>();
        list3.add("p(?x,f(g(?x)),A)");
        list4.add("p(B,?y,?z)");
        System.out.println(Unificateur.unifier(list3,list4));

        //Unifier(q(f(A,?x),?x),q(f(?z,f(?z,D)),?z))
        ArrayList<String> list5 = new ArrayList<String>();
        ArrayList<String> list6 = new ArrayList<String>();
        list5.add("q(f(A,?x),?x)");
        list6.add("q(f(?z,f(?z,D)),?z)");
        System.out.println(Unificateur.unifier(list5,list6));

        //Unifier(?x,g(?x))
        ArrayList<String> list7 = new ArrayList<String>();
        ArrayList<String> list8 = new ArrayList<String>();
        list7.add("?x");
        list8.add("g(?x)");
        System.out.println(Unificateur.unifier(list7,list8));
*/

    }
}
