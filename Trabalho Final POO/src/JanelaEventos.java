import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JanelaEventos extends JFrame implements ActionListener {
    private JTextField codinome, quantidade, latitude, longitude;
    private JButton botao, fechar;
    private JTextArea area;
    private ArrayList<Equipe> equipes;

    public JanelaEventos() {
        super();
        codinome = new JTextField(20);
        quantidade = new JTextField(2);
        latitude = new JTextField(20);
        longitude = new JTextField(20);
        botao = new JButton("OK");
        fechar = new JButton("Fechar");
        area = new JTextArea("");

        area.setEditable(false);

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
        container.add(botao);
        container.add(fechar);

        botao.addActionListener(this);
        fechar.addActionListener(this);

        this.add(container);
        this.setSize(600, 200);
        this.setTitle("Exemplo de eventos");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botao) {
            area.setText("Cadastrado com sucesso!");
            codinome.setText("");
            quantidade.setText("");
            latitude.setText("");
            longitude.setText("");
        }
        else if(e.getSource() == fechar) {
            System.exit(0);
        }
    }
}
