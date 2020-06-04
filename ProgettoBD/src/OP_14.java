/*
14.Stampa annuale di un report che mostri i dati delle strutture, incluso il numero totale di giorni 
in cui è stata libera;
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OP_14 {
	public static void runOP_14() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection con = DriverManager.getConnection(url,"root","Password00");
			PreparedStatement ps= con.prepareStatement("select struttura.codice , struttura. nomeCentro , struttura.tipo , mq , coperturaCampo, count(data) as giorni\r\n" + 
					"from struttura join pianificazione\r\n" + 
					"where struttura.nomeCentro=pianificazione.nomeCentro and codice=codiceStruttura and pianificazione.data > subdate(current_date(), interval 1 year)\r\n" +
					"and pianificazione.svolta = 'si \r\n" +
					"group by nomeCentro, codice;");
			ResultSet x= ps.executeQuery();	//number rows effected
			int giorniLiberi=360;
			while(x.next()) {
				giorniLiberi=360-x.getInt(6);
				System.out.println("codice struttura: " + x.getString(1) + "\t| nome centro : "
						+x.getString(2)+"\t| tipo : "+x.getString(3)+"\t| mq : "+x.getInt(4)+"\t| copertura : "+x.getString(5)+"\t| giorni liberi : "+ giorniLiberi);
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}

	}
}
