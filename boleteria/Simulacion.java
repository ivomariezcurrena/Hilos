package boleteria;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulacion {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("INICIO DE LA SIMULACION");
        ExecutorService aplicacion = Executors.newCachedThreadPool();
        BlockingQueue<Hincha> HinchasBoleteria = new ArrayBlockingQueue<>(50);
        BlockingQueue<Hincha> HinchasPuertaLocal = new ArrayBlockingQueue<>(50);
        BlockingQueue<Hincha> HinchasPuertaVisitante = new ArrayBlockingQueue<>(50);
        String[] operaciones = { "Local", "Visitante" };
        Random random = new Random();
        final int NUM_HINCHAS = 10;

        for (int i = 0; i < NUM_HINCHAS; i++) {
            int eleccionOp = random.nextInt(operaciones.length); // Selecciona operación al azar
            aplicacion.execute(new Hincha("Hincha " + i, operaciones[eleccionOp], random.nextInt(6), HinchasBoleteria));
        }

        for (int i = 0; i < 3; i++) {
            aplicacion.execute(
                    new Boleteria(HinchasBoleteria, "Boleteria " + i, 200, HinchasPuertaLocal, HinchasPuertaVisitante));
        }

        aplicacion.execute(new PuertaDeIngreso("Local", HinchasPuertaLocal));
        aplicacion.execute(new PuertaDeIngreso("Visitante", HinchasPuertaVisitante));

        // Esperar a que todas las boleterías terminen
        aplicacion.shutdown();
        if (!aplicacion.awaitTermination(60, TimeUnit.SECONDS)) {
            System.out.println("Forzando el cierre de la aplicación debido a la falta de actividad.");
            aplicacion.shutdownNow(); // Forzar la finalización si timeout excedido
        }

        System.out.println("SIMULACION FINALIZADA.");
    }
}
