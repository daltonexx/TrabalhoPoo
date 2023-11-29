public class Equipamento implements Comparable<Equipamento>, CSVConvertable, JSONConvertable{

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
	public String toString() {

		return "id=" + id + ", nome=" + nome + ", custoDia=" + custoDia + ", equipe=" + equipe.getCodinome();

	}

	@Override
	public int compareTo(Equipamento equipamento) {
		return Integer.compare(this.id, equipamento.id);
	}

	@Override
	public String toCSV() {
		return id+";"+nome+";"+custoDia+";"+equipe.getCodinome();
	}

	@Override
	public String toCSVHeader() {
		return "identificador;nome;custoDiario;codinome;tipo;capacidade_combustivel;carga";
	}

	@Override
	public String toJSONObject() {
		return String.format("{\"id\": %d, \"nome\": \"%s\", \"custoDiario\": %f, \"codinome\": \"%s\", ",id,nome,custoDia,equipe.getCodinome());
	}
}
