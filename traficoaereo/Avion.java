package traficoaereo;

import java.util.concurrent.ThreadLocalRandom;

public class Avion implements Runnable {
    private int id;
    private String nombre;
    private Pista pista;

    public Avion(int id, String nombre, Pista pista) {
        this.id = id;
        this.nombre = nombre;
        this.pista = pista;
    }

    public void run() {
        System.out.println("Avion " + id + " iniciando preparaciones...");
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000)); // Tiempo aleatorio de preparación
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pista.despegar(id);
        System.out.println("Avion " + id + " ha despegado y el espacio es seguro para el próximo avión.");
    }
}
