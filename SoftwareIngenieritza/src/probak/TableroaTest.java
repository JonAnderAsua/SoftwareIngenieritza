package probak;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packEstruktura.Bandera;
import packEstruktura.Egoera;
import packEstruktura.Galdera;
import packEstruktura.Gelaxka;
import packEstruktura.Hutsa;
import packEstruktura.Irekita;
import packEstruktura.Itxita;
import packEstruktura.Mina;
import packEstruktura.Tableroa;
import packEstruktura.Zenbakia;

public class TableroaTest {

	@Before
	public void setUp() throws Exception {
		Tableroa.getTableroa().hasieratu();
	}

	@After
	public void tearDown() throws Exception {
		Tableroa.getTableroa().hasieratu();
	}


	
	@Test
	public void testHasieratu() {
		//Probak galdu atributua begiratuz egingo dira, bata aldatzen bada besteak ere aldatuko direlako.
		//Gainera partidaGaldu() metodo publikoa jada sortuta dago eta erosoagoa da hori erabiltzea.
		
		//#################################
		//Tableroa sortu berri da, Galdu atributua beti FALSE izango da
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		assertEquals(false, Tableroa.getTableroa().partidaGaldu());
		
		//#################################
		//Partida bat nahita galdu, Galdu atributua TRUE izatera aldatzeko
		//Gelaxka guztiak ikutu
		for(int i=0; i<7 ; i++){
			for(int j=0; j<10; j++){
				Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
			}
		}
		assertEquals(true, Tableroa.getTableroa().partidaGaldu());
		
		//#################################
		//Hasieratzean Galdu atributua FALSE izan behar da
		Tableroa.getTableroa().hasieratu();
		assertEquals(false, Tableroa.getTableroa().partidaGaldu());
		
	}

	
	@Test
	public void testTableroaSortu() {
		//Konprobatzeko modu bakarra: Auretik tablero bat izanda, tamaina desberdineko tablero bat sortzea
		
		//#################################
		//1
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 0, 0);
		assertEquals(7,Tableroa.getTableroa().geti());
		assertEquals(10,Tableroa.getTableroa().getj());
		assertEquals(10,Tableroa.getTableroa().getMinak());
		
		//#################################
		//2
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(15, 10, 0, 0);
		assertEquals(10,Tableroa.getTableroa().geti());
		assertEquals(15,Tableroa.getTableroa().getj());
		assertEquals(30,Tableroa.getTableroa().getMinak());
		
		//#################################
		//3
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(25, 12, 0, 0);
		assertEquals(12,Tableroa.getTableroa().geti());
		assertEquals(25,Tableroa.getTableroa().getj());
		assertEquals(75,Tableroa.getTableroa().getMinak());
	}
	

	@Test
	public void testEskuinekoClick() {
		//#################################
		//Lehenengo klik-a ez da egin, kontagailua ez da aldatzen
		Tableroa.getTableroa().hasieratu();
		Egoera e = Tableroa.getTableroa().balioa(0, 0).getEgoera();
		Tableroa.getTableroa().eskuinekoClick(0, 0);
		assertEquals(e, Tableroa.getTableroa().balioa(0, 0).getEgoera()); //Egoera ez da aldatu
		assertEquals(10,Tableroa.getTableroa().getMinak());
		
		
		//#################################
		//Partida hasi da, bandera jar daiteke, kontagailua alda daiteke
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 1, 1);
		Tableroa.getTableroa().eskuinekoClick(6, 9);
		//Oso zaila izango da punta batetik bestera guztiak irekitzea lehengo klik-ean
		//Edo ireki da, egoera aldatu da
		//Edo itxita geratu da, bandera jarri da, egoera aldatu da 
		assertTrue((Tableroa.getTableroa().balioa(6, 9).getEgoera() instanceof Irekita)||(Tableroa.getTableroa().balioa(6, 9).getEgoera() instanceof Bandera));
		if(Tableroa.getTableroa().balioa(6, 9).getEgoera() instanceof Irekita){
			assertEquals(10,Tableroa.getTableroa().getMinak());
		}
		else{
			assertEquals(9,Tableroa.getTableroa().getMinak());
		}
		
		
		//#################################
		//Gelaxka itxita dago, bandera jarri, mina kontagailua -1
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 1, 1);
		boolean aurkitua = false;
		int i = 0; 
		int j = 0;
		
		//Gelaxka Itxi bat lortu
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Itxita){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}

		Tableroa.getTableroa().eskuinekoClick(i, j);
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Bandera);
		assertEquals(9,Tableroa.getTableroa().getMinak());
	
		
		//#################################
		//Gelaxkak bandera dauka, galdera jarri, mina kontagailua +1
		Tableroa.getTableroa().eskuinekoClick(i, j);
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Galdera);
		assertEquals(10,Tableroa.getTableroa().getMinak());
		
		
		//#################################
		//Gelaxkak galdera dauka, itxi, kontagailua ez da aldatzen
		Tableroa.getTableroa().eskuinekoClick(i, j);
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Itxita);
		assertEquals(10,Tableroa.getTableroa().getMinak());
		
		
		//#################################
		//Irekita dago, ezer ez egin, kontagailua ez da aldatzen
		Tableroa.getTableroa().eskuinekoClick(1, 1);
		assertTrue(Tableroa.getTableroa().balioa(1, 1).getEgoera() instanceof Irekita);
		assertEquals(10,Tableroa.getTableroa().getMinak());
		
		
		//#################################
		//Bandera kopuru maximoa jarri da, ezin da banderarik/galderarik jarri, kontagailua ez da aldatzen
		for(i=0; i<7 ; i++){
			for(j=0; j<10; j++){
				Tableroa.getTableroa().eskuinekoClick(i, j);
			}
		}
		aurkitua = false;
		i = 0; 
		j = 0;
		
		//Gelaxka Itxi bat lortu
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Itxita){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
		Tableroa.getTableroa().eskuinekoClick(i, j);
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Itxita);
		assertEquals(0,Tableroa.getTableroa().getMinak());
		
		
		//#################################
		//Partida irabazi da, ez da aldaketarik egongo
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		for(i=0; i<7 ; i++){
			for(j=0; j<10; j++){
				if(!(Tableroa.getTableroa().balioa(i, j) instanceof Mina)){
					Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
				}
				
			}
		}
		e = Tableroa.getTableroa().balioa(0, 0).getEgoera();
		int kont = Tableroa.getTableroa().getMinak();
		Tableroa.getTableroa().eskuinekoClick(0, 0);
		assertEquals(e, Tableroa.getTableroa().balioa(0, 0).getEgoera());
		assertEquals(kont, Tableroa.getTableroa().getMinak());
		
		
		//#################################
		//Partida galdu da, ez da aldaketarik egongo
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		for(i=0; i<7 ; i++){
			for(j=0; j<10; j++){
				Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
			}
		}
		e = Tableroa.getTableroa().balioa(0, 0).getEgoera();
		kont = Tableroa.getTableroa().getMinak();
		Tableroa.getTableroa().eskuinekoClick(0, 0);
		assertEquals(e, Tableroa.getTableroa().balioa(0, 0).getEgoera());
		assertEquals(kont, Tableroa.getTableroa().getMinak());
	}

	
	@Test
	public void testEzkerrekoClick() {
		//#################################
		//Partidako lehenengo klika (tableroa sortu dela begiratu)
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(15, 10, 0, 0);
		//Auretik tablero bat egongo da sortuta. Mina kop = 30
		Tableroa.getTableroa().hasieratu();
		assertEquals(30,Tableroa.getTableroa().getMinak()); //Mina kopurua ez da aldatu
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 0, 0); 
		//Tablero berria sortu da, mina kopurua aldatu da
		assertEquals(10,Tableroa.getTableroa().getMinak());
		
		
		//#################################
		//Gelaxka itxita dago, zenbakia da
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 1, 1);
		boolean aurkitua = false;
		int i = 0; 
		int j = 0;
		
		//Gelaxka Itxi Zenbakidun bat lortu
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Itxita) && (Tableroa.getTableroa().balioa(i, j) instanceof Zenbakia)){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
		
		Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita);
		
		
		//#################################
		//Gelaxka itxita dago, hutsa da (ez dago banderarik inguruan)
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		aurkitua = false;
		i = 0; 
		j = 0;
		
		//Gelaxka Itxi bat lortu
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Itxita) && Tableroa.getTableroa().balioa(i, j) instanceof Hutsa){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
		Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita);
		
		//Ingurukoak ere begiratu ireki diren
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Irekita);
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Irekita);
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Irekita);
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Irekita);
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Irekita);
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Irekita);
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Irekita);
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Irekita);
		}
		
		
		//#################################
		//Gelaxka itxita dago, hutsa da, inguruan galdera ikurrak daude
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		aurkitua = false;
		i = 0; 
		j = 0;
		
		//Gelaxka Itxi bat lortu
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Itxita) && Tableroa.getTableroa().balioa(i, j) instanceof Hutsa){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
		
		//Inguruan galdera ikurrak jarri
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			Tableroa.getTableroa().eskuinekoClick(i-1, j-1);
			Tableroa.getTableroa().eskuinekoClick(i-1, j-1);
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			Tableroa.getTableroa().eskuinekoClick(i-1, j);
			Tableroa.getTableroa().eskuinekoClick(i-1, j);
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			Tableroa.getTableroa().eskuinekoClick(i, j-1);
			Tableroa.getTableroa().eskuinekoClick(i, j-1);
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			Tableroa.getTableroa().eskuinekoClick(i+1, j+1);
			Tableroa.getTableroa().eskuinekoClick(i+1, j+1);
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			Tableroa.getTableroa().eskuinekoClick(i-1, j+1);
			Tableroa.getTableroa().eskuinekoClick(i-1, j+1);
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			Tableroa.getTableroa().eskuinekoClick(i+1, j-1);
			Tableroa.getTableroa().eskuinekoClick(i+1, j-1);
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			Tableroa.getTableroa().eskuinekoClick(i+1, j);
			Tableroa.getTableroa().eskuinekoClick(i+1, j);
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			Tableroa.getTableroa().eskuinekoClick(i, j+1);
			Tableroa.getTableroa().eskuinekoClick(i, j+1);
		}
				
		Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita);
		
		//Ingurukoak ere begiratu ireki diren
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Irekita);
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Irekita);
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Irekita);
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Irekita);
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Irekita);
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Irekita);
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Irekita);
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Irekita);
		}
		
		
		//#################################
		//Gelaxka itxita dago, hutsa da, inguruan banderak daude
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 1, 1);
		aurkitua = false;
		i = 0; 
		j = 0;
		
		//Gelaxka Itxi bat lortu
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Itxita) && Tableroa.getTableroa().balioa(i, j) instanceof Hutsa){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
		if(aurkitua){
			//Inguruan banderak jarri
			if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				Tableroa.getTableroa().eskuinekoClick(i-1, j-1);
			}
			if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
				Tableroa.getTableroa().eskuinekoClick(i-1, j);
			}
			if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				Tableroa.getTableroa().eskuinekoClick(i, j-1);
			}
			if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				Tableroa.getTableroa().eskuinekoClick(i+1, j+1);
			}
			if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				Tableroa.getTableroa().eskuinekoClick(i-1, j+1);
			}
			if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				Tableroa.getTableroa().eskuinekoClick(i+1, j-1);
			}
			if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
				Tableroa.getTableroa().eskuinekoClick(i+1, j);
			}
			if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				Tableroa.getTableroa().eskuinekoClick(i, j+1);
			}
			
			//Hasierako egoera gorde
			Egoera e1 = null;
			Egoera e2 = null;
			Egoera e3 = null;
			Egoera e4 = null;
			Egoera e5 = null;
			Egoera e6 = null;
			Egoera e7 = null;
			Egoera e8 = null;
			Egoera e = Tableroa.getTableroa().balioa(i, j).getEgoera();
			if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				e1 = Tableroa.getTableroa().balioa(i-1, j-1).getEgoera();
			}
			if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
				e2 = Tableroa.getTableroa().balioa(i-1, j).getEgoera();
			}
			if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				e3 = Tableroa.getTableroa().balioa(i, j-1).getEgoera();
			}
			if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				e4 = Tableroa.getTableroa().balioa(i+1, j+1).getEgoera();
			}
			if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				e5 = Tableroa.getTableroa().balioa(i-1, j+1).getEgoera();
			}
			if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				e6 = Tableroa.getTableroa().balioa(i+1, j-1).getEgoera();
			}
			if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
				e7 = Tableroa.getTableroa().balioa(i+1, j).getEgoera();
			}
			if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				e8 = Tableroa.getTableroa().balioa(i, j+1).getEgoera();
			}
					
			Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
			assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita);
			
			//Ingurukoak aldatu ez direla begiratu
			if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				assertEquals(e1,Tableroa.getTableroa().balioa(i-1, j-1).getEgoera());
			}
			if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
				assertEquals(e2,Tableroa.getTableroa().balioa(i-1, j).getEgoera());
			}
			if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				assertEquals(e3,Tableroa.getTableroa().balioa(i, j-1).getEgoera());
			}
			if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				assertEquals(e4,Tableroa.getTableroa().balioa(i+1, j+1).getEgoera());
			}
			if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				assertEquals(e5,Tableroa.getTableroa().balioa(i-1, j+1).getEgoera());
			}
			if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				assertEquals(e6,Tableroa.getTableroa().balioa(i+1, j-1).getEgoera());
			}
			if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
				assertEquals(e7,Tableroa.getTableroa().balioa(i+1, j).getEgoera());
			}
			if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
				assertEquals(e8,Tableroa.getTableroa().balioa(i, j+1).getEgoera());
			}
		}
		else{
			System.out.println("Ez da gelaxka hutsik aurkitu, berriz exekutatu");
		}
		
		
		
		//#################################
		//Gelaxka itxita dago, mina da
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 1, 1);
		aurkitua = false;
		i = 0; 
		j = 0;
		
		//Gelaxka Itxi bat lortu
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Itxita) && (Tableroa.getTableroa().balioa(i, j) instanceof Mina)){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
		
		Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
		assertEquals(true, Tableroa.getTableroa().partidaGaldu());
		
		
		//#################################
		//Gelaxka irekita dago, zenbakia da, inguruko bandera kopurua ez da nahikoa
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 1, 1);
		aurkitua = false;
		i = 0; 
		j = 0;
		
		//Gelaxka ireki Zenbakidun bat lortu
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita) && (Tableroa.getTableroa().balioa(i, j) instanceof Zenbakia)){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
		Egoera e1 = null;
		Egoera e2 = null;
		Egoera e3 = null;
		Egoera e4 = null;
		Egoera e5 = null;
		Egoera e6 = null;
		Egoera e7 = null;
		Egoera e8 = null;
		
		//Inguruko gelaxken egoera lortu
		Egoera e = Tableroa.getTableroa().balioa(i, j).getEgoera();
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e1 = Tableroa.getTableroa().balioa(i-1, j-1).getEgoera();
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e2 = Tableroa.getTableroa().balioa(i-1, j).getEgoera();
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e3 = Tableroa.getTableroa().balioa(i, j-1).getEgoera();
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e4 = Tableroa.getTableroa().balioa(i+1, j+1).getEgoera();
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e5 = Tableroa.getTableroa().balioa(i-1, j+1).getEgoera();
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e6 = Tableroa.getTableroa().balioa(i+1, j-1).getEgoera();
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e7 = Tableroa.getTableroa().balioa(i+1, j).getEgoera();
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e8 = Tableroa.getTableroa().balioa(i, j+1).getEgoera();
		}
		
		Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
		
		//Ingurukoak aldatu ez direla begiratu
		assertEquals(e, Tableroa.getTableroa().balioa(i, j).getEgoera());
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertEquals(e1,Tableroa.getTableroa().balioa(i-1, j-1).getEgoera());
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertEquals(e2,Tableroa.getTableroa().balioa(i-1, j).getEgoera());
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertEquals(e3,Tableroa.getTableroa().balioa(i, j-1).getEgoera());
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertEquals(e4,Tableroa.getTableroa().balioa(i+1, j+1).getEgoera());
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertEquals(e5,Tableroa.getTableroa().balioa(i-1, j+1).getEgoera());
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertEquals(e6,Tableroa.getTableroa().balioa(i+1, j-1).getEgoera());
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertEquals(e7,Tableroa.getTableroa().balioa(i+1, j).getEgoera());
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertEquals(e8,Tableroa.getTableroa().balioa(i, j+1).getEgoera());
		}
		
		
		//#################################
		//Gelaxka irekita dago, zenbakia da, inguruko bandera kopurua nahikoa da
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 1, 1);
		aurkitua = false;
		i = 0; 
		j = 0;
		
		//Gelaxka ireki Zenbakidun bat lortu
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita) && (Tableroa.getTableroa().balioa(i, j) instanceof Zenbakia)){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
		
		//Inguruko beharrezko bandera kopurua jarri
		int kop = ((Zenbakia) Tableroa.getTableroa().balioa(i, j)).getZenbakia();
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Itxita){
				Tableroa.getTableroa().eskuinekoClick(i-1, j-1);
				kop--;
			}
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Itxita){
				Tableroa.getTableroa().eskuinekoClick(i-1, j);
				kop--;
			}
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Itxita){
				Tableroa.getTableroa().eskuinekoClick(i, j-1);
				kop--;
			}
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Itxita){
				Tableroa.getTableroa().eskuinekoClick(i+1, j+1);
				kop--;
			}
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Itxita){
				Tableroa.getTableroa().eskuinekoClick(i-1, j+1);
				kop--;
			}
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Itxita){
				Tableroa.getTableroa().eskuinekoClick(i+1, j-1);
				kop--;
			}
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Itxita){
				Tableroa.getTableroa().eskuinekoClick(i+1, j);
				kop--;
			}
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Itxita){
				Tableroa.getTableroa().eskuinekoClick(i, j+1);
				kop--;
			}
		}
		
		//Hasierako egoera gorde
		e = Tableroa.getTableroa().balioa(i, j).getEgoera();
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e1 = Tableroa.getTableroa().balioa(i-1, j-1).getEgoera();
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e2 = Tableroa.getTableroa().balioa(i-1, j).getEgoera();
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e3 = Tableroa.getTableroa().balioa(i, j-1).getEgoera();
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e4 = Tableroa.getTableroa().balioa(i+1, j+1).getEgoera();
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e5 = Tableroa.getTableroa().balioa(i-1, j+1).getEgoera();
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e6 = Tableroa.getTableroa().balioa(i+1, j-1).getEgoera();
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e7 = Tableroa.getTableroa().balioa(i+1, j).getEgoera();
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e8 = Tableroa.getTableroa().balioa(i, j+1).getEgoera();
		}
		
		Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
		
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita);
		//Ingurukoak ere begiratu ireki diren edo bandera / irekita izaten jarraitzen duten
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e1.equals(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Irekita);
			}
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e2.equals(Tableroa.getTableroa().balioa(i-1, j).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Irekita);
			}
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e3.equals(Tableroa.getTableroa().balioa(i, j-1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Irekita);
			}
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e4.equals(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Irekita);
			}
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e5.equals(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Irekita);
			}
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e6.equals(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Irekita);
			}
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e7.equals(Tableroa.getTableroa().balioa(i+1, j).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Irekita);
			}
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e8.equals(Tableroa.getTableroa().balioa(i, j+1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Irekita);
			}
		}
		
		
		//#################################
		//Gelaxka irekita dago, zenbakia da, inguruko bandera kopurua nahikoa da, ondo jarri dira
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 1, 1);
		aurkitua = false;
		i = 0; 
		j = 0;
				
		//Gelaxka ireki Zenbakidun bat lortu
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita) && (Tableroa.getTableroa().balioa(i, j) instanceof Zenbakia)){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
				
		//Inguruko beharrezko bandera kopurua jarri
		kop = ((Zenbakia) Tableroa.getTableroa().balioa(i, j)).getZenbakia();
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i-1, j-1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j-1);
				kop--;
			}
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i-1, j) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j);
				kop--;
			}
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i, j-1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i, j-1);
				kop--;
			}
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i+1, j+1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j+1);
				kop--;
			}
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i-1, j+1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j+1);
				kop--;
			}
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i+1, j-1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j-1);
				kop--;
			}
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i+1, j) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j);
				kop--;
			}
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i, j+1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i, j+1);
				kop--;
			}
		}
				
		//Hasierako egoera gorde
		e = Tableroa.getTableroa().balioa(i, j).getEgoera();
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e1 = Tableroa.getTableroa().balioa(i-1, j-1).getEgoera();
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e2 = Tableroa.getTableroa().balioa(i-1, j).getEgoera();
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e3 = Tableroa.getTableroa().balioa(i, j-1).getEgoera();
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e4 = Tableroa.getTableroa().balioa(i+1, j+1).getEgoera();
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e5 = Tableroa.getTableroa().balioa(i-1, j+1).getEgoera();
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e6 = Tableroa.getTableroa().balioa(i+1, j-1).getEgoera();
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e7 = Tableroa.getTableroa().balioa(i+1, j).getEgoera();
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e8 = Tableroa.getTableroa().balioa(i, j+1).getEgoera();
		}
				
		Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
				
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita);
		//Ingurukoak ere begiratu ireki diren edo bandera / irekita izaten jarraitzen duten
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e1.equals(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Irekita);
			}
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e2.equals(Tableroa.getTableroa().balioa(i-1, j).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Irekita);
			}
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e3.equals(Tableroa.getTableroa().balioa(i, j-1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Irekita);
			}
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e4.equals(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Irekita);
			}
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e5.equals(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Irekita);
			}
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e6.equals(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Irekita);
			}
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e7.equals(Tableroa.getTableroa().balioa(i+1, j).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Irekita);
			}
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e8.equals(Tableroa.getTableroa().balioa(i, j+1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Irekita);
			}
		}
		assertEquals(false, Tableroa.getTableroa().partidaGaldu());
		
		
		//#################################
		//Gelaxka irekita dago, zenbakia da, inguruko bandera kopurua nahikoa da, txarto jarri dira
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 1, 1);
		aurkitua = false;
		i = 0; 
		j = 0;
				
		//Gelaxka ireki Zenbakidun bat lortu
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita) && (Tableroa.getTableroa().balioa(i, j) instanceof Zenbakia)){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
		
		//INGURUKO GELAXKA ITXIAK ZENBATU
		int zenb = 0;
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
			
		//Inguruko beharrezko bandera kopurua jarri
		kop = ((Zenbakia) Tableroa.getTableroa().balioa(i, j)).getZenbakia();
		int haskop = kop;
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i-1, j-1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j-1);
				kop--;
			}
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i-1, j) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j);
				kop--;
			}
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i, j-1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i, j-1);
				kop--;
			}
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i+1, j+1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j+1);
				kop--;
			}
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i-1, j+1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j+1);
				kop--;
			}
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i+1, j-1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j-1);
				kop--;
			}
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i+1, j) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j);
				kop--;
			}
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i, j+1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i, j+1);
				kop--;
			}
		}
		
		if(kop > 0){ //Ez dira bandera nahiko jarri, BEHARREZKOAK JARRI
			if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i-1, j-1);
					kop--;
				}
			}
			if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i-1, j);
					kop--;
				}
			}
			if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i, j-1);
					kop--;
				}
			}
			if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i+1, j+1);
					kop--;
				}
			}
			if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i-1, j+1);
					kop--;
				}
			}
			if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i+1, j-1);
					kop--;
				}
			}
			if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i+1, j);
					kop--;
				}
			}
			if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i, j+1);
					kop--;
				}
			}
			
		}
				
		//Hasierako egoera gorde
		e = Tableroa.getTableroa().balioa(i, j).getEgoera();
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e1 = Tableroa.getTableroa().balioa(i-1, j-1).getEgoera();
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e2 = Tableroa.getTableroa().balioa(i-1, j).getEgoera();
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e3 = Tableroa.getTableroa().balioa(i, j-1).getEgoera();
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e4 = Tableroa.getTableroa().balioa(i+1, j+1).getEgoera();
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e5 = Tableroa.getTableroa().balioa(i-1, j+1).getEgoera();
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e6 = Tableroa.getTableroa().balioa(i+1, j-1).getEgoera();
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e7 = Tableroa.getTableroa().balioa(i+1, j).getEgoera();
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e8 = Tableroa.getTableroa().balioa(i, j+1).getEgoera();
		}
				
		Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
				
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita);
		//Ingurukoak ere begiratu ireki diren edo bandera / irekita izaten jarraitzen duten
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e1.equals(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Irekita);
			}
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e2.equals(Tableroa.getTableroa().balioa(i-1, j).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Irekita);
			}
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e3.equals(Tableroa.getTableroa().balioa(i, j-1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Irekita);
			}
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e4.equals(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Irekita);
			}
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e5.equals(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Irekita);
			}
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e6.equals(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Irekita);
			}
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e7.equals(Tableroa.getTableroa().balioa(i+1, j).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Irekita);
			}
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e8.equals(Tableroa.getTableroa().balioa(i, j+1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Irekita);
			}
		}
		
		if(haskop == zenb){ //Ezinezkoa da partida galtzea
			assertEquals(false, Tableroa.getTableroa().partidaGaldu());
		}
		else{
			assertEquals(true, Tableroa.getTableroa().partidaGaldu());
		}
		
		
		
		//#################################
		//Gelaxka irekita dago, zenbakia da, inguruko bandera kopurua nahikoa da, ondo jarri dira, galderak ere daude
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 1, 1);
		aurkitua = false;
		i = 0; 
		j = 0;
					
		//Gelaxka ireki Zenbakidun bat lortu
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita) && (Tableroa.getTableroa().balioa(i, j) instanceof Zenbakia)){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
						
		//Inguruko beharrezko bandera kopurua jarri
		kop = ((Zenbakia) Tableroa.getTableroa().balioa(i, j)).getZenbakia();
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i-1, j-1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j-1);
				kop--;
			}
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i-1, j) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j);
				kop--;
			}
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i, j-1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i, j-1);
				kop--;
			}
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i+1, j+1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j+1);
				kop--;
			}
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i-1, j+1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j+1);
				kop--;
			}
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i+1, j-1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j-1);
				kop--;
			}
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i+1, j) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j);
				kop--;
			}
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Itxita)&&(Tableroa.getTableroa().balioa(i, j+1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i, j+1);
				kop--;
			}
		}
						
		//Galdera ikurrak jarri
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j-1);
				Tableroa.getTableroa().eskuinekoClick(i-1, j-1);
			}
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j);
				Tableroa.getTableroa().eskuinekoClick(i-1, j);
			}
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i, j-1);
				Tableroa.getTableroa().eskuinekoClick(i, j-1);
			}
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j+1);
				Tableroa.getTableroa().eskuinekoClick(i+1, j+1);
			}
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j+1);
				Tableroa.getTableroa().eskuinekoClick(i-1, j+1);
			}
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j-1);
				Tableroa.getTableroa().eskuinekoClick(i+1, j-1);
			}
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j);
				Tableroa.getTableroa().eskuinekoClick(i+1, j);
			}
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i, j+1);
				Tableroa.getTableroa().eskuinekoClick(i, j+1);
			}
		}
		
		//Hasierako egoera gorde
		e = Tableroa.getTableroa().balioa(i, j).getEgoera();
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e1 = Tableroa.getTableroa().balioa(i-1, j-1).getEgoera();
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e2 = Tableroa.getTableroa().balioa(i-1, j).getEgoera();
			}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e3 = Tableroa.getTableroa().balioa(i, j-1).getEgoera();
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e4 = Tableroa.getTableroa().balioa(i+1, j+1).getEgoera();
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e5 = Tableroa.getTableroa().balioa(i-1, j+1).getEgoera();
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e6 = Tableroa.getTableroa().balioa(i+1, j-1).getEgoera();
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e7 = Tableroa.getTableroa().balioa(i+1, j).getEgoera();
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e8 = Tableroa.getTableroa().balioa(i, j+1).getEgoera();
		}
						
		Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
						
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita);
		//Ingurukoak ere begiratu ireki diren edo bandera / irekita izaten jarraitzen duten
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e1.equals(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Irekita);
			}
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e2.equals(Tableroa.getTableroa().balioa(i-1, j).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Irekita);
			}
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e3.equals(Tableroa.getTableroa().balioa(i, j-1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Irekita);
			}
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e4.equals(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Irekita);
			}
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e5.equals(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Irekita);
			}
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e6.equals(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Irekita);
			}
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e7.equals(Tableroa.getTableroa().balioa(i+1, j).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Irekita);
			}
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e8.equals(Tableroa.getTableroa().balioa(i, j+1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Irekita);
			}
		}
		assertEquals(false, Tableroa.getTableroa().partidaGaldu());
		
		
		//#################################
		//Gelaxka irekita dago, zenbakia da, inguruko bandera kopurua nahikoa da, txarto jarri dira, galderak daude
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 1, 1);
		aurkitua = false;
		i = 0; 
		j = 0;
				
		//Gelaxka ireki Zenbakidun bat lortu
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita) && (Tableroa.getTableroa().balioa(i, j) instanceof Zenbakia)){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
		
		//INGURUKO GELAXKA ITXIAK ZENBATU
		zenb = 0;
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Itxita)){
				zenb++;
			}
		}
			
		//Inguruko beharrezko bandera kopurua jarri
		kop = ((Zenbakia) Tableroa.getTableroa().balioa(i, j)).getZenbakia();
		haskop = kop;
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i-1, j-1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j-1);
				kop--;
			}
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i-1, j) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j);
				kop--;
			}
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i, j-1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i, j-1);
				kop--;
			}
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i+1, j+1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j+1);
				kop--;
			}
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i-1, j+1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j+1);
				kop--;
			}
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i+1, j-1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j-1);
				kop--;
			}
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i+1, j) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j);
				kop--;
			}
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
			if((Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Itxita)&&!(Tableroa.getTableroa().balioa(i, j+1) instanceof Mina)){
				Tableroa.getTableroa().eskuinekoClick(i, j+1);
				kop--;
			}
		}
		
		if(kop > 0){ //Ez dira bandera nahiko jarri, BEHARREZKOAK JARRI
			if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i-1, j-1);
					kop--;
				}
			}
			if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i-1, j);
					kop--;
				}
			}
			if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i, j-1);
					kop--;
				}
			}
			if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i+1, j+1);
					kop--;
				}
			}
			if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i-1, j+1);
					kop--;
				}
			}
			if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i+1, j-1);
					kop--;
				}
			}
			if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i+1, j);
					kop--;
				}
			}
			if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() && kop > 0){//Tablero barruan badago
				if(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Itxita){
					Tableroa.getTableroa().eskuinekoClick(i, j+1);
					kop--;
				}
			}
			
		}
		
		//Galdera ikurrak jarri
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j-1);
				Tableroa.getTableroa().eskuinekoClick(i-1, j-1);
			}
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j);
				Tableroa.getTableroa().eskuinekoClick(i-1, j);
			}
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i, j-1);
				Tableroa.getTableroa().eskuinekoClick(i, j-1);
			}
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j+1);
				Tableroa.getTableroa().eskuinekoClick(i+1, j+1);
			}
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i-1, j+1);
				Tableroa.getTableroa().eskuinekoClick(i-1, j+1);
			}
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j-1);
				Tableroa.getTableroa().eskuinekoClick(i+1, j-1);
			}
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() ){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i+1, j);
				Tableroa.getTableroa().eskuinekoClick(i+1, j);
			}
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(!(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Bandera)){
				Tableroa.getTableroa().eskuinekoClick(i, j+1);
				Tableroa.getTableroa().eskuinekoClick(i, j+1);
			}
		}
				
		//Hasierako egoera gorde
		e = Tableroa.getTableroa().balioa(i, j).getEgoera();
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e1 = Tableroa.getTableroa().balioa(i-1, j-1).getEgoera();
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e2 = Tableroa.getTableroa().balioa(i-1, j).getEgoera();
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e3 = Tableroa.getTableroa().balioa(i, j-1).getEgoera();
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e4 = Tableroa.getTableroa().balioa(i+1, j+1).getEgoera();
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e5 = Tableroa.getTableroa().balioa(i-1, j+1).getEgoera();
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e6 = Tableroa.getTableroa().balioa(i+1, j-1).getEgoera();
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e7 = Tableroa.getTableroa().balioa(i+1, j).getEgoera();
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			e8 = Tableroa.getTableroa().balioa(i, j+1).getEgoera();
		}
				
		Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
				
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita);
		//Ingurukoak ere begiratu ireki diren edo bandera / irekita izaten jarraitzen duten
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e1.equals(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Irekita);
			}
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e2.equals(Tableroa.getTableroa().balioa(i-1, j).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Irekita);
			}
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e3.equals(Tableroa.getTableroa().balioa(i, j-1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Irekita);
			}
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e4.equals(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Irekita);
			}
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e5.equals(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Irekita);
			}
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e6.equals(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Irekita);
			}
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e7.equals(Tableroa.getTableroa().balioa(i+1, j).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Irekita);
			}
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			if(e8.equals(Tableroa.getTableroa().balioa(i, j+1).getEgoera())){
				assertTrue((Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Bandera)||(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Irekita));
			}
			else{
				assertTrue(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Irekita);
			}
		}
		
		if(haskop == zenb){ //Ezinezkoa da partida galtzea
			assertEquals(false, Tableroa.getTableroa().partidaGaldu());
		}
		else{
			assertEquals(true, Tableroa.getTableroa().partidaGaldu());
		}
		
		
		//#################################
		//Gelaxkak bandera dauka
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 1, 1);
		aurkitua = false;
		i = 0; 
		j = 0;
				
		//Gelaxka Itxi bat lortu
		
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Itxita)){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
		Tableroa.getTableroa().eskuinekoClick(i, j);	
		Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Bandera);
		
		
		//#################################
		//Gelaxkak galdera ikurra dauka 
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 1, 1);
		aurkitua = false;
		i = 0; 
		j = 0;
				
		//Gelaxka Itxi bat lortu
		
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Itxita)){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
		Tableroa.getTableroa().eskuinekoClick(i, j);
		Tableroa.getTableroa().eskuinekoClick(i, j);
		Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita);
		
		
		//#################################
		//Gelaxkak galdera ikurra dauka, mina da
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 1, 1);
		aurkitua = false;
		i = 0; 
		j = 0;
						
		//Gelaxka Itxi Minadun bat lortu
				
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if(((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Itxita) && (Tableroa.getTableroa().balioa(i, j) instanceof Mina))){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
		Tableroa.getTableroa().eskuinekoClick(i, j);
		Tableroa.getTableroa().eskuinekoClick(i, j);
		Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita);
		assertEquals(true, Tableroa.getTableroa().partidaGaldu());
		
		
		//#################################
		//Gelaxkak galdera ikurra dauka, Zenbakia da
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 1, 1);
		aurkitua = false;
		i = 0; 
		j = 0;
								
		//Gelaxka Itxi Zenbakidun bat lortu
						
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if(((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Itxita) && (Tableroa.getTableroa().balioa(i, j) instanceof Zenbakia))){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
		Tableroa.getTableroa().eskuinekoClick(i, j);
		Tableroa.getTableroa().eskuinekoClick(i, j);
		Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita);
		assertEquals(false, Tableroa.getTableroa().partidaGaldu());
		
		
		//#################################
		//Gelaxkak galdera ikurra dauka, Hutsa da
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		aurkitua = false;
		i = 0; 
		j = 0;
										
		//Gelaxka Itxi Huts bat lortu
								
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if(((Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Itxita) && (Tableroa.getTableroa().balioa(i, j) instanceof Hutsa))){
					aurkitua=true;
				}
				else{
					j++;
				}
			}
			if(!aurkitua){
				i++;
			}
		}
		Tableroa.getTableroa().eskuinekoClick(i, j);
		Tableroa.getTableroa().eskuinekoClick(i, j);
		Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
		assertTrue(Tableroa.getTableroa().balioa(i, j).getEgoera() instanceof Irekita);
		assertEquals(false, Tableroa.getTableroa().partidaGaldu());
		
		//Ingurukoak ere begiratu ireki diren
		if(i-1 >= 0 && j-1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i-1, j-1).getEgoera() instanceof Irekita);
		}
		if(i-1 >= 0 && j >= 0 && i-1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i-1, j).getEgoera() instanceof Irekita);
		}
		if(i >= 0 && j-1 >= 0 && i < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i, j-1).getEgoera() instanceof Irekita);
		}
		if(i+1 >= 0 && j+1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i+1, j+1).getEgoera() instanceof Irekita);
		}
		if(i-1 >= 0 && j+1 >= 0 && i-1 < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i-1, j+1).getEgoera() instanceof Irekita);
		}
		if(i+1 >= 0 && j-1 >= 0 && i+1 < Tableroa.getTableroa().geti() && j-1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i+1, j-1).getEgoera() instanceof Irekita);
		}
		if(i+1 >= 0 && j >= 0 && i+1 < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i+1, j).getEgoera() instanceof Irekita);
		}
		if(i >= 0 && j+1 >= 0 && i < Tableroa.getTableroa().geti() && j+1 < Tableroa.getTableroa().getj()){//Tablero barruan badago
			assertTrue(Tableroa.getTableroa().balioa(i, j+1).getEgoera() instanceof Irekita);
		}
		
		
		//#################################
		//Partida irabazi da, ez da aldaketarik egongo
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		for(i=0; i<7 ; i++){
			for(j=0; j<10; j++){
				if(!(Tableroa.getTableroa().balioa(i, j) instanceof Mina)){
					Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
				}
						
			}
		}
		e = Tableroa.getTableroa().balioa(0, 0).getEgoera();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 0, 0);
		assertEquals(e, Tableroa.getTableroa().balioa(0, 0).getEgoera());
			
		
		//#################################
		//Partida galdu da, ez da aldaketarik egongo
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		for(i=0; i<7 ; i++){
			for(j=0; j<10; j++){
				Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
			}
		}
		e = Tableroa.getTableroa().balioa(0, 0).getEgoera();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 0, 0);
		assertEquals(e, Tableroa.getTableroa().balioa(0, 0).getEgoera());
		
	}

	
	@Test
	public void testGetMinak() {
		//Tableroa sortzean hiru aukera posible
		//#################################
		//10 mina
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		assertEquals(10, Tableroa.getTableroa().getMinak());
		
		//#################################
		//30 mina
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(15, 10, 1, 1);
		assertEquals(30, Tableroa.getTableroa().getMinak());
		
		//#################################
		//75 mina
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(25, 12, 1, 1);
		assertEquals(75, Tableroa.getTableroa().getMinak());
		
		//#################################
		//Partida hasita dagoela bandera bat jarri
		//Zailtasun errazarekin egingo da, baina hirurekin berdin funtzionatu beharko luke
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 0, 0);
		Tableroa.getTableroa().eskuinekoClick(6, 9); 
		//Oso zaila izango da punta batetik bestera guztiak irekitzea lehengo klik-ean
		if(Tableroa.getTableroa().balioa(6, 9).getEgoera() instanceof Irekita){
			//Ireki da, bandera ez da jarri, kontagailua berdin jarraitzen du
			assertEquals(10, Tableroa.getTableroa().getMinak());
		}
		else{
			//Itxita geratu da, bandera jarri da, kontagailua eguneratu da
			assertEquals(9, Tableroa.getTableroa().getMinak());
		}
		
		
		//#################################
		//Partida hasita dagoela gelaxka guztietan bandera bat jartzen saiatu
		//Zailtasun errazarekin egingo da, baina hirurekin berdin funtzionatu beharko luke
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 0, 0);
		//Bandera kopuru maximoa jarri
		for(int i=0; i<7 ; i++){
			for(int j=0; j<10; j++){
				Tableroa.getTableroa().eskuinekoClick(i, j);
			}
		}
		assertEquals(0, Tableroa.getTableroa().getMinak());
		
		//#################################
		//Partida hasita dagoela bandera bat jarri eta kendu
		//Zailtasun errazarekin egingo da, baina hirurekin berdin funtzionatu beharko luke
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().ezkerrekoClick(10, 7, 0, 0);
		Tableroa.getTableroa().eskuinekoClick(6, 9); 
		//Oso zaila izango da punta batetik bestera guztiak irekitzea lehengo klik-ean
		//Bestela berriz exekutatu proba
		Tableroa.getTableroa().eskuinekoClick(6, 9);
		assertEquals(10, Tableroa.getTableroa().getMinak());
	}

	
	@Test
	public void testBalioa() {
		//Probak zailtasun errazeko tableroarekin egingo dira, baina guztietan berdin kudeatzen da.
		//Inoiz ez zaio pasatuko Array horren barruan sartzen ez den koordenatu bat.
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		boolean aurkitua = false;
		int i = 0; 
		int j = 0;
		
		//#################################
		//Gelaxka Huts bat lortu
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if(Tableroa.getTableroa().balioa(i, j) instanceof Hutsa){
					aurkitua=true;
				}
				
				j++;
			}
			i++;
		}
		assertEquals(true, aurkitua);
		
		//#################################
		//Gelaxka Zenbakidun bat lortu
		aurkitua = false;
		i = 0; 
		j = 0;
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if(Tableroa.getTableroa().balioa(i, j) instanceof Zenbakia){
					aurkitua=true;
				}
				
				j++;
			}
			i++;
		}
		assertEquals(true, aurkitua);
		
		//#################################
		//Minadun gelaxka bat lortu 
		i = 0; 
		j = 0;
		while(!aurkitua && i < 7){
			j=0;
			while(!aurkitua && j<10){
				if(Tableroa.getTableroa().balioa(i, j) instanceof Mina){
					aurkitua=true;
				}
				
				j++;
			}
			i++;
		}
		assertEquals(true, aurkitua);
		
	}

	
	@Test
	public void testGeti() {
		//Hiru tamaina desberdinetako tableroekin egingo dira probak
		
		//#################################
		//Zailtasuna:1
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		assertEquals(7, Tableroa.getTableroa().geti());
		
		//#################################
		//Zailtasuna:2
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(15, 10, 1, 1);
		assertEquals(10, Tableroa.getTableroa().geti());
		
		//#################################
		//Zailtasuna:3
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(25, 12, 1, 1);
		assertEquals(12, Tableroa.getTableroa().geti());
		
	}

	
	@Test
	public void testGetj() {
		//Hiru tamaina desberdinetako tableroekin egingo dira probak
		
		//#################################
		//Zailtasuna:1
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		assertEquals(10, Tableroa.getTableroa().getj());
			
		//#################################
		//Zailtasuna:2
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(15, 10, 1, 1);
		assertEquals(15, Tableroa.getTableroa().getj());
		
		//#################################
		//Zailtasuna:3
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(25, 12, 1, 1);
		assertEquals(25, Tableroa.getTableroa().getj());
		
	}

	
	@Test
	public void testPartidaGaldu() {
		//#################################
		//Tableroa sortu berri da, Galdu atributua beti FALSE izango da
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		assertEquals(false, Tableroa.getTableroa().partidaGaldu());
				
		//Partida bat nahita galdu, Galdu atributua TRUE izatera aldatzeko
		//Gelaxka guztiak ikutu
		for(int i=0; i<7 ; i++){
			for(int j=0; j<10; j++){
				Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
			}
		}
		assertEquals(true, Tableroa.getTableroa().partidaGaldu());
		
		//#################################
		//Hasieratzean Galdu atributua FALSE izan behar da
		Tableroa.getTableroa().hasieratu();
		assertEquals(false, Tableroa.getTableroa().partidaGaldu());
		
		//#################################
		//Partida bat irabazi
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		for(int i=0; i<7 ; i++){
			for(int j=0; j<10; j++){
				if(!(Tableroa.getTableroa().balioa(i, j) instanceof Mina)){
					Tableroa.getTableroa().ezkerrekoClick(10, 7, i, j);
				}
				
			}
		}
		assertEquals(false, Tableroa.getTableroa().partidaGaldu());
		
	}

	
	@Test
	public void testSetIzena() {
		//#################################
		//Izen bat sartu
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().setIzena("Josu");
		assertEquals("Josu", Tableroa.getTableroa().getIzena());
		
		//#################################
		//Aurreko izen bat aldatu
		Tableroa.getTableroa().setIzena("Irakasle");
		assertEquals("Irakasle", Tableroa.getTableroa().getIzena());
		
	}
	
	
	@Test
	public void testGetIzena() {
		//#################################
		//Izenik ez da sartu
		Tableroa.getTableroa().hasieratu();
		Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		assertNull(Tableroa.getTableroa().getIzena());
				
		//#################################
		//Izen bat sartu da
		Tableroa.getTableroa().setIzena("Josu");
		assertEquals("Josu", Tableroa.getTableroa().getIzena()); 
			
		//#################################
		//Aurreko izena aldatu da
		Tableroa.getTableroa().setIzena("Irakasle");
		assertEquals("Irakasle", Tableroa.getTableroa().getIzena());
		
	}

}
