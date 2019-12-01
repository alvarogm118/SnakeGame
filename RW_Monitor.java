package es.upm.dit.adsw.ej3;
/**
 * @author Alvaro Gomez Martinez 
 *
 */

public class RW_Monitor extends RW_Monitor_0 {

	private int nReadersIn;
	private int nWritersIn;
	private int nWritersWaiting;
	
	public RW_Monitor(){}
	
	private void waiting(){
		try{
			wait();
		}catch (InterruptedException ignored){
		}
	}
	
	public synchronized int getNReadersIn(){
		return nReadersIn;
	}
	
	public synchronized int getNWritersIn(){
		return nWritersIn;
	}
	
	public synchronized void openReading(){
		while ((nWritersIn > 0) || (nWritersWaiting > 0)){
			waiting();
		}
		nReadersIn++;
		if (nReadersIn > 2)
			System.err.println("nReadersIn= " + nReadersIn); // Aviso de q esta habiendo mas de 2 y q no es bueno
	}
	
	public synchronized void closeReading() throws IllegalMonitorStateException{
		try{
			if(nReadersIn <= 0) throw new IllegalMonitorStateException();
			
			nReadersIn--;
		} finally {
			notifyAll(); // interrumple los waits indefinidos y al salir de estos se vuelven a ejecutar los whiles.
		}
	}
	
	public synchronized void openWriting(){
		nWritersWaiting++;
		while ((nReadersIn > 0) || (nWritersIn > 0)){
			waiting();
		}
		nWritersWaiting--;
		nWritersIn++;
	}
	
	public synchronized void closeWriting() throws IllegalMonitorStateException{
		try{
			if(nWritersIn != 1) throw new IllegalMonitorStateException();
				
			nWritersIn--;
		} finally {
			notifyAll();
		}
	}
	
}
