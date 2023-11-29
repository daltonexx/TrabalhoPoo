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

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
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


	@Override
	public String toString() {
		return "Atendimento: " +
				"cod=" + cod +
				", dataInicio='" + dataInicio + '\'' +
				", duracao=" + duracao +
				", status='" + status + '\'' +
				", equipe=" + equipe +
				", evento=" + evento.getCodigo();
	}


	public double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {

			// Converte as coordenadas de graus para radianos
			double radLat1 = Math.toRadians(lat1);
			double radLon1 = Math.toRadians(lon1);
			double radLat2 = Math.toRadians(lat2);
			double radLon2 = Math.toRadians(lon2);

			// Diferença entre as latitudes e longitudes
			double deltaLat = radLat2 - radLat1;
			double deltaLon = radLon2 - radLon1;

			// Fórmula de Haversine
			double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
					+ Math.cos(radLat1) * Math.cos(radLat2) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

			// Distância em quilômetros
			double distancia = 6371 * c;

			return distancia;
		}
}
