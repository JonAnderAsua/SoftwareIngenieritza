package packdragamina;

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
					this.irekita=true;
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
		else if(irekita && Tableroa.getTableroa().balioa(i, j)!='0' && this.banderaKopurua(tablero, i, j)){
//			System.out.println("CUCU");
			((ClickEzkerra)this.ezker).dobleClick(i, j, tablero);
		}
		
	}
	
	private boolean banderaKopurua(Gelaxka[][] tablero, int i, int j){
		int kont = this.gehiketa(i, j, tablero);
		
		return kont == Integer.parseInt(String.valueOf(Tableroa.getTableroa().balioa(i, j)));
	}
	
	private int gehiketa(int i, int j, Gelaxka[][] tablero){
		int zenb = 0;
		
		Gelaxka GEzk = konprobatu(i-1,j-1, tablero);
        if(GEzk!= null&&GEzk.bandera) {
            zenb++;
        }
        Gelaxka GErr = konprobatu(i,j-1, tablero);
        if(GErr!=null&&GErr.bandera) {
            zenb++;
        }
        Gelaxka GEsk = konprobatu(i+1,j-1, tablero);
        if(GEsk!=null&&GEsk.bandera) {
            zenb++;
        }
        Gelaxka Ezk = konprobatu(i-1,j, tablero);
        if(Ezk!=null&&Ezk.bandera) {
            zenb++;
        }
        Gelaxka Esk = konprobatu(i+1,j, tablero);
        if(Esk!=null&&Esk.bandera) {
            zenb++;
        }
        Gelaxka BEzk = konprobatu(i-1,j+1, tablero);
        if(BEzk!=null&&BEzk.bandera) {
            zenb++;
        }
        Gelaxka BErr = konprobatu(i,j+1, tablero);
        if(BErr!=null&&BErr.bandera) {
            zenb++;
        }
        Gelaxka BEsk = konprobatu(i+1,j+1, tablero);
        if(BEsk!=null&&BEsk.bandera) {
            zenb++;
        }
		
		return zenb;
	}
	
	//Konprobatu tablerotik ez dela irtetzen
	private Gelaxka konprobatu(int i, int j, Gelaxka[][] tablero) {
	     if(i>=0&&j>=0&&i<=tablero.length-1&&j<=tablero[1].length-1) {
	          return tablero[i][j];
	     }
	     return null;
	 }

}
