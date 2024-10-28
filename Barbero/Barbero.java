package Barbero;

public class Barbero implements Runnable {
    private String id;
    private Barberia barberia;

    public Barbero(String id, Barberia barberia) {
        this.barberia = barberia;
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Barbero " + id + " comienza a trabajar.");
        while (true) {
            try {
                barberia.cortarPelo(); // Cortar pelo de un cliente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
