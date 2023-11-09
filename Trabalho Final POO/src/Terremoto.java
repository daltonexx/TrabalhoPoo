public class Terremoto extends Evento {

	private double magnitude;

	public Terremoto(String codigo, String data, double latitude, double longitude) {
		super(codigo, data, latitude, longitude);
	}

	public double getMagnitude() {
		return magnitude;
	}
}
