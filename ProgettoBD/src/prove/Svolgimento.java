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

public class Svolgimento extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField codiceStruttura;
	private JLabel lblCodiceStruttura;
	private JTextField nomeCentro;
	private JTextField codiceAttività;
	private JTextField oraInizio;
	private JTextField data;
	private JTextField durata;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Svolgimento frame = new Svolgimento();
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
	public Svolgimento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 263);
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
		
		codiceAttività = new JTextField();
		codiceAttività.setColumns(10);
		codiceAttività.setBounds(10, 94, 294, 22);
		contentPane.add(codiceAttività);
		
		JLabel lblCodiceAttività = new JLabel("Codice attivit\u00E0");
		lblCodiceAttività.setBounds(10, 68, 80, 16);
		contentPane.add(lblCodiceAttività);
		
		oraInizio = new JTextField();
		oraInizio.setToolTipText("hh:mm");
		oraInizio.setColumns(10);
		oraInizio.setBounds(10, 152, 49, 22);
		contentPane.add(oraInizio);
		
		JLabel lblOraInizio = new JLabel("Ora inizio");
		lblOraInizio.setBounds(10, 126, 49, 16);
		contentPane.add(lblOraInizio);
		
		data = new JTextField();
		data.setToolTipText("aaaa-mm-gg");
		data.setColumns(10);
		data.setBounds(208, 152, 96, 22);
		contentPane.add(data);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(198, 126, 80, 16);
		contentPane.add(lblData);
		
		JButton btnSvolgimento = new JButton("Svolgimento");
		btnSvolgimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/catena"
							+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
							+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
					Connection con = DriverManager.getConnection(url,"root","Password00");
					PreparedStatement ps= con.prepareStatement("insert into svolgimento (codiceStruttura,nomeCentro,codiceAttività,ora,durata,data) values(?,?,?,?,?,?);");
 					ps.setString(1, codiceStruttura.getText());
					ps.setString(2, nomeCentro.getText());
					ps.setString(3, codiceAttività.getText());
					ps.setString(4, oraInizio.getText()+":01");
					ps.setString(5, durata.getText());
					ps.setString(6, data.getText());
					int x= ps.executeUpdate();	//number rows effected
					if (x>0)	System.out.println("Svolgimento avvenuto con successo!");
					else System.out.println("QUALCOSA E' ANDATO STORTO!");

				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnSvolgimento.setBounds(10, 184, 294, 37);
		contentPane.add(btnSvolgimento);
		
		JLabel lblDurata = new JLabel("Durata");
		lblDurata.setBounds(88, 126, 80, 16);
		contentPane.add(lblDurata);
		
		durata = new JTextField();
		durata.setToolTipText("");
		durata.setColumns(10);
		durata.setBounds(88, 152, 49, 22);
		contentPane.add(durata);
		
	}
} 	