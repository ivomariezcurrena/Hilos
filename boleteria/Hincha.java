package boleteria;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Hincha implements Runnable {
    private String id;
    private String equipo;
    private int acompañantes;
    private BlockingQueue<Hincha> HinchasBoleteria;

    public Hincha(String id, String equipo, int acompañantes, BlockingQueue<Hincha> HinchasBoleteria) {
        this.id = id;
        this.equipo = equipo;
        this.acompañantes = acompañantes;
        this.HinchasBoleteria = HinchasBoleteria;

    }

    @Override
    public void run() {

        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 6000));
            System.out.println(id + " llega a la boleteria con " + acompañantes + " acompañantes");
            HinchasBoleteria.put(this);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public String getId() {
        return id;
    }

    public String getEquipo() {
        return equipo;
    }

    public int getAcompañantes() {
        return acompañantes;
    }

    public BlockingQueue<Hincha> getHinchasBoleteria() {
        return HinchasBoleteria;
    }

}
