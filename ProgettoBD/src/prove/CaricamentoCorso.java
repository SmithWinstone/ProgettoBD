package prove;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.sql.*;

public class CaricamentoCorso extends JFrame {

  /**
  * 
  */
 private static final long serialVersionUID = 1L;
private JPanel contentPane;
  private JTextField corso;
  private JTextField descrizione;
  private JTextField codice;
  private JTextField periodicità;
  private JTextField durata;
  private JTextField centro;
  private JTextField cfAllenatore1;

  /* Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          CaricamentoCorso frame = new CaricamentoCorso();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /* Create the frame.
   */
  public CaricamentoCorso() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 332, 379);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(null);
    setContentPane(contentPane);
    
    corso = new JTextField();
    corso.setColumns(10);
    corso.setBounds(10, 67, 294, 22);
    contentPane.add(corso);
    
    JLabel lblNomeCentro = new JLabel("Nome centro");
    lblNomeCentro.setBounds(10, 11, 294, 16);
    contentPane.add(lblNomeCentro);
    
    JButton btnCaricaCorso = new JButton("Carica corso");
    btnCaricaCorso.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/catena"
              + "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
              + "&useLegacyDatetimeCode=false&serverTimezone=UTC";
          Connection con = DriverManager.getConnection(url,"root","Password00");
          
          PreparedStatement ps= con.prepareStatement("INSERT INTO attività (codice, descrizione, corso, periodicitàCorso, "
            + "durataCorso)\r\n" + 
            "VALUES (?,?,?,?,?);");
          ps.setString(1, codice.getText());
          ps.setString(2, descrizione.getText());
          ps.setString(3, corso.getText());
          ps.setString(4, periodicità.getText());
          ps.setString(5, durata.getText());
          
          PreparedStatement ps2= con.prepareStatement("INSERT INTO abilitazione(centroAbilitato, attivitàAbilitata)\r\n" +
"VALUES (?, ?);");
          ps2.setString(1, centro.getText());
          ps2.setString(2, codice.getText());
          
          PreparedStatement ps3= con.prepareStatement("INSERT INTO insegnamento(cfIstruttore, codiceAttività)\r\n" + 
            "VALUES (?, ?);");
          ps3.setString(1, cfAllenatore1.getText());
          ps3.setString(2, codice.getText());
          
          int x= ps.executeUpdate();  //number rows effected
          int y= ps2.executeUpdate();
          int z= ps3.executeUpdate();
          
           if (x>0&&y>0&&z>0)  {
               System.out.println("Caricamento avvenuto con successo!");
              }
           else {
            System.out.println("Errore nel caricamento!");
           }
          

        }catch(Exception e1) {
          System.out.println(e1);
        }
      }
    });
    btnCaricaCorso.setBounds(10, 300, 294, 29);
    contentPane.add(btnCaricaCorso);
    
    JLabel lblNomeCorso = new JLabel("Nome corso");
    lblNomeCorso.setBounds(10, 50, 294, 16);
    contentPane.add(lblNomeCorso);
    
    JLabel lblDescrizione = new JLabel("Descrizione");
    lblDescrizione.setBounds(10, 96, 294, 16);
    contentPane.add(lblDescrizione);
    
    descrizione = new JTextField();
    descrizione.setColumns(10);
    descrizione.setBounds(10, 111, 294, 22);
    contentPane.add(descrizione);
    
    JLabel lblCodice = new JLabel("Codice");
    lblCodice.setBounds(10,139, 294, 16);
    contentPane.add(lblCodice);
    
    codice = new JTextField();
    codice.setColumns(10);
    codice.setBounds(10, 153, 294, 22);
    contentPane.add(codice);
    
    JLabel lblPeriodicita = new JLabel("Periodicit\u00E0");
    lblPeriodicita.setBounds(10, 177, 148, 16);
    contentPane.add(lblPeriodicita);
    
    periodicità = new JTextField();
    periodicità.setColumns(10);
    periodicità.setBounds(10, 195, 135, 22);
    contentPane.add(periodicità);
    
    JLabel lblDurataCorso = new JLabel("Durata corso");
    lblDurataCorso.setBounds(168, 177, 148, 16);
    contentPane.add(lblDurataCorso);
    
    durata = new JTextField();
    durata.setColumns(10);
    durata.setBounds(169, 195, 135, 22);
    contentPane.add(durata);
    
    centro = new JTextField();
    centro.setColumns(10);
    centro.setBounds(10, 28, 294, 22);
    contentPane.add(centro);
    
    JLabel lblCfAllenatore = new JLabel("CF allenatore");
    lblCfAllenatore.setBounds(10, 221, 148, 16);
    contentPane.add(lblCfAllenatore);
    
    cfAllenatore1 = new JTextField();
    cfAllenatore1.setColumns(10);
    cfAllenatore1.setBounds(10, 235, 294, 22);
    contentPane.add(cfAllenatore1);
    
  }
}