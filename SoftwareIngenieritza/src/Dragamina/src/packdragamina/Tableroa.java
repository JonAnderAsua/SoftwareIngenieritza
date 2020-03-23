package Dragamina.src.packdragamina;

import java.util.Random;

public class Tableroa {

	private static Tableroa nTableroa = null;
	private char[][] tablero;
	private int minaTotalak = 0;
	private int minak = 0;
	private int i;
	private int j;
	private int irekiKop = -1;
	private boolean galdu = false;
	
	private Tableroa() { }
	
	public static synchronized Tableroa getTableroa(){
		if(nTableroa == null){
			nTableroa = new Tableroa();
		}
		return nTableroa;
	}
	
	public void hasieratu(){
		this.irekiKop=-1;
        this.galdu = false;
	}
	
	//7x10 //10x15 //12x25
	public void tableroaSortu(Integer zutabe, Integer errenkada, Integer x, Integer y) {
        char[][] tablero = new char[errenkada][zutabe];
        this.irekiKop=-1;
        this.galdu = false;
        this.i = errenkada;
        this.j = zutabe;
         
        //Tableroa bete
        for(int i=0;i<errenkada;i++) {
            for(int j=0;j<zutabe;j++) {
                tablero[i][j]=' ';
            }
        }
        
        //Minak jarri
        tablero = this.minakJarri(tablero, y, x); //Aurrerago sartu aukeratutako lehenengoa
        //Zenbakiak jarri
        tablero = this.zenbakiakJarri(tablero);
        this.irekiKop=0;
        
        this.tablero = tablero;
        
    }
	
	//Minak jarri
	private char[][] minakJarri(char[][] tablero, Integer iKlik, Integer jKlik){
		//Mina kopurua kalkulatu
		int mina = 0;
		switch (tablero[0].length){
			//7x10 
			case 10:
				mina= 10;
				this.minaTotalak = 10;
				this.minak = 10;
				break;
			//10x15 
			case 15:
				this.minaTotalak = 30;
				mina = 30;
				this.minak=30;
				break;
			//12x25
			case 25:
				this.minaTotalak = 75;
				mina = 75;
				this.minak=75;
				break;
			//10x15
			default: 
				this.minaTotalak = 30;
				mina = 30;
				this.minak=30;
				break;
		}
		
		//Leku libreak bilatu
		Random rand = new Random();
		while(mina > 0){ //Guztiak jarri ez diren bitartean
			//Auzazko lauki bat aukeratu
			int i = rand.nextInt(tablero.length);
            int j = rand.nextInt(tablero[i].length);
            
            //Ingurukoak edo emandakoa minarik ez izatea konprobatu
            boolean T = i==(iKlik) && j==(jKlik);
            boolean GEzk = i==(iKlik-1) && j==(jKlik-1);
            boolean GEr = i==(iKlik-1) && j==(jKlik);
            boolean GEsk = i==(iKlik-1) && j==(jKlik+1);
            boolean Esk = i==(iKlik) && j==(jKlik-1);
            boolean Ezk = i==(iKlik) && j==(jKlik+1);
            boolean BEsk = i==(iKlik+1) && j==(jKlik+1);
            boolean BEzk = i==(iKlik+1) && j==(jKlik-1);
            boolean BEr = i==(iKlik+1) && j==(jKlik);
            
            boolean guztiak = T || GEzk || GEr || GEsk || Esk || Ezk || BEzk || BEr || BEsk;
            
            //Minarik ez badago
            if(tablero[i][j]!='M' && !guztiak){
            	tablero[i][j]='M';
                mina--;
            }
			
		}//End While
		
		return tablero;
	}
	
	//Laukien zenbakiak jarri
	private char[][] zenbakiakJarri(char[][] tablero){
		for(int i=0;i<tablero.length;i++) {
            for(int j=0;j<tablero[i].length;j++) {
                if(tablero[i][j]!='M') { //Mina ez bada, ingurunekoa kalkulatu
                    tablero[i][j]=gehiketa(i, j, tablero);
                }
            }
        }
		return tablero;
	}
	
	//Minen inguruko gehiketak egin
	private char gehiketa(int i, int j, char[][] tablero){
		char zenb = '0';
		
		char GEzk = konprobatu(i-1,j-1, tablero);
        if(GEzk!='e'&&GEzk=='M') {
            zenb++;
        }
        char GErr = konprobatu(i,j-1, tablero);
        if(GErr!='e'&&GErr=='M') {
            zenb++;
        }
        char GEsk = konprobatu(i+1,j-1, tablero);
        if(GEsk!='e'&&GEsk=='M') {
            zenb++;
        }
        char Ezk = konprobatu(i-1,j, tablero);
        if(Ezk!='e'&&Ezk=='M') {
            zenb++;
        }
        char Esk = konprobatu(i+1,j, tablero);
        if(Esk!='e'&&Esk=='M') {
            zenb++;
        }
        char BEzk = konprobatu(i-1,j+1, tablero);
        if(BEzk!='e'&&BEzk=='M') {
            zenb++;
        }
        char BErr = konprobatu(i,j+1, tablero);
        if(BErr!='e'&&BErr=='M') {
            zenb++;
        }
        char BEsk = konprobatu(i+1,j+1, tablero);
        if(BEsk!='e'&&BEsk=='M') {
            zenb++;
        }
		
		return zenb;
	}
	
	
	//Konprobatu tablerotik ez dela irtetzen
	private char konprobatu(int i, int j, char[][] tablero) {
        if(i>=0&&j>=0&&i<=tablero.length-1&&j<=tablero[1].length-1) {
            return tablero[i][j];
        }
        return 'e';
    }
	
	public void printTablero() {
        String lerroa;
        String banatu =" ";
        for(int z =0;z< this.tablero[0].length;z++) {
            banatu=banatu+"----";
        }
        
        for(int i=0;i<this.tablero.length;i++) {
            lerroa="";
            for(int j=0;j<this.tablero[i].length;j++) {
                lerroa=lerroa+ " | "+this.tablero[i][j];
            }
            System.out.println(lerroa+" |");
            System.out.println(banatu+"-");
        }
    }
	
	public int getMinak(){
		return this.minak;
	}
	
	public void minaGehitu(){
		this.minak++;
	}
	
	public void minaKendu(){
		this.minak--;
	}
	
	public char balioa(int i, int j){
		return this.tablero[i][j];
	}
	
	public int geti(){
		return this.i;
	}
	
	public int getj(){
		return this.j;
	}
	
	public void irekiEguneratu(){
		this.irekiKop++;
	}
	
	public void minaIkutu(){
		this.galdu = true;
	}
	
	public boolean partidaGaldu(){
		return this.galdu;
	}
	
	public boolean partidaIrabazi(){
		//Itxita gelditzen direnak mina kopuruaren berdina
		return (this.minaTotalak == ((this.i*this.j)-this.irekiKop));
	}
	
	public static void main(String[] args) {
		 Tableroa.getTableroa().tableroaSortu(10, 7, 1, 1);
		 Tableroa.getTableroa().printTablero();
	}

}
