package packEstruktura;

public class Jokalaria {
	private String izena;
	private int puntuazioa;

	public Jokalaria(String izena, int puntuazioa) {
		this.izena=izena;
		this.puntuazioa=puntuazioa;
	}
	
	
	//Jokalariaren puntuazioa lortu
	public int getPuntuazioa(){
		return puntuazioa;
	}
	
	
	//Jokalariaren izena lortu
	public String getIzena(){
		return izena;
	}

}
