package es.upm.dit.adsw.ej3;

import java.awt.*;

/**
 * Contador de puntuacion.
 *
 *
 * @author Alvaro Gomez Martinez 
 *
 */
public class Puntuacion
        implements Screen.Thing, java.lang.Runnable {
    private final Font font;
    private int puntos;
    private final RW_Monitor monitor = new RW_Monitor();
    
    /**
     * Constructor.
     */
    public Puntuacion() {
        font = new Font("SansSerif", Font.BOLD, 18);
        puntos = 100;
        Game.getScreen().add(this);
    }

    /**
     * Suma puntos.
     *
     * @param n a sumar.
     */
    public synchronized void increment(int n) {
        puntos += n;
    }

    /**
     * Resta puntos.
     *
     * @param n a restar.
     */
    public synchronized void decrement(int n) {
    	puntos -= n;
        if (puntos < 0) {
            Game.getSerpent().quit();
            Game.setState(Game.FINISHED);
        }
    }

    /**
     * Cada segundo resta 1 punto.
     */
    @Override
    public void run() {
        try{
        	while(true) {
        		Thread.sleep(1000);
        		puntos--;
        	}
        } catch(InterruptedException e) {
        	return;
        }
    }

    /**
     * Se imprime en pantalla.
     *
     * @param g pantalla.
     */
    @Override
    public void paint(Graphics2D g) {
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("puntos: " + puntos, 10, 20);
    }
}
