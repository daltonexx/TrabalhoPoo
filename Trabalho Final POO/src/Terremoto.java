public class Terremoto extends Evento {

	private double magnitude;

	public Terremoto(String codigo, String data, double latitude, double longitude, double magnitude) {
		super(codigo, data, latitude, longitude);
		this.magnitude = magnitude;
	}

	public double getMagnitude() {
		return magnitude;
	}

	@Override
	public String toString() {
		return "Terremoto: " + super.toString() + ", Magnitude= " + magnitude;
	}

	@Override
	public String toCSV() {
		return super.toCSV()+";2;"+magnitude;
	}

	@Override
	public String toJSONObject() {
		return super.toJSONObject()+ String.format( "\"tipo\": \"2\",\n		\"magnitude\": %f\n	}",magnitude);
	}
}
