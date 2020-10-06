import java.util.LinkedList;

public class Main {
    public static void main(String[]args){
        /*String[] s = {"Ole", null, "Per", "Kari", null};
        Liste<String> liste = new DobbeltLenketListe<>(s);
        System.out.println(liste.antall() + " " + liste.tom());


         */
        Liste<Integer> liste = new DobbeltLenketListe<>();
        liste = new DobbeltLenketListe<>(new Integer[]{1});

        System.out.println(liste.antall());
    }
}
