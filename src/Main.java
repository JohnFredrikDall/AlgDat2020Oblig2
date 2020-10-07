import java.util.LinkedList;

public class Main {
    public static void main(String[]args){
        Character[] c = {'A','B','C','D','E','F','G','H','I','J',};
        DobbeltLenketListe<Character> liste = new DobbeltLenketListe<>(c);

        System.out.println(liste.toString());

        for(int i = 0; i < 9; i++)
        {
            System.out.println(liste.hent(i));
        }
        //System.out.println(liste.subliste(3,8)); // [D, E, F, G, H]
        //System.out.println(liste.subliste(5,5)); // []
        //System.out.println(liste.subliste(8,liste.antall())); // [I, J]
// System.out.println(liste.subliste(0,11)); // skal kaste unnt
    }
}
