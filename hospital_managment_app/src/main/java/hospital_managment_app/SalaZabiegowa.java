package hospital_managment_app;

import java.util.Scanner;

public class SalaZabiegowa extends Pomieszczenie{
    private String opisOperacji;
    private String specjalnaAparatura;

//  Do przejzenia czy mozna uzywac scanera
    public void przygotujSale(int numer, int pietro){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadz opis operacji: ");
        this.opisOperacji = scanner.nextLine();
        System.out.println("Wprowadz specjalną aparature potrzebną w operacji: ");
        this.specjalnaAparatura = scanner.nextLine();
        System.out.println("Zapisano");
    }

    //czy da sie inaczej niz tworzyc metode lokalizacja
    protected void wyswietlOpis(){
        System.out.println("Sala zabiegowa");
        this.lokalizacja();
        System.out.println("Opis sali zabiegowej: ");
        System.out.println(opisOperacji);
        System.out.println("Specjalna aparatura zabiegowej: ");
        System.out.println(specjalnaAparatura);
    }

    @Override
    public void generujRaport(){
        super.generujRaport();
        System.out.println("Opis operacji" + opisOperacji);
        System.out.println("Specjalistyczna aparatura w pomieszczeniu" + specjalnaAparatura);
    }
}
