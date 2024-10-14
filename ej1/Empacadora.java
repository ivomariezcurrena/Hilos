package ej1;

import java.util.concurrent.BlockingQueue;

public class Empacadora implements Runnable {

    private final BlockingQueue<Producto> buffer;
    private final int EMPAQUETA = 6;
    private final int MAXIMO = 72;

    public Empacadora(BlockingQueue<Producto> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            int cantidad = 0;
            for (int count = 1; count <= MAXIMO; count++) {

                Thread.sleep(1000);// Cada segundo toma un producto
                Producto producto = buffer.take(); // Toma el producto del buffer
                cantidad++;// una vez que toma suma a empaqueta, cuando sean 6 va a empaquetar el producto
                System.out.println("La empaquetadora agarro " + cantidad);
                if (EMPAQUETA == cantidad) {// si llega a 6 tarda 3 seg para empaquetar
                    System.out.println("comenzo a empaquetar (3seg)");
                    Thread.sleep(3000);
                    cantidad = 0;// resetea empaqueta
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Consumidor interrumpido");
        }
    }
}
