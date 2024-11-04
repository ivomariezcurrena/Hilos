package heladeria;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Caja implements Runnable {
    private int id;
    private BlockingQueue<Cliente> cola_de_caja;
    private BlockingQueue<Cliente> cola_de_pedidos;
    private Cliente cliente;

    public Caja(int id, BlockingQueue<Cliente> cola_de_caja, BlockingQueue<Cliente> cola_de_pedidos) {
        this.id = id;
        this.cola_de_caja = cola_de_caja;
        this.cola_de_pedidos = cola_de_pedidos;
    }

    @Override
    public void run() {
        try {
            while (true) {
                cliente = cola_de_caja.take(); // Espera hasta que haya un cliente en la cola de caja
                System.out.println("El cliente: " + cliente.getId() + " está en la caja: " + id);
                hacerPedido();
                System.out.println("El cliente: " + cliente.getId() + " pasa a pedir");

                // `put` esperará si la cola de pedidos está llena
                cola_de_pedidos.put(cliente); // Pasa a la siguiente cola
            }
        } catch (InterruptedException exception) {
            System.out.println("Caja " + id + " interrumpida.");
            Thread.currentThread().interrupt(); // restablece el estado de interrupción del hilo
        }
    }

    public void hacerPedido() throws InterruptedException {
        System.out.println("Cajero: " + id + " cobrándole al cliente: " + cliente.getId());
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
    }
}