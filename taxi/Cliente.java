package taxi;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Cliente implements Runnable {
    private int id;
    private BlockingQueue<Taxi> colaDeTaxis;
    private int destino;
    private static int noSube;

    public Cliente(int id, BlockingQueue<Taxi> colaDeTaxis) {
        this.id = id;
        this.colaDeTaxis = colaDeTaxis;
        destino = (int) (Math.random() * 8) + 1;
    }

    public int getId() {
        return id;
    }

    public static synchronized int getNoSube() {
        return noSube;
    }

    @Override
    public void run() {
        try {
            // Intentar obtener un taxi con tiempo de espera
            Taxi taxi = colaDeTaxis.poll(1, TimeUnit.SECONDS);
            if (taxi == null) {
                System.out.println("El cliente " + id + " no encontró taxis disponibles.");
                synchronized (Cliente.class) {
                    noSube++;
                }
                return; // Sale si no encuentra taxi
            }
            System.out.println("El cliente: " + id + " sube al taxi: " + taxi.getId());
            viajar(taxi);

        } catch (InterruptedException e) {
            System.out.println("Cliente " + id + " fue interrumpido.");
            Thread.currentThread().interrupt(); // Restablece el estado de interrupción
        }
    }

    private void viajar(Taxi taxi) throws InterruptedException {
        int tiempo;
        System.out.println("El cliente: " + id + " va al destino " + destino);
        tiempo = destino * 1000; // Tiempo de viaje según el destino
        Thread.sleep(tiempo); // Tiempo de viaje según el destino
        System.out.println("El cliente: " + id + " llego al destino " + destino + " en: " + tiempo + " min");
        taxi.setViajes(taxi.getViajes() + 1);
        colaDeTaxis.put(taxi); // Retornar el taxi a la cola
    }
}
