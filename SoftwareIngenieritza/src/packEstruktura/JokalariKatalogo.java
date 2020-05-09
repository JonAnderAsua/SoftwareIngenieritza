package packEstruktura;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class JokalariKatalogo {
	private ArrayList<Jokalaria> lista;
	private static JokalariKatalogo nJokalariKatalogo = null;

	
	private JokalariKatalogo() {
		this.lista = new ArrayList<Jokalaria>();
	}
	
	
	//Singleton Patroia
	public static synchronized JokalariKatalogo getJokalariKatalogo(){
		if(nJokalariKatalogo == null){
			nJokalariKatalogo = new JokalariKatalogo();
		}
		return nJokalariKatalogo;
	}
	
	
	//Datuak fitxategitik irakurri eta jokalarien lista kargatzea
	public void datuakKargatu(){
		this.listaHustu(); //Bestela behin eta berriz kargatzen dira datuak
		String izena = "./Puntuazioak.txt";
		
		try(Scanner sc = new Scanner(new File(izena))){
			sc.useDelimiter(";"); // Hau erabilita, Scanner erabili daiteke datuak zatitzeko
			while(sc.hasNextLine()){
				// Scanner-a erabili daiteke zuzenean datuak erauzteko.
				int puntuazioa = sc.nextInt();
				String nick = sc.nextLine(); // Izena hartzeko, lerroan geratzen dena hartu
				Jokalaria j = new Jokalaria(nick.substring(1, nick.length()),puntuazioa);
				this.lista.add(j);
			}	
		}
		catch (IOException e) {
			System.out.println("Ezin da itxi");
		}
		// Scanner-a erabili daiteke zuzenean datuak erauzteko.
	}
	
	
	//Puntuazioa kalkulatu: (denbora maxioma - egindako denbora) * partidaren zailtasuna
	public int puntuazioaKalkulatu(int denbora, int zailtasuna){
		return (999-denbora)*zailtasuna;

	}
	
	
	//Partida irabazi duen jokalari bat, bere puntuazioaren arabera, listan sar daitekeen erabaki.
	//Sartu ahal bada, dagokion tokian sartuko da (puntuazoak altuenetik baxuenera ordenatuta daude, gehienez 10 elementu egongo dira)
	public void jokalarariaSartu(String izena, int denbora, int zailtasuna){
		int i = lista.size()-1;
		boolean aurkitua = false;
		
		int puntuazioa= this.puntuazioaKalkulatu(denbora, zailtasuna);
		Jokalaria j = new Jokalaria(izena, puntuazioa);
		
		if(i == -1){ //Lista hutsik dago
			this.lista.add(j);
		}
		else{ //Gutxienez elementu bat dago listan
			while(i>=0 && !aurkitua){ //Lista atzetik aurrera errekorritzen da
				Jokalaria jok = this.lista.get(i);
				if(jok.getPuntuazioa()>j.getPuntuazioa()){ //Listakoak puntuazio altuagoa badauka tokia aurkitu da
					aurkitua = true;
				}
				else{
					i--;
				}
			}
			
			if(aurkitua){ //Posizioa aukitu dugu
				this.lista.add(i+1,j);
				if(lista.size()>10){ //Jada 10 elementu badaude
					this.lista.remove(lista.size()-1);
				}
			}
			else if(i==-1 && !aurkitua){ //Lehenengo posizioan sartu behar da
				this.lista.add(0,j);
				if(lista.size()>10){ //Jada 10 elementu badaude
					this.lista.remove(lista.size()-1);
				}
			}
		}
		
		//Fitxategiko datuak eguneratu
		this.datuakIdatzi();
	}
	
	
	//Jokalari lista lortu
	public Iterator<Jokalaria> jokalariakLortu(){
		return this.lista.iterator();
	}
	
	
	//Lista hustea (batez ere jUnit-etan erabilia)
	public void listaHustu(){
		this.lista.clear();
	}

	
	//Datuak fitxategian idaztea
	private void datuakIdatzi(){
		String izena = "Puntuazioak.txt";
		
		try(PrintWriter fitx = new PrintWriter(new File(izena))){
			Iterator<Jokalaria> itr = this.jokalariakLortu();
				
			while(itr.hasNext()){
				Jokalaria j = itr.next();
				fitx.printf("%d;%s\n", j.getPuntuazioa(), j.getIzena());
			}
			
		}
		catch (FileNotFoundException e) {
			System.out.println("Fitxategia ez da aurkitu"); 
		}
	}
}
