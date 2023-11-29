public class Barco extends Equipamento{

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
		return "Barco: " + super.toString() + ", capacidade=" + capacidade;
	}

	@Override
	public String toCSV() {
		return super.toCSV()+";1;"+capacidade;
	}

	@Override
	public String toJSONObject() {
		return super.toJSONObject()+ String.format("\"tipo\": \"1\",\n		\"capacidade\": %d\n	}",capacidade);
	}
}
