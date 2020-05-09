package probak;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packEstruktura.Bandera;
import packEstruktura.Galdera;
import packEstruktura.Hutsa;
import packEstruktura.Irekita;
import packEstruktura.Itxita;

public class HutsaTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testEskuinekoClick() {
		Hutsa g = new Hutsa(0,0);
				
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
		g.egoeraAldatu("ezkerra");
		g.eskuinekoClick();
		assertTrue(g.getEgoera() instanceof Irekita);
				
				
		//Irekita dago, galdera jartzen saiatu
		g.eskuinekoClick();
		g.eskuinekoClick();
		assertTrue(g.getEgoera() instanceof Irekita);
						
	}

	@Test
	public void testEzkerrekoClick() {
		Hutsa g = new Hutsa(0,0);
		
		
		//Itxita
		assertTrue(g.getEgoera() instanceof Itxita);
		
		
		//Bandera zeukan, irekitzen saiatu
		g.eskuinekoClick();
		g.ezkerrekoClick();
		assertTrue(g.getEgoera() instanceof Bandera);
		
		
		//Itxita zegoen, itxita geratuko da
		//Gelaxka hutsen kasuan, errekurtsiboki irekitzeko, egoera aldatu metodoari egiten zaio dei irekitzeko (Itxita edo Galdera egoeretan).
		g.eskuinekoClick();
		g.eskuinekoClick();
		g.ezkerrekoClick();
		assertTrue(g.getEgoera() instanceof Itxita);
		
		
		//Irekita dago, irekitzen saiatu
		g.egoeraAldatu("ezkerra");
		g.ezkerrekoClick();
		assertTrue(g.getEgoera() instanceof Irekita);
		
		
		//Oso arraroa da tableroaren punta batetik bestera irekitzea lehenengo klikan
		g = new Hutsa(0,0);
		//Galdera dauka, irekitzen saiatu (Ez da irekiko, egoeraAldatu metodoaren bidez kudeatzen da)
		g.eskuinekoClick();
		g.eskuinekoClick();
		g.ezkerrekoClick();
		assertTrue(g.getEgoera() instanceof Galdera);
				
		
	}

}
