package heladeria;

import java.nio.Buffer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class GeneradorDeClientes implements Runnable {
    private BlockingQueue<Cliente> cola_de_caja;
    private int clientes;

    public GeneradorDeClientes(BlockingQueue<Cliente> colaCaja, int clientes) {
        this.cola_de_caja = colaCaja;
        this.clientes = clientes;
    }

    @Override
    public void run() {
        Cliente cliente;
        for (int count = 1; count <= clientes; count++) {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
                cliente = new Cliente(count);
                cliente.setIngreso(System.currentTimeMillis());

                // Si la cola está llena, `put()` esperará hasta que haya espacio
                cola_de_caja.put(cliente);
                System.out.println("Cliente " + cliente.getId() + " añadido a la cola de caja.");
            } catch (InterruptedException exception) {
                exception.printStackTrace();
                Thread.currentThread().interrupt(); // restablece el estado de interrupción del hilo
            }
        }
        System.out.println("Fin Ingreso Clientes");
    }
}
