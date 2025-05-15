package hospital_managment_app;

abstract class Pomieszczenie {
    protected int numer;
    protected int pietro;
    protected int pojemnoscSali;
    protected int aktualnaPojemnosc;

    public Pomieszczenie() {
        throw new UnsupportedOperationException("Brak danych");
    }

    public Pomieszczenie(int numer, int pietro, int pojemnoscSali){
        this.numer = numer;
        this.pietro = pietro;
        this.pojemnoscSali = pojemnoscSali;
        this.aktualnaPojemnosc = 0;
    }

    public void dodajDoSali() {
        if (aktualnaPojemnosc < pojemnoscSali) {
            aktualnaPojemnosc++;
        } else {
            System.out.println("Sala jest już pełna!");
        }
    }

    public boolean czyWolne() {
        return aktualnaPojemnosc < pojemnoscSali;
    }

    public boolean czyWolne(int iluPacjentow) {
        return (aktualnaPojemnosc + iluPacjentow) <= pojemnoscSali;
    }

    public void usunZSali() {
        if (aktualnaPojemnosc > 0) {
            aktualnaPojemnosc--;
        } else {
            System.out.println("Sala jest już pusta!");
        }
    }

    public void lokalizacja(){
        System.out.println("Pietro: " + pietro);
        System.out.println("Numer: " + numer);
        System.out.println("aktualnaPojemnosc: " + aktualnaPojemnosc);
        System.out.println("Pojemnosc: " + pojemnoscSali);
    }

    public void generujRaport(){
        System.out.println("Pietro: " + pietro);
        System.out.println("Numer sali: " + numer);
        System.out.println("Pojemnosc sali: " + pojemnoscSali);
        System.out.println("Aktualna Pojemnosc: " + aktualnaPojemnosc);
        System.out.println("Czy sala jest wolna: " + (czyWolne() ? "TAK" : "NIE"));
    }
}
