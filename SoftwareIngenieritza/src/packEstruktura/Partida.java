package packEstruktura;

import packInterfazeak.hasierakoMenua;

public class Partida {

	public Partida() {
		
	}
	
	public static void main(String[] args){
		//Fitxategiko datuak kargatu
		JokalariKatalogo.getJokalariKatalogo().datuakKargatu();
		
		//Jokoaren hasierako menua
		hasierakoMenua partida = new hasierakoMenua();
		partida.setVisible(true);
	}


}
