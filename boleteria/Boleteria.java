package boleteria;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Boleteria implements Runnable {
    private BlockingQueue<Hincha> HinchasBoleteria;
    private BlockingQueue<Hincha> HinchasPuertaLocal;
    private BlockingQueue<Hincha> HinchasPuertaVisitante;
    private String id;
    private AtomicInteger boletos;

    public Boleteria(BlockingQueue<Hincha> HinchasBoleteria, String id, int boletosIniciales,
            BlockingQueue<Hincha> HinchasPuertaLocal, BlockingQueue<Hincha> HinchasPuertaVisitante) {
        this.HinchasBoleteria = HinchasBoleteria;
        this.id = id;
        this.boletos = new AtomicInteger(boletosIniciales);
        this.HinchasPuertaLocal = HinchasPuertaLocal;
        this.HinchasPuertaVisitante = HinchasPuertaVisitante;
    }

    @Override
    public void run() {
        System.out.println("Boleteria " + id + " abre");
        try {
            while (boletos.get() != 0) {
                Hincha hincha = HinchasBoleteria.poll(10, TimeUnit.SECONDS);
                if (hincha == null)
                    break;
                Thread.sleep(ThreadLocalRandom.current().nextInt(4000, 6000)); // Tiempo aleatorio de 2 a 6 segundos
                System.out.println(id + " atiende al hincha --> " + hincha.getId());
                comprar(hincha);
                if (hincha.getEquipo().equals("Visitante")) {
                    HinchasPuertaVisitante.put(hincha);
                } else {
                    HinchasPuertaLocal.put(hincha);
                }
            }
            System.out.println("Boleteria " + id + " ha vendido todos sus boletos.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Boleteria " + id + " fue interrumpida.");
        }
    }

    private synchronized void comprar(Hincha hincha) throws InterruptedException {
        int boletosNecesarios = hincha.getAcompañantes() + 1;
        if (boletos.get() >= boletosNecesarios) {
            boletos.addAndGet(-boletosNecesarios);
            System.out.println(
                    hincha.getId() + " compro " + boletosNecesarios + " boletos");
        } else {
            System.out.println("No hay suficientes boletos para " + hincha.getId());
        }
    }
}
