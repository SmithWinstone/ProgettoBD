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

public class Prenotazione extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField codiceStruttura;
	private JLabel lblCodiceStruttura;
	private JTextField nomeCentro;
	private JTextField cfSegretario;
	private JTextField nomeCliente;
	private JTextField cognomeCliente;
	private JTextField oraInizio;
	private JTextField data;
	private JTextField telefonoCliente;
	private JTextField durata;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prenotazione frame = new Prenotazione();
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
	public Prenotazione() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 439);
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
		
		cfSegretario = new JTextField();
		cfSegretario.setColumns(10);
		cfSegretario.setBounds(10, 94, 294, 22);
		contentPane.add(cfSegretario);
		
		JLabel lblCfsegretario = new JLabel("CF Segretario");
		lblCfsegretario.setBounds(10, 68, 80, 16);
		contentPane.add(lblCfsegretario);
		
		nomeCliente = new JTextField();
		nomeCliente.setColumns(10);
		nomeCliente.setBounds(10, 152, 294, 22);
		contentPane.add(nomeCliente);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 126, 80, 16);
		contentPane.add(lblNome);
		
		cognomeCliente = new JTextField();
		cognomeCliente.setColumns(10);
		cognomeCliente.setBounds(10, 210, 294, 22);
		contentPane.add(cognomeCliente);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(10, 184, 80, 16);
		contentPane.add(lblCognome);
		
		oraInizio = new JTextField();
		oraInizio.setToolTipText("hh:mm");
		oraInizio.setColumns(10);
		oraInizio.setBounds(10, 268, 49, 22);
		contentPane.add(oraInizio);
		
		JLabel lblOraInizio = new JLabel("Ora inizio");
		lblOraInizio.setBounds(10, 242, 49, 16);
		contentPane.add(lblOraInizio);
		
		data = new JTextField();
		data.setToolTipText("aaaa-mm-gg");
		data.setColumns(10);
		data.setBounds(208, 268, 96, 22);
		contentPane.add(data);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(198, 242, 80, 16);
		contentPane.add(lblData);
		
		telefonoCliente = new JTextField();
		telefonoCliente.setColumns(10);
		telefonoCliente.setBounds(10, 326, 238, 22);
		contentPane.add(telefonoCliente);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 300, 80, 16);
		contentPane.add(lblTelefono);
		
		JButton btnPrenotazione = new JButton("Prenotazione");
		btnPrenotazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/catena"
							+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
							+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
					Connection con = DriverManager.getConnection(url,"root","Password00");
					PreparedStatement ps= con.prepareStatement("insert into prenotazione (codiceStruttura,nomeCentro,cfSegretario,nome,cognome,oraInizio,durata,data,telefono) values(?,?,?,?,?,?,?,?,?);");
					ps.setString(1, codiceStruttura.getText());
					ps.setString(2, nomeCentro.getText());
					ps.setString(3, cfSegretario.getText());
					ps.setString(4, nomeCliente.getText());
					ps.setString(5, cognomeCliente.getText());
					ps.setString(6, oraInizio.getText()+":01");
					ps.setString(7, durata.getText());
					ps.setString(8, data.getText());
					ps.setString(9, telefonoCliente.getText());
					int x= ps.executeUpdate();	//number rows effected
					if (x>0)	System.out.println("Prenotazione avvenuta con successo!");
					else System.out.println("QUALCOSA E' ANDATO STORTO!");

				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnPrenotazione.setBounds(10, 358, 126, 37);
		contentPane.add(btnPrenotazione);
		
		JLabel lblDurata = new JLabel("Durata");
		lblDurata.setBounds(88, 242, 80, 16);
		contentPane.add(lblDurata);
		
		durata = new JTextField();
		durata.setToolTipText("");
		durata.setColumns(10);
		durata.setBounds(88, 268, 49, 22);
		contentPane.add(durata);
		
	}
}