public class CaminhaoTanque extends Equipamento {

	private double capacidade;

	public double getCapacidade() {
		return capacidade;
	}

	public CaminhaoTanque(int id, String nome, double custoDia, double capacidade) {
		super(id, nome, custoDia);
		this.capacidade = capacidade;
	}

	@Override
	public String toString() {
		return "CaminhaoTanque = {" +
				"id=" + getId() +
				", nome='" + getNome() + '\'' +
				", custoDia=" + getCustoDia() +
				", capacidade=" + capacidade +
				'}';
	}
}
