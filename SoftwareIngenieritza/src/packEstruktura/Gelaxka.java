package packEstruktura;

public abstract class Gelaxka {
	protected Egoera egoera;
	protected int i;
	protected int j;

	public Gelaxka(int i, int j) {
		this.egoera = new Itxita(); 
		this.i = i;
		this.j = j;
	}
	
	
	//Gelaxkaren i posizioa lortu
	public int geti(){
		return this.i;
	}
	
	
	//Gelaxkaren j posizioa lortu
	public int getj(){
		return this.j;
	}
	
	
	//Gelaxkaren uneko egoera lortu
	public Egoera getEgoera(){
		return this.egoera;
	}
	
	
	//Gelaxka guztien egoera aldatzea modu zentralizatuan programatzea erabaki da
	//Horrela kodea ez errepikatzeaz gain, gelaxka bakotzean soilik bakoitzaren berezitasunak programatu beharko dira
	public void egoeraAldatu(String click){
		switch(click){
		case "eskuina":
			//Eskuineko click-a egin da
			if(this.egoera instanceof Itxita && Tableroa.getTableroa().getMinak()>=0){
				this.egoera = new Bandera();
			}
			else if(this.egoera instanceof Bandera){
				this.egoera = new Galdera();
			}
			else if(this.egoera instanceof Galdera){
				this.egoera = new Itxita();
			}
			//Irekita badago ez da ezer egin behar
			
			break;
		case "ezkerra":
			//Ezkerreko click-a egin da
			if((this.egoera instanceof Itxita) || (this.egoera instanceof Galdera)){
				this.egoera = new Irekita();
			}
			//Irekita badago edo bandera badauka ez da ezer egin behar
			break;
		}
	}
	
	public void eskuinekoClick(){
		//EGOERA ALDATU, Gelaxka bakoitzean programatu
		
	}
	
	public void ezkerrekoClick(){
		//EGOERA ALDATU, Gelaxka bakoitzean programatu
	}
	

}
