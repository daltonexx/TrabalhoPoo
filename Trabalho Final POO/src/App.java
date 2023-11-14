import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class App extends JFrame{
    private String[] especializacoes = {"Barco", "Caminhao Tanque", "Escavadeira"};
    private String[] combustiveis = {"Diesel", "Gasolina", "Alcool"};
    private JComboBox equipList,fuelList;
    private JTextField id,nome,custo,capacidadeTanque, capacidadeBarco, carga;
    public App(){
        super();

        equipList = new JComboBox(especializacoes);
        fuelList = new JComboBox(combustiveis);
        JPanel painel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(painel, BoxLayout.Y_AXIS);
        painel.setLayout(boxLayout);
        FlowLayout layoutEquipamentos = new FlowLayout();
        JPanel linha1 = new JPanel();
        linha1.setLayout(layoutEquipamentos);
        JLabel selecionaEquipamento = new JLabel("Selecione o tipo: ");
        linha1.add(selecionaEquipamento);
        linha1.add(equipList);
        JLabel titulo = new JLabel("CADASTRA EQUIPAMENTOS");
        JPanel containerTitulo = new JPanel();
        titulo.setFont(new Font("Rockwell",Font.BOLD, 22));
        containerTitulo.add(titulo);
        painel.add(containerTitulo);
        painel.add(linha1);
        painel.add(fuelList);
        this.add(painel);
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
