package tn.insat;

import tn.insat.util.Unificateur;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ArrayList<String> list = new ArrayList<String>();
        list = (ArrayList<String>) Unificateur.extractExpression("f(A,g(B,C),h(?x,?y),k(A,B,t(B,F)),K)");
        for (String element : list){
            System.out.println(element);
        }
    }
}
