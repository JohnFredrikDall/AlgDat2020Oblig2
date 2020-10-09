public class Main {
    public static void main(String[]args){
        Liste<Integer> nyliste = new DobbeltLenketListe<>();
        nyliste.leggInn(1);
        nyliste.leggInn(2);
        nyliste.leggInn(3);
        nyliste.leggInn(4);
        nyliste.leggInn(5);
        nyliste.leggInn(6);

        System.out.println(nyliste.toString());
    }
}
