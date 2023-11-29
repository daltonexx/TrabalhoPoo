public class Equipamento implements Comparable<Equipamento>{

	private int id;

	private String nome;

	private double custoDia;

	private Equipe equipe;

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getCustoDia() {
		return custoDia;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe){
		this.equipe = equipe;
	}

	public Equipamento(int id, String nome, double custoDia) {
		this.id = id;
		this.nome = nome;
		this.custoDia = custoDia;
		this.equipe = null;
	}

	public Equipamento(int id, String nome, double custoDia, Equipe equipe) {
		this.id = id;
		this.nome = nome;
		this.custoDia = custoDia;
		this.equipe = equipe;
	}

	@Override
	public int compareTo(Equipamento equipamento) {
		return Integer.compare(this.id, equipamento.id);
	}
}
