package packEstruktura;

public class Hutsa extends Gelaxka {

	public Hutsa(int i, int j) {
		super(i,j); 
	}
	
	public void eskuinekoClick(){
		this.egoeraAldatu("eskuina");
		
	}
	
	public void ezkerrekoClick(){
		if(!(this.egoera instanceof Itxita)){
			this.egoeraAldatu("ezkerra");
		}	
	}

}
