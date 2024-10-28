package Barbero;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class Simulacion {
    public static void main(String[] args) {
        Barberia barberia = new Barberia();
        Barbero barbero = new Barbero("Miguel", barberia);

        Thread thBarbero = new Thread(barbero);
        thBarbero.start(); // Inicia al barbero

        // Simulamos varios clientes llegando en diferentes momentos
        for (int i = 1; i <= 20; i++) {
            Cliente cliente = new Cliente("Cliente " + i, barberia);
            Thread thCliente = new Thread(cliente);
            thCliente.start();
        }
    }
}
