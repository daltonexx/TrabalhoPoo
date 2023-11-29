public class Seca extends Evento {

	private int estiagem;

	public Seca(String codigo, String data, double latitude, double longitude, int estiagem) {
		super(codigo, data, latitude, longitude);
		this.estiagem = estiagem;
    }



	public int getEstiagem() {
		return estiagem;
	}

	@Override
	public String toString() {
		return "Seca: " + super.toString() + ", Estiagem= " +estiagem;
	}

	@Override
	public String toCSV() {
		return super.toCSV()+";3;"+estiagem;
	}

	@Override
	public String toJSONObject() {
		return super.toJSONObject()+ String.format( "\"tipo\": \"3\", \"estiagem\": %d}",estiagem);
	}
}
