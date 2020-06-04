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

public class VisualizzaAllenatoreSpecializzato extends JFrame {

  /**
  * 
  */
 private static final long serialVersionUID = 1L;
private JPanel contentPane;
  private JTextField specializzazione;

  /* Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          VisualizzaAllenatoreSpecializzato frame = new VisualizzaAllenatoreSpecializzato();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /* Create the frame.
   */
  public VisualizzaAllenatoreSpecializzato() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 332, 166);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(null);
    setContentPane(contentPane);
    
    specializzazione = new JTextField();
    specializzazione.setColumns(10);
    specializzazione.setBounds(10, 38, 294, 22);
    contentPane.add(specializzazione);
    
    JLabel lblSpecializzazioneAllenatore = new JLabel("Specializzazione allenatore");
    lblSpecializzazioneAllenatore.setBounds(10, 11, 294, 16);
    contentPane.add(lblSpecializzazioneAllenatore);
    
    JButton btnRicerca = new JButton("Ricerca allenatore specializzato");
    btnRicerca.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/catena"
              + "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
              + "&useLegacyDatetimeCode=false&serverTimezone=UTC";
          Connection con = DriverManager.getConnection(url,"root","Password00");
          
          PreparedStatement ps= con.prepareStatement("SELECT *\r\n" + 
            "FROM istruttore\r\n" + 
            "WHERE specializzazione = ?;");
          ps.setString(1, specializzazione.getText());
          
          ResultSet x= ps.executeQuery();  //number rows effected
          
           while (x.next())  {
               System.out.println("\rcodice fiscale: " + x.getString(1) + "\rnome: "+ x.getString(2) + "\rcognome: "+
              x.getString(3)+"\ranni esperienza: "+ x.getInt(4)+ "\rcontratto: "+ x.getString(5)+ "\rtelefono: "+ x.getString(6)+
              "\rspecializzazione: "+ x.getString(7)+ "\rdocumento specializzazione: "+ x.getString(8) +"\r");
              }
          

        }catch(Exception e1) {
          System.out.println(e1);
        }
      }
    });
    btnRicerca.setBounds(10, 79, 294, 37);
    contentPane.add(btnRicerca);
    
  }
}