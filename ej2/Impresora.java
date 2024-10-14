package ej2;

import java.util.concurrent.BlockingQueue;

public class Impresora implements Runnable {
    private final BlockingQueue<Documento> buffer;
    private final int id;
    private int contador = 0;

    public Impresora(BlockingQueue<Documento> buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Documento documento = buffer.take(); // Toma el documento del buffer
                System.out.println("Impresora " + id + " ha comenzado a imprimir el documento: " + documento.getId()
                        + " Cant. hojas: " + documento.getHojas());

                // Procesar las hojas del documento
                for (int i = 1; i <= documento.getHojas(); i++) {
                    System.out.println("Impresora " + id + " procesando hoja " + i + " de " + documento.getHojas());
                    Thread.sleep(1000); // Simula tiempo de procesamiento por hoja
                }
                System.out.println("Documento " + documento.getId() + " impreso");
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            System.out.println("Consumidor interrumpido");
        }
    }

}
