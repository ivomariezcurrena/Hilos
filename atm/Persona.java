package atm;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Persona implements Runnable {
    private String id;
    private List<Cajero> cajeros;
    private String operacion;

    public Persona(String id, List<Cajero> cajeros, String operacion) {
        this.id = id;
        this.cajeros = cajeros;
        this.operacion = operacion;
    }

    @Override
    public void run() {
        System.out.println("Cliente " + id + " entra al banco");

        Cajero cajeroDisponible = buscarCajeroDisponible();
        if (cajeroDisponible != null) {
            try {
                operar(cajeroDisponible);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Cliente " + id + " no encontro un cajero disponible y se fue.");
        }
    }

    private Cajero buscarCajeroDisponible() {
        for (Cajero cajero : cajeros) {
            // Verificar si el cajero está libre
            synchronized (cajero) {
                if (!cajero.ocupado) {
                    return cajero;
                }
            }
        }
        return null; // Si no hay cajeros disponibles
    }

    public synchronized void operar(Cajero cajero) throws InterruptedException {
        System.out.println("El cliente " + id + " empieza a usar el cajero " + cajero);
        cajero.usar(operacion);
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
        System.out.println("El cliente " + id + " se va del cajero " + cajero);
        cajero.irse();
    }
}
