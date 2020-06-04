/*
9.Visualizzazione della struttura in cui sono state svolte il maggior numero di attività nell’anno 
corrente;
STRUTTURAMAGGIORNUMEROATTIVITà.JAVA
FATTO
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OP_09 {
	public static void runOP_09() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","Password00");
			PreparedStatement ps= con.prepareStatement("SELECT codiceStruttura, nomeCentro, COUNT(*)\r\n" + 
					"FROM pianificazione\r\n" +
					"WHERE pianificazione.svolta = 'si' \r\n" +
					"GROUP BY nomeCentro , codiceStruttura\r\n" + 
					"ORDER BY COUNT(*) DESC;\r\n" + 
					"");
			ResultSet x= ps.executeQuery();	//number rows effected
			if(x.next())
				System.out.println("codice struttura: " + x.getString(1) + " | nome centro : "+x.getString(2)+" |numero attivià: "+x.getInt(3));
		}catch(Exception e1) {
			System.out.println(e1);
		}

	}
}
