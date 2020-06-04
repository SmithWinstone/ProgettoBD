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

public class giorniDisponibili extends JFrame {
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					giorniDisponibili frame = new giorniDisponibili();
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
	public giorniDisponibili() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		lblCentro.setBounds(178, 10, 80, 16);
		contentPane.add(lblCentro);
		
		JButton btnOrariDisponibili = new JButton("Mostra orari disponibili");
		btnOrariDisponibili.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		       
			String[] giorni={"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
		        		"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30","31"};
	        String[] giorniOccupati= new String[31];
	        for (int i=0;i<31;i++)
	        	giorniOccupati[i]="0";
	        
			String m="02";
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/catena"
							+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
							+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
					Connection con = DriverManager.getConnection(url,"root","Password00");
					//prende struttura, centro e data e restituisce ore occupate
					PreparedStatement ps= con.prepareStatement("SELECT distinct day(prenotazione.data)\r\n" + 
							"FROM struttura\r\n" + 
							"JOIN prenotazione\r\n" + 
							"ON (struttura.codice = prenotazione.codiceStruttura \r\n" + 
							"	and struttura.nomeCentro = prenotazione.nomeCentro)\r\n" + 
							"WHERE\r\n" + 
							" prenotazione.oraInizio = ?\r\n" + 
							" and prenotazione.codiceStruttura= ? \r\n" + 
							" and prenotazione.nomeCentro= ? \r\n" + 
							" and month(prenotazione.data) = ? \r\n" + 
							" order by day(prenotazione.data);");
					ps.setString(1, oraInizio.getText()+":01");
					ps.setString(2, struttura.getText());
					ps.setString(3, centro.getText());
					ps.setString(4, mese.getText());

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
		});
		btnOrariDisponibili.setBounds(10, 121, 298, 37);
		contentPane.add(btnOrariDisponibili);
		
		mese = new JTextField();
		mese.setToolTipText("");
		mese.setColumns(10);
		mese.setBounds(178, 89, 60, 22);
		contentPane.add(mese);
		
		lblMese = new JLabel("Mese");
		lblMese.setBounds(178, 63, 80, 16);
		contentPane.add(lblMese);
		
		JLabel lblOraInizio = new JLabel("Ora ");
		lblOraInizio.setBounds(10, 63, 80, 16);
		contentPane.add(lblOraInizio);
		
		oraInizio = new JTextField();
		oraInizio.setToolTipText("");
		oraInizio.setColumns(10);
		oraInizio.setBounds(10, 89, 60, 22);
		contentPane.add(oraInizio);
		
	}
}