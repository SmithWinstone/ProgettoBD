package finale;

/*

6.Visualizzazione per ogni attività del numero di ore in cui sono state svolte in un anno;
OREATTIVITàSVOLTE.JAVA
FATTO
*/import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OP_06 {
	public static void runOP_06() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","Password00");
			PreparedStatement ps= con.prepareStatement("SELECT codiceAttività AS attività,\r\n" + 
					"SUM(durata) AS 'ore totali' \r\n" + 
					"FROM pianificazione\r\n" + 
					"WHERE pianificazione.data > subdate(current_date(),interval 1 year)\r\n" +
					"and pianificazione.svolta='si' \r\n" +
					"GROUP BY codiceAttività;");
			ResultSet x= ps.executeQuery();	//number rows effected
			while(x.next())
				System.out.println("attività: "+x.getString(1)+" | ore: "+ x.getInt(2));

		}catch(Exception e1) {
			System.out.println(e1);
		}
	}
}
