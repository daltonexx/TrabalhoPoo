public class Equipe implements Comparable<Equipe>{

	private String codinome;

	private int quantidade;

	private double latitude;

	private double longitude;

	public Equipe(String codinome, int quantidade, double latitude, double longitude) {
		this.codinome = codinome;
		this.quantidade = quantidade;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getCodinome() {
		return codinome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String toString(){
		String s;
		s = getCodinome() + ";" + getQuantidade() + ";" + getLatitude() + ";" +
				getLongitude() + "\n";
		return s;
	}

	@Override
	public int compareTo(Equipe outraEquipe) {
		return this.codinome.compareToIgnoreCase(outraEquipe.codinome);
	}
}
