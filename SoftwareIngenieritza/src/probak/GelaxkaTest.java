package probak;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packEstruktura.Bandera;
import packEstruktura.Galdera;
import packEstruktura.Gelaxka;
import packEstruktura.Irekita;
import packEstruktura.Itxita;
import packEstruktura.Mina;
import packEstruktura.Tableroa;

public class GelaxkaTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testGeti() {
		//Gelaxka baten i koordenatua lortu
		Mina g = new Mina(1,2);
		assertEquals(1,g.geti());
	}

	
	@Test
	public void testGetj() {
		//Gelaxka baten j koordenatua lortu
		Mina g = new Mina(1,2);
		assertEquals(2,g.getj());
	}

	@Test
	public void testGetEgoera() {
		Mina g = new Mina(1,2);
		//Lau egoera posible
		//Itxita
		assertTrue(g.getEgoera() instanceof Itxita);
		
		//Bandera
		g.eskuinekoClick();
		assertTrue(g.getEgoera() instanceof Bandera);
		
		
		//Galdera
		g.eskuinekoClick();
		assertTrue(g.getEgoera() instanceof Galdera);
		
		
		//Irekita
		g.ezkerrekoClick();
		assertTrue(g.getEgoera() instanceof Irekita);
		
	}

	@Test
	public void testEgoeraAldatu() {
		//Edozein motatako gelaxkarekin
		Tableroa.getTableroa().tableroaSortu(10, 7, 0, 0);
		Gelaxka g = Tableroa.getTableroa().balioa(0, 0);
		
		//Itxita
		assertTrue(g.getEgoera() instanceof Itxita);
		
		
		//Itxita zegoen bandera jarri
		g.egoeraAldatu("eskuina");
		assertTrue(g.getEgoera() instanceof Bandera);
		
		
		//Bandera zeukan, irekitzen saiatu
		g.ezkerrekoClick();
		assertTrue(g.getEgoera() instanceof Bandera);
		
		
		//Bandera zeukan, galdera jarri
		g.egoeraAldatu("eskuina");
		assertTrue(g.getEgoera() instanceof Galdera);
		
		
		//Galdera zeukan, itxita utzi
		g.egoeraAldatu("eskuina");
		assertTrue(g.getEgoera() instanceof Itxita);
		
		
		//Itxita zegoen, ireki
		g.egoeraAldatu("ezkerra");
		assertTrue(g.getEgoera() instanceof Irekita);
		
		
		//Irekita dago, bandera jartzen saiatu
		g.egoeraAldatu("eskuina");
		assertTrue(g.getEgoera() instanceof Irekita);
		
		
		//Irekita dago, galdera jartzen saiatu
		g.egoeraAldatu("eskuina");
		g.egoeraAldatu("eskuina");
		assertTrue(g.getEgoera() instanceof Irekita);
		
		
		//Irekita dago, irekitzen saiatu
		g.egoeraAldatu("ezkerra");
		assertTrue(g.getEgoera() instanceof Irekita);
		
		
		//Oso arraroa da tableroaren punta batetik bestera irekitzea lehenengo klikan
		g = Tableroa.getTableroa().balioa(6, 9);
		//Galdera dauka, irekitzen saiatu
		g.egoeraAldatu("eskuina");
		g.egoeraAldatu("eskuina");
		g.egoeraAldatu("ezkerra");
		assertTrue(g.getEgoera() instanceof Irekita);
				
		
	}

}
