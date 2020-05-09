package packEstruktura;

public class Kronometroa{
	
	private long zeroMomentua;
	private static Kronometroa nireKronometroa = null;
	
	
	private Kronometroa() {}
	
	 
	//Singleton patroia
	public static synchronized Kronometroa getKronometroa(){
		if(nireKronometroa == null){
			nireKronometroa = new Kronometroa();
		}
		return nireKronometroa;
	}
	
	
	//Kronometroa hasieratzen du (egungo denbora gorde)
	public void zeroanJarri(){
	 zeroMomentua = System.currentTimeMillis();
	}
	
	
	//Hasierako denbora lortzen da
	public long hasierakoDenb(){
		return zeroMomentua;
	}
	
	
	//Pasa diren segunduak itzultzen du segunduetan
	//(egungo denbora - hasierako denbora) / 1000
	public int pasaDirenSegunduakLortu(){
		return ((int) (System.currentTimeMillis() - zeroMomentua) / 1000);
	}

}
