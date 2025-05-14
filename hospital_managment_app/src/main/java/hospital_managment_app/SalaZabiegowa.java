package hospital_managment_app;

public class SalaZabiegowa extends Pomieszczenie{
    private String opisOperacji;
    private String specjalnaAparatura;

    public przygotujSale(){

    }

    @Override
    protected void wyswietlOpis(){
        System.out.println("Sala zabiegowa");
        this.lokalizacja();
        System.out.println("Opis sali zabiegowej: ");
        System.out.println(opisOperacji);
        System.out.println("Specjalna aparatura zabiegowej: ");
        System.out.println(specjalnaAparatura);
    }
}
