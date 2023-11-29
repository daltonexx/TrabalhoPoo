public class Barco extends Equipamento {

	private int capacidade;

	public int getCapacidade() {
		return capacidade;
	}

	public Barco(int id, String nome, double custoDia, int capacidade) {
		super(id, nome, custoDia);
		this.capacidade = capacidade;
	}

	public Barco(int id, String nome, double custoDia, Equipe equipe, int capacidade) {
		super(id, nome, custoDia, equipe);
		this.capacidade = capacidade;
	}

	@Override
	public String toString() {
		return super.toString() + ", capacidade=" + capacidade;
	}
}
