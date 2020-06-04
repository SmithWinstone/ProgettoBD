/*
13.Caricamento di un corso organizzato da un centro, con líassegnazione di eventuali allenatori;
CARICAMENTOCORSO.JAVA
FATTO DA ALESSANDRA
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

public class OP_13 extends JFrame {

  /**
  * 
  */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField descrizione;
	private JTextField codice;		
	private JTextField periodicit‡;
	private JTextField durata;
	private JTextField centro;
	private JTextField cfAllenatore1;
	private JTextField specializzazione;
	private JTextField data;
	private JLabel lblData;
	private JTextField ora;
	private JLabel lblOra;

  /* Launch the application.
   */
	public static void runOP_13() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OP_13 frame = new OP_13();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

  /* Create the frame.
   */
	public OP_13() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 332, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
    
		JLabel lblNomeCentro = new JLabel("Nome centro");
		lblNomeCentro.setBounds(10, 11, 294, 16);
		contentPane.add(lblNomeCentro);
    
		JButton btnCaricaCorso = new JButton("Carica corso");
		btnCaricaCorso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op13(e);
			}
		});
		btnCaricaCorso.setBounds(10, 334, 294, 29);
		contentPane.add(btnCaricaCorso);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setBounds(10, 60, 294, 16);
		contentPane.add(lblDescrizione);
    
		descrizione = new JTextField();
		descrizione.setColumns(10);
		descrizione.setBounds(10, 75, 294, 22);
		contentPane.add(descrizione);
		
		JLabel lblCodice = new JLabel("Codice");
		lblCodice.setBounds(10,103, 294, 16);
		contentPane.add(lblCodice);
    
		codice = new JTextField();
		codice.setColumns(10);
		codice.setBounds(10, 117, 294, 22);
		contentPane.add(codice);
    
		JLabel lblPeriodicita = new JLabel("Periodicit\u00E0");
		lblPeriodicita.setBounds(10, 141, 148, 16);
		contentPane.add(lblPeriodicita);
		
		periodicit‡ = new JTextField();
		periodicit‡.setColumns(10);
		periodicit‡.setBounds(10, 159, 135, 22);
		contentPane.add(periodicit‡);
		
		JLabel lblDurataCorso = new JLabel("Durata corso");
		lblDurataCorso.setBounds(168, 141, 148, 16);
		contentPane.add(lblDurataCorso);
		
		durata = new JTextField();
		durata.setColumns(10);
		durata.setBounds(169, 159, 135, 22);
		contentPane.add(durata);
		
		centro = new JTextField();
		centro.setColumns(10);
		centro.setBounds(10, 28, 294, 22);
		contentPane.add(centro);
		
		JLabel lblCfAllenatore = new JLabel("CF allenatore");
		lblCfAllenatore.setBounds(10, 185, 148, 16);
		contentPane.add(lblCfAllenatore);
		
		cfAllenatore1 = new JTextField();
		cfAllenatore1.setColumns(10);
		cfAllenatore1.setBounds(10, 199, 294, 22);
		contentPane.add(cfAllenatore1);
		
		JLabel lblEventualeSpecializzazione = new JLabel("Eventuale Specializzazione");
		lblEventualeSpecializzazione.setBounds(10, 288, 148, 16);
		contentPane.add(lblEventualeSpecializzazione);
		
		specializzazione = new JTextField();
		specializzazione.setColumns(10);
		specializzazione.setBounds(10, 302, 294, 22);
		contentPane.add(specializzazione);
		
		data = new JTextField();
		data.setColumns(10);
		data.setBounds(11, 249, 135, 22);
		contentPane.add(data);
		
		lblData = new JLabel("Data");
		lblData.setBounds(10, 231, 148, 16);
		contentPane.add(lblData);
		
		ora = new JTextField();
		ora.setColumns(10);
		ora.setBounds(157, 249, 135, 22);
		contentPane.add(ora);
		
		lblOra = new JLabel("Ora");
		lblOra.setBounds(156, 231, 148, 16);
		contentPane.add(lblOra);
		
	}
	public void op13(ActionEvent e) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","Password00");
			
			PreparedStatement ps= con.prepareStatement("INSERT INTO attivit‡ (codice, descrizione, corso, periodicit‡Corso, "
					+ "durataCorso)\r\n" + 
					"VALUES (?,?,'corso',?,?);");
			ps.setString(1, codice.getText());
			ps.setString(2, descrizione.getText());
			ps.setString(3, periodicit‡.getText());
			ps.setString(4, durata.getText());
			
			PreparedStatement ps2= con.prepareStatement("INSERT INTO abilitazione(nomeCentro, codiceAttivit‡)\r\n" +
					"VALUES (?, ?);");
			ps2.setString(1, centro.getText());
			ps2.setString(2, codice.getText());
			
			
			PreparedStatement ps3= con.prepareStatement("INSERT INTO insegnamento(cfIstruttore, codiceAttivit‡,ora,data)\r\n" + 
					"VALUES (?,?,?,?);");
			ps3.setString(1, cfAllenatore1.getText());
			ps3.setString(2, codice.getText());
			ps3.setString(3, ora.getText());
			ps3.setString(4, data.getText());
			
			PreparedStatement spec=con.prepareStatement("select cf "
					+ "from istruttore "
					+ "where cf = ? "
            		+ "and specializzazione = ?");
            spec.setString(1, cfAllenatore1.getText());
            spec.setString(2, specializzazione.getText());

            PreparedStatement occupato=con.prepareStatement("select * from \r\n" + 
            		"insegnamento join attivit‡ on insegnamento.codiceAttivit‡ = attivit‡.codice\r\n" + 
            		"where cfIstruttore=?\r\n" + 
            		"and data=?\r\n" + 
            		"and ? <=(insegnamento.ora+ interval attivit‡.durataCorso hour)\r\n" + 
            		"and (addtime(?,?) >=insegnamento.ora)"+
            		"and periodicit‡Corso=?;");
            occupato.setString(1, cfAllenatore1.getText());
            occupato.setString(2, data.getText());
            occupato.setString(3, ora.getText());
            occupato.setString(4, ora.getText());
            occupato.setString(5, durata.getText());
            occupato.setString(6, periodicit‡.getText());
            ResultSet allenatoreOccupato=occupato.executeQuery();
            
            if(allenatoreOccupato.next()){ System.out.println("ALLENATORE IMPEGNATO");}
            else{
            	ResultSet allen = spec.executeQuery();
            	if(!specializzazione.getText().equals("")) {
            		if (allen.next()) {
            			int attivit‡= ps.executeUpdate();  //number rows effected
            			int abilitazione= ps2.executeUpdate();
            			int insegnamento= ps3.executeUpdate();
          
            			if (attivit‡>0&&abilitazione>0&&insegnamento>0)  {
            				System.out.println("Caricamento avvenuto con successo!");
            			}
            			else {
            				System.out.println("Errore nel caricamento!");
            			}
            		}
            		else {
            			System.out.println("l'allenatore non Ë specializzato in questo campo!");
            			}
            	}
            	else {
            		int attivit‡= ps.executeUpdate();  //number rows effected
            		int abilitazione= ps2.executeUpdate();
            		int insegnamento= ps3.executeUpdate();
            		
            		if (attivit‡>0&&abilitazione>0&&insegnamento>0)  {
            			System.out.println("Caricamento avvenuto con successo!");
            		}
            		else {
            			System.out.println("Errore nel caricamento!");
            		}
            	}
            }
        }catch(Exception e1) {
        	System.out.println(e1);
        }
  	}
}