package carrera_caballos;

import java.util.concurrent.ThreadLocalRandom;

public class Caballo implements Runnable {
    private String id;
    private double distancia;
    private final double META;
    private static volatile boolean carreraTerminada = false; // Bandera compartida para determinar si la carrera
                                                              // terminó

    public Caballo(String id, double META) {
        this.id = id;
        this.META = META;
        this.distancia = 0; // Inicializamos la distancia a 0
    }

    @Override
    public void run() {
        while (!carreraTerminada && distancia < META) { // Sigue corriendo si la carrera no ha terminado

            correr();

            if (distancia >= META && !carreraTerminada) {
                synchronized (this) {
                    carreraTerminada = true; // Solo el primer caballo en alcanzar la meta marca el fin de la carrera
                    System.out.println(id + " gana la carrera!");
                }
            }
        }
    }

    private void correr() {
        // Avance aleatorio entre 1 y 10 metros
        distancia += ThreadLocalRandom.current().nextInt(1, 11);
        System.out.println(id + " corre y está a " + distancia + " metros");
        try {
            Thread.sleep(1000); // Simula el tiempo que tarda en correr
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
