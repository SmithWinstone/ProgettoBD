package finale;

/*

4.Visualizzazione dei giorni disponibili per prenotare una struttura in un determinato orario;
GIORNIDISPONIBILI.JAVA
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

public class OP_04 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField struttura;
	private JTextField centro;
	private JTextField mese;
	private JLabel lblMese;
	private JTextField oraInizio;
	private JTextField durata;

	/**
	 * Launch the application.
	 */
	public static void runOP_04() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OP_04 frame = new OP_04();
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
	public OP_04() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 326, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		struttura = new JTextField();
		struttura.setToolTipText("");
		struttura.setColumns(10);
		struttura.setBounds(10, 36, 144, 22);
		contentPane.add(struttura);
		
		JLabel lblOra = new JLabel("Struttura");
		lblOra.setBounds(10, 11, 80, 16);
		contentPane.add(lblOra);
		
		centro = new JTextField();
		centro.setToolTipText("");
		centro.setColumns(10);
		centro.setBounds(164, 36, 144, 22);
		contentPane.add(centro);
		
		JLabel lblCentro = new JLabel("Centro");
		lblCentro.setBounds(165, 11, 80, 16);
		contentPane.add(lblCentro);
		
		JButton btnOrariDisponibili = new JButton("Mostra giorni disponibili");
		btnOrariDisponibili.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op04(e);
			}
		});
		btnOrariDisponibili.setBounds(10, 137, 298, 37);
		contentPane.add(btnOrariDisponibili);
		
		mese = new JTextField();
		mese.setToolTipText("");
		mese.setColumns(10);
		mese.setBounds(228, 88, 80, 22);
		contentPane.add(mese);
		
		lblMese = new JLabel("Mese(numero)");
		lblMese.setBounds(228, 69, 96, 16);
		contentPane.add(lblMese);
		
		JLabel lblOraInizio = new JLabel("Ora inizio");
		lblOraInizio.setBounds(10, 69, 80, 16);
		contentPane.add(lblOraInizio);
		
		oraInizio = new JTextField();
		oraInizio.setToolTipText("");
		oraInizio.setColumns(10);
		oraInizio.setBounds(10, 89, 80, 22);
		contentPane.add(oraInizio);
		
		JLabel lblDurata = new JLabel("Durata");
		lblDurata.setBounds(118, 69, 80, 16);
		contentPane.add(lblDurata);
		
		durata = new JTextField();
		durata.setToolTipText("");
		durata.setColumns(10);
		durata.setBounds(118, 88, 80, 22);
		contentPane.add(durata);
		
	}
	public void op04(ActionEvent e) {
	       
		String[] giorni={"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
	        		"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30","31"};
     String[] giorniOccupati= new String[31];
     for (int i=0;i<31;i++)
     	giorniOccupati[i]="0";
     
		String m=mese.getText();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/catena"
						+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
						+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
				Connection con = DriverManager.getConnection(url,"root","Password00");
				//prende struttura, centro e data e restituisce ore occupate
				PreparedStatement ps= con.prepareStatement("SELECT day(prenotazione.data)\r\n" + 
						"FROM struttura\r\n" + 
						"JOIN prenotazione \r\n" + 
						"ON (struttura.codice = prenotazione.codiceStruttura  \r\n" + 
						"AND struttura.nomeCentro = prenotazione.nomeCentro) \r\n" + 
						"WHERE\r\n" + 
						"month(prenotazione.data) = ? \r\n" + 
						"AND prenotazione.codiceStruttura=?\r\n" + 
						"AND prenotazione.nomeCentro=?\r\n" + 
						"AND ? <= (prenotazione.oraInizio+interval prenotazione.durata hour)\r\n" + 
						"AND (addtime(?,?) >= prenotazione.oraInizio);");
				ps.setString(1, mese.getText()+":01");
				ps.setString(2, struttura.getText());
				ps.setString(3, centro.getText());
				ps.setString(4, oraInizio.getText()+":02");
				ps.setString(5, oraInizio.getText());
				ps.setString(6, durata.getText());
								

				ResultSet x= null;
				x=ps.executeQuery();	//number rows effected
					int j=0;

					//creo array buf con ore occupate
					while (x.next()) {
						giorniOccupati[j]=(x.getString(1));
						j++;
					}				
					j=0;
					int i=0;
					if(m.equals("04")||m.equals("06")||m.equals("09")||m.equals("11")
							||m.equals("4")||m.equals("6")||m.equals("9")) {
						//confronto array di giorni con giorniOccupati
						for (i=0;i<30;i++) {
							if (giorni[i].equals(giorniOccupati[j])) {
								j++;
							}
							else
								System.out.println(giorni[i]);
						}
					}
					else if(m.equals("02")||m.equals("2")) {
						//confronto array di giorni con giorniOccupati
						for (i=0;i<28;i++) {
							if (giorni[i].compareTo(giorniOccupati[j])==0) {
								j++;
							}
							else
								System.out.println(giorni[i]);
						}
					}
					else {
						for (i=0;i<31;i++) {
							if (giorni[i].equals(giorniOccupati[j])) {
								j++;
							}
							else
								System.out.println(giorni[i]);
						}
					}

			}catch(Exception e1) {
				System.out.println(e1);
			}

	}
}