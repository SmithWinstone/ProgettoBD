/*
15.Stampa annuale di un report che mostri i dati delle strutture, incluso il numero di ore in cui 
sono state occupate negli ultimi due anni;
 
*/import java.sql.*;

public class OP_15 {
  
  public static void runOP_15() {
        try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         String url = "jdbc:mysql://localhost:3306/catena"
                       + "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
                       + "&useLegacyDatetimeCode=false&serverTimezone=UTC";
                   Connection con = DriverManager.getConnection(url,"root","Password00");                   
                   PreparedStatement ps= con.prepareStatement("SELECT struttura.codice, struttura.nomeCentro, tipo, mq, coperturaCampo, SUM(durata) AS numOre\r\n" + 
                    "FROM struttura JOIN pianificazione \r\n" + 
                    "WHERE struttura.nomeCentro= pianificazione.nomeCentro AND codice=codiceStruttura AND pianificazione.data>SUBDATE(current_date(), INTERVAL 2 Year)\r\n" + 
                    "AND pianificazione.svolta='si'\r\n"+
                	"GROUP BY nomeCentro,codice;");                                
                   ResultSet x= ps.executeQuery();  //number rows effected                   
                    while (x.next())  {
                        System.out.println("\rcodice: " + x.getString(1) + "\nnome centro: "+ x.getString(2) + "\rtipo: "+
                       x.getString(3)+"\rmq: "+ x.getInt(4)+ "\rcopertura campo: "+ x.getString(5)+ "\rdurata: "+
                          x.getInt(6)+"\r");
                       }      
        } catch (Exception e) {
          e.printStackTrace();
        }    
  }
}