/*
1.Prenotazione di una struttura;	 
-Non è possibile prenotare un tipo di struttura se non è presente nessuna struttura in cui poterlo effettuare.
-Quando la prenotazione prevede l'utilizzo di attrezzature la sala oltre ad essere disponibile dee contenere attrezzature
PRENOTAZIONE.JAVA
FATTO BETA  
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

public class OP_01 extends JFrame {

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
	private JLabel lblAttrezzatura;
	private JTextField attrezzatura;

	/**
	 * Launch the application.
	 */
	public static void runOP_01() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OP_01 frame = new OP_01();
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
	public OP_01() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 332, 506);
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
				//if(/*condizioni verificate*/)
				if(!attrezzatura.getText().equals("")) {
					try {
						if(attrezzatura(e)) {
							if(libera(e)) {
								op01(e);
							}
						}
						else {
							System.out.println("La struttura specificata non è attrezzata di "+ attrezzatura.getText());
							}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else/*if not attrezzatura*/ {
					if(libera(e)) {
						op01(e);
					}
				}
				
			}
		});
		btnPrenotazione.setBounds(10, 422, 126, 37);
		contentPane.add(btnPrenotazione);
		
		JLabel lblDurata = new JLabel("Durata");
		lblDurata.setBounds(88, 242, 80, 16);
		contentPane.add(lblDurata);
		
		durata = new JTextField();
		durata.setToolTipText("");
		durata.setColumns(10);
		durata.setBounds(88, 268, 49, 22);
		contentPane.add(durata);
		
		lblAttrezzatura = new JLabel("Eventuale attrezzatura richiesta");
		lblAttrezzatura.setBounds(10, 358, 238, 16);
		contentPane.add(lblAttrezzatura);
		
		attrezzatura = new JTextField();
		attrezzatura.setColumns(10);
		attrezzatura.setBounds(10, 384, 238, 22);
		contentPane.add(attrezzatura);
	}
	
	
	
	public boolean attrezzatura(ActionEvent e) throws Exception {
		boolean result=false;
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/catena"
				+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
				+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
		Connection con = DriverManager.getConnection(url,"root","Password00");
		PreparedStatement ps= con.prepareStatement("select nomeCentro,codiceStruttura from attrezzatura where nome = ?");
		ps.setString(1, attrezzatura.getText());
		ResultSet x=ps.executeQuery();
		while(x.next()) {
			if(x.getString(1).equals(nomeCentro.getText()) && x.getString(2).equals(codiceStruttura.getText())) {
				result=true;
			}
		}

		return result;
	}
	
	public boolean libera(ActionEvent e) { 	//simile ad op02
		boolean result=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","Password00");
			PreparedStatement prenotazioni= con.prepareStatement("SELECT * /*struttura.codice,struttura.nomeCentro*/\r\n" + 
					"FROM struttura\r\n" + 
					"JOIN prenotazione\r\n" + 
					"ON (struttura.codice = prenotazione.codiceStruttura and struttura.nomeCentro=prenotazione.nomeCentro)\r\n" + 
					"WHERE\r\n" + 
					"prenotazione.nomeCentro=?\r\n" + 
					"AND prenotazione.codiceStruttura=?\r\n" + 
					"AND prenotazione.data = ?  \r\n" + 
					"AND ? <= (prenotazione.oraInizio+interval prenotazione.durata hour)\r\n" + 
					"AND (addtime(?,?) >= prenotazione.oraInizio);");
			prenotazioni.setString(1, nomeCentro.getText());			
			prenotazioni.setString(2, codiceStruttura.getText());
			prenotazioni.setString(3, data.getText());
			prenotazioni.setString(4, oraInizio.getText()+":02");
			prenotazioni.setString(5, oraInizio.getText());
			prenotazioni.setString(6, durata.getText()+"0000");
			
			PreparedStatement pianificazioni= con.prepareStatement("SELECT * /*struttura.codice,struttura.nomeCentro*/ \r\n" + 
					"FROM struttura \r\n" + 
					"JOIN pianificazione\r\n" + 
					"ON (struttura.codice = pianificazione.codiceStruttura and struttura.nomeCentro=pianificazione.nomeCentro)\r\n" + 
					"WHERE\r\n" + 
					"pianificazione.nomeCentro=?\r\n" + 
					"AND pianificazione.codiceStruttura=? \r\n" + 
					"AND pianificazione.data = ?\r\n" + 
					"AND  ?<= (pianificazione.ora+interval pianificazione.durata hour)\r\n" + 
					"AND (addtime(?,?) >= pianificazione.ora);");
			pianificazioni.setString(1, nomeCentro.getText());			
			pianificazioni.setString(2, codiceStruttura.getText());
			pianificazioni.setString(3, data.getText());
			pianificazioni.setString(4, oraInizio.getText()+":02");
			pianificazioni.setString(5, oraInizio.getText());
			pianificazioni.setString(6, durata.getText()+"0000");

			
			ResultSet x= prenotazioni.executeQuery();	//number rows effected
			ResultSet y=pianificazioni.executeQuery();
			if (x.next() || y.next()) {
				System.out.println("Struttura occupata!");
				result=false;
			}
			else{
				result = true;
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}
		return result;
	}
	
	
	public void op01(ActionEvent e) {				
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
	}}
}