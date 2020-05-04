package packEstruktura;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
	
	
	public static synchronized JokalariKatalogo getJokalariKatalogo(){
		if(nJokalariKatalogo == null){
			nJokalariKatalogo = new JokalariKatalogo();
		}
		return nJokalariKatalogo;
	}
	
	
	
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
	
	
	//Puntuazioa kalkulatu
	public int puntuazioaKalkulatu(int denbora, int zailtasuna){
		return (999-denbora)*zailtasuna;

	}
	
	
	public void jokalarariaSartu(String izena, int denbora, int zailtasuna){
		int i = lista.size()-1;
		boolean aurkitua = false;
		
		int puntuazioa= this.puntuazioaKalkulatu(denbora, zailtasuna);
		Jokalaria j = new Jokalaria(izena, puntuazioa);
		
		if(i == -1){ //Lista hutsik dago
			this.lista.add(j);
		}
		else{
			while(i>=0 && !aurkitua){ //Gutzienez elementu bat dago listan
				Jokalaria jok = this.lista.get(i);
				if(jok.getPuntuazioa()>j.getPuntuazioa()){
					aurkitua = true;
				}
				else{
					i--;
				}
			}
			
			if(aurkitua){ //Posizioa aukitu dugu
				this.lista.add(i+1,j);
				if(lista.size()>10){
					this.lista.remove(lista.size()-1);
				}
			}
			else if(i==-1 && !aurkitua){ //Lehenengo posizioa
				this.lista.add(0,j);
				if(lista.size()>10){
					this.lista.remove(lista.size()-1);
				}
			}
		}
		
		this.datuakIdatzi();
	}
	
	
	public Iterator<Jokalaria> jokalariakLortu(){
		return this.lista.iterator();
	}
	
	private void listaHustu(){
		this.lista.clear();
	}

	
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
