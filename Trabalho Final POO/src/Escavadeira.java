public class Escavadeira extends Equipamento implements CSVConvertable {

	private Combustivel combustivel;

	private double carga;

	public Escavadeira(int id, String nome, double custoDia, Combustivel combustivel, double carga) {
		super(id, nome, custoDia);
		this.combustivel = combustivel;
		this.carga = carga;
	}

	public Escavadeira(int id, String nome, double custoDia, Equipe equipe, Combustivel combustivel, double carga) {
		super(id, nome, custoDia, equipe);
		this.combustivel = combustivel;
		this.carga = carga;
	}

	@Override
	public String toString() {
		return "Escavadeira: " + super.toString() + ", combustivel=" + combustivel.getCombustivel() + ", carga=" + carga;
	}

	@Override
	public String toCSV() {
		return super.toCSV()+";3;"+combustivel.toString().toUpperCase()+";"+carga;
	}

	@Override
	public String toJSONObject() {
		return super.toJSONObject()+ String.format("\"tipo\": \"3\",\n		\"combsutivel\": \"%s\",\n		\"carga\": %f\n	}",combustivel.toString(),carga);
	}
}
