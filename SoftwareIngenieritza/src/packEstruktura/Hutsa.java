package packEstruktura;

public class Hutsa extends Gelaxka {

	public Hutsa(int i, int j) {
		super(i,j); 
	}
	
	//Eskuineko klika egitean egoera aldatzea 
	public void eskuinekoClick(){
		this.egoeraAldatu("eskuina");
		
	}
	
	//Ezkerreko klika egitean egoera aldatzea 
	public void ezkerrekoClick(){
		//Gelaxka hutsen kasuan, errekurtsiboki irekitzeko, egoera aldatu metodoari egiten zaio dei irekitzeko (Itxita edo Galdera egoeretan).
		if(!((this.egoera instanceof Itxita) || (this.egoera instanceof Galdera))){
			this.egoeraAldatu("ezkerra");
		}	
	}

}
