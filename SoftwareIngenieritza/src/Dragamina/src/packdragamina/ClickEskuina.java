package packdragamina;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ClickEskuina extends Click{
	private int kont = 0; 

	public ClickEskuina() {
		
	}

	public void clickEgin(int i, int j, Gelaxka[][] tablero){
		Image img;
		switch (kont){
		case 0: //Bandera
			
			try {
				if(Tableroa.getTableroa().getMinak() <= 0){
					img = ImageIO.read(getClass().getResource("marca.gif"));
					tablero[i][j].setIcon(new ImageIcon(img));
					kont=2;
					tablero[i][j].bandera(false);
				}
				else{
					img = ImageIO.read(getClass().getResource("bandera.gif"));
					tablero[i][j].setIcon(new ImageIcon(img));
					Tableroa.getTableroa().minaKendu();
					kont++;
					tablero[i][j].bandera(true);
				}
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			
			break;
		case 1: //Galdera
			try {
				img = ImageIO.read(getClass().getResource("marca.gif"));
				tablero[i][j].setIcon(new ImageIcon(img));
				Tableroa.getTableroa().minaGehitu();
				tablero[i][j].bandera(false);
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			kont++;
			break;
		case 2: //Hutsa
			kont = 0;
			try {
				img = ImageIO.read(getClass().getResource("tablero.gif"));
				tablero[i][j].setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			break;
		}
			
		
	}
	

}
