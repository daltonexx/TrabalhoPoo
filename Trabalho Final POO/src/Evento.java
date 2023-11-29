public class Evento implements CSVConvertable, JSONConvertable{

	private String codigo;

	private String data;

	private double latitude;

	private double longitude;

	public String getCodigo() {
		return codigo;
	}

	public String getData() {
		return data;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public Evento(String codigo, String data, double latitude, double longitude) {
		this.codigo = codigo;
		this.data = data;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "codigo= " + codigo  + ", data= " + data  + ", latitude= " + latitude + ", longitude= " + longitude ;
	}

	@Override
	public String toCSVHeader() {
		return "codigo;data;latitude;longitude;tipo;velocidade_magnitude_estiagem;prceipitacao";
	}

	@Override
	public String toCSV() {
		return codigo+";"+data+";"+latitude+";"+longitude;
	}

	@Override
	public String toJSONObject() {
		return String.format("{\"codigo\": \"%s\", \"data\": \"%s\", \"latitude\": %f, \"longitude\": %f, ",codigo,data,latitude,longitude);
	}
}
