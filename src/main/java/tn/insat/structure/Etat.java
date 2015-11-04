package tn.insat.structure;

/**
 * Created by Devcartha on 11/30/2015.
 */
// Structure symbolique qui représente une configuration du probléme : Etat

public class Etat implements Comparable{
    // Nombre des missionnaires
    private int x;
    // Nombre des cannibales
    private int y;
    // Bateau présent/non
    private int p;

    public Etat() {
    }

    public Etat(int x, int y, int p) {
        this.x = x;
        this.y = y;
        this.p = p;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public boolean isValide() {
        return ((x==0)||(x==3)||((x>=y)&&(3-x>=3-y)));
    }

    @Override
    public boolean equals(Object o) {
        Etat etat = (Etat) o;
        if (x != etat.x) return false;
        if (y != etat.y) return false;
        return p == etat.p;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + p;
        return result;
    }

    @Override
    public String toString() {
        return "p("+x+","+y+","+p+")";
    }

    public int compareTo(Object o) {
        boolean egale = (((this.x+this.y)/2)==((((Etat) o).x+((Etat) o).y)/2));
        boolean sup = (((this.x+this.y)/2)>((((Etat) o).x+((Etat) o).y)/2));
        if(egale){
            return 0;
        }else if(sup)
            return 1;
        return -1;
    }
}
