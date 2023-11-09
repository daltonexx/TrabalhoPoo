public class Ciclone extends Evento {

	private double velocidade;

	private double precipitacao;

	public Ciclone(String codigo, String data, double latitude, double longitude) {
		super(codigo, data, latitude, longitude);
	}

	public double getVelocidade() {
		return velocidade;
	}

	public double getPrecipitacao() {
		return precipitacao;
	}
}
