import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.Border;

public class ACMERescue extends JFrame implements ActionListener {
    private Scanner entrada = null;                 // Atributo para entrada de dados
    private PrintStream saidaPadrao = System.out;   // Guarda a saida padrao - tela (console)
    private ArrayList<Evento> eventos = new ArrayList<>();
    private JButton cEvento, cEquipe, cEquipamento, cAtendimento, mRelatorio, vEquipamento, aAtendimento;
    private JLabel titulo;

    public ACMERescue(){
        super();

        /** leitura de arquivo
         * try {
         *             BufferedReader streamEntrada = new BufferedReader(new FileReader("entrada.txt"));
         *             entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
         *             PrintStream streamSaida = new PrintStream(new File("saida.txt"), Charset.forName("UTF-8"));
         *             System.setOut(streamSaida);             // Usa como saida um arquivo
         *         } catch (Exception e) {
         *             System.out.println(e);
         *         }
         *         Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
         *         entrada.useLocale(Locale.ENGLISH);
         *
         */

        titulo = new JLabel("Menu de atendimento ACMERescue");
        cEvento =new JButton("Cadastrar Evento");


        JPanel painel = new JPanel();
        BoxLayout layout = new BoxLayout(painel, BoxLayout.Y_AXIS);
        painel.setLayout(layout);
        painel.add(titulo);
        painel.add(cEvento);
        this.add(painel);
        this.setTitle("MENU");
        this.setSize(400,300);
        this.setVisible(true);

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

    /**
     * public boolean cadastraEventos(Evento e) {
     *         for (Evento l : eventos) {
     *             if (l.getCodigo().equals(e.getCodigo()))
     *                 return false;
     *         }
     *         int i = 0;
     *         while (i < eventos.size() && e.getCodigo().compareTo(eventos.get(i).getCodigo()) > 0) {
     *             i++;
     *         }
     *
     *         eventos.add(i, e);
     *         return true;
     *     }
     * ado ado ado quem ler é viado
     */


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
