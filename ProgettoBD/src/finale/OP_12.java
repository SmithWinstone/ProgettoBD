package finale;

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
	private JTextField codiceStruttura;
	private JTextField nomeCentro;
	private JTextField data;

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
		setBounds(100, 100, 275, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		codice = new JTextField();
		codice.setBounds(10, 36, 239, 22);
		contentPane.add(codice);
		codice.setColumns(10);
		
		lblCodicePrenotazione = new JLabel("Codice Prenotazione");
		lblCodicePrenotazione.setBounds(10, 10, 154, 16);
		contentPane.add(lblCodicePrenotazione);
		
		oraInizio = new JTextField();
		oraInizio.setToolTipText("hh:mm");
		oraInizio.setColumns(10);
		oraInizio.setBounds(10, 205, 64, 22);
		contentPane.add(oraInizio);
		
		JLabel lblOraInizio = new JLabel("Ora inizio");
		lblOraInizio.setBounds(10, 184, 96, 16);
		contentPane.add(lblOraInizio);
		
		JButton btnModificaPrenotazione = new JButton("Modifica orario prenotazione");
		btnModificaPrenotazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((op02prenotazione(e)==true) && (op02pianificazione(e)==true))
					op12(e);
				else System.out.println("impossibile modificare l'orario della prenotazione");
			}
		});
		
		btnModificaPrenotazione.setBounds(10, 253, 244, 37);
		contentPane.add(btnModificaPrenotazione);		
		JLabel lblDurata = new JLabel("Durata");
		lblDurata.setBounds(84, 184, 80, 16);
		contentPane.add(lblDurata);		
		durata = new JTextField();
		durata.setToolTipText("");
		durata.setColumns(10);
		durata.setBounds(84, 205, 64, 22);
		contentPane.add(durata);
		
		codiceStruttura = new JTextField();
		codiceStruttura.setColumns(10);
		codiceStruttura.setBounds(10, 94, 239, 22);
		contentPane.add(codiceStruttura);
		
		JLabel lblCodiceStruttura = new JLabel("Codice Struttura");
		lblCodiceStruttura.setBounds(10, 68, 154, 16);
		contentPane.add(lblCodiceStruttura);
		
		nomeCentro = new JTextField();
		nomeCentro.setColumns(10);
		nomeCentro.setBounds(10, 152, 239, 22);
		contentPane.add(nomeCentro);
		
		JLabel lblNomeCentro = new JLabel("Nome Centro");
		lblNomeCentro.setBounds(10, 126, 154, 16);
		contentPane.add(lblNomeCentro);
		
		data = new JTextField();
		data.setToolTipText("");
		data.setColumns(10);
		data.setBounds(169, 205, 64, 22);
		contentPane.add(data);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(169, 184, 80, 16);
		contentPane.add(lblData);
	}
	
	public boolean op02prenotazione(ActionEvent e) {
		boolean result=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","Password00");
			PreparedStatement ps= con.prepareStatement("SELECT * /*struttura.codice,struttura.nomeCentro*/\r\n" + 
					"FROM prenotazione\r\n" + 
					"WHERE \r\n" + 
					"prenotazione.codice!=?\r\n" + 
					"AND prenotazione.oraInizio <= (addtime(?,?)) \r\n" + 
					"AND (prenotazione.oraInizio+interval prenotazione.durata hour >= ?)\r\n" + 
					"AND prenotazione.data = ?\r\n" + 
					"AND prenotazione.codiceStruttura=?\r\n" + 
					"AND prenotazione.nomeCentro = ?;");
		
			ps.setString(1, codice.getText());
			ps.setString(2, oraInizio.getText()+":02");
			String dur=durata.getText()+":00"; 	
			ps.setString(3, dur);
			ps.setString(4, oraInizio.getText()+":02");
			ps.setString(5, codiceStruttura.getText());
			ps.setString(6, nomeCentro.getText());
			ps.setString(7, data.getText());
			
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

	
	public boolean op02pianificazione(ActionEvent e) {
		boolean result=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","Password00");
			PreparedStatement ps= con.prepareStatement("SELECT * /*struttura.codice,struttura.nomeCentro*/\r\n" + 
					"FROM pianificazione \r\n" + 
					"WHERE pianificazione.ora <= (addtime(?,?)) \r\n" + 
					"AND (pianificazione.ora+interval pianificazione.durata hour >= ?) \r\n" + 
					"AND pianificazione.codiceStruttura=?\r\n" + 
					"AND pianificazione.nomeCentro = ?\r\n" + 
					"and pianificazione.data=?;");
		
			ps.setString(1, oraInizio.getText()+":02");
			String dur=durata.getText()+":00"; 	//modifica n * 3600
			ps.setString(2, dur);
			ps.setString(3, oraInizio.getText()+":02");
			ps.setString(4, codiceStruttura.getText());
			ps.setString(5, nomeCentro.getText());
			ps.setString(6, durata.getText());
			
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

			ps.setString(1, oraInizio.getText()+":01");
			ps.setString(2, durata.getText());
			ps.setString(3, codice.getText());
			ps.executeUpdate();	//number rows effected
			System.out.println("Modifica dell'orario della prenotazione effettuata con successo");
		}catch(Exception e1) {
			System.out.println(e1);
		}
	}
}