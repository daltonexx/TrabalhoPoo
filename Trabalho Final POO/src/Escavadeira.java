public class Escavadeira extends Equipamento {

	private Combustivel combustivel;

	private double carga;

	public Escavadeira(int id, String nome, double custoDia, Combustivel combustivel, double carga) {
		super(id, nome, custoDia);
		this.combustivel = combustivel;
		this.carga = carga;
	}

	@Override
	public String toString() {
		return "Escavadeira = {" +
				"id=" + getId() +
				", nome='" + getNome() + '\'' +
				", custoDia=" + getCustoDia() +
				", combustivel=" + combustivel.getCombustivel() +
				", carga=" + carga +
				'}';
	}
}
