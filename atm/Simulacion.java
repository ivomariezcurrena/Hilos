package atm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulacion {
    private final static int NUM_CAJEROS = 3;

    public static void main(String[] args) {
        System.out.println("INICIO DE LA SIMULACION");

        String[] operaciones = { "Extraer", "Depositar" };
        Random random = new Random();

        // Crear múltiples cajeros
        List<Cajero> cajeros = new ArrayList<>();
        for (int i = 1; i <= NUM_CAJEROS; i++) {
            cajeros.add(new Cajero(i, 1000)); // Cada cajero empieza con $1000
        }

        // Crea y arranca los hilos para los clientes
        for (int i = 1; i <= 6; i++) {
            int eleccionOp = random.nextInt(operaciones.length); // Selecciona operación al azar
            Persona persona = new Persona("Persona " + i, cajeros, operaciones[eleccionOp]);
            new Thread(persona).start(); // Lanza el hilo de la persona
        }
    }
}
