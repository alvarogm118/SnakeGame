package es.upm.dit.adsw.ej3;
/**
 * @author Alvaro Gomez Martinez 
 *
 */
import org.junit.Before;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RW_MonitorTest {

	    private RW_Monitor monitor;

	    @Before
	    public void setup() {
	        monitor = new RW_Monitor();
	    }
	    
	    @Test
	    public void test1() {
	    	assertEquals(0, monitor.getNReadersIn());
	    	assertEquals(0, monitor.getNWritersIn()); 
	    	
	    	monitor.openReading(); 
	    	
	    	assertEquals(1, monitor.getNReadersIn()); 
	    	assertEquals(0, monitor.getNWritersIn()); 
	    }
	   
}
