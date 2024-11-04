package heladeria;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Atencion implements Runnable {
    private int id;
    private BlockingQueue<Cliente> cola_de_pedidos;
    private Cliente cliente;

    public Atencion(int id, BlockingQueue<Cliente> cola_de_pedidos) {
        this.id = id;
        this.cola_de_pedidos = cola_de_pedidos;
    }

    @Override
    public void run() {
        try {
            while (true) {
                cliente = cola_de_pedidos.take(); // Espera hasta que haya un cliente en la cola de pedidos
                System.out.println("Heladero: " + id + " atendiendo al cliente: " + cliente.getId());
                darHelado();
                long tiempo = System.currentTimeMillis() - cliente.getIngreso();
                System.out.println("El cliente: " + cliente.getId() + " se fue y tardó: " + tiempo + " ms");
            }
        } catch (InterruptedException e) {
            System.out.println("Atención " + id + " interrumpida.");
            Thread.currentThread().interrupt(); // restablece el estado de interrupción del hilo
        }
    }

    public void darHelado() throws InterruptedException {
        System.out.println("Heladero: " + id + " haciendo el helado al cliente: " + cliente.getId());
        Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 4000));
    }
}