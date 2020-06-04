/*3.Visualizzazione degli orari disponibili per prenotare una struttura in un determinato giorno;*/
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

public class OP_03 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField struttura;
	private JTextField centro;
	private JTextField data;
	private JLabel lblData;

	/**
	 * Launch the application.
	 */
	public static void runOP_03() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OP_03 frame = new OP_03();
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
	public OP_03() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 326, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		struttura = new JTextField();
		struttura.setToolTipText("");
		struttura.setColumns(10);
		struttura.setBounds(10, 36, 96, 22);
		contentPane.add(struttura);
		
		JLabel lblOra = new JLabel("Struttura");
		lblOra.setBounds(10, 10, 80, 16);
		contentPane.add(lblOra);
		
		centro = new JTextField();
		centro.setToolTipText("");
		centro.setColumns(10);
		centro.setBounds(178, 36, 96, 22);
		contentPane.add(centro);
		
		JLabel lblCentro = new JLabel("Centro");
		lblCentro.setBounds(168, 10, 80, 16);
		contentPane.add(lblCentro);
		
		JButton btnOrariDisponibili = new JButton("Mostra orari disponibili");
		btnOrariDisponibili.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op03(e);
		    			}
		});
	
		btnOrariDisponibili.setBounds(10, 121, 298, 37);
		contentPane.add(btnOrariDisponibili);
		
		data = new JTextField();
		data.setToolTipText("");
		data.setColumns(10);
		data.setBounds(111, 89, 96, 22);
		contentPane.add(data);
		
		lblData = new JLabel("Data");
		lblData.setBounds(101, 63, 80, 16);
		contentPane.add(lblData);
		
	}
	public void op03(ActionEvent e) {
	    int []ora =new int[24];
	    for (int i=0;i<24;i++) ora[i]=i;
        int []oreOccupate=new int [24];
	    for (int i=0;i<24;i++) oreOccupate[i]=0;
        int []orePianificate=new int [24];
	    for (int i=0;i<24;i++) orePianificate[i]=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","Password00");
			//prende struttura, centro e data e restituisce ore occupate
			
			PreparedStatement prenotazione= con.prepareStatement("/* op 03*/SELECT prenotazione.oraInizio,prenotazione.durata\r\n" + 
					"FROM struttura\r\n" + 
					"JOIN prenotazione \r\n" + 
					"ON (struttura.codice = prenotazione.codiceStruttura \r\n" + 
					"AND struttura.nomeCentro = prenotazione.nomeCentro)\r\n" + 
					"WHERE \r\n" + 
					"prenotazione.data = ?\r\n" + 
					"and prenotazione.codiceStruttura=? \r\n" + 
					"and prenotazione.nomeCentro=?\r\n" + 
					"order by oraInizio;");
			prenotazione.setString(1, data.getText());
			prenotazione.setString(2, struttura.getText());
			prenotazione.setString(3, centro.getText());
			
			ResultSet x= prenotazione.executeQuery();	//number rows effected
			int i=0;
			int durata=0;
			while (x.next()) {
				String oraOccupata=x.getString(1).substring(0,2);
			//	System.out.println(oraOccupata);
				int y = Integer.parseInt(oraOccupata);
				System.out.println("\n\n y: "+y+"\n\n");
				oreOccupate[y]=-1;
				durata=x.getInt(2);
				int oraOccupataSuccessiva;
				for (i=0; i<durata;i++) {
					oraOccupataSuccessiva=Integer.parseInt(oraOccupata)+i;
					oreOccupate[oraOccupataSuccessiva]=-1;
				}
			}
			
			PreparedStatement pianificazione= con.prepareStatement("/* op 03*/SELECT pianificazione.ora,pianificazione.durata\r\n" + 
					"FROM struttura\r\n" + 
					"JOIN pianificazione \r\n" + 
					"ON (struttura.codice = pianificazione.codiceStruttura \r\n" + 
					"AND struttura.nomeCentro = pianificazione.nomeCentro)\r\n" + 
					"WHERE \r\n" + 
					"pianificazione.data = ?\r\n" + 
					"and pianificazione.codiceStruttura=? \r\n" + 
					"and pianificazione.nomeCentro=?\r\n" + 
					"order by ora;");
			pianificazione.setString(1, data.getText());
			pianificazione.setString(2, struttura.getText());
			pianificazione.setString(3, centro.getText());
			
			ResultSet z= pianificazione.executeQuery();	//number rows effected
			while (z.next()) {
				String oraOccupata=z.getString(1).substring(0,2);
			//	System.out.println(oraOccupata);
				int y = Integer.parseInt(oraOccupata);
				orePianificate[y]=-1;
				durata=z.getInt(2);
				int oraOccupataSuccessiva;
				for (i=0; i<durata;i++) {
					oraOccupataSuccessiva=Integer.parseInt(oraOccupata)+i;
					orePianificate[oraOccupataSuccessiva]=-1;
				}
			}

				for (i=8;i<22;i++) {
					if ((oreOccupate[i]==-1) || (orePianificate[i]==-1))
						;
					else System.out.println(ora[i]+":00");
				}
				
		}catch(Exception e1) {
			System.out.println(e1);
		}
	}
}