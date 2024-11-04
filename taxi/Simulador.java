package taxi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulador {
    private final static Random generator = new Random();
    private static final int TIEMPO_FIJO = 1000;
    private static final int TIEMPO_VARIABLE = 1000;

    public static void main(String[] args) throws InterruptedException {
        simular(25, 5);
    }

    public static void simular(int nroClientes, int nroTaxis) throws InterruptedException {
        ExecutorService application = Executors.newCachedThreadPool();
        BlockingQueue<Taxi> colaDeTaxis = new ArrayBlockingQueue<>(nroTaxis);
        for (int i = 1; i <= nroTaxis; i++) {
            colaDeTaxis.put(new Taxi("T" + i));
        }

        for (int i = 1; i <= nroClientes; i++) {
            application.execute(new Cliente(i, colaDeTaxis));
            Thread.sleep(generator.nextInt(TIEMPO_VARIABLE) + TIEMPO_FIJO);
        }

        application.shutdown();
        application.awaitTermination(1, TimeUnit.MINUTES); // Espera a que terminen todos los clientes

        // Resumen
        System.out.println("Clientes que no consiguieron taxi: " + Cliente.getNoSube());
        for (Taxi taxi : colaDeTaxis) {
            System.out.println("Taxi " + taxi.getId() + " realizó " + taxi.getViajes() + " viajes.");
        }
    }
}