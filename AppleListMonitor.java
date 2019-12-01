package es.upm.dit.adsw.ej3;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Alvaro Gomez Martinez 
 *
 */
public class AppleListMonitor extends java.lang.Object{

	
	private final List<Apple> appleList = new ArrayList<Apple>(); // Lista de objetos Apple
	private final RW_Monitor monitor = new RW_Monitor(); // Nos creamos un objeto RW_Monitor
	
	
	public AppleListMonitor(){}
	
	public void add(Apple apple){
		monitor.openWriting(); // Solicitamos el permiso de escritura, que se hará si no hay otro escribiendo.
		if (appleList.contains(apple) == false)
			appleList.add(apple);
		monitor.closeWriting(); // Eliminamos el espacio que se ha usado para escribir.
	}
	
	public void remove(Apple apple){ //Análogo.
		monitor.openWriting();
		if (appleList.contains(apple) == true)
			appleList.remove(apple);
		monitor.closeWriting();
	}
	
	
	public Apple getCloseApple(XY P1, XY P2){
		monitor.openReading();
		Apple closest = null;
		for (Apple a : appleList){
			if (a.getXY().isCloseTo(P1,P2)) // Es la 1a que pille.
				closest=a;
		}
		monitor.closeReading();
		return closest;
	}
	
	public Apple hitCloseApple(XY P1, XY P2){
		monitor.openWriting();
		Apple closest = null;
		for (Apple a : appleList){
			if (a.getXY().isCloseTo(P1,P2))
			closest=a;
		}
		appleList.remove(closest);
		closest.quit(); // Metodo para quitar la manzana de la pantalla.
		monitor.closeWriting();
		return closest;
	}
	
}
