package packEstruktura;

import java.util.Observable;
import java.util.Random;


public class Tableroa extends Observable {

	private static Tableroa nTableroa = null;
	private Gelaxka[][] gelaxkaTablero;
	private int minaTotalak = 0;
	private int minak = 0;
	private int i;
	private int j;
	private int irekiKop = -1;
	private boolean galdu = false;
	private boolean lehenengoa = true;
	private String izena;
	
	private Tableroa() {
		super();
	}
	
	//Singleton Patroia
	public static synchronized Tableroa getTableroa(){
		if(nTableroa == null){
			nTableroa = new Tableroa();
		}
		return nTableroa;
	}
	
	
	//Partida berri bat hasi bada zenbait balio hasieratu
	public void hasieratu(){
		this.irekiKop=-1;
        this.galdu = false;
        this.lehenengoa=true;
	}
	
	
	//7x10 //10x15 //12x25
	public void tableroaSortu(Integer zutabe, Integer errenkada, Integer x, Integer y) {
        char[][] tablero = new char[errenkada][zutabe];
        this.gelaxkaTablero = new Gelaxka[errenkada][zutabe];
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
        this.printTablero(tablero);

        //Gelaxken tableroa sortu
        this.gelaxkaTableroa(tablero);
        this.irekiKop=0;
        //Denbora martxan jarri
        Kronometroa.getKronometroa().zeroanJarri();
    }
	
	
	//Gelaxken tableroa sortu
	private void gelaxkaTableroa(char[][] ptablero){
		for(int i=0; i<ptablero.length; i++){
			for(int j=0; j<ptablero[0].length;j++){
				switch(ptablero[i][j]){
				case 'M':
					this.gelaxkaTablero[i][j]= GelaxkaFactory.getGelaxkaFactory().sortuGelaxka("Mina",i,j);
					break;
				case '0':
					this.gelaxkaTablero[i][j]= GelaxkaFactory.getGelaxkaFactory().sortuGelaxka("Hutsa",i,j);
					break;
				default:
					this.gelaxkaTablero[i][j]= GelaxkaFactory.getGelaxkaFactory().sortuGelaxka("Zenbakia",i,j);
					((Zenbakia)this.gelaxkaTablero[i][j]).setZenbakia(Integer.parseInt(String.valueOf(ptablero[i][j])));
					break;
				}
				
			}
			
		}
	}
	
	
	//Tableroan Minak jarri
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
	
	
	//Gelaxkako inguruko gehiketak egin
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
	
	
	//Aldaketa TableroaI Interfazean adierazi
	private void aldaketa(int i, int j){
		this.setChanged();
		//Tableroa eguneratu
		if(i >= 0){
			this.notifyObservers(gelaxkaTablero[i][j]);
		}
		else if (i == -1){ //Mina kontagailua eguneratu
			this.notifyObservers();
		}
		else if(i == -2){ //Partida irabazi da
			this.notifyObservers("Irabazi");
		}
		else if(i == -3){ //Partida galdu da
			this.notifyObservers("Galdu");
		}
		else if(i == -4){ //Popup atera (irabaztean)
			this.notifyObservers("PopupI");
		}
		else if(i == -5){ //Popup atera (galtzean)
			this.notifyObservers("PopupG");
		}
		this.clearChanged();
	}
	
	
	//Eskuineko click-a
	public void eskuinekoClick(int i, int j){
		if(!this.galdu && !this.partidaIrabazi()){ //Mina ikutu ez badugu eta irabazi ez badugu
			if(!lehenengoa){//Tableroa jada sortu da
				
				Gelaxka g = this.gelaxkaTablero[i][j];
				if(g.egoera instanceof Itxita && this.minak>0){ //Aurkitu gabeko minak badaude
					this.gelaxkaTablero[i][j].eskuinekoClick();
					this.minaKendu();
					this.aldaketa(-1, -1);
				}
				else if(g.egoera instanceof Bandera && this.minak < this.minaTotalak){ //Kendu gabeko banderak badaude
					this.gelaxkaTablero[i][j].eskuinekoClick();
					this.minaGehitu();
					this.aldaketa(-1, -1);
				}
				else if(g.egoera instanceof Galdera){
					this.gelaxkaTablero[i][j].eskuinekoClick();
				}
				//Irekita badago ez da ezer egin behar
				
				this.aldaketa(i, j);
			}
		}
		
	}
	
	
	//Ezkerreko click-a
	public void ezkerrekoClick(int zutabe, int errenkada, int i, int j){
		if(!this.galdu && !this.partidaIrabazi()){//Mina ikutu ez badugu eta irabazi ez badugu
			if(lehenengoa){ //Lehenengo click-a bada, tableroa sortu
				this.lehenengoa=false;
				this.tableroaSortu(zutabe, errenkada, j, i);
			}
			
			Gelaxka g = this.gelaxkaTablero[i][j];
			
			if(g.egoera instanceof Itxita || g.egoera instanceof Galdera){
				this.gelaxkaTablero[i][j].ezkerrekoClick();
				if(g instanceof Mina){
					this.aldaketa(i, j);
					this.minaIkutu();
				}
				else if(g instanceof Hutsa){
					this.errekurtsibokiIreki(i, j);
					//Errekurtsiboki ireki
				}
				else{
					this.irekiEguneratu();
				}
			}
			//Irekita badago edo bandera badauka ez da ezer egin behar
			
			else if(g instanceof Zenbakia && g.egoera instanceof Irekita){ //Zenbakia bada eta irekita badago
				this.dobleclick(i, j);
				//Click bikoitza egin
			}
			this.aldaketa(i, j);
			
			//Partida irabazi den begiratu, eta hala bada beharrezkoa egin
			if(this.partidaIrabazi()){
				this.aldaketa(-2, -2);
				int denbora = Kronometroa.getKronometroa().pasaDirenSegunduakLortu();
				JokalariKatalogo.getJokalariKatalogo().jokalarariaSartu(izena, denbora, this.zailtasunaKalkulatu());
				this.aldaketa(-4, -4);
				
			}
			
			//Partida galdu den begiratu, eta hala bada beharrezkoa egin
			if(this.galdu){
				this.aldaketa(-3,-3);
				this.tableroGaldu();
				this.aldaketa(-5, -5);
				
			}
		}
	}
	
	
	//Gelaxka hutsa denean, zenbakidun gelaxka bat topatu arte inguruko guztiak ireki
	private void errekurtsibokiIreki(int i, int j){
		if(i >= 0 && j >= 0 && i < this.i && j < this.j){//Tablero barruan badago
			Gelaxka g = Tableroa.getTableroa().balioa(i, j);
			if((g.egoera instanceof Itxita) || (g.egoera instanceof Galdera)){
				g.egoeraAldatu("ezkerra");
				this.irekiEguneratu();
				Tableroa.getTableroa().aldaketa(i, j);
				
				if(g instanceof Hutsa){
					//Ingurukoak aztertu
					this.errekurtsibokiIreki(i-1, j-1);
					this.errekurtsibokiIreki(i-1, j);
					this.errekurtsibokiIreki(i-1, j+1);
					this.errekurtsibokiIreki(i, j-1);
					this.errekurtsibokiIreki(i, j+1);
					this.errekurtsibokiIreki(i+1, j-1);
					this.errekurtsibokiIreki(i+1, j);
					this.errekurtsibokiIreki(i+1, j+1);
				}
			}
		}
	}
	
	
	//Klik bikoitza egin bada, ingurukoak errekurtsiboki ireki
	private void dobleclick(int i, int j){
		if(this.banderaKopurua(i, j)){
			this.ireki(i-1, j-1);
			this.ireki(i-1, j);
			this.ireki(i-1, j+1);
			this.ireki(i, j-1);
			this.ireki(i, j+1);
			this.ireki(i+1, j-1);
			this.ireki(i+1, j);
			this.ireki(i+1, j+1);
		}
	}
	
	
	//Klik bikoitza egin bada, ingurukoak errekurtsiboki ireki
	private void ireki(int i, int j){
		if(i >= 0 && j >= 0 && i < this.i && j < this.j){//Tablero barruan badago
			Gelaxka g = this.gelaxkaTablero[i][j];
			if(g instanceof Mina){
				this.ezkerrekoClick(this.i, this.j, i, j);
			}
			if(g.egoera instanceof Itxita || g.egoera instanceof Galdera){
				g.egoeraAldatu("ezkerra");
				this.irekiEguneratu();
				this.aldaketa(i, j);
				
				if(g instanceof Hutsa){
					//Ingurukoak aztertu
					this.ireki(i-1, j-1);
					this.ireki(i-1, j);
					this.ireki(i-1, j+1);
					this.ireki(i, j-1);
					this.ireki(i, j+1);
					this.ireki(i+1, j-1);
					this.ireki(i+1, j);
					this.ireki(i+1, j+1);
				}
			}
		}
	}

	
	//Gelaxkaren inguruan jarrita dauden bandera kopurua inguruko mina kopuruaren berdina den begiratu
	private boolean banderaKopurua(int i, int j){
		int kont = this.gehiketa(i, j);
		Gelaxka g = Tableroa.getTableroa().balioa(i, j);
		if(g instanceof Zenbakia){
			return kont == ((Zenbakia)g).getZenbakia();
		}
		
		return false;
	}
	
	
	//Gelaxkaren inguruan jarrita dauden bandera kopurua kalkulatu
	private int gehiketa(int i, int j){
		int zenb = 0;
		
		//Inguruneko bandera kopurua kalkulatu
		Gelaxka GEzk = konprobatu(i-1,j-1);
        if(GEzk!= null&&GEzk.egoera instanceof Bandera) {
            zenb++;
        }
        Gelaxka GErr = konprobatu(i,j-1);
        if(GErr!=null&&GErr.egoera instanceof Bandera) {
            zenb++;
        }
        Gelaxka GEsk = konprobatu(i+1,j-1);
        if(GEsk!=null&&GEsk.egoera instanceof Bandera) {
            zenb++;
        }
        Gelaxka Ezk = konprobatu(i-1,j);
        if(Ezk!=null&&Ezk.egoera instanceof Bandera) {
            zenb++;
        }
        Gelaxka Esk = konprobatu(i+1,j);
        if(Esk!=null&&Esk.egoera instanceof Bandera) {
            zenb++;
        }
        Gelaxka BEzk = konprobatu(i-1,j+1);
        if(BEzk!=null&&BEzk.egoera instanceof Bandera) {
            zenb++;
        }
        Gelaxka BErr = konprobatu(i,j+1);
        if(BErr!=null&&BErr.egoera instanceof Bandera) {
            zenb++;
        }
        Gelaxka BEsk = konprobatu(i+1,j+1);
        if(BEsk!=null&&BEsk.egoera instanceof Bandera) {
            zenb++;
        }
		
		return zenb;
	}
	
	
	//Gelaxka tablero barruan dagoela konprobatzen du
	private Gelaxka konprobatu(int i, int j) {
	     if(i>=0&&j>=0&&i<=Tableroa.getTableroa().geti()-1&&j<=Tableroa.getTableroa().getj()-1) {
	          return Tableroa.getTableroa().balioa(i, j);
	     }
	     return null;
	 }
	
	
	//Partida galdu bada, tableroa errekorritu minak erakusteko
	private void tableroGaldu(){
		for(int i=0; i < gelaxkaTablero.length; i++){
			for(int j=0; j < gelaxkaTablero[0].length; j++){
				if((gelaxkaTablero[i][j].egoera instanceof Itxita) && (gelaxkaTablero[i][j] instanceof Mina)){
					//Mina erakutsi
					this.aldaketa(i, j);
				}
				else if((gelaxkaTablero[i][j].egoera instanceof Bandera) && !(gelaxkaTablero[i][j] instanceof Mina)){
					//Mina x-rekin
					this.aldaketa(i, j);
				}
				if((gelaxkaTablero[i][j].egoera instanceof Galdera) && (gelaxkaTablero[i][j] instanceof Mina)){
					//Mina erakutsi
					this.aldaketa(i, j);
				}
			}
		}
	}
	
	
	//Tableroa pantailan printeatu, gida izateko
	private void printTablero(char[][] tablero) {
        String lerroa;
        String banatu =" ";
        for(int z =0;z< tablero[0].length;z++) {
            banatu=banatu+"----";
        }
        
        for(int i=0;i<tablero.length;i++) {
            lerroa="";
            for(int j=0;j<tablero[i].length;j++) {
                lerroa=lerroa+ " | "+tablero[i][j];
            }
            System.out.println(lerroa+" |");
            System.out.println(banatu+"-");
        }
    }
	
	
	//Aurkitutako minen kontagailua itzuli
	public int getMinak(){
		return this.minak;
	}
	
	
	//Mina aurkituen kontagailuan mina bat gehitu
	private void minaGehitu(){
		if(this.minak < this.minaTotalak){
			this.minak++;
		}
	}
	
	
	//Mina aurkituen kontagailuan mina bat kendu
	private void minaKendu(){
		if(this.minak > 0){
			this.minak--;
		}
	}
	
	
	//Zehaztutako posizioko gelaxka itzultzen du
	public Gelaxka balioa(int i, int j){
		return this.gelaxkaTablero[i][j];
	}
	
	
	//Tableroko i eremuaren balioa itzultzen du
	public int geti(){
		return this.i;
	}
	
	
	//Tableroko j eremuaren balioa itzultzen du
	public int getj(){
		return this.j;
	}
	
	
	//Gelaxka irekien kontagailuan gehi bat egin
	private void irekiEguneratu(){
		this.irekiKop++;
	}
	
	
	//Mina bat ikutu denez, partida galdu dela adierazi
	private void minaIkutu(){
		this.galdu = true;
	}
	
	
	//Partida galdu den itzultzen du
	public boolean partidaGaldu(){
		return this.galdu;
	}
	
	
	//Patida irabazi den kalkulatzen du
	public boolean partidaIrabazi(){
		//Itxita gelditzen direnak mina kopuruaren berdina
		return (this.minaTotalak == ((this.i*this.j)-this.irekiKop));
	}
	
	
	//Jokatzen ari den partidaren zailtasuna kalkulatzen du
	private int zailtasunaKalkulatu(){
		int z = 0;
		
		switch(j){
		case 10:
			z=1;
			break;
		case 15:
			z=2;
			break;
		case 25:
			z=3;
			break;
		}
		
		return z;
	}
	
	
	//Partida jokatzen ari duen jokalariaren izena gordetzen da
	public void setIzena(String izena){
		this.izena=izena;
	}
	
	
	//#####################################################################################
	//setIzena metodoaren probak egiteko beharrezkoa soilik
	//Partida jokatzen ari duen jokalariaren izena lortu
	public String getIzena(){
		return this.izena;
	}
	

}
