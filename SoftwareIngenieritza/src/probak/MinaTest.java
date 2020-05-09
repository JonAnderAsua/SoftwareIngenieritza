package probak;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packEstruktura.Bandera;
import packEstruktura.Galdera;
import packEstruktura.Irekita;
import packEstruktura.Itxita;
import packEstruktura.Mina;

public class MinaTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEskuinekoClick() {
		Mina g = new Mina(0,0);
		
		//Itxita
		assertTrue(g.getEgoera() instanceof Itxita);
				
				
		//Itxita zegoen bandera jarri
		g.eskuinekoClick();
		assertTrue(g.getEgoera() instanceof Bandera);
				
				
		//Bandera zeukan, galdera jarri
		g.eskuinekoClick();
		assertTrue(g.getEgoera() instanceof Galdera);
				
				
		//Galdera zeukan, itxita utzi
		g.eskuinekoClick();
		assertTrue(g.getEgoera() instanceof Itxita);
						
				
		//Irekita dago, bandera jartzen saiatu
		g.ezkerrekoClick();
		g.eskuinekoClick();
		assertTrue(g.getEgoera() instanceof Irekita);
				
				
		//Irekita dago, galdera jartzen saiatu
		g.eskuinekoClick();
		g.eskuinekoClick();
		assertTrue(g.getEgoera() instanceof Irekita);
		
	}

	@Test
	public void testEzkerrekoClick() {
		Mina g = new Mina(0,0);
		
		
		//Itxita
		assertTrue(g.getEgoera() instanceof Itxita);
		
		
		//Bandera zeukan, irekitzen saiatu
		g.eskuinekoClick();
		g.ezkerrekoClick();
		assertTrue(g.getEgoera() instanceof Bandera);
		
		
		//Itxita zegoen, ireki
		g.eskuinekoClick();
		g.eskuinekoClick();
		g.ezkerrekoClick();
		assertTrue(g.getEgoera() instanceof Irekita);
		
		
		//Irekita dago, irekitzen saiatu
		g.ezkerrekoClick();
		assertTrue(g.getEgoera() instanceof Irekita);
		
		
		//Oso arraroa da tableroaren punta batetik bestera irekitzea lehenengo klikan
		g = new Mina(0,0);
		//Galdera zeukan, ireki
		g.eskuinekoClick();
		g.eskuinekoClick();
		g.ezkerrekoClick();
		assertTrue(g.getEgoera() instanceof Irekita);
				
	}

}
