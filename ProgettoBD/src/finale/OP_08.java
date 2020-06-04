
package finale;
/*

8.Assunzione di un nuovo allenatore;			
ASSUNZIONEALLENATORE.JAVA
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

public class OP_08 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
  	private JTextField specializzazione;
  	private JLabel lblSpecializzazione;
  	private JTextField cfAllenatore;
  	private JTextField nomeAllenatore;
  	private JTextField cognomeAllenatore;
  	private JTextField esperienza;
  	private JTextField documentoSpecializzazione;
  	private JTextField telefonoAllenatore;
  	private JTextField contratto;

  	/* Launch the application.
  	 */
  	public static void runOP_08() {
  		EventQueue.invokeLater(new Runnable() {
  			public void run() {
  				try {
  					OP_08 frame = new OP_08();
  					frame.setVisible(true);
  				} catch (Exception e) {
  					e.printStackTrace();
  				}
  			}
  		});
  	}

  	/* Create the frame.
  	*/
  	public OP_08() {
  		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		setBounds(100, 100, 332, 439);
  		contentPane = new JPanel();
  		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  		contentPane.setLayout(null);
  		setContentPane(contentPane);
    
  		specializzazione = new JTextField();
  		specializzazione.setBounds(10, 268, 294, 22);
  		contentPane.add(specializzazione);
  		specializzazione.setColumns(10);
    
  		lblSpecializzazione = new JLabel("Eventuale specializzazione");
  		lblSpecializzazione.setBounds(10, 252, 165, 16);
  		contentPane.add(lblSpecializzazione);
    
  		cfAllenatore = new JTextField();
  		cfAllenatore.setColumns(10);
  		cfAllenatore.setBounds(10, 27, 294, 22);
  		contentPane.add(cfAllenatore);
  		
  		JLabel lblCfAllenatore = new JLabel("CF Allenatore");
  		lblCfAllenatore.setBounds(10, 11, 80, 16);
  		contentPane.add(lblCfAllenatore);
  		
  		nomeAllenatore = new JTextField();
  		nomeAllenatore.setColumns(10);
  		nomeAllenatore.setBounds(10, 74, 294, 22);
  		contentPane.add(nomeAllenatore);
  		
  		JLabel lblNome = new JLabel("Nome");
  		lblNome.setBounds(10, 60, 80, 16);
  		contentPane.add(lblNome);
  		
  		cognomeAllenatore = new JTextField();
  		cognomeAllenatore.setColumns(10);
  		cognomeAllenatore.setBounds(10, 122, 294, 22);
  		contentPane.add(cognomeAllenatore);
  		
  		JLabel lblCognome = new JLabel("Cognome");
  		lblCognome.setBounds(10, 107, 80, 16);
  		contentPane.add(lblCognome);
  		
  		esperienza = new JTextField();
  		esperienza.setToolTipText("hh:mm");
  		esperienza.setColumns(10);
  		esperienza.setBounds(10, 171, 80, 22);
  		contentPane.add(esperienza);
  		
  		JLabel lblEsperienza = new JLabel("Anni esperienza");
  		lblEsperienza.setBounds(10, 155, 80, 16);
  		contentPane.add(lblEsperienza);
  		
  		documentoSpecializzazione = new JTextField();
  		documentoSpecializzazione.setToolTipText("aaaa-mm-gg");
  		documentoSpecializzazione.setColumns(10);
  		documentoSpecializzazione.setBounds(10, 316, 294, 22);
  		contentPane.add(documentoSpecializzazione);
  		
  		JLabel lblDocumentoSpecializzazione = new JLabel("Eventuale documento specializzazione");
  		lblDocumentoSpecializzazione.setBounds(10, 301, 235, 16);
  		contentPane.add(lblDocumentoSpecializzazione);
  		
  		telefonoAllenatore = new JTextField();
  		telefonoAllenatore.setColumns(10);
  		telefonoAllenatore.setBounds(122, 171, 184, 22);
  		contentPane.add(telefonoAllenatore);
  		
  		JLabel lblTelefono = new JLabel("Telefono");
  		lblTelefono.setBounds(122, 155, 80, 16);
  		contentPane.add(lblTelefono);
  		
  		JButton btnAssunzione = new JButton("Assunzione allenatore");
  		btnAssunzione.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				op08(e);
  			}
  		});
  		btnAssunzione.setBounds(10, 358, 184, 37);
  		contentPane.add(btnAssunzione);
  		
  		JLabel lblContratto = new JLabel("Contratto lavoro");
  		lblContratto.setBounds(10, 204, 126, 16);
  		contentPane.add(lblContratto);
  		
  		contratto = new JTextField();
  		contratto.setToolTipText("");
  		contratto.setColumns(10);
  		contratto.setBounds(10, 219, 294, 22);
  		contentPane.add(contratto);
  		
  	}	
  	public void op08(ActionEvent e) {
			try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/catena"
							+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
							+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
					Connection con = DriverManager.getConnection(url,"root","Password00");
      
					PreparedStatement ps= con.prepareStatement("insert into istruttore (cf,nome,cognome,esperienza,contratto,telefono,specializzazione,documentoSpecializzazione) values(?,?,?,?,?,?,?,?);");
					ps.setString(1, cfAllenatore.getText());
					ps.setString(2, nomeAllenatore.getText());
					ps.setString(3, cognomeAllenatore.getText());
					ps.setString(4, esperienza.getText());
					ps.setString(5, contratto.getText());
					ps.setString(6, telefonoAllenatore.getText());
					ps.setString(7, specializzazione.getText());
					ps.setString(8, documentoSpecializzazione.getText());
					int x= ps.executeUpdate();  //number rows effected
					if (x>0)  System.out.println("Assunzione avvenuta con successo!");
					else System.out.println("Assunzione fallita!");

				}catch(Exception e1) {
					System.out.println(e1);
				}

  	}
}