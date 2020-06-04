package prove;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class provaop03 {
	public static void main(String [] args) {
	    String[] ora = {"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
        int []oreOccupate=new int [14];
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","Password00");
			//prende struttura, centro e data e restituisce ore occupate
			PreparedStatement ps= con.prepareStatement("SELECT prenotazione.oraInizio,prenotazione.durata\r\n" + 
					"FROM struttura\r\n" + 
					"JOIN prenotazione\r\n" + 
					"ON (struttura.codice = prenotazione.codiceStruttura \r\n" + 
					"	and struttura.nomeCentro = prenotazione.nomeCentro)\r\n" + 
					"WHERE\r\n" + 
					"	prenotazione.data = ?\r\n" + 
					"	and prenotazione.codiceStruttura=?\r\n" + 
					"	and prenotazione.nomeCentro=?;\r\n" + 
					"	");
			ps.setString(1, "2020-01-10");
			ps.setString(2, "codice3");
			ps.setString(3, "Milano");
			
			ResultSet x= ps.executeQuery();	//number rows effected
			if(x.next()) {
				int j=0;
				int durata=x.getInt(2);
				int i=0;
				int y=0;
				while(x.next()) {
				String oraOccupata=x.getString(1).substring(0,2);
				for (i=0; i<durata;i++) {
					y=Integer.parseInt(oraOccupata)+i;
					oreOccupate[i]=y;
				}
				}
				j=0;
				//confronto array di ora con oreOccupate
				System.out.println("Ore libere: ");
				for (i=0;i<14;i++) {
					int ora1=Integer.parseInt(ora[i]);
					int oraOccupata1=oreOccupate[j];
					if (ora1==oraOccupata1) {
						j++;
					}
					else
						System.out.println(ora[i]);
				}
			}
			else System.out.println("QUALCOSA E' ANDATO STORTO!");

		}catch(Exception e1) {
			System.out.println(e1);
		}

	}
}
