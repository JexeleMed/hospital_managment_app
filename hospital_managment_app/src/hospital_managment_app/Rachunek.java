package hospital_managment_app;

public class Rachunek {
    protected String usluga;
    protected float cena;

    Rachunek(String usluga, float cena) {
        this.usluga = usluga;
        this.cena = cena;
        }

    Rachunek() {
        throw new UnsupportedOperationException("Niepoprawnie zapisany rachunek");
    }

}
