

////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;



public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = null;
        hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a)  {
        this();
        Objects.requireNonNull(a, "Tabellen a er null!");
        int i = 0;


        for (T t : a) {
            if (t != null) {
                Node<T> p = hode = new Node<>(t, null, null);
                antall = 1;

                for(i++; i < a.length; i++)
                {
                    if(a[i] != null)
                    {
                        p = p.neste = new Node<>(a[i], p, null);
                        antall++;
                    }
                }
                hale = p;
                break;
            }
            i++;
        }
    }

    private void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0)
            throw new IndexOutOfBoundsException(
                    ("fra(" + fra + ") er negativ!"));

        if (til > antall)
            throw new IndexOutOfBoundsException(
                    ("til(" + til + ") > antall(" + antall + ")"));

        if (fra > til)
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public Liste<T> subliste(int fra, int til){
        fratilKontroll(antall, fra, til);

        int i = fra;
        DobbeltLenketListe<T> a = new DobbeltLenketListe<>();
        if(fra == til) return a;
        a.leggInn(finnNode(fra).verdi);
        i++;
        a.antall = til - fra;

        while (i < til){
            a.leggInn(finnNode(i).verdi);
            i++;
        }
        return a;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Null verdi ikke tillatt!");

        //Dersom listen er tom peker hode og hale på samme node, forrige og neste blir satt til null.
        if(antall == 0) hode = hale = new Node<>(verdi, null, null);

        //Hvis listen allerede har verdier i seg legges den nye verdien inn på slutten
        //som ny hale og forrige-peker peker mot gammel hale
        else hale = hale.neste = new Node<>(verdi, hale, null);

        antall++;
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    private Node<T> finnNode(int indeks){
        Node<T> p;
        if(indeks < (antall / 2))
        {
            p = hode;
            for(int i = 0; i < indeks; i++)
            {
                p = p.neste;
            }
        }
        else
        {
            p = hale;
            for(int i = antall-1; i > indeks; i--){
                p = p.forrige;
            }
        }
        return p;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        if(verdi == null) return -1;

        Node<T> p = hode;

        for(int indeks = 0; indeks < antall; indeks++){
            if(p.verdi.equals(verdi)) return indeks;
            p = p.neste;
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi, "Nullverdi er ikke godtatt!");
        indeksKontroll(indeks, false);

        Node<T> p = finnNode(indeks);
        T temp = p.verdi;
        p.verdi = nyverdi;

        endringer++;
        return temp;
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append('[');

        if(!tom())
        {
            Node<T> p = hode;
            str.append(p.verdi);

            p=p.neste;

            while(p != null)
            {
                str.append(',').append(" ").append(p.verdi);
                p=p.neste;
            }

        }
        str.append(']');
        return str.toString();
    }

    public String omvendtString() {
        StringBuilder str = new StringBuilder();

        str.append('[');

        if(!tom())
        {
            Node<T> p = hale;
            str.append(p.verdi);

            p = p.forrige;

            while(p != null)
            {
                str.append(',').append(" ").append(p.verdi);
                p = p.forrige;
            }

        }
        str.append(']');

        return str.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks){
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


