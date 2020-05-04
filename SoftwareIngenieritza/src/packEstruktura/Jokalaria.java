package packEstruktura;

public class Jokalaria {
	private String izena;
	private int puntuazioa;

	public Jokalaria(String izena, int puntuazioa) {
		this.izena=izena;
		this.puntuazioa=puntuazioa;
	}
	
	public int getPuntuazioa(){
		return puntuazioa;
	}
	
	public String getIzena(){
		return izena;
	}

}
