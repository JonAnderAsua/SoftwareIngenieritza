package probak;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packEstruktura.Bandera;
import packEstruktura.Galdera;
import packEstruktura.Irekita;
import packEstruktura.Itxita;
import packEstruktura.Zenbakia;

public class ZenbakiaTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEskuinekoClick() {
		Zenbakia g = new Zenbakia(0,0);
		
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
		Zenbakia g = new Zenbakia(0,0);
		
		
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
		g = new Zenbakia(0,0);
		//Galdera zeukan, ireki
		g.eskuinekoClick();
		g.eskuinekoClick();
		g.ezkerrekoClick();
		assertTrue(g.getEgoera() instanceof Irekita);
				
	}

	@Test
	public void testSetZenbakia() {
		//Ez dauka zenbakirik, zenbakia jarri
		Zenbakia z = new Zenbakia(0,0);
		//Defektuz 0 balioa hartzen du
		assertEquals(0,z.getZenbakia());
		z.setZenbakia(1);
		assertEquals(1,z.getZenbakia());
		
		
		//Jada zenbakia dauka, aldatu
		z = new Zenbakia(0,0);
		//Defektuz 0 balioa hartzen du
		assertEquals(0,z.getZenbakia());
		z.setZenbakia(1);
		assertEquals(1,z.getZenbakia());
		z.setZenbakia(12);
		assertEquals(12,z.getZenbakia());
	}
	

	@Test
	public void testGetZenbakia() {
		//Zenbakia lortu
		Zenbakia z = new Zenbakia(0,0);
		//Defektuz 0 balioa hartzen du
		assertEquals(0,z.getZenbakia());
		z.setZenbakia(1);
		assertEquals(1,z.getZenbakia());
		
		
		//Aurreko zenbakia aldatu eta berria lortu
		z = new Zenbakia(0,0);
		//Defektuz 0 balioa hartzen du
		assertEquals(0,z.getZenbakia());
		z.setZenbakia(1);
		assertEquals(1,z.getZenbakia());
		z.setZenbakia(12);
		assertEquals(12,z.getZenbakia());
	}

}
