public class Equipamento implements Comparable<Equipamento>{

	private int id;

	private String nome;

	private double custoDia;

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getCustoDia() {
		return custoDia;
	}

	public Equipamento(int id, String nome, double custoDia) {
		this.id = id;
		this.nome = nome;
		this.custoDia = custoDia;
	}
	@Override
	public int compareTo(Equipamento equipamento) {
		return Integer.compare(this.id, equipamento.id);
	}
}
