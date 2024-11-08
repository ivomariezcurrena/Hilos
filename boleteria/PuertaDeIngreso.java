package boleteria;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class PuertaDeIngreso implements Runnable {
    private String puerta;
    private BlockingQueue<Hincha> hinchasPuerta;
    private static final int TIMEOUT = 10; // Tiempo de espera máximo en segundos

    public PuertaDeIngreso(String puerta, BlockingQueue<Hincha> hinchasPuerta) {
        this.puerta = puerta;
        this.hinchasPuerta = hinchasPuerta;
    }

    @Override
    public void run() {
        System.out.println("Puerta de ingreso " + puerta + " abre");

        try {
            while (true) {
                // Esperar a que llegue un hincha, con timeout de inactividad
                Hincha hincha = hinchasPuerta.poll(TIMEOUT, TimeUnit.SECONDS);

                if (hincha == null) {
                    System.out.println("Puerta de ingreso " + puerta + " cierra porque ya no llegan mas hinchas.");
                    break; // Salir del bucle si no llega ningún hincha en el tiempo de timeout

                }

                // Procesar al hincha si llega a la puerta
                System.out.println("Puerta " + puerta + " recibe al hincha " + hincha.getId());
                Thread.sleep(new Random().nextInt(2000) + 1000); // Pausa de procesamiento entre 1 y 2 segundos
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Puerta de ingreso " + puerta + " fue interrumpida.");
        }
    }
}
