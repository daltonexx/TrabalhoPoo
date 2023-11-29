import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.Border;
import java.util.Collections;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Queue;


public class ACMERescue extends JFrame implements ActionListener {
    private Scanner entrada = null;                 // Atributo para entrada de dados
    private PrintStream saidaPadrao = System.out;   // Guarda a saida padrao - tela (console)
    private JButton cEvento, cEquipe, cEquipamento, cAtendimento, mRelatorio, vEquipamento, aAtendimento, consultaAtendimento, alteraAtendimento;
    private JButton salvaDados, carregaDados, finalizaSistema;
    private JLabel titulo;
    private AppEvento appEvento;
    private AppEquipe appEquipe;
    private AppEquipamento appEquipamento;
    private CadastraAtendimento cadastraAtendimento;
    private VincularEquipamento vincularEquipamento;
    private CarregaArquivos carregaArquivos;
    private CarregaArquivosInicial carregaArquivosInicial;
    private SalvaArquivos salvaArquivos;
    private ArrayList<Evento> eventos;
    private ArrayList<Equipe> equipes;
    private Queue<Atendimento> atendimentos;
    private ArrayList<Equipamento> equipamentos;
    private JButton mostrarEventos;
    private LocalDate data;
    private JPanel Titulo;


    public ACMERescue() {
        super();

        eventos = new ArrayList<>();
        equipamentos = new ArrayList<>();
        equipes = new ArrayList<>();
        atendimentos = new LinkedList<>();

        titulo = new JLabel("Menu de atendimento ACMERescue");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        BoxLayout layout = new BoxLayout(painel, BoxLayout.Y_AXIS);
        JPanel Titulo = new JPanel();
        painel.setLayout(layout);



        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        Titulo.add(titulo);
        painel.add(Titulo);

        cEvento = new JButton("Cadastrar Evento");
        cEvento.addActionListener(this);
        cEvento.setPreferredSize(new Dimension(200,50));
        JPanel Linha1 = new JPanel();
        Linha1.add(cEvento);
        painel.add(Linha1);

        cEquipamento = new JButton("Cadastrar Equipamento");
        cEquipamento.addActionListener(this);
        cEquipamento.setPreferredSize(new Dimension(200, 50));
        JPanel Linha3 = new JPanel();
        Linha3.add(cEquipamento);
        painel.add(Linha3);

        cEquipe = new JButton("Cadastrar Equipe");
        cEquipe.addActionListener(this);
        cEquipe.setPreferredSize(new Dimension(200, 50));
        JPanel Linha4 = new JPanel();
        Linha4.add(cEquipe);
        painel.add(Linha4);

        cAtendimento = new JButton("Cadastrar Atendimento");
        cAtendimento.addActionListener(this);
        cAtendimento.setPreferredSize(new Dimension(200, 50));
        JPanel Linha5 = new JPanel();
        Linha5.add(cAtendimento);
        painel.add(Linha5);

        mRelatorio = new JButton("Mostrar Relatório");
        mRelatorio.addActionListener(this);
        mRelatorio.setPreferredSize(new Dimension(200, 50));
        JPanel Linha6 = new JPanel();
        Linha6.add(mRelatorio);
        painel.add(Linha6);

        vEquipamento = new JButton("Vincular Equipamento");
        vEquipamento.addActionListener(this);
        vEquipamento.setPreferredSize(new Dimension(200, 50));
        JPanel Linha7 = new JPanel();
        Linha7.add(vEquipamento);
        painel.add(Linha7);

        aAtendimento = new JButton("Alocar Atendimento");
        aAtendimento.addActionListener(this);
        aAtendimento.setPreferredSize(new Dimension(200, 50));
        JPanel Linha8 = new JPanel();
        Linha8.add(aAtendimento);
        painel.add(Linha8);

        consultaAtendimento = new JButton("Consultar Atendimento");
        consultaAtendimento.addActionListener(this);
        consultaAtendimento.setPreferredSize(new Dimension(200, 50));
        JPanel Linha9 = new JPanel();
        Linha9.add(consultaAtendimento);
        painel.add(Linha9);

        alteraAtendimento = new JButton("Alterar Atendimento");
        alteraAtendimento.addActionListener(this);
        alteraAtendimento.setPreferredSize(new Dimension(200, 50));
        JPanel Linha10 = new JPanel();
        Linha10.add(alteraAtendimento);
        painel.add(Linha10);

        salvaDados = new JButton("Salvar Dados");
        salvaDados.addActionListener(this);
        salvaDados.setPreferredSize(new Dimension(200, 50));
        JPanel Linha12 = new JPanel();
        Linha12.add(salvaDados);
        painel.add(Linha12);

        carregaDados = new JButton("Carregar Dados");
        carregaDados.addActionListener(this);
        carregaDados.setPreferredSize(new Dimension(200, 50));
        JPanel Linha13 = new JPanel();
        Linha13.add(carregaDados);
        painel.add(Linha13);

        finalizaSistema = new JButton("Finalizar Sistema");
        finalizaSistema.addActionListener(this);
        finalizaSistema.setPreferredSize(new Dimension(200, 50));
        JPanel Linha14 = new JPanel();
        Linha14.add(finalizaSistema);
        painel.add(Linha14);



        this.add(painel);
        this.setTitle("MENU");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        this.setVisible(false);
        abreCarregaArquivosIniciais();
    }

    public class CadastraAtendimento extends JFrame implements ActionListener{
        private JTextArea campoEvento;
        private JFrame janelaAtendimento;
        private JPanel painelAtendimento, tituloCodigo, linhaCodePanel, botoesBaixo;
        private JLabel tituloevento, mensagemErro, Mensagem;
        private JButton confirmaCodigo, voltar, cancelar, confirmaCadastro;
        private JTextField code, codigoAtendimento, dataAtendimento, duracaoAtendimento;
        private JPanel linhacod, linhadata, linhaduracao;
        private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        private Evento eventoCadastro;


        public CadastraAtendimento() {
            campoEvento = new JTextArea(5, 20);
            StringBuilder eventosStr = new StringBuilder("Eventos Cadastrados:\n");
            for (Evento evento : eventos) {
                eventosStr.append(evento.toString()).append("\n");
            }
            campoEvento.setBackground(Color.white);
            campoEvento.setSize(20, 10);
            campoEvento.setText(eventosStr.toString());
            campoEvento.setEditable(false);
            campoEvento.setFocusable(false);


            tituloevento = new JLabel("Insira o código do evento que você deseja cadastrar o atendimento");



            janelaAtendimento = new JFrame("Janela de Cadastro de Atendimento");
            janelaAtendimento.setSize(1000, 800);

            painelAtendimento = new JPanel();
            painelAtendimento.setLayout(new BoxLayout(painelAtendimento, BoxLayout.Y_AXIS));

            tituloCodigo = new JPanel(new FlowLayout());
            tituloCodigo.add(tituloevento);

            code = new JTextField(10);
            confirmaCodigo = new JButton("Confirmar código");

            mensagemErro = new JLabel("");
            linhaCodePanel = new JPanel();
            linhaCodePanel.setLayout(new FlowLayout());
            linhaCodePanel.add(code);
            linhaCodePanel.add(mensagemErro);
            confirmaCodigo.addActionListener(this);

            botoesBaixo = new JPanel(new FlowLayout());

            voltar = new JButton("Voltar");
            voltar.addActionListener(this);
            cancelar = new JButton("Cancelar");
            cancelar.addActionListener(this);
            botoesBaixo.add(voltar);
            botoesBaixo.add(cancelar);
            cancelar.setVisible(false);

            painelAtendimento.add(campoEvento);
            painelAtendimento.add(tituloevento);
            painelAtendimento.add(linhaCodePanel);
            painelAtendimento.add(confirmaCodigo);

            linhacod = new JPanel();
            linhacod.setLayout(new FlowLayout());
            codigoAtendimento = new JTextField(10);
            linhacod.add(new JLabel("Insira o Código do Atendimento"));
            linhacod.add(codigoAtendimento);
            linhacod.setForeground(Color.WHITE);
            linhacod.setVisible(false);
            painelAtendimento.add(linhacod);



            linhadata = new JPanel();
            linhadata.setLayout(new FlowLayout());
            dataAtendimento = new JTextField("dd/mm/aaaa", 10);
            linhadata.add(new JLabel("Insira a data de início"));
            linhadata.add(dataAtendimento);
            linhadata.setForeground(Color.WHITE);
            linhadata.setVisible(false);
            painelAtendimento.add(linhadata);

            linhaduracao = new JPanel();
            linhaduracao.setLayout(new FlowLayout());
            duracaoAtendimento = new JTextField(3);
            linhaduracao.add(new JLabel("Insira a duração em dias"));
            linhaduracao.add(duracaoAtendimento);
            linhaduracao.setForeground(Color.WHITE);
            linhaduracao.setVisible(false);
            painelAtendimento.add(linhaduracao);


            confirmaCadastro = new JButton("Confirmar Cadastro");
            botoesBaixo.add(confirmaCadastro);
            confirmaCadastro.setVisible(false);

            confirmaCadastro.addActionListener(this);
            Mensagem = new JLabel("");
            painelAtendimento.add(Mensagem);
            painelAtendimento.add(botoesBaixo);

            janelaAtendimento.getContentPane().add(painelAtendimento);
            janelaAtendimento.setVisible(true);




        }

        public void mostraAtendimento(){
            linhacod.setVisible(true);
            linhadata.setVisible(true);
            linhaduracao.setVisible(true);
            confirmaCadastro.setVisible(true);
            cancelar.setVisible(true);
        }

        public void cancelaAtendimento(){
            linhacod.setVisible(false);
            linhadata.setVisible(false);
            linhaduracao.setVisible(false);
            confirmaCadastro.setVisible(false);
            cancelar.setVisible(false);
        }



        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == voltar) {
                janelaAtendimento.dispose();
                menuVisivel();
            } else if (e.getSource() == cancelar) {
                cancelaAtendimento();
                confirmaCadastro.setVisible(false);
            }
            else if (e.getSource() == confirmaCodigo) {
                Mensagem.setText("");
                boolean eventoEncontrado = false;
                if(eventos.isEmpty()){
                    mensagemErro.setText("Erro: Nenhum evento cadastrado");
                }
                for (Evento x : eventos) {
                    if (x.getCodigo().equals(code.getText())) {
                        eventoEncontrado = true;
                        for (Atendimento a : atendimentos) {
                            if (a.getEvento().equals(x)) {
                                mensagemErro.setText("Erro: Evento já está cadastrado em um atendimento");
                                return;
                            }
                        }
                        mensagemErro.setText("");
                        mostraAtendimento();
                        eventoCadastro = x;
                        break;
                    }
                }
                if (!eventoEncontrado) {
                    mensagemErro.setText("Erro: Não há nenhum evento com esse código");
                }
            } else if (e.getSource() == confirmaCadastro) {
                int cod;
                try {
                    cod = Integer.parseInt(codigoAtendimento.getText());
                } catch (NumberFormatException n) {
                    Mensagem.setText("Erro: o código tem que ser composto de números inteiro");
                    return;
                }

                for (Atendimento b : atendimentos) {
                    if (b.getCod() == cod) {
                        Mensagem.setText("Erro: código já cadastrado");
                        return;
                    }
                }

                int duracao;
                try {
                    duracao = Integer.parseInt(duracaoAtendimento.getText());
                } catch (NumberFormatException n) {
                    Mensagem.setText("Erro: a duração tem que ser composta de números inteiro");
                    return;
                }

                String dataString = dataAtendimento.getText();
                try {
                    LocalDate data = LocalDate.parse(dataString, formatter);
                    Mensagem.setText("");
                } catch (DateTimeParseException ex) {
                    Mensagem.setText("Erro: a data não está no formato correto (dd/MM/yyyy)");
                    return;
                }

                atendimentos.add(new Atendimento(cod, dataString, duracao, "PENDENTE", null, eventoCadastro));
                Mensagem.setText("Atendimento Cadastrado com sucesso!");
                code.setText("");
                cancelaAtendimento();

            }
        }
    }






    public class AppEquipamento extends JFrame{
        private JButton mostrarButton;
        private JButton finalizarButton;
        private JButton cadastrarButton;
        private JButton limparButton;
        private JTextArea cadastrosTextArea;
        private JScrollPane scrollCadastros;
        private JComboBox tipoComboBox;
        private JTextField idTextField;
        private JTextField nomeTextField;
        private JTextField custoTextField;
        private JTextField capacidadeBarcoTextField;
        private JTextField capacidadeTanqueTextField;
        private JTextField cargaTextField;
        private JLabel idLabel;
        private JLabel nomeLabel;
        private JLabel custoLabel;
        private JComboBox combustivelComboBox;
        private JLabel capacidadeBarcoLabel;
        private JLabel capacidadeTanqueLabel;
        private JLabel combustivelLabel;
        private JLabel cargaLabel;
        private JLabel tipoLabel;
        private JLabel titulo;
        private int selecaoTipo;
        private ArrayList<Equipamento> equipamentos;

        public AppEquipamento(ArrayList<Equipamento> equipamentos){
            super();
            Locale.setDefault(Locale.ENGLISH);
            this.equipamentos = equipamentos;

            //COMBO BOX CONTENTS
            String[] tipoContent = {"Barco", "Caminhao Tanque", "Escavadeira"};
            String[] combustivelContent = {"Alcool", "Diesel", "Gasolina"};

            //CRIA FONTES
            Font fonteTitulo = new Font("Rockwell",Font.BOLD,24);

            //COMPONENTES
            titulo = new JLabel("CADASTRA EQUIPAMENTOS");
            titulo.setFont(fonteTitulo);

            tipoLabel = new JLabel("Tipo");
            tipoComboBox = new JComboBox<>(tipoContent);

            idLabel = new JLabel("Id");
            idTextField = new JTextField(10);
            nomeLabel = new JLabel("Nome");
            nomeTextField = new JTextField(30);
            custoLabel = new JLabel("Custo");
            custoTextField = new JTextField(10);

            capacidadeBarcoLabel = new JLabel("Capacidade Barco");
            capacidadeBarcoTextField = new JTextField(10);
            //
            capacidadeTanqueLabel = new JLabel("Capacidade Tanque");
            capacidadeTanqueTextField = new JTextField(15);
            //
            combustivelLabel = new JLabel("Combustivel");
            combustivelComboBox = new JComboBox<>(combustivelContent);
            cargaLabel = new JLabel("Carga");
            cargaTextField = new JTextField(15);

            cadastrosTextArea = new JTextArea(10, 30);
            cadastrosTextArea.setEditable(false);
            cadastrosTextArea.setFocusable(false);
            scrollCadastros = new JScrollPane(cadastrosTextArea);

            mostrarButton = new JButton("Mostrar");
            cadastrarButton = new JButton("Cadstrar");
            limparButton = new JButton("Limpar");
            finalizarButton = new JButton("Voltar");

            //CRIA LAYOUTS
            BorderLayout borderLayout = new BorderLayout();
            FlowLayout flowLayout = new FlowLayout();


            //CRIA JANELA
            this.setTitle("Cadastra Equipamentos");

            //LAYOUT DO PANEL PRINCIPAL BORDER
            JPanel container = new JPanel();
            container.setLayout(borderLayout);



            //LAYOUT CONTAINER BOX UP(NO BORDER NORTH) - FLOW LAYOUT
            JPanel containerBoxUp = new JPanel();
            containerBoxUp.setLayout(flowLayout);

            containerBoxUp.add(tipoLabel);
            containerBoxUp.add(tipoComboBox);
            containerBoxUp.add(idLabel);
            containerBoxUp.add(idTextField);
            containerBoxUp.add(nomeLabel);
            containerBoxUp.add(nomeTextField);


            //LAYOUT CONTAINER BOX DOWN(NO BORDER NORTH) - FLOW LAYOUT
            JPanel containerBoxDown = new JPanel();
            containerBoxDown.setLayout(flowLayout);

            containerBoxDown.add(custoLabel);
            containerBoxDown.add(custoTextField);
            containerBoxDown.add(capacidadeBarcoLabel);
            containerBoxDown.add(capacidadeBarcoTextField);
            containerBoxDown.add(capacidadeTanqueLabel);
            containerBoxDown.add(capacidadeTanqueTextField);
            containerBoxDown.add(combustivelLabel);
            containerBoxDown.add(combustivelComboBox);
            containerBoxDown.add(cargaLabel);
            containerBoxDown.add(cargaTextField);

            //LAYOUT NORTE DO BORDER(BOX LAYOUT)
            JPanel containerNorth = new JPanel();
            containerNorth.setLayout(new BoxLayout(containerNorth, BoxLayout.Y_AXIS));
            containerNorth.add(titulo);
            containerNorth.add(containerBoxUp);
            containerNorth.add(containerBoxDown);

            //LAYOUT CENTER DO BORDER
            JPanel containerCenter = new JPanel();
            containerCenter.setLayout(new BorderLayout());
            containerCenter.add(scrollCadastros);

            //LAYOUT SOUTH DO BORDER
            JPanel containerSouth = new JPanel();
            containerSouth.setLayout(flowLayout);
            containerSouth.add(mostrarButton);
            containerSouth.add(cadastrarButton);
            containerSouth.add(limparButton);
            containerSouth.add(finalizarButton);

            //ADICIONA NO CONTAINER PRINCIPAL
            container.add(containerNorth, BorderLayout.NORTH);
            container.add(containerCenter, BorderLayout.CENTER);
            container.add(containerSouth, BorderLayout.SOUTH);

            //INICIA O APP
            mostraBarco();
            selecaoTipo = 0;
            this.add(container);
            this.pack();
            this.setVisible(true);

            //INICIAR FULLSCREEN
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);


            //ACTION LISTENERS


            tipoComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selecaoTipo = tipoComboBox.getSelectedIndex();
                    if(selecaoTipo == 0){mostraBarco();}
                    else if(selecaoTipo == 1){mostraCaminhao();}
                    else{mostraEscavadeira();}
                }
            });

            cadastrarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == cadastrarButton){
                        boolean certo = true;
                        //ID
                        String idString = idTextField.getText();
                        int id = 0;
                        try{
                            id = Integer.parseInt(idString);
                            if(id < 0){
                                certo = false;
                                cadastrosTextArea.append("ERRO: Campo ID nao pode ser negativo" + "\n");
                            }
                        }catch (Exception exc){
                            certo = false;
                            cadastrosTextArea.append("ERRO: Campo ID precisa ser numero inteiro" + "\n");
                        }
                        //NOME
                        String nome = nomeTextField.getText();
                        if(nome.isBlank()){
                            certo = false;
                            cadastrosTextArea.append("ERRO: Campo nome não pode ser nulo" + "\n");
                        }
                        //CUSTO DIA
                        String custoString = custoTextField.getText();
                        double custoDia = 0;
                        try{
                            custoDia = Double.parseDouble(custoString);
                            if(custoDia < 0){
                                certo = false;
                                cadastrosTextArea.append("ERRO: Campo custo nao pode ser negativo" + "\n");
                            }
                        }catch (Exception exc){
                            certo = false;
                            cadastrosTextArea.append("ERRO: Campo custo precisa ser numero real (Modelo Americano)" + "\n");
                        }
                        if(selecaoTipo == 0){
                            int capacidadeBarco = 0;
                            String capBarcoString = capacidadeBarcoTextField.getText();
                            try{
                                capacidadeBarco = Integer.parseInt(capBarcoString);
                                if(capacidadeBarco < 0){
                                    certo = false;
                                    cadastrosTextArea.append("ERRO: Campo capacidade nao pode ser negativo" + "\n");
                                }
                            }catch (Exception exc){
                                certo = false;
                                cadastrosTextArea.append("ERRO: Campo capacidade precisa ser numero inteiro" + "\n");
                            }
                            if(certo){
                                if(procuraID(id)){
                                    codigoDuplicado();
                                }
                                else{
                                    Barco barco = new Barco(id,nome,custoDia,capacidadeBarco);
                                    equipamentos.add(barco);
                                    cadastrosTextArea.append("Equipamento cadastrado: " + barco + "\n");
                                }
                            }
                        }
                        else if(selecaoTipo == 1){
                            double capacidadeTanque = 0;
                            String capTanqueString = capacidadeTanqueTextField.getText();
                            try{
                                capacidadeTanque = Double.parseDouble(capTanqueString);
                                if(capacidadeTanque < 0){
                                    certo = false;
                                    cadastrosTextArea.append("ERRO: Campo capacidade nao pode ser negativo" + "\n");
                                }
                            }catch (Exception exc){
                                certo = false;
                                cadastrosTextArea.append("ERRO: Campo capacidade precisa ser numero real (Modelo Americano)" + "\n");
                            }
                            if(certo){
                                if(procuraID(id)){
                                    codigoDuplicado();
                                }
                                else{
                                    CaminhaoTanque caminhaoTanque = new CaminhaoTanque(id,nome,custoDia,capacidadeTanque);
                                    equipamentos.add(caminhaoTanque);
                                    cadastrosTextArea.append("Equipamento cadastrado: " + caminhaoTanque + "\n");
                                }
                            }
                        }
                        else if(selecaoTipo == 2){
                            Combustivel combustivel = null;
                            try{
                                combustivel = Combustivel.getEnum(combustivelComboBox.getSelectedIndex());
                            }
                            catch (Exception exc){
                                combustivel = Combustivel.ALCOOL;
                            }


                            double carga = 0.0;
                            String cargaString = cargaTextField.getText();
                            try{
                                carga = Double.parseDouble(cargaString);
                                if(carga < 0){
                                    certo = false;
                                    cadastrosTextArea.append("ERRO: Campo carga nao pode ser negativo" + "\n");
                                }
                            }catch (Exception exc){
                                certo = false;
                                cadastrosTextArea.append("ERRO: Campo carga precisa ser numero real (Modelo Americano)" + "\n");
                            }
                            if(certo){
                                if(procuraID(id)){
                                    codigoDuplicado();
                                }
                                else{
                                    Escavadeira escavadeira = new Escavadeira(id,nome,custoDia,combustivel,carga);
                                    equipamentos.add(escavadeira);
                                    cadastrosTextArea.append("Equipamento cadastrado: " + escavadeira + "\n");
                                }
                            }
                        }
                        sortByID();
                    }
                }
            });
            mostrarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == mostrarButton){
                        mostraCadastros();
                    }
                }
            });

            limparButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == limparButton){
                        limpaCadastros();
                    }
                }
            });

            finalizarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == finalizarButton){
                        fechaAplicativo();
                        menuVisivel();

                    }
                }
            });

        }

        private void mostraBarco(){
            capacidadeTanqueLabel.setVisible(false);
            capacidadeTanqueTextField.setVisible(false);
            combustivelLabel.setVisible(false);
            combustivelComboBox.setVisible(false);
            cargaLabel.setVisible(false);
            cargaTextField.setVisible(false);

            capacidadeBarcoLabel.setVisible(true);
            capacidadeBarcoTextField.setVisible(true);
        }

        private void mostraCaminhao(){
            capacidadeBarcoLabel.setVisible(false);
            capacidadeBarcoTextField.setVisible(false);
            combustivelLabel.setVisible(false);
            combustivelComboBox.setVisible(false);
            cargaLabel.setVisible(false);
            cargaTextField.setVisible(false);

            capacidadeTanqueLabel.setVisible(true);
            capacidadeTanqueTextField.setVisible(true);
        }

        private void mostraEscavadeira(){
            capacidadeBarcoLabel.setVisible(false);
            capacidadeBarcoTextField.setVisible(false);
            capacidadeTanqueLabel.setVisible(false);
            capacidadeTanqueTextField.setVisible(false);

            combustivelLabel.setVisible(true);
            combustivelComboBox.setVisible(true);
            cargaLabel.setVisible(true);
            cargaTextField.setVisible(true);
        }

        private boolean procuraID(int id){
            for(Equipamento equip: equipamentos){
                if(equip.getId() == id){
                    return true;
                }
            }
            return false;
        }

        private void codigoDuplicado(){
            cadastrosTextArea.append("ERRO: ID já cadastrado" + "\n");
        }


        private void mostraCadastros(){
            if(equipamentos.isEmpty()){
                cadastrosTextArea.append("ERRO: Nenhum equipamento cadastrado\n");
                return;
            }
            for(Equipamento equip : equipamentos){
                cadastrosTextArea.append(equip.toString() + "\n");
            }
        }

        private void limpaCadastros(){
            cadastrosTextArea.setText("");
            capacidadeTanqueTextField.setText("");
            capacidadeBarcoTextField.setText("");
            custoTextField.setText("");
            idTextField.setText("");
            cargaTextField.setText("");
            nomeTextField.setText("");
            combustivelComboBox.setSelectedIndex(0);
        }

        private void fechaAplicativo(){
            dispose();
        }

        private void sortByID(){
            Collections.sort(equipamentos);
        }

    }

    private void abreCadastroAtendimento(){
        cadastraAtendimento = new CadastraAtendimento();
    }

    private void iniciarAppEvento() {
        appEvento = new AppEvento(eventos);
    }
    private void iniciarAppEquipamento(){
        appEquipamento = new AppEquipamento(equipamentos);
    }
    private void iniciarAppEquipe(){
        appEquipe = new AppEquipe(equipes);
    }

    private void abreVincularEquipamento(){
        vincularEquipamento = new VincularEquipamento(equipamentos,equipes);
    }

    private void abreCarregaArquivos(){
        carregaArquivos = new CarregaArquivos();
    }

    private void abreCarregaArquivosIniciais(){
        carregaArquivosInicial = new CarregaArquivosInicial();
    }

    private void abreSalvaArquivos(){
        salvaArquivos = new SalvaArquivos();
    }


    //metodo pra fazer acmeRescue se tornar executavel (pra testar)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ACMERescue();
            }
        });
    }


    private class AppEquipe extends JFrame implements ActionListener {
        private JTextField codinome, quantidade, latitude, longitude;
        private JButton botao, fechar, limpar, crescente;
        private JTextArea area;
        private ArrayList<Equipe> equipes;

        public AppEquipe(ArrayList<Equipe> equipes) {
            super();
            this.equipes = equipes;
            equipes = new ArrayList<>();
            codinome = new JTextField(20);
            quantidade = new JTextField(20);
            latitude = new JTextField(20);
            longitude = new JTextField(20);
            botao = new JButton("Cadastrar");
            fechar = new JButton("Voltar");
            limpar = new JButton("Limpar");
            crescente = new JButton("Mostrar");
            Color color = new Color(211,211,211);
            Border border = BorderFactory.createLineBorder(color, 12);
            area = new JTextArea(5,10);
            area.setBorder(border);

            area.setEditable(false);
            area.setFocusable(false);

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
                    area.append(eq.toString() + "\n");
                }
            }
            else if(e.getSource() == fechar) {
                appEquipe.dispose();
                menuVisivel();
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



    private class AppEvento extends JFrame implements ActionListener {

        private JTextField code, dia, mes, longitude, latitude, velocidade, precipitacao, magnitude, estiagem;
        private JButton terremoto, ciclone, seca, limpar, confirmar, mostrar, finalizar;
        private JLabel labelCodigo, labelDia, labelMes, labelLatitude, labelLongitude, labelVelocidade, labelPrecipitacao, labelMagnitude, labelEstiagem;
        private JTextArea mensagemArea;
        private int atual = 0;
        private ArrayList<Evento> eventos;

        public AppEvento(ArrayList<Evento> eventos) {
            super();
            this.eventos = eventos;

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
            finalizar = new JButton("Voltar para o Menu");


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

        public void limpar() {
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
            if (e.getSource() == confirmar) {
                if (atual == 0) {
                    mensagemArea.append("Erro ao cadastrar: Selecione um Evento, Não há tipo de evento. \n");
                } else if (atual == 1) {
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
                    } else if (la < -90|| la > 90) {
                        mensagemArea.append("Erro ao cadastrar: Latitude deve estar entre 0 e 90.\n");
                    } else if (lo < -180 || lo > 180) {
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
                } else if (atual == 2) {
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
                    } else if (la < -90 || la > 90) {
                        mensagemArea.append("Erro ao cadastrar: Latitude deve estar entre 0 e 90.\n");
                    } else if (lo < -180 || lo > 180) {
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
                } else {
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
                    } else if (la < -90 || la > 90) {
                        mensagemArea.append("Erro ao cadastrar: Latitude deve estar entre 0 e 90.\n");
                    } else if (lo < -180 || lo > 180) {
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


            if (e.getSource() == mostrar) {
                String texto = "";
                for (Evento x : eventos) {
                    texto += x.toString() + "\n";
                }
                mensagemArea.append("\nEventos cadastrados: \n " + texto);
            }
            if (e.getSource() == limpar) {
                limpar();

            } else if (e.getSource() == finalizar) {
                appEvento.dispose();
                menuVisivel();
            }
        }
    }


    @Override //action do acmeRescue
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cEvento) {
            iniciarAppEvento();
            this.setVisible(false);

        }else if (e.getSource()==cEquipamento){
            iniciarAppEquipamento();
            this.setVisible(false);
        } else if (e.getSource()==cEquipe){
            iniciarAppEquipe();
            this.setVisible(false);
        }else if (e.getSource() == cAtendimento){
            abreCadastroAtendimento();
            this.setVisible(false);
        }else if (e.getSource() == vEquipamento){
            abreVincularEquipamento();
            this.setVisible(false);
        }else if (e.getSource() == carregaDados){
            abreCarregaArquivos();
            this.setVisible(false);
        }else if(e.getSource() == salvaDados){
            abreSalvaArquivos();
            this.setVisible(false);
        } else if(e.getSource() == consultaAtendimento) {
            consultaTodosAtendimentos();
        }else if(e.getSource() == aAtendimento){
            alocarAtendimentosAutomaticamente();
        }else if(e.getSource() == mRelatorio){
            relatorioGeral();
        }else if (e.getSource() == alteraAtendimento) {
           alteraStatusAtendimento();
        } else if (e.getSource() == finalizaSistema){
            this.dispose();
        }
    }

    private void relatorioGeral(){
        if (eventos.isEmpty() && equipamentos.isEmpty() && equipes.isEmpty() && atendimentos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Erro: Nenhum dado cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            StringBuilder relatorio = new StringBuilder("Relatório Geral:\n\n");

            relatorio.append("Eventos:\n");
            for (Evento evento : eventos) {
                relatorio.append(evento.toString()).append("\n");
            }
            relatorio.append("\n");

            relatorio.append("Equipamentos:\n");
            for (Equipamento equipamento : equipamentos) {
                relatorio.append(equipamento.toString()).append("\n");
            }
            relatorio.append("\n");

            relatorio.append("Equipes:\n");
            for (Equipe equipe : equipes) {
                relatorio.append(equipe.toString()).append("\n");
            }
            relatorio.append("\n");

            relatorio.append("Atendimentos:\n");
            for (Atendimento atendimento : atendimentos) {
                relatorio.append(atendimento.toString()).append("\n");
            }

            JTextArea relatorioTextArea = new JTextArea(relatorio.toString());
            relatorioTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(relatorioTextArea);
            scrollPane.setPreferredSize(new Dimension(600, 400));
            JOptionPane.showMessageDialog(this, scrollPane, "Relatório Geral", JOptionPane.INFORMATION_MESSAGE);
        }

    }


    private void alocarAtendimentosAutomaticamente() {
        Queue<Atendimento> atendimentosPendentes = new LinkedList<>();
        for (Atendimento a : atendimentos){
            if (a.getStatus().equals("PENDENTE")){
                atendimentosPendentes.add(a);
            }
        }
        if(atendimentosPendentes.isEmpty())  JOptionPane.showMessageDialog(this, "Erro: Não há atendimentos pendentes.", "Erro", JOptionPane.ERROR_MESSAGE);
        for (Atendimento atendimento : atendimentosPendentes) {
            boolean equipeAlocada = false;

            for (Equipe equipe : equipes) {
                if (atendimento.calcularDistancia(equipe.getLatitude(), equipe.getLongitude(), atendimento.getEvento().getLatitude(),atendimento.getEvento().getLongitude()) < 5000) {

                    atendimento.setEquipe(equipe);
                    atendimento.setStatus("EXECUTANDO");
                    equipeAlocada = true;
                    break;
                }
            }
            if (!equipeAlocada) {
                atendimento.setStatus("CANCELADO");
            }
        }
    }

    private void alteraStatusAtendimento(){
        String codigoInput = JOptionPane.showInputDialog(this, "Digite o código do atendimento:");

        if (codigoInput == null) {
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoInput);

            Atendimento atendimentoSelecionado = null;
            for (Atendimento atendimento : atendimentos) {
                if (atendimento.getCod() == codigo) {
                    atendimentoSelecionado = atendimento;
                    break;
                }
            }

            if (atendimentoSelecionado == null) {
                JOptionPane.showMessageDialog(this, "Erro: Não há atendimento com o código indicado.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                String dadosAtendimento = "Dados do Atendimento:\n" + "Código do Atendimento: " + atendimentoSelecionado.getCod() + "\n" +
                        "Data de Início: " + atendimentoSelecionado.getDataInicio() + "\n" +
                        "Duração em Dias: " + atendimentoSelecionado.getDuracao() + "\n" +
                        "Status: " + atendimentoSelecionado.getStatus() + "\n";

                JTextArea areaTexto = new JTextArea(dadosAtendimento);
                areaTexto.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(areaTexto);

                String novaSituacao = JOptionPane.showInputDialog(this, dadosAtendimento + "\nDigite a nova situação do atendimento:");

                if (novaSituacao == null) {
                    return;
                }

                if (atendimentoSelecionado.getStatus().equals("FINALIZADO")) {
                    JOptionPane.showMessageDialog(this, "Erro: Atendimento já está FINALIZADO e não pode ser alterado.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else if(atendimentoSelecionado.getStatus().equals(novaSituacao)) {
                    JOptionPane.showMessageDialog(this, "Erro: Atendimento já está " + novaSituacao, "Erro", JOptionPane.ERROR_MESSAGE);
                } else if (!novaSituacao.equals("CANCELADO") && !novaSituacao.equals("PENDENTE") && !novaSituacao.equals("EXECUTANDO") && !novaSituacao.equals("FINALIZADO")) {
                    JOptionPane.showMessageDialog(this, "Erro: A nova situação tem que ser uma dessas: PENDENTE, EXECUTANDO, FINALIZADO, CANCELADO", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    atendimentoSelecionado.setStatus(novaSituacao);
                    JOptionPane.showMessageDialog(this, "Situação do atendimento atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Código do atendimento deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultaTodosAtendimentos(){
        if (atendimentos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Erro: Não há atendimentos cadastrados.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {

            StringBuilder resultadoConsulta = new StringBuilder("Atendimentos Cadastrados:\n");

            for (Atendimento atendimento : atendimentos) {

                resultadoConsulta.append("Código do Atendimento: ").append(atendimento.getCod()).append("\n");
                resultadoConsulta.append("Data de Início: ").append(atendimento.getDataInicio()).append("\n");
                resultadoConsulta.append("Duração em Dias: ").append(atendimento.getDuracao()).append("\n");
                resultadoConsulta.append("Status: ").append(atendimento.getStatus()).append("\n");

                Evento evento = atendimento.getEvento();
                resultadoConsulta.append("Detalhes do Evento:\n").append(atendimento.getEvento().toString());

                Equipe equipe = atendimento.getEquipe();
                if (equipe != null) {
                    resultadoConsulta.append("\nEquipe Alocada:\n").append(equipe.toString());


                    ArrayList<Equipamento> equipamentos = equipe.getEquipamentos();
                    if (!equipamentos.isEmpty()) {
                        resultadoConsulta.append("Equipamentos da Equipe:\n");
                        for (Equipamento equipamento : equipamentos) {
                            resultadoConsulta.append(" - ").append(equipamento.toString()).append("\n");
                        }
                    } else {
                        resultadoConsulta.append("Esta equipe não possui equipamentos cadastrados.\n");
                    }

                    resultadoConsulta.append("Custo da Equipe: ").append(equipe.getCustoEquipe() * atendimento.getDuracao()).append("\n");
                } else {
                    resultadoConsulta.append("\nEste atendimento não possui uma equipe alocada.\n");
                }

                resultadoConsulta.append("\n-----------------\n"); // Separador entre atendimentos
            }

            JTextArea areaTexto = new JTextArea(resultadoConsulta.toString());
            areaTexto.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(areaTexto);
            JOptionPane.showMessageDialog(this, scrollPane, "Consulta de Atendimentos", JOptionPane.INFORMATION_MESSAGE);
        }

    }



    private void mostrarEventosCadastrados() {
        StringBuilder eventosStr = new StringBuilder("Eventos Cadastrados:\n");
        for (Evento evento : eventos) {
            eventosStr.append(evento.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, eventosStr.toString(), "Eventos Cadastrados", JOptionPane.INFORMATION_MESSAGE);
    }
    private void menuVisivel(){
        this.setVisible(true);
    }

    private class VincularEquipamento extends JFrame implements ActionListener {
        private JPanel container;
        private JList listEquipamentos, listEquipes;
        private JScrollPane scrollEquipamentos, scrollEquipes;
        private JTextArea console;
        private JButton vincular, finalizar, limparConsole;

        VincularEquipamento(ArrayList<Equipamento> equipamentos, ArrayList<Equipe> equipes) {
            super();
            if (!equipamentos.isEmpty() && !equipes.isEmpty()) {

                //JLists
                DefaultListModel<String> stringEquipamentos = new DefaultListModel<String>();
                DefaultListModel<String> stringEquipes = new DefaultListModel<String>();
                for (Equipamento equipamento : equipamentos) {
                    stringEquipamentos.addElement(equipamento.getNome());
                }
                for (Equipe equipe : equipes) {
                    stringEquipes.addElement(equipe.getCodinome());
                }
                listEquipes = new JList<String>(stringEquipes);
                listEquipamentos = new JList<String>(stringEquipamentos);

                //LAYOUTS
                BorderLayout borderLayout = new BorderLayout();
                FlowLayout flowLayout = new FlowLayout();

                //Container
                container = new JPanel();
                container.setLayout(borderLayout);
                this.add(container);

                //Border Layout West
                scrollEquipamentos = new JScrollPane(listEquipamentos);
                scrollEquipamentos.setMinimumSize(new Dimension(200, 200));
                scrollEquipamentos.setPreferredSize(new Dimension(300, 200));
                container.add(scrollEquipamentos, BorderLayout.WEST);

                //Border Layout East
                scrollEquipes = new JScrollPane(listEquipes);
                scrollEquipes.setMinimumSize(new Dimension(200, 200));
                scrollEquipes.setPreferredSize(new Dimension(300, 200));
                container.add(scrollEquipes, BorderLayout.EAST);

                //Border Layout Center
                console = new JTextArea();
                console.setEditable(false);
                console.setFocusable(false);
                container.add(console, BorderLayout.CENTER);

                //Border Layout South
                JPanel borderSouth = new JPanel();
                borderSouth.setLayout(flowLayout);
                vincular = new JButton("Vincular");
                limparConsole = new JButton("Limpar");
                finalizar = new JButton("Voltar");
                borderSouth.add(vincular);
                borderSouth.add(limparConsole);
                borderSouth.add(finalizar);
                container.add(borderSouth, BorderLayout.SOUTH);


                //VISIVEL/PACK
                this.setVisible(true);
                this.setSize(1000, 800);
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);

                //DEBBUG
                listEquipamentos.setSelectedIndex(0);
                listEquipes.setSelectedIndex(0);

                vincular.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == vincular) {
                            Equipe equipe = equipes.get(listEquipes.getSelectedIndex());
                            Equipamento equipamento = equipamentos.get(listEquipamentos.getSelectedIndex());
                            if (equipamento.getEquipe() == null) {
                                equipamento.setEquipe(equipe);
                                equipe.addEquipamento(equipamento);
                            } else {
                                console.append("ERRO, EQUIPAMENTO JA VINCULADO A OUTRA EQUIPE!\n");
                            }
                        }
                    }
                });

                limparConsole.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == limparConsole) {
                            console.setText("");
                            listEquipamentos.setSelectedIndex(0);
                            listEquipes.setSelectedIndex(0);
                        }
                    }
                });

                finalizar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == finalizar) {
                            menuVisivel();
                            dispose();
                        }
                    }
                });
            } else {
                container = new JPanel();
                finalizar = new JButton("Voltar");
                console = new JTextArea();
                container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
                container.add(console);
                container.add(Box.createVerticalStrut(10));
                container.add(finalizar);
                console.setEditable(false);
                console.setFocusable(false);
                console.setText("ERRO, EQUIPAMENTO OU EQUIPE NÃO FORAM CADASTRADAS AINDA. \n " +
                        "PARA TER ACESSO AO VINCULAR EQUIPAMENTOS, POR FAVOR, \n " +
                        "ADICIONE PELO MENOS 1 EQUIPE E " +
                        "1 EQUIPAMENTO");
                this.add(container);
                finalizar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == finalizar) {
                            menuVisivel();
                            dispose();
                        }
                    }
                });

                this.setVisible(true);
                this.pack();
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }
    public class CarregaArquivos extends JFrame implements ActionListener{
        private JTextField nomeArquivo;
        private JLabel labelNomeArquivo;
        private JButton confirmaNome, voltar, limpaTerminal, json;
        private JPanel container;
        private JScrollPane scrollCadastros;
        private JTextArea cadastros;
        CarregaArquivos(){
            super();

            //LAYOUTS
            BorderLayout borderLayout = new BorderLayout();
            FlowLayout flowLayout = new FlowLayout();

            //CONTAINER
            container = new JPanel();
            container.setLayout(borderLayout);
            this.add(container);

            //BORDER NORTH
            JPanel borderNorth = new JPanel();
            borderNorth.setLayout(flowLayout);
            container.add(borderNorth, BorderLayout.NORTH);
            labelNomeArquivo = new JLabel("Nome arquivo: ");
            nomeArquivo = new JTextField(20);
            borderNorth.add(labelNomeArquivo);
            borderNorth.add(nomeArquivo);

            //BORDER CENTER
            cadastros = new JTextArea(20,50);
            cadastros.setEditable(false);
            cadastros.setFocusable(false);
            scrollCadastros = new JScrollPane(cadastros);
            container.add(scrollCadastros, BorderLayout.CENTER);

            //BORDER SOUTH
            JPanel borderSouth = new JPanel();
            borderSouth.setLayout(flowLayout);
            container.add(borderSouth, BorderLayout.SOUTH);
            confirmaNome = new JButton("CSV");
            json = new JButton("JSON");
            voltar = new JButton("Voltar");
            limpaTerminal = new JButton("Limpar");
            borderSouth.add(confirmaNome);
            borderSouth.add(json);
            borderSouth.add(voltar);
            borderSouth.add(limpaTerminal);

            confirmaNome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == confirmaNome){
                        try {
                            cadastraEventos(nomeArquivo.getText());
                        }catch (Exception exc){

                        }

                        try{
                            cadastraEquipe(nomeArquivo.getText());
                        }catch (Exception exc){

                        }

                        try{
                            cadastraEquipamentos(nomeArquivo.getText(), equipes);
                        }catch (Exception exc){

                        }
                        try {
                            cadastraAtendimentos(nomeArquivo.getText());
                        }catch (Exception exc){

                        }
                    }
                }
            });

            json.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == json){
                        try {
                            cadastraEventosJSON(nomeArquivo.getText());
                        }catch (Exception exc){

                        }

                        try{
                            cadastraEquipeJSON(nomeArquivo.getText());
                        }catch (Exception exc){

                        }

                        try{
                            cadastraEquipamentosJSON(nomeArquivo.getText(), equipes);
                        }catch (Exception exc){

                        }
                        try {
                            cadastraAtendimentosJSON(nomeArquivo.getText());
                        }catch (Exception exc){

                        }
                    }
                }
            });

            voltar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == voltar){
                        menuVisivel();
                        dispose();
                    }
                }
            });

            limpaTerminal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == limpaTerminal){
                        cadastros.setText("");
                    }
                }
            });

            //SET VISIBLE
            this.setVisible(true);
            this.setSize(1000,800);

        }
        @Override
        public void actionPerformed(ActionEvent e) {

        }
        private void cadastraEquipeJSON(String name){
            cadastra(name + "-EQUIPES.JSON");
            entrada.nextLine();
            StringBuilder linha = new StringBuilder();
            while(linha != null){
                String atributo = entrada.nextLine();
                while(!atributo.trim().equals("}") && !atributo.trim().equals("},")){
                    atributo = atributo.replace("\n", "");
                    if(!atributo.trim().equals("{")) {
                        linha.append(atributo);
                    }
                    atributo = entrada.nextLine();
                }
                boolean certo = true;
                int quantidade = 0;
                double latitude = 0.0;
                double longitude = 0.0;
                linha = new StringBuilder(linha.toString().replace(",", ":"));
                linha = new StringBuilder(linha.toString().replace("\"", " "));
                linha = new StringBuilder(linha.toString().replace("{", " "));
                linha = new StringBuilder(linha.toString().replace("}", " "));
                String[] equipeAtributos = linha.toString().split(":");
                String codinome = equipeAtributos[1].trim();
                String strQuantidade = equipeAtributos[3].trim();
                try{
                    quantidade = Integer.parseInt(strQuantidade);
                }catch (Exception e){
                    certo = false;
                }
                String strLatitude = equipeAtributos[5].trim();
                try{
                    latitude = Double.parseDouble(strLatitude);
                }catch (Exception e){
                    certo = false;
                }
                String strLongitude = equipeAtributos[7].trim();
                try{
                    longitude = Double.parseDouble(strLongitude);
                }catch (Exception e){
                    certo = false;
                }
                if(certo){
                    if(equipeUnica(codinome)){
                        Equipe equipe = new Equipe(codinome,quantidade,latitude,longitude);
                        equipes.add(equipe);
                        cadastros.append("Equipe: " + equipe.toString() + "\n");
                    }
                    else{
                        cadastros.append("CODINOME REPETIDO" + "\n");
                    }
                }
                linha = new StringBuilder(entrada.nextLine());
                if(linha.toString().equals("]")){
                    linha = null;
                }
            }
        }

        private void cadastraEquipamentosJSON(String name, ArrayList<Equipe> equipes){
            cadastra(name + "-EQUIPAMENTOS.JSON");
            entrada.nextLine();
            StringBuilder linha = new StringBuilder(entrada.nextLine());
            while(linha != null){
                String atributo = entrada.nextLine();
                while(!atributo.trim().equals("}") && !atributo.trim().equals("},")){
                    atributo = atributo.replace("\n", "");
                    if(!atributo.trim().equals("{")) {
                        linha.append(atributo);
                    }
                    atributo = entrada.nextLine();
                }
                boolean certo = true;
                linha = new StringBuilder(linha.toString().replace(",", ":"));
                linha = new StringBuilder(linha.toString().replace("\"", " "));
                linha = new StringBuilder(linha.toString().replace("{", " "));
                linha = new StringBuilder(linha.toString().replace("}", " "));
                String[] equipamentoAtributos = linha.toString().split(":");
                int id = 0;
                double custoDiario = 0.0;
                int tipo = 0;
                Combustivel combustivel = null;
                int capacidadeBarco = 0;
                double capacidadeTanque = 0.0;
                double carga = 0.0;
                Equipe equipe = null;
                String strId = equipamentoAtributos[1].trim();
                try{
                    id = Integer.parseInt(strId);
                }catch (Exception e){
                    certo = false;
                }
                String nome = equipamentoAtributos[3].trim();
                String strCusto = equipamentoAtributos[5].trim();
                try{
                    custoDiario = Double.parseDouble(strCusto);
                }catch (Exception e){
                    certo = false;
                }
                String codinome = equipamentoAtributos[7].trim();
                for(Equipe equi : equipes){
                    if(equi.getCodinome().equals(codinome)){
                        equipe = equi;
                    }
                }
                String strTipo = equipamentoAtributos[9].trim();
                tipo = Integer.parseInt(strTipo);
                if(tipo == 1){
                    String strCapBarco = equipamentoAtributos[11].trim();
                    try{
                        capacidadeBarco = Integer.parseInt(strCapBarco);
                    }catch (Exception e){
                        certo = false;
                    }
                    if(certo){
                        if(equipamentoUnico(id)){
                            Barco barco = new Barco(id,nome,custoDiario,equipe,capacidadeBarco);
                            equipamentos.add(barco);
                            cadastros.append(barco.toString() + "\n");
                        }
                        else{
                            cadastros.append("ID REPETIDO" + "\n");
                        }
                    }
                } else if (tipo == 2) {
                    String strCapTanque = equipamentoAtributos[11].trim();
                    try{
                        capacidadeTanque = Double.parseDouble(strCapTanque);
                    }catch (Exception e){
                        certo = false;
                    }
                    if(certo){
                        if(equipamentoUnico(id)){
                            CaminhaoTanque tanque = new CaminhaoTanque(id,nome,custoDiario,equipe,capacidadeTanque);
                            equipamentos.add(tanque);
                            cadastros.append(tanque.toString() + "\n");
                        }
                        else{
                            cadastros.append("ID REPETIDO" + "\n");
                        }

                    }
                } else if (tipo == 3) {
                    String strCombustivel = equipamentoAtributos[11].trim();
                    try{
                        for(Combustivel c : Combustivel.values()){
                            if(c.getCombustivel().toUpperCase().equals(strCombustivel)){
                                combustivel = c;
                            }
                        }
                    }catch (Exception e){
                        certo = false;
                    }
                    String strCarga = equipamentoAtributos[13].trim();
                    try {
                        carga = Double.parseDouble(strCarga);
                    }catch (Exception e){
                        certo = false;
                    }
                    if(certo){
                        if(equipamentoUnico(id)){
                            Escavadeira escavadeira = new Escavadeira(id,nome,custoDiario,equipe,combustivel,carga);
                            equipamentos.add(escavadeira);
                            cadastros.append(escavadeira.toString() + "\n");
                        }
                        else{
                            cadastros.append("ID REPETIDO" + "\n");
                        }
                    }
                }
                linha = new StringBuilder(entrada.nextLine());
                if(linha.toString().equals("]")){
                    linha = null;
                }
            }
        }


        private void cadastraEventosJSON(String nome){
            cadastra(nome + "-EVENTOS.JSON");
            entrada.nextLine();
            StringBuilder linha = new StringBuilder(entrada.nextLine());
            while (linha != null) {
                String atributo = entrada.nextLine();
                while(!atributo.trim().equals("}") && !atributo.trim().equals("},")){
                    atributo = atributo.replace("\n", "");
                    if(!atributo.trim().equals("{")) {
                        linha.append(atributo);
                    }
                    atributo = entrada.nextLine();
                }
                linha = new StringBuilder(linha.toString().replace(",", ":"));
                linha = new StringBuilder(linha.toString().replace("\"", " "));
                linha = new StringBuilder(linha.toString().replace("{", " "));
                linha = new StringBuilder(linha.toString().replace("}", " "));
                String[] atributosEvento = linha.toString().split(":");
                boolean certo = true;
                double latitude = 0.0;
                double longitude = 0.0;
                int tipo = 0;
                double velocidade = 0.0;
                double precipitacao = 0.0;
                double magnitude = 0.0;
                int estiagem = 0;
                String codigo = atributosEvento[1].trim();
                String data = atributosEvento[3].trim();
                String strLatitude = atributosEvento[5].trim();
                try {
                    latitude = Double.parseDouble(strLatitude);
                } catch (Exception e) {
                    cadastros.append("Erro em latitude");
                    certo = false;
                }
                String strLongitude = atributosEvento[7].trim();
                try {
                    longitude = Double.parseDouble(strLongitude);
                } catch (Exception e) {
                    cadastros.append("Erro em longitude");
                    certo = false;
                }
                String strTipo = atributosEvento[9].trim();
                if (strTipo.equals("1")) {
                    String strVelocidade = atributosEvento[11].trim();
                    try {
                        velocidade = Double.parseDouble(strVelocidade);
                    } catch (Exception e) {
                        cadastros.append("Erro em velocidade");
                        certo = false;
                    }
                    String strPrecipitacao = atributosEvento[13].trim();
                    try {
                        precipitacao = Double.parseDouble(strPrecipitacao);
                    } catch (Exception e) {
                        cadastros.append("Erro em precipitacao");
                        certo = false;
                    }
                    if (certo) {
                        if (eventoUnico(codigo)) {
                            Ciclone ciclone = new Ciclone(codigo, data, latitude, longitude, velocidade, precipitacao);
                            eventos.add(ciclone);
                            cadastros.append(ciclone.toString() + "\n");
                        } else {
                            cadastros.append("CODIGO REPETIDO" + "\n");
                        }

                    } else {
                        cadastros.append("ERRADO" + "\n");
                    }
                } else if (strTipo.equals("2")) {
                    String strMagnitude = atributosEvento[11].trim();
                    try {
                        magnitude = Double.parseDouble(strMagnitude);
                    } catch (Exception e) {
                        certo = false;
                    }
                    if (certo) {
                        if (eventoUnico(codigo)) {
                            Terremoto terremoto = new Terremoto(codigo, data, latitude, longitude, magnitude);
                            eventos.add(terremoto);
                            cadastros.append(terremoto.toString() + "\n");
                        } else {
                            cadastros.append("CODIGO REPETIDO" + "\n");
                        }
                    }
                } else if (strTipo.equals("3")) {
                    String strEstiagem = atributosEvento[11].trim();
                    try {
                        estiagem = Integer.parseInt(strEstiagem);
                    } catch (Exception e) {
                        certo = false;
                    }
                    if (certo) {
                        if (eventoUnico(codigo)) {
                            Seca seca = new Seca(codigo, data, latitude, longitude, estiagem);
                            eventos.add(seca);
                            cadastros.append(seca.toString() + "\n");
                        } else {
                            cadastros.append("CODIGO REPETIDO" + "\n");
                        }
                    }
                }
                linha = new StringBuilder(entrada.nextLine());
                if(linha.toString().equals("]")){
                    linha = null;
                }
            }
        }

        private void cadastraAtendimentosJSON(String nome){
            cadastra(nome + "-ATENDIMENTOS.JSON");
            entrada.nextLine();
            StringBuilder linha = new StringBuilder(entrada.nextLine());
            while (linha != null) {
                String atributo = entrada.nextLine();
                while(!atributo.trim().equals("}") && !atributo.trim().equals("},")){
                    atributo = atributo.replace("\n", "");
                    if(!atributo.trim().equals("{")) {
                        linha.append(atributo);
                    }
                    atributo = entrada.nextLine();
                }
                linha = new StringBuilder(linha.toString().replace(",", ":"));
                linha = new StringBuilder(linha.toString().replace("\"", " "));
                linha = new StringBuilder(linha.toString().replace("{", " "));
                linha = new StringBuilder(linha.toString().replace("}", " "));
                String[] atendimentoAtributos = linha.toString().split(":");
                boolean certo = true;
                int cod = 0;
                int duracao = 0;
                Evento evento = null;
                String strCod = atendimentoAtributos[1].trim();
                try {
                    cod = Integer.parseInt(strCod);
                } catch (Exception e) {
                    certo = false;
                }
                String dataInicio = atendimentoAtributos[3].trim();
                String strDuracao = atendimentoAtributos[5].trim();
                try {
                    duracao = Integer.parseInt(strDuracao);
                } catch (NumberFormatException n) {
                    certo = false;
                }
                String status = atendimentoAtributos[7].trim();
                String strEvento = atendimentoAtributos[9].trim();
                for (Evento ev : eventos) {
                    if (ev.getCodigo().equals(strEvento)) {
                        evento = ev;
                    }
                }
                if (certo) {
                    if(atendimentoUnico(cod)){
                        Atendimento atendimento = new Atendimento(cod, dataInicio, duracao, status, null, evento);
                        atendimentos.add(atendimento);
                        cadastros.append(atendimento.toString() + "\n");
                    }else{
                        cadastros.append("CODIGO REPETIDO" + "\n");
                    }
                }
                linha = new StringBuilder(entrada.nextLine());
                if(linha.toString().equals("]")){
                    linha = null;
                }
            }
        }

        private boolean eventoUnico (String codigo){
            for (Evento evento : eventos) {
                if (evento.getCodigo().equals(codigo)) {
                    return false;
                }
            }
            return true;
        }

        private boolean equipamentoUnico ( int id){
            for (Equipamento equipamento : equipamentos) {
                if (equipamento.getId() == id) {
                    return false;
                }
            }
            return true;
        }

        private boolean equipeUnica (String codinome){
            for (Equipe equipe : equipes) {
                if (equipe.getCodinome().equals(codinome)) {
                    return false;
                }
            }
            return true;
        }

        private boolean atendimentoUnico (int cod){
            for (Atendimento atend : atendimentos) {
                if (atend.getCod() == cod) {
                    return false;
                }
            }
            return true;
        }

        private void cadastra (String path){
            try {
                BufferedReader streamEntrada = new BufferedReader(new FileReader(path));
                entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
            } catch (Exception e) {
                cadastros.append("ERRO DE LEITURA: " + e.getMessage() + "\n");
            }
            Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
            entrada.useLocale(Locale.ENGLISH);
        }

        private void cadastraEquipe(String name){
            cadastra(name + "-EQUIPES.CSV");
            entrada.nextLine();
            String linha = entrada.nextLine();
            while(linha != null){
                boolean certo = true;
                int quantidade = 0;
                double latitude = 0.0;
                double longitude = 0.0;
                String[] equipeAtributos = linha.split(";");
                String codinome = equipeAtributos[0];
                String strQuantidade = equipeAtributos[1];
                try{
                    quantidade = Integer.parseInt(strQuantidade);
                }catch (Exception e){
                    certo = false;
                }
                String strLatitude = equipeAtributos[2];
                try{
                    latitude = Double.parseDouble(strLatitude);
                }catch (Exception e){
                    certo = false;
                }
                String strLongitude = equipeAtributos[3];
                try{
                    longitude = Double.parseDouble(strLongitude);
                }catch (Exception e){
                    certo = false;
                }
                if(certo){
                    if(equipeUnica(codinome)){
                        Equipe equipe = new Equipe(codinome,quantidade,latitude,longitude);
                        equipes.add(equipe);
                        cadastros.append("Equipe: " + equipe.toString() + "\n");
                    }
                    else{
                        cadastros.append("CODINOME REPETIDO" + "\n");
                    }
                }
                if(entrada.hasNextLine()){
                    linha = entrada.nextLine();
                }else {
                    linha = null;
                }
            }
        }

        private void cadastraEquipamentos(String name, ArrayList<Equipe> equipes){
            cadastra(name + "-EQUIPAMENTOS.CSV");
            entrada.nextLine();
            String linha = entrada.nextLine();
            while(linha != null){
                boolean certo = true;
                String[] equipamentoAtributos = linha.split(";");
                int id = 0;
                double custoDiario = 0.0;
                int tipo = 0;
                Combustivel combustivel = null;
                int capacidadeBarco = 0;
                double capacidadeTanque = 0.0;
                double carga = 0.0;
                Equipe equipe = null;
                String strId = equipamentoAtributos[0];
                try{
                    id = Integer.parseInt(strId);
                }catch (Exception e){
                    certo = false;
                }
                String nome = equipamentoAtributos[1];
                String strCusto = equipamentoAtributos[2];
                try{
                    custoDiario = Double.parseDouble(strCusto);
                }catch (Exception e){
                    certo = false;
                }
                String codinome = equipamentoAtributos[3];
                for(Equipe equi : equipes){
                    if(equi.getCodinome().equals(codinome)){
                        equipe = equi;
                    }
                }
                String strTipo = equipamentoAtributos[4];
                tipo = Integer.parseInt(strTipo);
                if(tipo == 1){
                    String strCapBarco = equipamentoAtributos[5];
                    try{
                        capacidadeBarco = Integer.parseInt(strCapBarco);
                    }catch (Exception e){
                        certo = false;
                    }
                    if(certo){
                        if(equipamentoUnico(id)){
                            Barco barco = new Barco(id,nome,custoDiario,equipe,capacidadeBarco);
                            equipamentos.add(barco);
                            cadastros.append(barco.toString() + "\n");
                        }
                        else{
                            cadastros.append("ID REPETIDO" + "\n");
                        }
                    }
                } else if (tipo == 2) {
                    String strCapTanque = equipamentoAtributos[5];
                    try{
                        capacidadeTanque = Double.parseDouble(strCapTanque);
                    }catch (Exception e){
                        certo = false;
                    }
                    if(certo){
                        if(equipamentoUnico(id)){
                            CaminhaoTanque tanque = new CaminhaoTanque(id,nome,custoDiario,equipe,capacidadeTanque);
                            equipamentos.add(tanque);
                            cadastros.append(tanque.toString() + "\n");
                        }
                        else{
                            cadastros.append("ID REPETIDO" + "\n");
                        }

                    }
                } else if (tipo == 3) {
                    String strCombustivel = equipamentoAtributos[5];
                    try{
                        for(Combustivel c : Combustivel.values()){
                            if(c.getCombustivel().toUpperCase().equals(strCombustivel)){
                                combustivel = c;
                            }
                        }
                    }catch (Exception e){
                        certo = false;
                    }
                    String strCarga = equipamentoAtributos[6];
                    try {
                        carga = Double.parseDouble(strCarga);
                    }catch (Exception e){
                        certo = false;
                    }
                    if(certo){
                        if(equipamentoUnico(id)){
                            Escavadeira escavadeira = new Escavadeira(id,nome,custoDiario,equipe,combustivel,carga);
                            equipamentos.add(escavadeira);
                            cadastros.append(escavadeira.toString() + "\n");
                        }
                        else{
                            cadastros.append("ID REPETIDO" + "\n");
                        }
                    }
                }
                if(entrada.hasNextLine()){
                    linha = entrada.nextLine();
                }else {
                    linha = null;
                }
            }
        }


        private void cadastraEventos (String nome){
            cadastra(nome + "-EVENTOS.CSV");
            entrada.nextLine();
            String linha = entrada.nextLine();
            while (linha != null) {
                String[] atributosEvento = linha.split(";");
                boolean certo = true;
                double latitude = 0.0;
                double longitude = 0.0;
                int tipo = 0;
                double velocidade = 0.0;
                double precipitacao = 0.0;
                double magnitude = 0.0;
                int estiagem = 0;
                String codigo = atributosEvento[0];
                String data = atributosEvento[1];
                String strLatitude = atributosEvento[2];
                try {
                    latitude = Double.parseDouble(strLatitude);
                } catch (Exception e) {
                    certo = false;
                }
                String strLongitude = atributosEvento[3];
                try {
                    longitude = Double.parseDouble(strLongitude);
                } catch (Exception e) {
                    certo = false;
                }
                String strTipo = atributosEvento[4];
                if (strTipo.equals("1")) {
                    String strVelocidade = atributosEvento[5];
                    try {
                        velocidade = Double.parseDouble(strVelocidade);
                    } catch (Exception e) {
                        certo = false;
                        cadastros.append("ERRO NA VELOCIDADE");
                    }
                    String strPrecipitacao = atributosEvento[6];
                    try {
                        precipitacao = Double.parseDouble(strPrecipitacao);
                    } catch (Exception e) {
                        certo = false;
                    }
                    if (certo) {
                        if (eventoUnico(codigo)) {
                            Ciclone ciclone = new Ciclone(codigo, data, latitude, longitude, velocidade, precipitacao);
                            eventos.add(ciclone);
                            cadastros.append(ciclone.toString() + "\n");
                        } else {
                            cadastros.append("CODIGO REPETIDO" + "\n");
                        }
                    }
                } else if (strTipo.equals("2")) {
                    String strMagnitude = atributosEvento[5];
                    try {
                        magnitude = Double.parseDouble(strMagnitude);
                    } catch (Exception e) {
                        certo = false;
                    }
                    if (certo) {
                        if (eventoUnico(codigo)) {
                            Terremoto terremoto = new Terremoto(codigo, data, latitude, longitude, magnitude);
                            eventos.add(terremoto);
                            cadastros.append(terremoto.toString() + "\n");
                        } else {
                            cadastros.append("CODIGO REPETIDO" + "\n");
                        }
                    }
                } else if (strTipo.equals("3")) {
                    String strEstiagem = atributosEvento[5];
                    try {
                        estiagem = Integer.parseInt(strEstiagem);
                    } catch (Exception e) {
                        certo = false;
                    }
                    if (certo) {
                        if (eventoUnico(codigo)) {
                            Seca seca = new Seca(codigo, data, latitude, longitude, estiagem);
                            eventos.add(seca);
                            cadastros.append(seca.toString() + "\n");
                        } else {
                            cadastros.append("CODIGO REPETIDO" + "\n");
                        }
                    }
                }
                if (entrada.hasNextLine()) {
                    linha = entrada.nextLine();
                } else {
                    linha = null;
                }
            }
        }

        private void cadastraAtendimentos (String nome){
            cadastra(nome + "-ATENDIMENTOS.CSV");
            entrada.nextLine();
            String linha = entrada.nextLine();
            while (linha != null) {
                String[] atendimentoAtributos = linha.split(";");
                boolean certo = true;
                int cod = 0;
                int duracao = 0;
                Evento evento = null;
                String strCod = atendimentoAtributos[0];
                try {
                    cod = Integer.parseInt(strCod);
                } catch (Exception e) {
                    certo = false;
                }
                String dataInicio = atendimentoAtributos[1];
                String strDuracao = atendimentoAtributos[2];
                try {
                    duracao = Integer.parseInt(strDuracao);
                } catch (NumberFormatException n) {
                    certo = false;
                }
                String status = atendimentoAtributos[3];
                String strEvento = atendimentoAtributos[4];
                for (Evento ev : eventos) {
                    if (ev.getCodigo().equals(strEvento)) {
                        evento = ev;
                    }
                }
                if (certo) {
                    if(atendimentoUnico(cod)){
                        Atendimento atendimento = new Atendimento(cod, dataInicio, duracao, status, null, evento);
                        atendimentos.add(atendimento);
                        cadastros.append(atendimento.toString() + "\n");
                    }else{
                        cadastros.append("CODIGO REPETIDO" + "\n");
                    }
                }
                if (entrada.hasNextLine()) {
                    linha = entrada.nextLine();
                } else {
                    linha = null;
                }
            }
        }

    }
    public class CarregaArquivosInicial extends JFrame implements ActionListener{
        private JTextField nomeArquivo;
        private JLabel labelNomeArquivo;
        private JButton confirmaNome, voltar, limpaTerminal, json;
        private JPanel container;
        private JScrollPane scrollCadastros;
        private JTextArea cadastros;
        CarregaArquivosInicial(){
            super();

            //LAYOUTS
            BorderLayout borderLayout = new BorderLayout();
            FlowLayout flowLayout = new FlowLayout();

            //CONTAINER
            container = new JPanel();
            container.setLayout(borderLayout);
            this.add(container);

            //BORDER NORTH
            JPanel borderNorth = new JPanel();
            borderNorth.setLayout(flowLayout);
            container.add(borderNorth, BorderLayout.NORTH);
            labelNomeArquivo = new JLabel("Nome arquivo: ");
            nomeArquivo = new JTextField(20);
            borderNorth.add(labelNomeArquivo);
            borderNorth.add(nomeArquivo);

            //BORDER CENTER
            cadastros = new JTextArea(20,50);
            cadastros.setEditable(false);
            cadastros.setFocusable(false);
            scrollCadastros = new JScrollPane(cadastros);
            container.add(scrollCadastros, BorderLayout.CENTER);

            //BORDER SOUTH
            JPanel borderSouth = new JPanel();
            borderSouth.setLayout(flowLayout);
            container.add(borderSouth, BorderLayout.SOUTH);
            confirmaNome = new JButton("CSV");
            voltar = new JButton("Ir para Menu");
            limpaTerminal = new JButton("Limpar");
            json = new JButton("JSON");
            borderSouth.add(confirmaNome);
            borderSouth.add(json);
            borderSouth.add(voltar);
            borderSouth.add(limpaTerminal);
            this.setTitle("Cadastro de dados iniciais");

            confirmaNome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == confirmaNome){
                        try {
                            cadastraEventos(nomeArquivo.getText());
                        }catch (Exception exc){

                        }

                        try{
                            cadastraEquipe(nomeArquivo.getText());
                        }catch (Exception exc){

                        }

                        try{
                            cadastraEquipamentos(nomeArquivo.getText(), equipes);
                        }catch (Exception exc){

                        }
                        try {
                            cadastraAtendimentos(nomeArquivo.getText());
                        }catch (Exception exc){

                        }
                    }
                }
            });

            json.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == json){
                        try {
                            cadastraEventosJSON(nomeArquivo.getText());
                        }catch (Exception exc){

                        }

                        try{
                            cadastraEquipeJSON(nomeArquivo.getText());
                        }catch (Exception exc){

                        }

                        try{
                            cadastraEquipamentosJSON(nomeArquivo.getText(), equipes);
                        }catch (Exception exc){

                        }
                        try {
                            cadastraAtendimentosJSON(nomeArquivo.getText());
                        }catch (Exception exc){

                        }
                    }
                }
            });

            voltar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == voltar){
                        menuVisivel();
                        dispose();
                    }
                }
            });

            limpaTerminal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == limpaTerminal){
                        cadastros.setText("");
                    }
                }
            });

            //SET VISIBLE
            this.setVisible(true);
            this.setSize(1000,800);

        }
        private void cadastraEquipeJSON(String name){
            cadastra(name + "-EQUIPES.JSON");
            entrada.nextLine();
            StringBuilder linha = new StringBuilder();
            while(linha != null){
                String atributo = entrada.nextLine();
                while(!atributo.trim().equals("}") && !atributo.trim().equals("},")){
                    atributo = atributo.replace("\n", "");
                    if(!atributo.trim().equals("{")) {
                        linha.append(atributo);
                    }
                    atributo = entrada.nextLine();
                }
                boolean certo = true;
                int quantidade = 0;
                double latitude = 0.0;
                double longitude = 0.0;
                linha = new StringBuilder(linha.toString().replace(",", ":"));
                linha = new StringBuilder(linha.toString().replace("\"", " "));
                linha = new StringBuilder(linha.toString().replace("{", " "));
                linha = new StringBuilder(linha.toString().replace("}", " "));
                String[] equipeAtributos = linha.toString().split(":");
                String codinome = equipeAtributos[1].trim();
                String strQuantidade = equipeAtributos[3].trim();
                try{
                    quantidade = Integer.parseInt(strQuantidade);
                }catch (Exception e){
                    certo = false;
                }
                String strLatitude = equipeAtributos[5].trim();
                try{
                    latitude = Double.parseDouble(strLatitude);
                }catch (Exception e){
                    certo = false;
                }
                String strLongitude = equipeAtributos[7].trim();
                try{
                    longitude = Double.parseDouble(strLongitude);
                }catch (Exception e){
                    certo = false;
                }
                if(certo){
                    if(equipeUnica(codinome)){
                        Equipe equipe = new Equipe(codinome,quantidade,latitude,longitude);
                        equipes.add(equipe);
                        cadastros.append("Equipe: " + equipe.toString() + "\n");
                    }
                    else{
                        cadastros.append("CODINOME REPETIDO" + "\n");
                    }
                }
                linha = new StringBuilder(entrada.nextLine());
                if(linha.toString().equals("]")){
                    linha = null;
                }
            }
        }

        private void cadastraEquipamentosJSON(String name, ArrayList<Equipe> equipes){
            cadastra(name + "-EQUIPAMENTOS.JSON");
            entrada.nextLine();
            StringBuilder linha = new StringBuilder(entrada.nextLine());
            while(linha != null){
                String atributo = entrada.nextLine();
                while(!atributo.trim().equals("}") && !atributo.trim().equals("},")){
                    atributo = atributo.replace("\n", "");
                    if(!atributo.trim().equals("{")) {
                        linha.append(atributo);
                    }
                    atributo = entrada.nextLine();
                }
                boolean certo = true;
                linha = new StringBuilder(linha.toString().replace(",", ":"));
                linha = new StringBuilder(linha.toString().replace("\"", " "));
                linha = new StringBuilder(linha.toString().replace("{", " "));
                linha = new StringBuilder(linha.toString().replace("}", " "));
                String[] equipamentoAtributos = linha.toString().split(":");
                int id = 0;
                double custoDiario = 0.0;
                int tipo = 0;
                Combustivel combustivel = null;
                int capacidadeBarco = 0;
                double capacidadeTanque = 0.0;
                double carga = 0.0;
                Equipe equipe = null;
                String strId = equipamentoAtributos[1].trim();
                try{
                    id = Integer.parseInt(strId);
                }catch (Exception e){
                    certo = false;
                }
                String nome = equipamentoAtributos[3].trim();
                String strCusto = equipamentoAtributos[5].trim();
                try{
                    custoDiario = Double.parseDouble(strCusto);
                }catch (Exception e){
                    certo = false;
                }
                String codinome = equipamentoAtributos[7].trim();
                for(Equipe equi : equipes){
                    if(equi.getCodinome().equals(codinome)){
                        equipe = equi;
                    }
                }
                String strTipo = equipamentoAtributos[9].trim();
                tipo = Integer.parseInt(strTipo);
                if(tipo == 1){
                    String strCapBarco = equipamentoAtributos[11].trim();
                    try{
                        capacidadeBarco = Integer.parseInt(strCapBarco);
                    }catch (Exception e){
                        certo = false;
                    }
                    if(certo){
                        if(equipamentoUnico(id)){
                            Barco barco = new Barco(id,nome,custoDiario,equipe,capacidadeBarco);
                            equipamentos.add(barco);
                            cadastros.append(barco.toString() + "\n");
                        }
                        else{
                            cadastros.append("ID REPETIDO" + "\n");
                        }
                    }
                } else if (tipo == 2) {
                    String strCapTanque = equipamentoAtributos[11].trim();
                    try{
                        capacidadeTanque = Double.parseDouble(strCapTanque);
                    }catch (Exception e){
                        certo = false;
                    }
                    if(certo){
                        if(equipamentoUnico(id)){
                            CaminhaoTanque tanque = new CaminhaoTanque(id,nome,custoDiario,equipe,capacidadeTanque);
                            equipamentos.add(tanque);
                            cadastros.append(tanque.toString() + "\n");
                        }
                        else{
                            cadastros.append("ID REPETIDO" + "\n");
                        }

                    }
                } else if (tipo == 3) {
                    String strCombustivel = equipamentoAtributos[11].trim();
                    try{
                        for(Combustivel c : Combustivel.values()){
                            if(c.getCombustivel().toUpperCase().equals(strCombustivel)){
                                combustivel = c;
                            }
                        }
                    }catch (Exception e){
                        certo = false;
                    }
                    String strCarga = equipamentoAtributos[13].trim();
                    try {
                        carga = Double.parseDouble(strCarga);
                    }catch (Exception e){
                        certo = false;
                    }
                    if(certo){
                        if(equipamentoUnico(id)){
                            Escavadeira escavadeira = new Escavadeira(id,nome,custoDiario,equipe,combustivel,carga);
                            equipamentos.add(escavadeira);
                            cadastros.append(escavadeira.toString() + "\n");
                        }
                        else{
                            cadastros.append("ID REPETIDO" + "\n");
                        }
                    }
                }
                linha = new StringBuilder(entrada.nextLine());
                if(linha.toString().equals("]")){
                    linha = null;
                }
            }
        }


        private void cadastraEventosJSON(String nome){
            cadastra(nome + "-EVENTOS.JSON");
            entrada.nextLine();
            StringBuilder linha = new StringBuilder(entrada.nextLine());
            while (linha != null) {
                String atributo = entrada.nextLine();
                while(!atributo.trim().equals("}") && !atributo.trim().equals("},")){
                    atributo = atributo.replace("\n", "");
                    if(!atributo.trim().equals("{")) {
                        linha.append(atributo);
                    }
                    atributo = entrada.nextLine();
                }
                linha = new StringBuilder(linha.toString().replace(",", ":"));
                linha = new StringBuilder(linha.toString().replace("\"", " "));
                linha = new StringBuilder(linha.toString().replace("{", " "));
                linha = new StringBuilder(linha.toString().replace("}", " "));
                String[] atributosEvento = linha.toString().split(":");
                boolean certo = true;
                double latitude = 0.0;
                double longitude = 0.0;
                int tipo = 0;
                double velocidade = 0.0;
                double precipitacao = 0.0;
                double magnitude = 0.0;
                int estiagem = 0;
                String codigo = atributosEvento[1].trim();
                String data = atributosEvento[3].trim();
                String strLatitude = atributosEvento[5].trim();
                try {
                    latitude = Double.parseDouble(strLatitude);
                } catch (Exception e) {
                    cadastros.append("Erro em latitude");
                    certo = false;
                }
                String strLongitude = atributosEvento[7].trim();
                try {
                    longitude = Double.parseDouble(strLongitude);
                } catch (Exception e) {
                    cadastros.append("Erro em longitude");
                    certo = false;
                }
                String strTipo = atributosEvento[9].trim();
                if (strTipo.equals("1")) {
                    String strVelocidade = atributosEvento[11].trim();
                    try {
                        velocidade = Double.parseDouble(strVelocidade);
                    } catch (Exception e) {
                        cadastros.append("Erro em velocidade");
                        certo = false;
                    }
                    String strPrecipitacao = atributosEvento[13].trim();
                    try {
                        precipitacao = Double.parseDouble(strPrecipitacao);
                    } catch (Exception e) {
                        cadastros.append("Erro em precipitacao");
                        certo = false;
                    }
                    if (certo) {
                        if (eventoUnico(codigo)) {
                            Ciclone ciclone = new Ciclone(codigo, data, latitude, longitude, velocidade, precipitacao);
                            eventos.add(ciclone);
                            cadastros.append(ciclone.toString() + "\n");
                        } else {
                            cadastros.append("CODIGO REPETIDO" + "\n");
                        }

                    } else {
                        cadastros.append("ERRADO" + "\n");
                    }
                } else if (strTipo.equals("2")) {
                    String strMagnitude = atributosEvento[11].trim();
                    try {
                        magnitude = Double.parseDouble(strMagnitude);
                    } catch (Exception e) {
                        certo = false;
                    }
                    if (certo) {
                        if (eventoUnico(codigo)) {
                            Terremoto terremoto = new Terremoto(codigo, data, latitude, longitude, magnitude);
                            eventos.add(terremoto);
                            cadastros.append(terremoto.toString() + "\n");
                        } else {
                            cadastros.append("CODIGO REPETIDO" + "\n");
                        }
                    }
                } else if (strTipo.equals("3")) {
                    String strEstiagem = atributosEvento[11].trim();
                    try {
                        estiagem = Integer.parseInt(strEstiagem);
                    } catch (Exception e) {
                        certo = false;
                    }
                    if (certo) {
                        if (eventoUnico(codigo)) {
                            Seca seca = new Seca(codigo, data, latitude, longitude, estiagem);
                            eventos.add(seca);
                            cadastros.append(seca.toString() + "\n");
                        } else {
                            cadastros.append("CODIGO REPETIDO" + "\n");
                        }
                    }
                }
                linha = new StringBuilder(entrada.nextLine());
                if(linha.toString().equals("]")){
                    linha = null;
                }
            }
        }

        private void cadastraAtendimentosJSON(String nome){
            cadastra(nome + "-ATENDIMENTOS.JSON");
            entrada.nextLine();
            StringBuilder linha = new StringBuilder(entrada.nextLine());
            while (linha != null) {
                String atributo = entrada.nextLine();
                while(!atributo.trim().equals("}") && !atributo.trim().equals("},")){
                    atributo = atributo.replace("\n", "");
                    if(!atributo.trim().equals("{")) {
                        linha.append(atributo);
                    }
                    atributo = entrada.nextLine();
                }
                linha = new StringBuilder(linha.toString().replace(",", ":"));
                linha = new StringBuilder(linha.toString().replace("\"", " "));
                linha = new StringBuilder(linha.toString().replace("{", " "));
                linha = new StringBuilder(linha.toString().replace("}", " "));
                String[] atendimentoAtributos = linha.toString().split(":");
                boolean certo = true;
                int cod = 0;
                int duracao = 0;
                Evento evento = null;
                String strCod = atendimentoAtributos[1].trim();
                try {
                    cod = Integer.parseInt(strCod);
                } catch (Exception e) {
                    certo = false;
                }
                String dataInicio = atendimentoAtributos[3].trim();
                String strDuracao = atendimentoAtributos[5].trim();
                try {
                    duracao = Integer.parseInt(strDuracao);
                } catch (NumberFormatException n) {
                    certo = false;
                }
                String status = atendimentoAtributos[7].trim();
                String strEvento = atendimentoAtributos[9].trim();
                for (Evento ev : eventos) {
                    if (ev.getCodigo().equals(strEvento)) {
                        evento = ev;
                    }
                }
                if (certo) {
                    if(atendimentoUnico(cod)){
                        Atendimento atendimento = new Atendimento(cod, dataInicio, duracao, status, null, evento);
                        atendimentos.add(atendimento);
                        cadastros.append(atendimento.toString() + "\n");
                    }else{
                        cadastros.append("CODIGO REPETIDO" + "\n");
                    }
                }
                linha = new StringBuilder(entrada.nextLine());
                if(linha.toString().equals("]")){
                    linha = null;
                }
            }
        }
        @Override
        public void actionPerformed(ActionEvent e) {

        }

        private void cadastraEquipe(String name){
            cadastra(name + "-EQUIPES.CSV");
            entrada.nextLine();
            String linha = entrada.nextLine();
            while(linha != null){
                boolean certo = true;
                int quantidade = 0;
                double latitude = 0.0;
                double longitude = 0.0;
                String[] equipeAtributos = linha.split(";");
                String codinome = equipeAtributos[0];
                String strQuantidade = equipeAtributos[1];
                try{
                    quantidade = Integer.parseInt(strQuantidade);
                }catch (Exception e){
                    certo = false;
                }
                String strLatitude = equipeAtributos[2];
                try{
                    latitude = Double.parseDouble(strLatitude);
                }catch (Exception e){
                    certo = false;
                }
                String strLongitude = equipeAtributos[3];
                try{
                    longitude = Double.parseDouble(strLongitude);
                }catch (Exception e){
                    certo = false;
                }
                if(certo){
                    if(equipeUnica(codinome)){
                        Equipe equipe = new Equipe(codinome,quantidade,latitude,longitude);
                        equipes.add(equipe);
                        cadastros.append("Equipe: " + equipe.toString() + "\n");
                    }
                    else{
                        cadastros.append("CODINOME REPETIDO" + "\n");
                    }
                }
                if(entrada.hasNextLine()){
                    linha = entrada.nextLine();
                }else {
                    linha = null;
                }
            }
        }

        private void cadastraEquipamentos(String name, ArrayList<Equipe> equipes){
            cadastra(name + "-EQUIPAMENTOS.CSV");
            entrada.nextLine();
            String linha = entrada.nextLine();
            while(linha != null){
                boolean certo = true;
                String[] equipamentoAtributos = linha.split(";");
                int id = 0;
                double custoDiario = 0.0;
                int tipo = 0;
                Combustivel combustivel = null;
                int capacidadeBarco = 0;
                double capacidadeTanque = 0.0;
                double carga = 0.0;
                Equipe equipe = null;
                String strId = equipamentoAtributos[0];
                try{
                    id = Integer.parseInt(strId);
                }catch (Exception e){
                    certo = false;
                }
                String nome = equipamentoAtributos[1];
                String strCusto = equipamentoAtributos[2];
                try{
                    custoDiario = Double.parseDouble(strCusto);
                }catch (Exception e){
                    certo = false;
                }
                String codinome = equipamentoAtributos[3];
                for(Equipe equi : equipes){
                    if(equi.getCodinome().equals(codinome)){
                        equipe = equi;
                    }
                }
                String strTipo = equipamentoAtributos[4];
                tipo = Integer.parseInt(strTipo);
                if(tipo == 1){
                    String strCapBarco = equipamentoAtributos[5];
                    try{
                        capacidadeBarco = Integer.parseInt(strCapBarco);
                    }catch (Exception e){
                        certo = false;
                    }
                    if(certo){
                        if(equipamentoUnico(id)){
                            Barco barco = new Barco(id,nome,custoDiario,equipe,capacidadeBarco);
                            equipamentos.add(barco);
                            cadastros.append(barco.toString() + "\n");
                        }
                        else{
                            cadastros.append("ID REPETIDO" + "\n");
                        }
                    }
                } else if (tipo == 2) {
                    String strCapTanque = equipamentoAtributos[5];
                    try{
                        capacidadeTanque = Double.parseDouble(strCapTanque);
                    }catch (Exception e){
                        certo = false;
                    }
                    if(certo){
                        if(equipamentoUnico(id)){
                            CaminhaoTanque tanque = new CaminhaoTanque(id,nome,custoDiario,equipe,capacidadeTanque);
                            equipamentos.add(tanque);
                            cadastros.append(tanque.toString() + "\n");
                        }
                        else{
                            cadastros.append("ID REPETIDO" + "\n");
                        }

                    }
                } else if (tipo == 3) {
                    String strCombustivel = equipamentoAtributos[5];
                    try{
                        for(Combustivel c : Combustivel.values()){
                            if(c.getCombustivel().toUpperCase().equals(strCombustivel)){
                                combustivel = c;
                            }
                        }
                    }catch (Exception e){
                        certo = false;
                    }
                    String strCarga = equipamentoAtributos[6];
                    try {
                        carga = Double.parseDouble(strCarga);
                    }catch (Exception e){
                        certo = false;
                    }
                    if(certo){
                        if(equipamentoUnico(id)){
                            Escavadeira escavadeira = new Escavadeira(id,nome,custoDiario,equipe,combustivel,carga);
                            equipamentos.add(escavadeira);
                            cadastros.append(escavadeira.toString() + "\n");
                        }
                        else{
                            cadastros.append("ID REPETIDO" + "\n");
                        }
                    }
                }
                if(entrada.hasNextLine()){
                    linha = entrada.nextLine();
                }else {
                    linha = null;
                }
            }
        }

        private void cadastraEventos (String nome){
            cadastra(nome + "-EVENTOS.CSV");
            entrada.nextLine();
            String linha = entrada.nextLine();
            while (linha != null) {
                String[] atributosEvento = linha.split(";");
                boolean certo = true;
                double latitude = 0.0;
                double longitude = 0.0;
                int tipo = 0;
                double velocidade = 0.0;
                double precipitacao = 0.0;
                double magnitude = 0.0;
                int estiagem = 0;
                String codigo = atributosEvento[0];
                String data = atributosEvento[1];
                String strLatitude = atributosEvento[2];
                try {
                    latitude = Double.parseDouble(strLatitude);
                } catch (Exception e) {
                    certo = false;
                }
                String strLongitude = atributosEvento[3];
                try {
                    longitude = Double.parseDouble(strLongitude);
                } catch (Exception e) {
                    certo = false;
                }
                String strTipo = atributosEvento[4];
                if (strTipo.equals("1")) {
                    String strVelocidade = atributosEvento[5];
                    try {
                        velocidade = Double.parseDouble(strVelocidade);
                    } catch (Exception e) {
                        certo = false;
                        cadastros.append("ERRO NA VELOCIDADE");
                    }
                    String strPrecipitacao = atributosEvento[6];
                    try {
                        precipitacao = Double.parseDouble(strPrecipitacao);
                    } catch (Exception e) {
                        certo = false;
                    }
                    if (certo) {
                        if (eventoUnico(codigo)) {
                            Ciclone ciclone = new Ciclone(codigo, data, latitude, longitude, velocidade, precipitacao);
                            eventos.add(ciclone);
                            cadastros.append(ciclone.toString() + "\n");
                        } else {
                            cadastros.append("CODIGO REPETIDO" + "\n");
                        }
                    }
                } else if (strTipo.equals("2")) {
                    String strMagnitude = atributosEvento[5];
                    try {
                        magnitude = Double.parseDouble(strMagnitude);
                    } catch (Exception e) {
                        certo = false;
                    }
                    if (certo) {
                        if (eventoUnico(codigo)) {
                            Terremoto terremoto = new Terremoto(codigo, data, latitude, longitude, magnitude);
                            eventos.add(terremoto);
                            cadastros.append(terremoto.toString() + "\n");
                        } else {
                            cadastros.append("CODIGO REPETIDO" + "\n");
                        }
                    }
                } else if (strTipo.equals("3")) {
                    String strEstiagem = atributosEvento[5];
                    try {
                        estiagem = Integer.parseInt(strEstiagem);
                    } catch (Exception e) {
                        certo = false;
                    }
                    if (certo) {
                        if (eventoUnico(codigo)) {
                            Seca seca = new Seca(codigo, data, latitude, longitude, estiagem);
                            eventos.add(seca);
                            cadastros.append(seca.toString() + "\n");
                        } else {
                            cadastros.append("CODIGO REPETIDO" + "\n");
                        }
                    }
                }
                if (entrada.hasNextLine()) {
                    linha = entrada.nextLine();
                } else {
                    linha = null;
                }
            }
        }

        private void cadastraAtendimentos (String nome){
            cadastra(nome + "-ATENDIMENTOS.CSV");
            entrada.nextLine();
            String linha = entrada.nextLine();
            while (linha != null) {
                String[] atendimentoAtributos = linha.split(";");
                boolean certo = true;
                int cod = 0;
                int duracao = 0;
                Evento evento = null;
                String strCod = atendimentoAtributos[0];
                try {
                    cod = Integer.parseInt(strCod);
                } catch (Exception e) {
                    certo = false;
                }
                String dataInicio = atendimentoAtributos[1];
                String strDuracao = atendimentoAtributos[2];
                try {
                    duracao = Integer.parseInt(strDuracao);
                } catch (NumberFormatException n) {
                    certo = false;
                }
                String status = atendimentoAtributos[3];
                String strEvento = atendimentoAtributos[4];
                for (Evento ev : eventos) {
                    if (ev.getCodigo().equals(strEvento)) {
                        evento = ev;
                    }
                }
                if (certo) {
                    if(atendimentoUnico(cod)){
                        Atendimento atendimento = new Atendimento(cod, dataInicio, duracao, status, null, evento);
                        atendimentos.add(atendimento);
                        cadastros.append(atendimento.toString() + "\n");
                    }else{
                        cadastros.append("CODIGO REPETIDO" + "\n");
                    }
                }
                if (entrada.hasNextLine()) {
                    linha = entrada.nextLine();
                } else {
                    linha = null;
                }
            }
        }

        private boolean eventoUnico (String codigo){
            for (Evento evento : eventos) {
                if (evento.getCodigo().equals(codigo)) {
                    return false;
                }
            }
            return true;
        }

        private boolean equipamentoUnico ( int id){
            for (Equipamento equipamento : equipamentos) {
                if (equipamento.getId() == id) {
                    return false;
                }
            }
            return true;
        }

        private boolean equipeUnica (String codinome){
            for (Equipe equipe : equipes) {
                if (equipe.getCodinome().equals(codinome)) {
                    return false;
                }
            }
            return true;
        }

        private boolean atendimentoUnico (int cod){
            for (Atendimento atend : atendimentos) {
                if (atend.getCod() == cod) {
                    return false;
                }
            }
            return true;
        }

        private void cadastra (String path){
            try {
                BufferedReader streamEntrada = new BufferedReader(new FileReader(path));
                entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
            } catch (Exception e) {
                cadastros.append("ERRO DE LEITURA: " + e.getMessage() + "\n");
            }
            Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
            entrada.useLocale(Locale.ENGLISH);
        }
    }

    private class SalvaArquivos extends JFrame implements ActionListener{
        private JPanel container;
        private JButton csv,json,voltar;
        private JTextField nomeArquivo;
        private JLabel labelNome, titulo;
        private JTextArea console;
        private JScrollPane scrollConsole;
        SalvaArquivos(){
            //LAYOUT
            BorderLayout borderLayout = new BorderLayout();
            FlowLayout flowLayout = new FlowLayout();

            //CONTAINER
            container = new JPanel();
            this.add(container);
            container.setLayout(borderLayout);

            //BORDER NORTH 0
            JPanel containerNorth0 = new JPanel();
            containerNorth0.setLayout(new BoxLayout(containerNorth0, BoxLayout.Y_AXIS));
            voltar = new JButton("Voltar");
            containerNorth0.add(voltar);
            titulo = new JLabel();
            containerNorth0.add(titulo);

            //BORDER NORTH 1
            JPanel containerNorth1 = new JPanel();
            containerNorth1.setLayout(new BoxLayout(containerNorth1, BoxLayout.Y_AXIS));
            labelNome = new JLabel("Nome do Arquivo: ");
            containerNorth1.add(labelNome);
            nomeArquivo = new JTextField(20);
            containerNorth1.add(nomeArquivo);

            //BORDER NORTH
            JPanel containerNorth = new JPanel();
            containerNorth.add(containerNorth0);
            containerNorth.add(containerNorth1);
            container.add(containerNorth, BorderLayout.NORTH);

            //BORDER CENTER
            console = new JTextArea();
            scrollConsole = new JScrollPane(console);
            container.add(scrollConsole,BorderLayout.CENTER);

            //BORDER SOUTH
            JPanel containerSouth = new JPanel();
            containerSouth.setLayout(flowLayout);
            csv = new JButton("CSV");
            json = new JButton("JSON");
            containerSouth.add(csv);
            containerSouth.add(json);
            container.add(containerSouth, BorderLayout.SOUTH);

            //ACTIONS
            voltar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == voltar){
                        menuVisivel();
                        dispose();
                    }
                }
            });

            csv.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == csv){
                        try{
                            salvaCSVEventos(nomeArquivo.getText());
                        }catch (Exception exc){
                            System.out.println(e);
                        }
                        try{
                            salvaCSVEquipe(nomeArquivo.getText());
                        }catch (Exception exc){
                            System.out.println(e);
                        }
                        try{
                            salvaCSVEquipamentos(nomeArquivo.getText());
                        }catch (Exception exc){
                            System.out.println(e);
                        }
                        try{
                            salvaCSVAtendimento(nomeArquivo.getText());
                        }catch (Exception exc){
                            System.out.println(e);
                        }

                    }
                }
            });

            json.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == json){
                        try{
                            salvaJSONEventos(nomeArquivo.getText());
                        }catch (Exception exc){
                            System.out.println(e);
                        }
                        try{
                            salvaJSONEquipe(nomeArquivo.getText());
                        }catch (Exception exc){
                            System.out.println(e);
                        }
                        try{
                            salvaJSONEquipamentos(nomeArquivo.getText());
                        }catch (Exception exc){
                            System.out.println(e);
                        }
                        try{
                            salvaJSONAtendimento(nomeArquivo.getText());
                        }catch (Exception exc){
                            System.out.println(e);
                        }
                    }
                }
            });

            //SET VISIBLE
            this.setVisible(true);
            this.setSize(1000,800);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }

        private void salvaCSVAtendimento(String path){
            ArrayList<Atendimento> atend = new ArrayList<>(atendimentos);
            salvaCSV(path+"-ATENDIMENTOS.CSV",atend);
        }

        private void salvaCSVEquipe(String path){
            salvaCSV(path+"-EQUIPES.CSV",equipes);
        }

        private void salvaCSVEquipamentos(String path){
            salvaCSV(path+"-EQUIPAMENTOS.CSV",equipamentos);
        }

        private void salvaCSVEventos(String path){
            salvaCSV(path+"-EVENTOS.CSV",eventos);
        }
        private void salvaCSV(String path, ArrayList<?> lista){
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
                if(!lista.isEmpty()){
                    writer.write(((CSVConvertable) lista.get(0)).toCSVHeader() + "\n");
                }

                for (Object objeto : lista) {
                    CSVConvertable convertableObjeto = (CSVConvertable) objeto;
                    writer.write(convertableObjeto.toCSV() + "\n");
                }

                console.append("Dados salvos em: " + path + "\n");
            }catch (Exception e){
                console.append("Erro ao salvar dados em: "+ path +"\n");
            }
        }

        private void salvaJSONAtendimento(String path){
            ArrayList<Atendimento> atend = new ArrayList<>(atendimentos);
            salvaJSON(path+"-ATENDIMENTOS.JSON",atend);
        }

        private void salvaJSONEquipe(String path){
            salvaJSON(path+"-EQUIPES.JSON",equipes);
        }

        private void salvaJSONEquipamentos(String path){
            salvaJSON(path+"-EQUIPAMENTOS.JSON",equipamentos);
        }

        private void salvaJSONEventos(String path){
            salvaJSON(path+"-EVENTOS.JSON",eventos);
        }

        private void salvaJSON(String path, ArrayList<?> lista){
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
                writer.write("[\n");
                int tam = lista.size();
                int i = 0;
                for (Object objeto : lista) {
                    JSONConvertable objetoConvert = (JSONConvertable) objeto;
                    String objetoJSON = objetoConvert.toJSONObject();
                    writer.write(objetoJSON);
                    if (i < tam - 1) {
                        writer.write(",\n");
                    }
                    i++;
                }
                writer.write("\n]\n");
                console.append("Dados salvos em: " + path + "\n");
            }catch (Exception e){
                console.append("Erro ao salvar dados em: "+ path +"\n");
            }
        }
    }
}

