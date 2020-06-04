package finale;

/*
2.Verifica della possibilità di prenotare una struttura per un determinato giorno dell’anno ad una 
determinata ora;			
VERIFICAPRENOTAZIONE.JAVA
FATTO
*/import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.sql.*;

public class OP_02 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField codiceStruttura;
	private JLabel lblCodiceStruttura;
	private JTextField nomeCentro;
	private JTextField oraInizio;
	private JTextField data;
	private JTextField durata;

	/**
	 * Launch the application.
	 */
	public static void runOP_02() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OP_02 frame = new OP_02();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OP_02() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 332, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		codiceStruttura = new JTextField();
		codiceStruttura.setBounds(10, 36, 126, 22);
		contentPane.add(codiceStruttura);
		codiceStruttura.setColumns(10);
		
		lblCodiceStruttura = new JLabel("Codice Struttura");
		lblCodiceStruttura.setBounds(10, 10, 126, 16);
		contentPane.add(lblCodiceStruttura);
		
		nomeCentro = new JTextField();
		nomeCentro.setColumns(10);
		nomeCentro.setBounds(178, 36, 126, 22);
		contentPane.add(nomeCentro);
		
		JLabel lblNomeCentro = new JLabel("Nome Centro");
		lblNomeCentro.setBounds(179, 10, 125, 16);
		contentPane.add(lblNomeCentro);
		
		oraInizio = new JTextField();
		oraInizio.setToolTipText("hh:mm");
		oraInizio.setColumns(10);
		oraInizio.setBounds(10, 94, 49, 22);
		contentPane.add(oraInizio);
		
		JLabel lblOraInizio = new JLabel("Ora inizio");
		lblOraInizio.setBounds(10, 68, 80, 16);
		contentPane.add(lblOraInizio);
		
		data = new JTextField();
		data.setToolTipText("aaaa-mm-gg");
		data.setColumns(10);
		data.setBounds(208, 94, 96, 22);
		contentPane.add(data);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(208, 68, 80, 16);
		contentPane.add(lblData);
		
		JButton btnVerificaPrenotazione = new JButton("Verifica possibilit\u00E0 di prenotare");
		btnVerificaPrenotazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op02(e);
			}
		});
		btnVerificaPrenotazione.setBounds(10, 146, 294, 37);
		contentPane.add(btnVerificaPrenotazione);		
		JLabel lblDurata = new JLabel("Durata");
		lblDurata.setBounds(108, 68, 80, 16);
		contentPane.add(lblDurata);		
		durata = new JTextField();
		durata.setToolTipText("");
		durata.setColumns(10);
		durata.setBounds(108, 94, 49, 22);
		contentPane.add(durata);
	}
	
	public boolean op02(ActionEvent e) { 
		boolean result=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","Password00");
			PreparedStatement prenotazioni= con.prepareStatement("SELECT * /*struttura.codice,struttura.nomeCentro*/\r\n" + 
					"FROM struttura\r\n" + 
					"JOIN prenotazione\r\n" + 
					"ON (struttura.codice = prenotazione.codiceStruttura and struttura.nomeCentro=prenotazione.nomeCentro)\r\n" + 
					"WHERE\r\n" + 
					"prenotazione.nomeCentro=?\r\n" + 
					"AND prenotazione.codiceStruttura=?\r\n" + 
					"AND prenotazione.data = ?  \r\n" + 
					"AND ? <= (prenotazione.oraInizio+interval prenotazione.durata hour)\r\n" + 
					"AND (addtime(?,?) >= prenotazione.oraInizio);");
			prenotazioni.setString(1, nomeCentro.getText());			
			prenotazioni.setString(2, codiceStruttura.getText());
			prenotazioni.setString(3, data.getText());
			prenotazioni.setString(4, oraInizio.getText()+":02");
			prenotazioni.setString(5, oraInizio.getText());
			prenotazioni.setString(6, durata.getText()+"0000");
			
			PreparedStatement pianificazioni= con.prepareStatement("SELECT * /*struttura.codice,struttura.nomeCentro*/ \r\n" + 
					"FROM struttura \r\n" + 
					"JOIN pianificazione\r\n" + 
					"ON (struttura.codice = pianificazione.codiceStruttura and struttura.nomeCentro=pianificazione.nomeCentro)\r\n" + 
					"WHERE\r\n" + 
					"pianificazione.nomeCentro=?\r\n" + 
					"AND pianificazione.codiceStruttura=? \r\n" + 
					"AND pianificazione.data = ?\r\n" + 
					"AND  ?<= (pianificazione.ora+interval pianificazione.durata hour)\r\n" + 
					"AND (addtime(?,?) >= pianificazione.ora);");
			pianificazioni.setString(1, nomeCentro.getText());			
			pianificazioni.setString(2, codiceStruttura.getText());
			pianificazioni.setString(3, data.getText());
			pianificazioni.setString(4, oraInizio.getText()+":02");
			pianificazioni.setString(5, oraInizio.getText());
			pianificazioni.setString(6, durata.getText()+"0000");

			
			ResultSet x= prenotazioni.executeQuery();	//number rows effected
			ResultSet y=pianificazioni.executeQuery();
			if (x.next() || y.next()) {
				System.out.println("Struttura occupata!");
				result=false;
			}
			else{
				System.out.print("E' POSSIBILE PRENOTARE!");
				result = true;
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}
		return result;
	}

}