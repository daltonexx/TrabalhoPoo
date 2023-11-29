import java.util.ArrayList;

public class Equipe implements Comparable<Equipe>, CSVConvertable, JSONConvertable {

	private String codinome;

	private int quantidade;

	private double latitude;

	private double longitude;

	private ArrayList<Equipamento> equipamentos;

	public Equipe(String codinome, int quantidade, double latitude, double longitude) {
		this.codinome = codinome;
		this.quantidade = quantidade;
		this.latitude = latitude;
		this.longitude = longitude;
		equipamentos = new ArrayList<Equipamento>();
	}



	public String getCodinome() {
		return codinome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public ArrayList<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void addEquipamento(Equipamento equipamento){
		equipamentos.add(equipamento);
	}

	public double getSomatorioEquipamento(){
		double soma = 0;
		for(Equipamento e: equipamentos){
			soma += e.getCustoDia();
		}
		return soma;
	}

	public double getCustoEquipe(){
		return 250 * quantidade;
	}

	public String toString(){
		return "Codinome = " + codinome + "; Quantidade = " + quantidade + "; Latitude = " + latitude + "; Longitude" + longitude;
	}

	@Override
	public int compareTo(Equipe o) {
		return this.codinome.compareTo(o.codinome);
	}

	@Override
	public String toCSVHeader() {
		return "codinome;quantidade;latitude;longitude";
	}

	@Override
	public String toCSV() {
		return codinome+";"+quantidade+";"+latitude+";"+longitude;
	}

	@Override
	public String toJSONObject() {
		return String.format("	{\n		\"codinome\": \"%s\",\n		\"quantidade\": %d,\n		\"latitude\": %f,\n		\"longitude\": %f\n	}",
				getCodinome(), getQuantidade(), getLatitude(), getLongitude());
	}
}
