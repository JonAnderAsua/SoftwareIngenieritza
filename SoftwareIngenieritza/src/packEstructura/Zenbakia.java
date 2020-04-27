package packEstructura;

public class Zenbakia extends Gelaxka {
	
	private int zenb;

	public Zenbakia(int i, int j) {
		super(i,j);
	}
	
	public void setZenbakia(int pZenb){
		this.zenb = pZenb;
	}
	
	public int getZenbakia(){
		return this.zenb;
	}
	
	public void eskuinekoClick(){
		this.egoeraAldatu("eskuina");
		
	}
	
	public void ezkerrekoClick(){
		if(!(this.egoera instanceof Irekita)){
			this.egoeraAldatu("ezkerra");
		}
	}

}
