package finale;

/*
7.Abilitazione di un nuovo centro allo svolgimento di unattivitą;	
ABILITAZIONE.JAVA
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

public class OP_07 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeCentro;
	private JTextField codiceAttivitą;

	/**
	 * Launch the application.
	 */
	public static void runOP_07() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OP_07 frame = new OP_07();
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
	public OP_07() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 332, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		nomeCentro = new JTextField();
		nomeCentro.setColumns(10);
		nomeCentro.setBounds(178, 35, 126, 22);
		contentPane.add(nomeCentro);
		
		JLabel lblNomeCentro = new JLabel("Nome Centro");
		lblNomeCentro.setBounds(179, 10, 125, 16);
		contentPane.add(lblNomeCentro);
		
		JButton btnAbilitazione = new JButton("Abilitazione centro a svolgimento attivit\u00E0");
		btnAbilitazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			op07(e);
			}
			
		});
		btnAbilitazione.setBounds(20, 75, 284, 37);
		contentPane.add(btnAbilitazione);
		
		codiceAttivitą = new JTextField();
		codiceAttivitą.setColumns(10);
		codiceAttivitą.setBounds(20, 36, 126, 22);
		contentPane.add(codiceAttivitą);
		
		JLabel lblCodiceAttivit = new JLabel("Codice Attivit\u00E0");
		lblCodiceAttivit.setBounds(20, 10, 126, 16);
		contentPane.add(lblCodiceAttivit);
		
	}
	public void op07(ActionEvent e) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","Password00");
			PreparedStatement ps= con.prepareStatement("insert into abilitazione (codiceAttivitą,nomeCentro) values(?,?);");
			ps.setString(1, codiceAttivitą.getText());
			ps.setString(2, nomeCentro.getText());
			int x= ps.executeUpdate();	//number rows effected
			if (x>0)	System.out.println("Abilitazione effettuata con successo");
			else System.out.println("QUALCOSA E' ANDATO STORTO");

		}catch(Exception e1) {
			System.out.println(e1);
		}

	}
}