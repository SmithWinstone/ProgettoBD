package finale;

/*

5.Svolgimento di un’attività;
SVOLGIMENTO.JAVA
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

public class OP_05 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField codiceStruttura;
	private JLabel lblCodiceStruttura;
	private JTextField nomeCentro;
	private JTextField codiceAttività;
	private JTextField id;

	/**
	 * Launch the application.
	 */
	public static void runOP_05() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OP_05 frame = new OP_05();
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
	public OP_05() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 332, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		codiceStruttura = new JTextField();
		codiceStruttura.setBounds(10, 36, 138, 22);
		contentPane.add(codiceStruttura);
		codiceStruttura.setColumns(10);
		
		lblCodiceStruttura = new JLabel("Codice Struttura");
		lblCodiceStruttura.setBounds(10, 10, 138, 16);
		contentPane.add(lblCodiceStruttura);
		
		nomeCentro = new JTextField();
		nomeCentro.setColumns(10);
		nomeCentro.setBounds(166, 36, 138, 22);
		contentPane.add(nomeCentro);
		
		JLabel lblNomeCentro = new JLabel("Nome Centro");
		lblNomeCentro.setBounds(168, 10, 121, 16);
		contentPane.add(lblNomeCentro);
		
		codiceAttività = new JTextField();
		codiceAttività.setColumns(10);
		codiceAttività.setBounds(10, 94, 138, 22);
		contentPane.add(codiceAttività);
		
		JLabel lblCodiceAttività = new JLabel("Codice attivit\u00E0");
		lblCodiceAttività.setBounds(10, 68, 138, 16);
		contentPane.add(lblCodiceAttività);
		
		id = new JTextField();
		id.setToolTipText("");
		id.setColumns(10);
		id.setBounds(166, 94, 138, 22);
		contentPane.add(id);
		
		JLabel lblId = new JLabel("Id Pianificazione");
		lblId.setBounds(166, 68, 138, 16);
		contentPane.add(lblId);
		
		JButton btnSvolgimento = new JButton("Svolgimento attivit\u00E0");
		btnSvolgimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op05(e);			
				}
		});
		btnSvolgimento.setBounds(10, 139, 294, 37);
		contentPane.add(btnSvolgimento);
		
	}
	public void op05(ActionEvent e) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","Password00");
			PreparedStatement ps= con.prepareStatement("update pianificazione\r\n" + 
					"set svolta = 'si'\r\n" + 
					"where codiceStruttura = ?\r\n" + 
					"and nomeCentro = ?\r\n" + 
					"and codiceAttività = ?\r\n" + 
					"and id = ?;");
			ps.setString(1, codiceStruttura.getText());
			ps.setString(2, nomeCentro.getText());
			ps.setString(3, codiceAttività.getText());
			ps.setString(4, id.getText());
			int x= ps.executeUpdate();	//number rows effected
			if (x>0)	System.out.println("Svolgimento avvenuto con successo!");
			else System.out.println("QUALCOSA E' ANDATO STORTO!");
		}catch(Exception e1) {
			System.out.println(e1);
		}
	}
} 	