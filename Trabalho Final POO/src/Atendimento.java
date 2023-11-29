public class Atendimento {

	private int cod;
	private String dataInicio;
	private int duracao;
	private Util util;
	private String status;
	private Equipe equipe;
	private Evento evento;

	public int getCod() {
		return cod;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public int getDuracao() {
		return duracao;
	}

	public String getStatus() {
		return status;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public Evento getEvento() {
		return evento;
	}
	public void setStatus(String status){
		this.status = status;
	}

	public Atendimento(int cod, String dataInicio, int duracao, String status, Equipe equipe, Evento evento) {
		this.cod = cod;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		this.status = "PENDENTE";
		this.equipe = equipe;
		this.evento = evento;
	}

	public void alocarEquipe(Equipe equipe) {
		this.equipe = equipe;
		this.status = "EXECUTANDO";
	}

	public void finalizarAtendimento() {
		if (equipe != null) {
			this.status = "FINALIZADO";
		} else {
			System.out.println("Erro: Não é possível finalizar atendimento sem equipe alocada.");
		}
	}

	public void cancelarAtendimento() {
		this.status = "CANCELADO";
	}

	public double calculaCusto() {
		double custo = 0, eq = 0, eqp = 0, desl = 0;

		eq = duracao * equipe.getQuantidade() * 250;
		eqp = duracao * equipe.getSomatorioEquipamento() * 250;
		desl = 100 * equipe.getQuantidade() + (equipe.getSomatorioEquipamento()*0.10) * util.calcularDistancia(equipe.getLatitude(), equipe.getLongitude(), evento.getLatitude(), evento.getLongitude());

		return eq + eqp + desl;
	}

}
