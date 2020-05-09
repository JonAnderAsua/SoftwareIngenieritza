package probak;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packEstruktura.Gelaxka;
import packEstruktura.GelaxkaFactory;
import packEstruktura.Hutsa;
import packEstruktura.Mina;
import packEstruktura.Zenbakia;

public class GelaxkaFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSortuGelaxka() {
		//Hiru mota desberdinetako gelaxkak sortu
		Gelaxka g;
		
		//HUTSA
		g = GelaxkaFactory.getGelaxkaFactory().sortuGelaxka("Hutsa", 0, 0);
		assertTrue(g instanceof Hutsa);
		
		//MINA
		g = GelaxkaFactory.getGelaxkaFactory().sortuGelaxka("Mina", 0, 0);
		assertTrue(g instanceof Mina);
		
		//ZENBAKIA
		g = GelaxkaFactory.getGelaxkaFactory().sortuGelaxka("Zenbakia", 0, 0);
		assertTrue(g instanceof Zenbakia);
		
		//Mota ez bada ondo sartzen, HUTSA
		g = GelaxkaFactory.getGelaxkaFactory().sortuGelaxka("Emma", 0, 0);
		assertTrue(g instanceof Hutsa);
		
	}

}
