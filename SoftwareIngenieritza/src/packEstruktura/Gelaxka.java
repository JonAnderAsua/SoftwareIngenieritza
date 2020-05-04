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
	
	public int geti(){
		return this.i;
	}
	
	public int getj(){
		return this.j;
	}
	
	public Egoera getEgoera(){
		return this.egoera;
	}
	
	protected void egoeraAldatu(String click){
		switch(click){
		case "eskuina":
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
			if(this.egoera instanceof Itxita || this.egoera instanceof Galdera){
				this.egoera = new Irekita();
			}
			//Irekita badago edo bandera badauka ez da ezer egin behar
			break;
		}
	}
	
	public void eskuinekoClick(){
		//EGOERA ALDATU
		
	}
	
	public void ezkerrekoClick(){
		//EGOERA ALDATU
	}
	

}
