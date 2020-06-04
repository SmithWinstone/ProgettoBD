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

public class Abilitazione extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeCentro;
	private JTextField codiceAttività;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Abilitazione frame = new Abilitazione();
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
	public Abilitazione() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		nomeCentro = new JTextField();
		nomeCentro.setColumns(10);
		nomeCentro.setBounds(178, 36, 126, 22);
		contentPane.add(nomeCentro);
		
		JLabel lblNomeCentro = new JLabel("Nome Centro");
		lblNomeCentro.setBounds(168, 10, 80, 16);
		contentPane.add(lblNomeCentro);
		
		JButton btnAbilitazione = new JButton("Abilitazione");
		btnAbilitazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/catena"
							+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
							+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
					Connection con = DriverManager.getConnection(url,"root","Password00");
					PreparedStatement ps= con.prepareStatement("insert into abilitazione (attivitàAbilitata,centroAbilitato) values(?,?);");
					ps.setString(1, codiceAttività.getText());
					ps.setString(2, nomeCentro.getText());
					int x= ps.executeUpdate();	//number rows effected
					if (x>0)	System.out.println("Abilitazione effettuata con successo");
					else System.out.println("QUALCOSA E' ANDATO STORTO");

				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnAbilitazione.setBounds(20, 68, 284, 37);
		contentPane.add(btnAbilitazione);
		
		codiceAttività = new JTextField();
		codiceAttività.setColumns(10);
		codiceAttività.setBounds(20, 36, 126, 22);
		contentPane.add(codiceAttività);
		
		JLabel lblCodiceAttivit = new JLabel("Codice Attivit\u00E0");
		lblCodiceAttivit.setBounds(10, 10, 80, 16);
		contentPane.add(lblCodiceAttivit);
		
	}
}