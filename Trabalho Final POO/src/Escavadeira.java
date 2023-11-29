public class Escavadeira extends Equipamento {

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
		return super.toString() + ", combustivel=" + combustivel.getCombustivel() + ", carga=" + carga;
	}
}
