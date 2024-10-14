package ej1;

import java.util.concurrent.BlockingQueue;

public class Productor implements Runnable {
    private final BlockingQueue<Producto> buffer;
    private int contador = 0;
    private final int MAXIMO = 24;
    private String id;

    public Productor(String id, BlockingQueue<Producto> buffer) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            int sum = 0;
            for (int count = 1; count <= MAXIMO; count++) {
                Producto producto = new Producto("queso rayado");
                buffer.put(producto); // Pone el producto en el buffer
                sum++;
                System.out.println("Productor " + id + " ha producido: " + producto + " suma: " + sum);
                Thread.sleep(2000, 3000); // Simula tiempo de producción
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Productor interrumpido");
        }
    }

}
