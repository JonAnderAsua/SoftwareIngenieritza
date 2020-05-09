package probak;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packEstruktura.JokalariKatalogo;
import packEstruktura.Jokalaria;

public class JokalariKatalogoTest {

	@Before
	public void setUp() throws Exception {
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
	}

	@After
	public void tearDown() throws Exception {
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		
	}

	@Test
	public void testDatuakKargatu() {
		//#################################################################################################
		//Lista hutsik dagoela datuak kargatzea
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		Iterator<Jokalaria> itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		assertTrue(!itr.hasNext()); //Hutsik dago
		JokalariKatalogo.getJokalariKatalogo().datuakKargatu();
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		assertTrue(itr.hasNext()); //Gutxienez elemetu bat izango du
		
		
		//#################################################################################################
		//Lista hutsik ez dagoela datuak kargatzea
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon", 997, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon Ander", 998, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Emma", 999, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Paul", 996, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Ander", 995, 1);
		
		JokalariKatalogo.getJokalariKatalogo().datuakKargatu();
		
		//Fitxategia irakurri eta konprobatu datuak kargati direla
		String izena = "./Puntuazioak.txt";
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		Jokalaria j1;
		try(Scanner sc = new Scanner(new File(izena))){
			sc.useDelimiter(";"); 
			while(sc.hasNextLine()){
				j1 = itr.next();
				int puntuazioa = sc.nextInt();
				String nick = sc.nextLine(); 
				assertEquals(nick,";"+j1.getIzena());
				assertEquals(puntuazioa,j1.getPuntuazioa());
			}	
		}
		catch (IOException e) {
			System.out.println("Ezin da itxi");
		}
	}

	
	@Test
	public void testPuntuazioaKalkulatu() {
		//#################################################################################################
		//denbora 0 , zailtasuna 1
		int punt = JokalariKatalogo.getJokalariKatalogo().puntuazioaKalkulatu(0, 1);
		assertEquals(999,punt);
		
		//#################################################################################################
		//denbora 0 , zailtasuna 2
		punt = JokalariKatalogo.getJokalariKatalogo().puntuazioaKalkulatu(0, 2);
		assertEquals(1998,punt);
		
		//#################################################################################################
		//denbora 0 , zailtasuna 3
		punt = JokalariKatalogo.getJokalariKatalogo().puntuazioaKalkulatu(0, 3);
		assertEquals(2997,punt);
		
		//#################################################################################################
		//denbora x , zailtasuna 1
		punt = JokalariKatalogo.getJokalariKatalogo().puntuazioaKalkulatu(99, 1);
		assertEquals(900,punt);
		
		//#################################################################################################
		//denbora x , zailtasuna 2
		punt = JokalariKatalogo.getJokalariKatalogo().puntuazioaKalkulatu(99, 2);
		assertEquals(1800,punt);
		
		//#################################################################################################
		//denbora x , zailtasuna 1
		punt = JokalariKatalogo.getJokalariKatalogo().puntuazioaKalkulatu(99, 3);
		assertEquals(2700,punt);
		
		//#################################################################################################
		//denbora 999 , zailtasuna 1
		punt = JokalariKatalogo.getJokalariKatalogo().puntuazioaKalkulatu(999, 1);
		assertEquals(0,punt);
				
		//#################################################################################################
		//denbora 999 , zailtasuna 2
		punt = JokalariKatalogo.getJokalariKatalogo().puntuazioaKalkulatu(999, 2);
		assertEquals(0,punt);
				
		//#################################################################################################
		//denbora 999 , zailtasuna 3
		punt = JokalariKatalogo.getJokalariKatalogo().puntuazioaKalkulatu(999, 3);
		assertEquals(0,punt);
		
	}

	
	@Test
	public void testJokalarariaSartu() {
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		Jokalaria j1;
		Iterator<Jokalaria> itr;
		
		//#################################################################################################
		//Lista hutsa
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		assertTrue(!itr.hasNext()); //Hutsik dago
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Emma", 999, 1);
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		j1 = itr.next();
		assertEquals("Emma", j1.getIzena());
		assertEquals(0, j1.getPuntuazioa());
		assertFalse(itr.hasNext());
		
		
		//#################################################################################################
		//Elementu bat listan, lehengoa jarri
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Emma", 999, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon Ander", 998, 1);
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		j1 = itr.next();
		assertEquals("Jon Ander", j1.getIzena());
		assertEquals(1, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Emma", j1.getIzena());
		assertEquals(0, j1.getPuntuazioa());
		assertFalse(itr.hasNext());
		
		//#################################################################################################
		//Elementu bat listan, bigarrena jarri
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon Ander", 998, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Emma", 999, 1);
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		j1 = itr.next();
		assertEquals("Jon Ander", j1.getIzena());
		assertEquals(1, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Emma", j1.getIzena());
		assertEquals(0, j1.getPuntuazioa());
		assertFalse(itr.hasNext());
		
		
		//#################################################################################################
		//Zenbait elementu listan, lehenengoa jarri
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon Ander", 998, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Emma", 999, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon", 997, 1);
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		j1 = itr.next();
		assertEquals("Jon", j1.getIzena());
		assertEquals(2, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Jon Ander", j1.getIzena());
		assertEquals(1, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Emma", j1.getIzena());
		assertEquals(0, j1.getPuntuazioa());
		assertFalse(itr.hasNext());
		
		
		//#################################################################################################
		//Zenbait elementu listan, erdian jarri
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon", 997, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Emma", 999, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon Ander", 998, 1);
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		j1 = itr.next();
		assertEquals("Jon", j1.getIzena());
		assertEquals(2, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Jon Ander", j1.getIzena());
		assertEquals(1, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Emma", j1.getIzena());
		assertEquals(0, j1.getPuntuazioa());
		assertFalse(itr.hasNext());
		
		
		//#################################################################################################
		//Zenbait elementu listan, bukaeran jarri
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon", 997, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon Ander", 998, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Emma", 999, 1);
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		j1 = itr.next();
		assertEquals("Jon", j1.getIzena());
		assertEquals(2, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Jon Ander", j1.getIzena());
		assertEquals(1, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Emma", j1.getIzena());
		assertEquals(0, j1.getPuntuazioa());
		assertFalse(itr.hasNext());
		
		//#################################################################################################
		//Hamar elementu listan, sartu
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon", 997, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon Ander", 998, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Emma", 999, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Paul", 996, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Ander", 995, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Kerman", 993, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("AnderPri", 992, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Sanju", 991, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Txefin", 990, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Mikel", 989, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Josu", 994, 1);
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		j1 = itr.next();
		assertEquals("Mikel", j1.getIzena());
		assertEquals(10, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Txefin", j1.getIzena());
		assertEquals(9, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Sanju", j1.getIzena());
		assertEquals(8, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("AnderPri", j1.getIzena());
		assertEquals(7, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Kerman", j1.getIzena());
		assertEquals(6, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Josu", j1.getIzena());
		assertEquals(5, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Ander", j1.getIzena());
		assertEquals(4, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Paul", j1.getIzena());
		assertEquals(3, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Jon", j1.getIzena());
		assertEquals(2, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Jon Ander", j1.getIzena());
		assertEquals(1, j1.getPuntuazioa());
		assertFalse(itr.hasNext());
		
		
		//#################################################################################################
		//Hamar elementu listan, ez sartu
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon", 997, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon Ander", 998, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Paul", 996, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Ander", 995, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Kerman", 993, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("AnderPri", 992, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Sanju", 991, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Txefin", 990, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Mikel", 989, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Josu", 994, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Emma", 999, 1);
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		j1 = itr.next();
		assertEquals("Mikel", j1.getIzena());
		assertEquals(10, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Txefin", j1.getIzena());
		assertEquals(9, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Sanju", j1.getIzena());
		assertEquals(8, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("AnderPri", j1.getIzena());
		assertEquals(7, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Kerman", j1.getIzena());
		assertEquals(6, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Josu", j1.getIzena());
		assertEquals(5, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Ander", j1.getIzena());
		assertEquals(4, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Paul", j1.getIzena());
		assertEquals(3, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Jon", j1.getIzena());
		assertEquals(2, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Jon Ander", j1.getIzena());
		assertEquals(1, j1.getPuntuazioa());
		assertFalse(itr.hasNext());
		
		
		//#################################################################################################
		//Hamar elementu listan, lehenengoa jarri
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon", 997, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon Ander", 998, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Emma", 999, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Paul", 996, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Ander", 995, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Kerman", 993, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("AnderPri", 992, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Sanju", 991, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Txefin", 990, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Josu", 994, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Mikel", 989, 1);
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		j1 = itr.next();
		assertEquals("Mikel", j1.getIzena());
		assertEquals(10, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Txefin", j1.getIzena());
		assertEquals(9, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Sanju", j1.getIzena());
		assertEquals(8, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("AnderPri", j1.getIzena());
		assertEquals(7, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Kerman", j1.getIzena());
		assertEquals(6, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Josu", j1.getIzena());
		assertEquals(5, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Ander", j1.getIzena());
		assertEquals(4, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Paul", j1.getIzena());
		assertEquals(3, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Jon", j1.getIzena());
		assertEquals(2, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Jon Ander", j1.getIzena());
		assertEquals(1, j1.getPuntuazioa());
		assertFalse(itr.hasNext());
		
		//#################################################################################################
		//Hamar elementu listan, azkena jarri
		//Hamar elementu listan, sartu
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon", 997, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Emma", 999, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Paul", 996, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Ander", 995, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Kerman", 993, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("AnderPri", 992, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Sanju", 991, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Txefin", 990, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Mikel", 989, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Josu", 994, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon Ander", 998, 1);
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		j1 = itr.next();
		assertEquals("Mikel", j1.getIzena());
		assertEquals(10, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Txefin", j1.getIzena());
		assertEquals(9, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Sanju", j1.getIzena());
		assertEquals(8, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("AnderPri", j1.getIzena());
		assertEquals(7, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Kerman", j1.getIzena());
		assertEquals(6, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Josu", j1.getIzena());
		assertEquals(5, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Ander", j1.getIzena());
		assertEquals(4, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Paul", j1.getIzena());
		assertEquals(3, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Jon", j1.getIzena());
		assertEquals(2, j1.getPuntuazioa());
		j1 = itr.next();
		assertEquals("Jon Ander", j1.getIzena());
		assertEquals(1, j1.getPuntuazioa());
		assertFalse(itr.hasNext());
		
	}

	
	@Test
	public void testJokalariakLortu() {
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		Iterator<Jokalaria> itr;
		
		//#################################################################################################
		//Lista hutsa dagoenean
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		assertFalse(itr.hasNext());
		
		//#################################################################################################
		//Elementu bat du
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Emma", 999, 1);
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		int kont = 0;
		while(itr.hasNext()){
			kont++;
			itr.next();
		}
		assertEquals(1,kont);
		
		//#################################################################################################
		//Elementu ugari ditu
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		JokalariKatalogo.getJokalariKatalogo().listaHustu();
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon", 997, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Jon Ander", 998, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Emma", 999, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Paul", 996, 1);
		JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu("Ander", 995, 1);
		
		itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		kont = 0;
		while(itr.hasNext()){
			kont++;
			itr.next();
		}
		assertEquals(5,kont);
	}

}
