package traficoaereo;

public class Simulador {
    public static void main(String[] args) {
        Pista pista = new Pista();
        for (int i = 1; i <= 5; i++) {
            new Thread(new Avion(i, "Avion " + i, pista)).start();
        }
    }
}
