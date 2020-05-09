package packEstruktura;

public class GelaxkaFactory {
	
	private static GelaxkaFactory nGelaxkaFactory = null;
	

	private GelaxkaFactory() {}
	
	
	//Singleton Patroia
	public static synchronized GelaxkaFactory getGelaxkaFactory(){
		if(nGelaxkaFactory == null){
			nGelaxkaFactory = new GelaxkaFactory();
		}
		return nGelaxkaFactory;
	}
	
	//Mota zehatz bateko Gelaxkak sortu
	public Gelaxka sortuGelaxka(String pMota, int i, int j){
		
		switch(pMota){
		case "Hutsa":
			return new Hutsa(i,j);
		case "Zenbakia":
			return new Zenbakia(i,j);
		case "Mina":
			return new Mina(i,j);
		default:
			//Mota txarto sartzekotan Gelaxka huts bat sortuko da
			return new Hutsa(i,j);
		}
	}

}
