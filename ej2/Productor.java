package ej2;

import java.util.concurrent.BlockingQueue;

public class Productor implements Runnable {
    private final BlockingQueue<Documento> buffer;
    private final int nroDoc;

    public Productor(BlockingQueue<Documento> buffer, int nroDoc) {
        this.buffer = buffer;
        this.nroDoc = nroDoc;
    }

    @Override
    public void run() {
        try {

            int cantHojas = (int) (Math.random() * 10) + 1;// sGenera documentos con entre 1 y 10 hojas
            Documento documento = new Documento(nroDoc, cantHojas);
            buffer.put(documento);
            System.out.println("Documento: " + nroDoc + " Cantidad de hojas: " + cantHojas);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Documento " + nroDoc + " interrumpido");
        }
    }
}
