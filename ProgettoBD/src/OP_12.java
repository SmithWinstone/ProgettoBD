/*
12.Modifica dell’orario della prenotazione di una struttura (se possibile);		
VERIFICAPRENOTAZIONE.JAVA
FATTO
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

public class OP_12 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField codice;
	private JLabel lblCodicePrenotazione;
	private JTextField oraInizio;
	private JTextField durata;

	/**
	 * Launch the application.
	 */
	public static void runOP_12() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OP_12 frame = new OP_12();
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
	public OP_12() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 275, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		codice = new JTextField();
		codice.setBounds(10, 36, 126, 22);
		contentPane.add(codice);
		codice.setColumns(10);
		
		lblCodicePrenotazione = new JLabel("Codice Prenotazione");
		lblCodicePrenotazione.setBounds(10, 10, 126, 16);
		contentPane.add(lblCodicePrenotazione);
		
		oraInizio = new JTextField();
		oraInizio.setToolTipText("hh:mm");
		oraInizio.setColumns(10);
		oraInizio.setBounds(146, 36, 49, 22);
		contentPane.add(oraInizio);
		
		JLabel lblOraInizio = new JLabel("Ora inizio");
		lblOraInizio.setBounds(146, 10, 49, 16);
		contentPane.add(lblOraInizio);
		
		JButton btnModificaPrenotazione = new JButton("Modifica orario prenotazione");
		btnModificaPrenotazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(op02modificata(e)==true)
					op12(e);
				else System.out.println("impossibile modificare l'orario della prenotazione");
			}
		});
		
		btnModificaPrenotazione.setBounds(10, 68, 244, 37);
		contentPane.add(btnModificaPrenotazione);		
		JLabel lblDurata = new JLabel("Durata");
		lblDurata.setBounds(205, 10, 80, 16);
		contentPane.add(lblDurata);		
		durata = new JTextField();
		durata.setToolTipText("");
		durata.setColumns(10);
		durata.setBounds(205, 36, 49, 22);
		contentPane.add(durata);
	}
	
	public boolean op02modificata(ActionEvent e) {
		boolean result=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","Password00");
			PreparedStatement ps= con.prepareStatement("SELECT * /*struttura.codice,struttura.nomeCentro*/\r\n" + 
					"FROM struttura\r\n" + 
					"JOIN prenotazione\r\n" + 
					"ON (struttura.codice = prenotazione.codiceStruttura and struttura.nomeCentro=prenotazione.nomeCentro)\r\n" + 
					"WHERE \r\n" + 
					"prenotazione.codice!=?\r\n" + 
					"AND ? <= (prenotazione.oraInizio+interval prenotazione.durata hour)\r\n" + 
					"AND (addtime(?,?) >= prenotazione.oraInizio);");
		
			ps.setString(1, codice.getText());
			ps.setString(2, oraInizio.getText());
			ps.setString(3, oraInizio.getText());
			String dur=durata.getText()+"0000";
			ps.setString(4, dur);
			
			ResultSet x= ps.executeQuery();	//number rows effected
			if (x.next()) {
				result=false;
			}
			else{
				result = true;
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}
		return result;
	}

	public void op12(ActionEvent e) { 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","Password00");
			PreparedStatement ps= con.prepareStatement("update prenotazione \r\n" + 
					"set oraInizio = ? , durata = ?\r\n" + 
					"where codice = ?;");

			ps.setString(1, oraInizio.getText()+"01");
			ps.setString(2, durata.getText());
			ps.setString(3, codice.getText());
			ps.executeUpdate();	//number rows effected
			System.out.println("Modifica dell'orario della prenotazione effettuata con successo");
		}catch(Exception e1) {
			System.out.println(e1);
		}
	}
}