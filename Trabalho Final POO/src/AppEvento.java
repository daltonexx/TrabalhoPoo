import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.Border;

public class AppEvento extends JFrame implements ActionListener {

    private JTextField code, dia, mes, longitude, latitude, velocidade, precipitacao, magnitude, estiagem;
    private JButton terremoto, ciclone, seca, limpar, confirmar, mostrar, finalizar;
    private JLabel labelCodigo, labelDia, labelMes, labelLatitude, labelLongitude, labelVelocidade, labelPrecipitacao, labelMagnitude, labelEstiagem;
    private JTextArea mensagemArea;
    private int atual = 0;
    private ArrayList<Evento> eventos = new ArrayList<Evento>();

    public AppEvento() {
        super();

        code = new JTextField(10);
        dia = new JTextField(2);
        mes = new JTextField(2);
        longitude = new JTextField(10);
        latitude = new JTextField(10);
        velocidade = new JTextField(3);
        precipitacao = new JTextField(3);
        magnitude = new JTextField(2);
        estiagem = new JTextField(2);

        limpar = new JButton("Limpar Tudo");
        confirmar = new JButton("Confirmar Cadastro");
        mostrar = new JButton("Mostrar Cadastros");
        finalizar = new JButton("Finalizar Programa");



        Color vermelhoCardinalMaisClaro = new Color(211, 211, 211);
        Color faluRed = new Color(128, 128, 128);
        Border border = BorderFactory.createLineBorder(vermelhoCardinalMaisClaro, 10);
        mensagemArea = new JTextArea(5, 10);
        mensagemArea.setFont(new Font("Arial", Font.BOLD, 22));
        mensagemArea.setEditable(false);
        mensagemArea.setBorder(border);



        JPanel painel = new JPanel();
        BoxLayout layout = new BoxLayout(painel, BoxLayout.Y_AXIS);
        painel.setLayout(layout);

        FlowLayout titulo = new FlowLayout();
        JPanel linhaE = new JPanel();
        JLabel evento = new JLabel("Selecione um tipo de evento para Registrar");
        evento.setFont(new Font("Western", Font.BOLD, 22));
        evento.setBorder(border);
        linhaE.setBackground(vermelhoCardinalMaisClaro);
        linhaE.setLayout(titulo);
        linhaE.add(evento);
        painel.add(linhaE);

        FlowLayout botoes = new FlowLayout();
        JPanel linhabot = new JPanel();
        linhabot.setBackground(faluRed);
        linhabot.setLayout(botoes);

        terremoto = new JButton("Terremoto");
        ciclone = new JButton("Ciclone");
        seca = new JButton("Seca");
        terremoto.setBackground(Color.white);
        ciclone.setBackground(Color.white);
        seca.setBackground(Color.white);
        terremoto.setPreferredSize(new Dimension(150, 50));
        ciclone.setPreferredSize(new Dimension(150, 50));
        seca.setPreferredSize(new Dimension(150, 50));

        linhabot.add(terremoto);
        linhabot.add(ciclone);
        linhabot.add(seca);
        painel.add(linhabot);

        labelCodigo = new JLabel("Código:");
        labelDia = new JLabel("Dia:");
        labelMes = new JLabel("Mês:");
        labelLatitude = new JLabel("Latitude:");
        labelLongitude = new JLabel("Longitude:");
        labelVelocidade = new JLabel("Velocidade:");
        labelPrecipitacao = new JLabel("Precipitação:");
        labelMagnitude = new JLabel("Magnitude:");
        labelEstiagem = new JLabel("Estiagem:");
        labelCodigo.setForeground(Color.BLACK);
        labelDia.setForeground(Color.BLACK);
        labelMes.setForeground(Color.BLACK);
        labelLatitude.setForeground(Color.BLACK);
        labelLongitude.setForeground(Color.BLACK);
        labelVelocidade.setForeground(Color.BLACK);
        labelPrecipitacao.setForeground(Color.BLACK);
        labelMagnitude.setForeground(Color.BLACK);
        labelEstiagem.setForeground(Color.BLACK);

        FlowLayout linha = new FlowLayout();


        JPanel linhaCodePanel = new JPanel();
        linhaCodePanel.setLayout(linha);
        linhaCodePanel.add(labelCodigo);
        linhaCodePanel.add(code);
        linhaCodePanel.setBackground(faluRed);
        labelCodigo.setForeground(Color.WHITE);
        painel.add(linhaCodePanel);


        JPanel linhaDiaPanel = new JPanel();
        linhaDiaPanel.setLayout(linha);
        linhaDiaPanel.add(labelDia);
        linhaDiaPanel.add(dia);
        linhaDiaPanel.add(labelMes);
        linhaDiaPanel.add(mes);
        linhaDiaPanel.setBackground(faluRed);
        labelDia.setForeground(Color.WHITE);
        labelMes.setForeground(Color.WHITE);
        painel.add(linhaDiaPanel);

        JPanel linhaLatitudePanel = new JPanel();
        linhaLatitudePanel.setLayout(linha);
        linhaLatitudePanel.add(labelLatitude);
        linhaLatitudePanel.add(latitude);
        linhaLatitudePanel.setBackground(faluRed);
        labelLatitude.setForeground(Color.WHITE);
        painel.add(linhaLatitudePanel);


        JPanel linhaLongitudePanel = new JPanel();
        linhaLongitudePanel.setLayout(linha);
        linhaLongitudePanel.add(labelLongitude);
        linhaLongitudePanel.add(longitude);
        linhaLongitudePanel.setBackground(faluRed);
        labelLongitude.setForeground(Color.WHITE);
        painel.add(linhaLongitudePanel);



        JPanel linhaVelocidadePanel = new JPanel();
        linhaVelocidadePanel.setLayout(linha);
        linhaVelocidadePanel.add(labelVelocidade);
        linhaVelocidadePanel.add(velocidade);
        linhaVelocidadePanel.setBackground(faluRed);
        labelVelocidade.setForeground(Color.WHITE);
        painel.add(linhaVelocidadePanel);



        JPanel linhaPrecipitacaoPanel = new JPanel();
        linhaPrecipitacaoPanel.setLayout(linha);
        linhaPrecipitacaoPanel.add(labelPrecipitacao);
        linhaPrecipitacaoPanel.add(precipitacao);
        linhaPrecipitacaoPanel.setBackground(faluRed);
        labelPrecipitacao.setForeground(Color.WHITE);
        painel.add(linhaPrecipitacaoPanel);


        JPanel linhaMagnitudePanel = new JPanel();
        linhaMagnitudePanel.setLayout(linha);
        linhaMagnitudePanel.add(labelMagnitude);
        linhaMagnitudePanel.add(magnitude);
        linhaMagnitudePanel.setBackground(faluRed);
        labelMagnitude.setForeground(Color.WHITE);
        painel.add(linhaMagnitudePanel);


        JPanel linhaEstiagemPanel = new JPanel();
        linhaEstiagemPanel.setLayout(linha);
        linhaEstiagemPanel.add(labelEstiagem);
        linhaEstiagemPanel.add(estiagem);
        linhaEstiagemPanel.setBackground(faluRed);
        labelEstiagem.setForeground(Color.WHITE);
        painel.add(linhaEstiagemPanel);

        JPanel botoes2 = new JPanel();
        botoes2.setLayout(linha);
        botoes2.add(confirmar);
        botoes2.add(limpar);
        botoes2.setBackground(vermelhoCardinalMaisClaro);
        painel.add(botoes2);
        limpar.setBackground(Color.white);
        limpar.setPreferredSize(new Dimension(150, 50));
        confirmar.setBackground(Color.white);
        confirmar.setPreferredSize(new Dimension(150, 50));

        JPanel botoes3 = new JPanel();
        painel.add(mensagemArea);
        botoes3.setLayout(linha);
        botoes3.add(mostrar);
        botoes3.add(finalizar);
        botoes3.setBackground(vermelhoCardinalMaisClaro);
        painel.add(botoes3);
        mostrar.setBackground(Color.white);
        mostrar.setPreferredSize(new Dimension(150, 50));
        finalizar.setBackground(Color.white);
        finalizar.setPreferredSize(new Dimension(150, 50));


        terremoto.addActionListener(this);
        ciclone.addActionListener(this);
        seca.addActionListener(this);
        limpar.addActionListener(this);
        mostrar.addActionListener(this);
        finalizar.addActionListener(this);
        confirmar.addActionListener(this);

        this.add(painel);
        painel.setBackground(vermelhoCardinalMaisClaro);
        this.setSize(800, 600);
        this.setTitle("Registrador de Eventos");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setInvisibleAll();
        this.setVisible(true);
    }

    public void setInvisibleAll() {
        code.setVisible(false);
        dia.setVisible(false);
        mes.setVisible(false);
        longitude.setVisible(false);
        latitude.setVisible(false);
        precipitacao.setVisible(false);
        velocidade.setVisible(false);
        estiagem.setVisible(false);
        magnitude.setVisible(false);
        labelCodigo.setVisible(false);
        labelDia.setVisible(false);
        labelMes.setVisible(false);
        labelLatitude.setVisible(false);
        labelLongitude.setVisible(false);
        labelVelocidade.setVisible(false);
        labelPrecipitacao.setVisible(false);
        labelMagnitude.setVisible(false);
        labelEstiagem.setVisible(false);

    }

    public void setVisibleAll() {
        code.setVisible(true);
        dia.setVisible(true);
        mes.setVisible(true);
        longitude.setVisible(true);
        latitude.setVisible(true);
        labelCodigo.setVisible(true);
        labelDia.setVisible(true);
        labelMes.setVisible(true);
        labelLatitude.setVisible(true);
        labelLongitude.setVisible(true);
    }

    public void setVisibleTerremoto() {
        magnitude.setVisible(true);
        labelMagnitude.setVisible(true);

        precipitacao.setVisible(false);
        velocidade.setVisible(false);
        labelVelocidade.setVisible(false);
        labelPrecipitacao.setVisible(false);
        estiagem.setVisible(false);
        labelEstiagem.setVisible(false);
    }

    public void setVisibleCiclone() {
        precipitacao.setVisible(true);
        velocidade.setVisible(true);
        labelVelocidade.setVisible(true);
        labelPrecipitacao.setVisible(true);

        magnitude.setVisible(false);
        labelMagnitude.setVisible(false);
        estiagem.setVisible(false);
        labelEstiagem.setVisible(false);
    }

    public void setVisibleSeca() {
        estiagem.setVisible(true);
        labelEstiagem.setVisible(true);

        precipitacao.setVisible(false);
        velocidade.setVisible(false);
        labelVelocidade.setVisible(false);
        labelPrecipitacao.setVisible(false);
        magnitude.setVisible(false);
        labelMagnitude.setVisible(false);
    }

    public void limpar(){
        code.setText("");
        dia.setText("");
        mes.setText("");
        longitude.setText("");
        latitude.setText("");
        velocidade.setText("");
        precipitacao.setText("");
        magnitude.setText("");
        estiagem.setText("");
        mensagemArea.setText("");
    }

    public boolean cadastraEvento(Evento e) {
        for (Evento l : eventos) {
            if (l.getCodigo().equals(e.getCodigo()))
                return false;
        }
        int i = 0;
        while (i < eventos.size() && e.getCodigo().compareTo(eventos.get(i).getCodigo()) > 0) {
            i++;
        }

        eventos.add(i, e);
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AppEvento();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == terremoto) {
            setVisibleAll();
            setVisibleTerremoto();
            atual = 1;
        } else if (e.getSource() == ciclone) {
            setVisibleAll();
            setVisibleCiclone();
            atual = 2;
        } else if (e.getSource() == seca) {
            setVisibleAll();
            setVisibleSeca();
            atual = 3;
        }
        if(e.getSource() == confirmar){
            if (atual == 0){
                mensagemArea.append("Erro ao cadastrar: Selecione um Evento, Não há tipo de evento. \n");
            }
            else if (atual == 1) {
                String c = code.getText();
                String data;
                int d = 0, me = 0;
                double la = 0;
                double lo = 0;
                int ma = 0;

                try {
                    d = Integer.parseInt(dia.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Dia deve ser um valor numérico.\n");
                    return;
                }
                try {
                    me = Integer.parseInt(mes.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Mês deve ser um valor numérico.\n");
                    return;
                }
                try {
                    la = Double.parseDouble(latitude.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Latitude deve ser um valor numérico.\n");
                    return;
                }
                try {
                    lo = Double.parseDouble(longitude.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Longitude deve ser um valor numérico.\n");
                    return;
                }
                try {
                    ma = Integer.parseInt(magnitude.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Magnitude deve ser um valor numérico.\n");
                    return;
                }

                data = d + "/" + me;
                Terremoto terre = new Terremoto(c, data, la, lo, ma);

                if (c == null || c.isEmpty()) {
                    mensagemArea.append("Erro ao cadastrar: Código não pode ser nulo ou vazio.\n");
                } else if (la < 0 || la > 90) {
                    mensagemArea.append("Erro ao cadastrar: Latitude deve estar entre 0 e 90.\n");
                } else if (lo < 0 || lo > 180) {
                    mensagemArea.append("Erro ao cadastrar: Longitude deve estar entre 0 e 180.\n");
                } else if (d <= 0 || d > 31) {
                    mensagemArea.append("Erro ao cadastrar: Dia deve estar entre 1 e 31.\n");
                } else if (me <= 0 || me > 12) {
                    mensagemArea.append("Erro ao cadastrar: Mês deve estar entre 1 e 12.\n");
                } else if (ma <= 0) {
                    mensagemArea.append("Erro ao cadastrar: Magnitude deve ser maior que zero.\n");
                } else if (!cadastraEvento(terre)) {
                    mensagemArea.append("Erro ao cadastrar: O código já existe.\n");
                } else {
                    mensagemArea.append("\nEvento cadastrado com sucesso!\n");
                }
            }
            else if (atual == 2) {
                String c = code.getText();
                String data;
                int d = 0, me = 0;
                double la = 0, lo = 0, v = 0, p = 0;

                try {
                    d = Integer.parseInt(dia.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Dia deve ser um valor numérico.\n");
                    return;
                }
                try {
                    me = Integer.parseInt(mes.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Mês deve ser um valor numérico.\n");
                    return;
                }
                try {
                    la = Double.parseDouble(latitude.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Latitude deve ser um valor numérico.\n");
                    return;
                }
                try {
                    lo = Double.parseDouble(longitude.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Longitude deve ser um valor numérico.\n");
                    return;
                }
                try {
                    v = Double.parseDouble(velocidade.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Velocidade deve ser um valor numérico.\n");
                    return;
                }
                try {
                    p = Double.parseDouble(precipitacao.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Precipitação deve ser um valor numérico.\n");
                    return;
                }

                data = d + "/" + me;
                Ciclone cic = new Ciclone(c, data, la, lo, v, p);

                if (c == null || c.isEmpty()) {
                    mensagemArea.append("Erro ao cadastrar: Código não pode ser nulo ou vazio.\n");
                } else if (la < 0 || la > 90) {
                    mensagemArea.append("Erro ao cadastrar: Latitude deve estar entre 0 e 90.\n");
                } else if (lo < 0 || lo > 180) {
                    mensagemArea.append("Erro ao cadastrar: Longitude deve estar entre 0 e 180.\n");
                } else if (d <= 0 || d > 31) {
                    mensagemArea.append("Erro ao cadastrar: Dia deve estar entre 1 e 31.\n");
                } else if (me <= 0 || me > 12) {
                    mensagemArea.append("Erro ao cadastrar: Mês deve estar entre 1 e 12.\n");
                } else if (v <= 0) {
                    mensagemArea.append("Erro ao cadastrar: Velocidade deve ser maior que zero.\n");
                } else if (p <= 0) {
                    mensagemArea.append("Erro ao cadastrar: Precipitação deve ser maior que zero.\n");
                } else if (!cadastraEvento(cic)) {
                    mensagemArea.append("Erro ao cadastrar: O código já existe.\n");
                } else {
                    mensagemArea.append("\nEvento cadastrado com sucesso!\n");
                }
            }
            else {
                String c = code.getText();
                String data;
                int d = 0, me = 0, di = 0;
                double la = 0, lo = 0;

                try {
                    d = Integer.parseInt(dia.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Dia deve ser um valor numérico.\n");
                    return;
                }
                try {
                    me = Integer.parseInt(mes.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Mês deve ser um valor numérico.\n");
                    return;
                }
                try {
                    la = Double.parseDouble(latitude.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Latitude deve ser um valor numérico.\n");
                    return;
                }
                try {
                    lo = Double.parseDouble(longitude.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Longitude deve ser um valor numérico.\n");
                    return;
                }
                try {
                    di = Integer.parseInt(estiagem.getText());
                } catch (NumberFormatException ex) {
                    mensagemArea.append("Erro ao cadastrar: Dias de estiagem deve ser um valor numérico.\n");
                    return;
                }


                data = d + "/" + me;
                Seca est = new Seca(c, data, la, lo, di);

                if (c == null || c.isEmpty()) {
                    mensagemArea.append("Erro ao cadastrar: Código não pode ser nulo ou vazio.\n");
                } else if (la < 0 || la > 90) {
                    mensagemArea.append("Erro ao cadastrar: Latitude deve estar entre 0 e 90.\n");
                } else if (lo < 0 || lo > 180) {
                    mensagemArea.append("Erro ao cadastrar: Longitude deve estar entre 0 e 180.\n");
                } else if (d <= 0 || d > 31) {
                    mensagemArea.append("Erro ao cadastrar: Dia deve estar entre 1 e 31.\n");
                } else if (me <= 0 || me > 12) {
                    mensagemArea.append("Erro ao cadastrar: Mês deve estar entre 1 e 12.\n");
                } else if (di <= 0) {
                    mensagemArea.append("Erro ao cadastrar: Dias de estiagem deve ser maior que zero.\n");
                } else if (!cadastraEvento(est)) {
                    mensagemArea.append("Erro ao cadastrar: O código já existe.\n");
                } else {
                    mensagemArea.append("\nEvento cadastrado com sucesso!\n");
                }
            }
        }


        if(e.getSource() == mostrar){
            String texto = "";
            for (Evento x: eventos) {
                texto += x.toString() + "\n";
            }
            mensagemArea.append("\nEventos cadastrados: \n " + texto);
        }
        if(e.getSource() == limpar){
            limpar();
        }
        if(e.getSource() == finalizar){
            System.exit(0);
        }
    }
}