package packEstruktura;

public class Mina extends Gelaxka {

	public Mina(int i, int j) {
		super(i,j);
	}
	
	//Eskuineko klika egitean egoera aldatzea 
	public void eskuinekoClick(){
		this.egoeraAldatu("eskuina");
		
	}
	
	
	//Ezkerreko klika egitean egoera aldatzea 
	public void ezkerrekoClick(){
		this.egoeraAldatu("ezkerra");
		
	}

}
