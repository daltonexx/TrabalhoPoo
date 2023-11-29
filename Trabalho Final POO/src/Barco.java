public class Barco extends Equipamento {

	private int capacidade;

	public int getCapacidade() {
		return capacidade;
	}

	public Barco(int id, String nome, double custoDia, int capacidade) {
		super(id, nome, custoDia);
		this.capacidade = capacidade;
	}

	@Override
	public String toString() {
		return "Barco = {" +
				"id=" + getId() +
				", nome='" + getNome() + '\'' +
				", custoDia=" + getCustoDia() +
				", capacidade=" + capacidade +
				'}';
	}
}
