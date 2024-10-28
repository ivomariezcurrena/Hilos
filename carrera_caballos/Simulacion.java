package carrera_caballos;

public class Simulacion {
    private final static double META = 60;

    public static void main(String[] args) {
        System.out.println("INICIO DE LA CARRERA DE CABALLOS");

        // Crea y arranca los hilos para los caballos
        for (int i = 1; i <= 3; i++) {
            Caballo caballo = new Caballo("Caballo " + i, META);
            new Thread(caballo).start();
        }
    }
}
