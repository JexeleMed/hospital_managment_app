package hospital_managment_app;

public class Para<T, U> {
    protected T nazwa;
    protected U wartosc;

    Para(T nazwa, U wartosc) {
        this.nazwa = nazwa;
        this.wartosc = wartosc;
        }

    Para() {
        throw new UnsupportedOperationException("Niepoprawnie zapisany rachunek");
    }
    @Override
    public String toString() {
        return this.nazwa + ": " + this.wartosc;
    }

}
