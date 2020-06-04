package prove;



/*
 * 
 * 
 * 
 * NON FA PARTE DEL TASK
 * 
 * 
 * 
 * 
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

public class struttureDisponibili extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ora;
	private JTextField data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					struttureDisponibili frame = new struttureDisponibili();
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
	public struttureDisponibili() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 326, 161);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		ora = new JTextField();
		ora.setToolTipText("hh:mm");
		ora.setColumns(10);
		ora.setBounds(10, 36, 96, 22);
		contentPane.add(ora);
		
		JLabel lblOra = new JLabel("Ora");
		lblOra.setBounds(10, 10, 80, 16);
		contentPane.add(lblOra);
		
		data = new JTextField();
		data.setToolTipText("aaaa-mm-gg");
		data.setColumns(10);
		data.setBounds(178, 36, 96, 22);
		contentPane.add(data);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(168, 10, 80, 16);
		contentPane.add(lblData);
		
		JButton btnStruttureDisponibili = new JButton("Mostra strutture disponibili");
		btnStruttureDisponibili.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/catena"
							+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
							+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
					Connection con = DriverManager.getConnection(url,"root","Password00");
					PreparedStatement ps= con.prepareStatement("SELECT struttura.codice, struttura.nomeCentro\r\n" + 
							"FROM struttura\r\n" + 
							"WHERE (struttura.codice, struttura.nomeCentro) NOT IN (\r\n" + 
							"	SELECT struttura.codice,struttura.nomeCentro\r\n" + 
							"	FROM struttura\r\n" + 
							"	JOIN prenotazione\r\n" + 
							"	ON struttura.codice = prenotazione.codiceStruttura \r\n" + 
							"		AND struttura.nomeCentro=prenotazione.nomeCentro \r\n" + 
							"		AND prenotazione.data = ?\r\n" + 
							"        AND ? BETWEEN prenotazione.oraInizio and prenotazione.oraFine);\r\n" + 
							"	");
					ps.setString(1, data.getText());
					ps.setString(2, ora.getText());
					ResultSet x= ps.executeQuery();	//number rows effected
					if(x!=null) {
						while (x.next()){ 
						System.out.println(x.getString(1) + "|" + x.getString(2));
						}	
					}
					else System.out.println("Registration failed!");

				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnStruttureDisponibili.setBounds(10, 77, 298, 37);
		contentPane.add(btnStruttureDisponibili);
		
	}
}