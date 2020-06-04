package prove;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OreAttivit�Svolte {
	public static void main(String[]args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","Password00");
			PreparedStatement ps= con.prepareStatement("SELECT codiceAttivit� AS attivit�,\r\n" + 
					"SUM(durata) AS 'ore totali' \r\n" + 
					"FROM svolgimento\r\n" + 
					"WHERE svolgimento.data > subdate(current_date(),interval 1 year)\r\n" + 
					"GROUP BY codiceAttivit�;");
			ResultSet x= ps.executeQuery();	//number rows effected
			while(x.next())
				System.out.println("attivit�: "+x.getString(1)+" | ore: "+ x.getInt(2));

		}catch(Exception e1) {
			System.out.println(e1);
		}
	}
}
