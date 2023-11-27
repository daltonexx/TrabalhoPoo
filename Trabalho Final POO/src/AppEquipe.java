import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.border.Border;

public class AppEquipe extends JFrame implements ActionListener {
    private JTextField codinome, quantidade, latitude, longitude;
    private JButton botao, fechar, limpar, crescente;
    private JTextArea area;
    private ArrayList<Equipe> equipes;

    public AppEquipe() {
        super();
        equipes = new ArrayList<>();
        codinome = new JTextField(20);
        quantidade = new JTextField(20);
        latitude = new JTextField(20);
        longitude = new JTextField(20);
        botao = new JButton("OK");
        fechar = new JButton("Fechar");
        limpar = new JButton("Limpar");
        crescente = new JButton("Ordenar Crescente");
        Color color = new Color(211,211,211);
        Border border = BorderFactory.createLineBorder(color, 12);
        area = new JTextArea(5,10);
        area.setBorder(border);

        area.setEditable(false);

        JPanel container = new JPanel();
        container.setBackground(color);
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(color);
        JLabel title = new JLabel("Cadastro de Equipe");
        title.setFont(new Font("Impact", Font.BOLD, 32));
        titlePanel.add(title);
        container.add(titlePanel);

        FlowLayout flowLayout = new FlowLayout();

        JPanel painelCodinome = new JPanel();
        painelCodinome.setLayout(flowLayout);
        painelCodinome.setBackground(color);
        container.add(painelCodinome);
        JPanel painelQuantidade = new JPanel();
        painelQuantidade.setLayout(flowLayout);
        painelQuantidade.setBackground(color);
        container.add(painelQuantidade);
        JPanel painelLatitude = new JPanel();
        painelLatitude.setLayout(flowLayout);
        painelLatitude.setBackground(color);
        container.add(painelLatitude);
        JPanel painelLongitude = new JPanel();
        painelLongitude.setLayout(flowLayout);
        painelLongitude.setBackground(color);
        container.add(painelLongitude);

        painelCodinome.add(new JLabel("Codinome: "));
        painelCodinome.add(codinome);
        painelQuantidade.add(new JLabel("Quantidade: "));
        painelQuantidade.add(quantidade);
        painelLatitude.add(new JLabel("Latitude: "));
        painelLatitude.add(latitude);
        painelLongitude.add(new JLabel("Longitude: "));
        painelLongitude.add(longitude);
        GridLayout gridLayout = new GridLayout(1,4);
        JPanel painelButtons = new JPanel(gridLayout);

        JPanel painelRetorno = new JPanel();
        painelRetorno.setBackground(color);
        container.add(painelRetorno);

        container.add(area);

        painelButtons.add(botao);
        painelButtons.add(limpar);
        painelButtons.add(crescente);
        painelButtons.add(fechar);
        container.add(painelButtons);

        container.add(painelRetorno);

        botao.addActionListener(this);
        limpar.addActionListener(this);
        crescente.addActionListener(this);
        fechar.addActionListener(this);

        this.add(container);
        this.setSize(600, 200);
        this.setTitle("Cadastro de equipe");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botao) {
            try {
                Equipe equipe = new Equipe(codinome.getText(), Integer.parseInt(quantidade.getText()),
                        Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText()));
                if (cadastraEquipe(equipe)) {
                    area.setText("Cadastrado com sucesso!");
                } else {
                    area.setText("Erro! Codinome já existente.");
                }
                codinome.setText("");
                quantidade.setText("");
                latitude.setText("");
                longitude.setText("");
                area.append("\n\nDados da equipe cadastrada: " + equipe.getCodinome() + ";"
                        + equipe.getQuantidade() + ";" + equipe.getLatitude() + ";" +
                        equipe.getLongitude());
            }
            catch (NumberFormatException exception){
                area.setText("Erro! Certifique-se de inserir números válidos para quantidade, latitude e longitude.");
            }
            catch (Exception exception){
                area.setText(exception.getMessage());
            }
        }
        else if (e.getSource() == limpar){
            codinome.setText("");
            quantidade.setText("");
            latitude.setText("");
            longitude.setText("");
            area.setText("");
        }
        else if(e.getSource() == crescente){
            if (equipes.isEmpty()){
                area.setText("Nenhuma equipe cadastrada.");
                return;
            }

            Collections.sort(equipes);

            area.setText("Dados das equipes:\n\n");
            for (Equipe eq : equipes) {
                area.append(eq.toString());
            }
        }
        else if(e.getSource() == fechar) {
            System.exit(0);
        }
    }

    private boolean cadastraEquipe(Equipe equipe){
        if (equipe.getCodinome().isEmpty()){
            throw new NullPointerException("Erro! Campo de texto vazio.");
        }
        if (equipes.isEmpty()){
            equipes.add(equipe);
            return true;
        }
        if (pesquisaEquipe(equipe.getCodinome()) == null){
            equipes.add(equipe);
            return true;
        }
        return false;
    }

    private Equipe pesquisaEquipe(String codinome){
        for(Equipe e: equipes){
            if (e.getCodinome().equalsIgnoreCase(codinome)){
                return e;
            }
        }
        return null;
    }
}