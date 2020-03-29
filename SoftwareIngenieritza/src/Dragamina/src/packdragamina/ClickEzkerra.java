package packdragamina;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ClickEzkerra extends Click {

	public ClickEzkerra() {
		
	}
	
	public void clickEgin(int i, int j, Gelaxka[][] tablero){
		char balioa = Tableroa.getTableroa().balioa(i, j);
		
		
		switch(balioa){
		case 'M': //Mina dago
			Image img;
			try {
				tablero[i][j].ireki();
				Tableroa.getTableroa().minaIkutu();
				img = ImageIO.read(getClass().getResource("mina-r.gif"));
				tablero[i][j].setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
	
		case '0': //Hutsik
			this.errekutsibokiIreki(i, j, tablero);
			break;
			
		default: //Zenbaki bat
			tablero[i][j].ireki();
			Tableroa.getTableroa().irekiEguneratu();
			this.zenbakiaIpini(tablero[i][j], balioa);
			break;
		}
	}
	
	private void zenbakiaIpini(JLabel gelaxka, char zenb){
		Image img;
		switch(zenb){
		case '1':
			try {
				img = ImageIO.read(getClass().getResource("c1.gif"));
				gelaxka.setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
		case '2':
			try {
				img = ImageIO.read(getClass().getResource("c2.gif"));
				gelaxka.setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
			
		case '3':
			try {
				img = ImageIO.read(getClass().getResource("c3.gif"));
				gelaxka.setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
			
		case '4':
			try {
				img = ImageIO.read(getClass().getResource("c4.gif"));
				gelaxka.setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
			
		case '5':
			try {
				img = ImageIO.read(getClass().getResource("c5.gif"));
				gelaxka.setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
			
		case '6':
			try {
				img = ImageIO.read(getClass().getResource("c6.gif"));
				gelaxka.setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
			
		case '7':
			try {
				img = ImageIO.read(getClass().getResource("c7.gif"));
				gelaxka.setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
			
		case '8':
			try {
				img = ImageIO.read(getClass().getResource("c8.gif"));
				gelaxka.setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
		}
	}
	private void errekutsibokiIreki(int i, int j, Gelaxka[][] tablero){
		if(i >= 0 && j >= 0 && i < Tableroa.getTableroa().geti() && j < Tableroa.getTableroa().getj() && !tablero[i][j].irekita()){ //Tablero barruan badago
			char zer = Tableroa.getTableroa().balioa(i, j);
			
			if(zer == 'M' && !tablero[i][j].banderaJarrita()){ //Mina da
				tablero[i][j].ireki();
				Tableroa.getTableroa().irekiEguneratu();
				
				Image img;
				try {
					tablero[i][j].ireki();
					Tableroa.getTableroa().minaIkutu();
					img = ImageIO.read(getClass().getResource("mina-r.gif"));
					tablero[i][j].setIcon(new ImageIcon(img));
					
				} catch (IOException e) {
					System.out.println("Ezin da irudia kargatu");
					e.printStackTrace();
				}
			}
			else if(zer == '0'){
				tablero[i][j].ireki();
				Tableroa.getTableroa().irekiEguneratu();
				
				Image img;
				try {
					img = ImageIO.read(getClass().getResource("c0.gif"));
					tablero[i][j].setIcon(new ImageIcon(img));
					
				} catch (IOException e) {
					System.out.println("Ezin da irudia kargatu");
					e.printStackTrace();
				}
				
				this.errekutsibokiIreki(i-1, j-1,tablero);
				this.errekutsibokiIreki(i-1, j, tablero);
				this.errekutsibokiIreki(i-1, j+1, tablero);
				this.errekutsibokiIreki(i, j-1, tablero);
				this.errekutsibokiIreki(i, j+1, tablero);
				this.errekutsibokiIreki(i+1, j-1, tablero);
				this.errekutsibokiIreki(i+1, j, tablero);
				this.errekutsibokiIreki(i+1, j+1, tablero);
				
			}
			else{//Zenbakia da
				if(!tablero[i][j].banderaJarrita()){
					tablero[i][j].ireki();
					Tableroa.getTableroa().irekiEguneratu();
					this.zenbakiaIpini(tablero[i][j], zer);
				}
			}
		}
	}
	
	public void dobleClick(int i, int j, Gelaxka[][] tablero){	
		this.errekutsibokiIreki(i-1, j-1,tablero);
		this.errekutsibokiIreki(i-1, j, tablero);
		this.errekutsibokiIreki(i-1, j+1, tablero);
		this.errekutsibokiIreki(i, j-1, tablero);
		this.errekutsibokiIreki(i, j+1, tablero);
		this.errekutsibokiIreki(i+1, j-1, tablero);
		this.errekutsibokiIreki(i+1, j, tablero);
		this.errekutsibokiIreki(i+1, j+1, tablero);
				
			
	}
	

}
