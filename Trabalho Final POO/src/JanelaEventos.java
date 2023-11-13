import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class JanelaEventos extends JFrame implements ActionListener {
    private JTextField codinome, quantidade, latitude, longitude;
    private JButton botao, fechar;
    private JTextArea area, areaEquipes;
    private ArrayList<Equipe> equipes;

    public JanelaEventos() {
        super();
        equipes = new ArrayList<>();

        codinome = new JTextField(20);
        quantidade = new JTextField(3);
        latitude = new JTextField(20);
        longitude = new JTextField(20);
        botao = new JButton("OK");
        fechar = new JButton("Fechar");
        area = new JTextArea();
        areaEquipes = new JTextArea();

        area.setEditable(false);
        areaEquipes.setEditable(false);

        JPanel container = new JPanel();
        BoxLayout layout = new BoxLayout(container, BoxLayout.PAGE_AXIS);
        container.setLayout(layout);

        container.add(new JLabel("Codinome: "));
        container.add(codinome);
        container.add(new JLabel("Quantidade: "));
        container.add(quantidade);
        container.add(new JLabel("Latitude: "));
        container.add(latitude);
        container.add(new JLabel("Longitude: "));
        container.add(longitude);
        container.add(area);
        container.add(areaEquipes);
        container.add(botao);
        container.add(fechar);

        botao.addActionListener(this);
        fechar.addActionListener(this);

        this.add(container);
        this.setSize(600, 200);
        this.setTitle("Cadastro de equipe");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
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

                Collections.sort(equipes);

                areaEquipes.setText("");
                for (Equipe eq : equipes) {
                    areaEquipes.append(eq.toString());
                }
            }
            catch (NumberFormatException exception){
                area.setText("Erro! Certifique-se de inserir números válidos para quantidade, latitude e longitude.");
            }
            catch (Exception exception){
                area.setText(exception.getMessage());
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
