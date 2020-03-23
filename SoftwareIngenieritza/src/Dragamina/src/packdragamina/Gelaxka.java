package Dragamina.src.packdragamina;
import javax.swing.JLabel;

public class Gelaxka extends JLabel {
	private int i;
	private int j;
	private boolean irekita;
	private boolean bandera;
	private int zailtasuna;
	private Click eskuin;
	private Click ezker;

	public Gelaxka(int i, int j, int zailtasuna) {
		this.i = i;
		this.j = j;
		this.irekita = false;
		this.bandera = false;
		this.zailtasuna = zailtasuna;
		this.eskuin = new ClickEskuina();
		this.ezker = new ClickEzkerra();
	}
	
	public void bandera(boolean nola){
		this.bandera = nola;
	}
	
	public boolean banderaJarrita(){
		return this.bandera;
	}
	
	public void ireki(){
		this.irekita=true;
	}
	
	public boolean irekita(){
		return this.irekita;
	}
	
	public void clickEgin(String botoia, Gelaxka[][] tablero){
		if(!irekita){
			switch(botoia){
			case "eskuina":
				System.out.println("Eskuin botoia sakatuta");
				this.eskuin.clickEgin(i,j,tablero);
				break;
				
			case "ezkerra":
				if(!bandera){
					this.ezker.clickEgin(i,j,tablero);
					//this.irekita=true;
				}
				System.out.println("Ezker botoia sakatuta");
				break;
				
			case "lehengoa":
				switch(zailtasuna){
				case 1:
					Tableroa.getTableroa().tableroaSortu(10, 7, j, i);
					break;
				case 2:
					Tableroa.getTableroa().tableroaSortu(15, 10, j, i);
					break;
				case 3:
					Tableroa.getTableroa().tableroaSortu(25, 12, j, i);
					break;
				}
				Tableroa.getTableroa().printTablero();
				
				this.ezker.clickEgin(i,j,tablero);
				this.irekita=true;
				break;
			}
		}
		
	}

}
