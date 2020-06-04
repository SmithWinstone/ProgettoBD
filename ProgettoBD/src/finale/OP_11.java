package finale;

/*

11.Cancellazione di uno dei responsabili di un centro, con elezione di un nuovo responsabile;
SOSTITUZIONERESPONSABILE.JAVA
FATTO DA ALESSANDRA
*/
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

public class OP_11 extends JFrame {

  /**
  * 
  */
 private static final long serialVersionUID = 1L;
private JPanel contentPane;
  private JTextField cf;
  private JLabel lblCF;
  private JTextField cfResponsabileVecchio;
  private JTextField nome;
  private JTextField cognome;
  private JTextField telefono;
  private JTextField contratto;

  /* Launch the application.
   */
  public static void runOP_11() {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          OP_11 frame = new OP_11();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /* Create the frame.
   */
  public OP_11() {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 332, 413);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(null);
    setContentPane(contentPane);
    
    cf = new JTextField();
    cf.setBounds(10, 195, 294, 22);
    contentPane.add(cf);
    cf.setColumns(10);
    
    lblCF = new JLabel("Codice fiscale");
    lblCF.setBounds(10, 182, 80, 16);
    contentPane.add(lblCF);
    
    cfResponsabileVecchio = new JTextField();
    cfResponsabileVecchio.setColumns(10);
    cfResponsabileVecchio.setBounds(10, 27, 294, 22);
    contentPane.add(cfResponsabileVecchio);
    
    JLabel lblCfResponsabileNo = new JLabel("CF responsabile da rimuovere");
    lblCfResponsabileNo.setBounds(10, 11, 294, 16);
    contentPane.add(lblCfResponsabileNo);
    
    nome = new JTextField();
    nome.setColumns(10);
    nome.setBounds(10, 102, 294, 22);
    contentPane.add(nome);
    
    JLabel lblDatiResponsabileNew = new JLabel("DATI NUOVO RESPONSABILE");
    lblDatiResponsabileNew.setBounds(10, 60, 294, 16);
    contentPane.add(lblDatiResponsabileNew);
    
    cognome = new JTextField();
    cognome.setColumns(10);
    cognome.setBounds(10, 149, 294, 22);
    contentPane.add(cognome);
    
    JLabel lblCognome = new JLabel("Cognome");
    lblCognome.setBounds(10, 135, 80, 16);
    contentPane.add(lblCognome);
    
    telefono = new JTextField();
    telefono.setColumns(10);
    telefono.setBounds(10, 291, 228, 22);
    contentPane.add(telefono);
    
    JLabel lblTelefono = new JLabel("Telefono");
    lblTelefono.setBounds(10, 276, 80, 16);
    contentPane.add(lblTelefono);
    
    JButton btnSostituisci = new JButton("Sostituisci");
    btnSostituisci.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  op11(e);
      }
    });
    btnSostituisci.setBounds(10, 336, 126, 37);
    contentPane.add(btnSostituisci);
    
    JLabel lblContratto = new JLabel("Contratto");
    lblContratto.setBounds(10, 228, 80, 16);
    contentPane.add(lblContratto);
    
    contratto = new JTextField();
    contratto.setToolTipText("");
    contratto.setColumns(10);
    contratto.setBounds(10, 243, 294, 22);
    contentPane.add(contratto);
    
    JLabel lblNome = new JLabel("Nome");
    lblNome.setBounds(10, 87, 294, 16);
    contentPane.add(lblNome);
    
  }
  public void op11(ActionEvent e) {
	  try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          String url = "jdbc:mysql://localhost:3306/catena"
              + "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
              + "&useLegacyDatetimeCode=false&serverTimezone=UTC";
          Connection con = DriverManager.getConnection(url,"root","Password00");
          
          PreparedStatement ps= con.prepareStatement("DELETE \r\n" + 
            "FROM responsabile\r\n" + 
            "WHERE cf= ?;\r\n"); 
          
          PreparedStatement ps2= con.prepareStatement("INSERT INTO responsabile (cf, nome, cognome, contratto, telefono)\r\n" + 
            "VALUES (?, ?, ?, ?, ?);");
          ps.setString(1, cfResponsabileVecchio.getText());
          ps2.setString(1, cf.getText());
          ps2.setString(2, nome.getText());
          ps2.setString(3, cognome.getText());
          ps2.setString(4, contratto.getText());
          ps2.setString(5, telefono.getText());
         
          int x= ps.executeUpdate();  //number rows effected
          int y= ps2.executeUpdate();
          if (x>0&&y>0)  System.out.println("Sostituzione avvenuta con successo!");

          else System.out.println("Sostituzione fallita!");

        }catch(Exception e1) {
          System.out.println(e1);
        }

  }
}