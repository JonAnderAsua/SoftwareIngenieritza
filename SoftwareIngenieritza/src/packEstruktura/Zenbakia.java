package packEstruktura;

public class Zenbakia extends Gelaxka {
	
	private int zenb;

	public Zenbakia(int i, int j) {
		super(i,j);
	}
	
	
	//Gelaxkari dagokion zenbakia jarri
	public void setZenbakia(int pZenb){
		this.zenb = pZenb;
	}
	
	
	//Gelaxkaren zenbakia lortu
	public int getZenbakia(){
		return this.zenb;
	}
	
	
	//Eskuineko klika egitean egoera aldatzea 
	public void eskuinekoClick(){
		this.egoeraAldatu("eskuina");
		
	}
	
	
	//Ezkerreko klika egitean egoera aldatzea 
	public void ezkerrekoClick(){
		if(!(this.egoera instanceof Irekita)){ //Errekurtsiboki irekitzean eraginkorrakoa izateko
			this.egoeraAldatu("ezkerra");
		}
	}

}
