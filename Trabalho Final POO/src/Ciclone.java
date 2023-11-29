public class Ciclone extends Evento {

	private double velocidade;

	private double precipitacao;

	public Ciclone(String codigo, String data, double latitude, double longitude, double velocidade, double precipitacao) {
		super(codigo, data, latitude, longitude);
		this.velocidade = velocidade;
		this.precipitacao = precipitacao;
	}

	public double getVelocidade() {
		return velocidade;
	}

	public double getPrecipitacao() {
		return precipitacao;
	}

	@Override
	public String toString() {
		return "Ciclone: " + super.toString() + ", velocidade= " + velocidade +
				", precipitacao= " + precipitacao;
	}

	@Override
	public String toCSV() {
		return super.toCSV()+";1;"+velocidade+";"+precipitacao;
	}

	@Override
	public String toJSONObject() {
		return super.toJSONObject()+ String.format( "\"tipo\": \"1\",\n		\"velocidade\": %f,\n		\"precipitacao\": %f\n	}",velocidade,precipitacao);
	}
}
