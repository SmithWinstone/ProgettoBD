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

public class VerificaPrenotazione extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField codiceStruttura;
	private JLabel lblCodiceStruttura;
	private JTextField nomeCentro;
	private JTextField oraInizio;
	private JTextField data;
	private JTextField oraFine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerificaPrenotazione frame = new VerificaPrenotazione();
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
	public VerificaPrenotazione() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		codiceStruttura = new JTextField();
		codiceStruttura.setBounds(10, 36, 126, 22);
		contentPane.add(codiceStruttura);
		codiceStruttura.setColumns(10);
		
		lblCodiceStruttura = new JLabel("Codice Struttura");
		lblCodiceStruttura.setBounds(10, 10, 80, 16);
		contentPane.add(lblCodiceStruttura);
		
		nomeCentro = new JTextField();
		nomeCentro.setColumns(10);
		nomeCentro.setBounds(178, 36, 126, 22);
		contentPane.add(nomeCentro);
		
		JLabel lblNomeCentro = new JLabel("Nome Centro");
		lblNomeCentro.setBounds(168, 10, 80, 16);
		contentPane.add(lblNomeCentro);
		
		oraInizio = new JTextField();
		oraInizio.setToolTipText("hh:mm");
		oraInizio.setColumns(10);
		oraInizio.setBounds(10, 94, 49, 22);
		contentPane.add(oraInizio);
		
		JLabel lblOraInizio = new JLabel("Ora inizio");
		lblOraInizio.setBounds(10, 68, 49, 16);
		contentPane.add(lblOraInizio);
		
		data = new JTextField();
		data.setToolTipText("aaaa-mm-gg");
		data.setColumns(10);
		data.setBounds(208, 94, 96, 22);
		contentPane.add(data);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(198, 68, 80, 16);
		contentPane.add(lblData);
		
		JButton btnVerificaPrenotazione = new JButton("Verifica possibilit\u00E0 di prenotare");
		btnVerificaPrenotazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/catena"
							+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
							+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
					Connection con = DriverManager.getConnection(url,"root","Password00");
					PreparedStatement ps= con.prepareStatement("SELECT * /*struttura.codice,struttura.nomeCentro*/\r\n" + 
							"	FROM struttura\r\n" + 
							"	JOIN prenotazione\r\n" + 
							"	ON (struttura.codice = prenotazione.codiceStruttura and struttura.nomeCentro=prenotazione.nomeCentro)\r\n" + 
							"    WHERE\r\n" + 
							"		prenotazione.nomeCentro=?\r\n" + 
							"        AND prenotazione.codiceStruttura=?\r\n" + 
							"		AND prenotazione.data = ?  \r\n" + 
							"		AND ? <= (prenotazione.oraInizio+interval prenotazione.durata hour) \r\n" + 
							"		AND (? >= prenotazione.oraInizio);\r\n");
					ps.setString(1, nomeCentro.getText());			
					ps.setString(2, codiceStruttura.getText());
					ps.setString(3, data.getText());
					ps.setString(4, oraInizio.getText());
					ps.setString(5, oraFine.getText());
					ResultSet x= ps.executeQuery();	//number rows effected
					if (x.next())	System.out.println("Impossibile prenotare!!!");
					else System.out.println("E' possibile prenotare!!!");
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnVerificaPrenotazione.setBounds(10, 126, 294, 37);
		contentPane.add(btnVerificaPrenotazione);		
		JLabel lblDurata = new JLabel("Ora Fine");
		lblDurata.setBounds(88, 68, 80, 16);
		contentPane.add(lblDurata);		
		oraFine = new JTextField();
		oraFine.setToolTipText("");
		oraFine.setColumns(10);
		oraFine.setBounds(88, 94, 49, 22);
		contentPane.add(oraFine);
	}
}