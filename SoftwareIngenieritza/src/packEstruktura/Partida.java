package packEstruktura;

import packInterfazeak.hasierakoMenua;

public class Partida {

	public Partida() {
		
	}
	
	public static void main(String[] args){
		JokalariKatalogo.getJokalariKatalogo().datuakKargatu();
		hasierakoMenua partida = new hasierakoMenua();
		partida.setVisible(true);
	}


}
