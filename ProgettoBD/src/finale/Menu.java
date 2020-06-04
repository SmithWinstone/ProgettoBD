package finale;

import java.sql.DriverManager;

import java.util.*;
public class Menu {

	private static Scanner k;

	public static void main(String []args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/catena"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
			DriverManager.getConnection(url,"root","Password00");
		}catch(Exception e1) {
			System.out.println(e1);
		}
		
		k = new Scanner(System.in);
		int inserito = 0;
		
		do {
			System.out.println("Scegliere l'operazione da svolgere digitando il numero corrispondente.");
			System.out.println("0.Esci.");
			System.out.println("1.Prenotazione di una struttura.");
			System.out.println("2.Verifica della possibilità di prenotare una struttura per un determinato giorno dell’anno ad una determinata ora.");
			System.out.println("3.Visualizzazione degli orari disponibili per prenotare una struttura in un determinato giorno.");
			System.out.println("4.Visualizzazione dei giorni disponibili per prenotare una struttura in un determinato orario.");
			System.out.println("5.Svolgimento di un’attività.");
			System.out.println("6.Visualizzazione per ogni attività del numero di ore in cui sono state svolte in un anno.");
			System.out.println("7.Abilitazione di un nuovo centro allo svolgimento di un’attività.");
			System.out.println("8.Assunzione di un nuovo allenatore.");
			System.out.println("9.Visualizzazione della struttura in cui sono state svolte il maggior numero di attività nell’anno corrente.");
			System.out.println("10.Visualizzazione di tutti gli allenatori specializzati in una determinata disciplina.");
			System.out.println("11.Cancellazione di uno dei responsabili di un centro, con elezione di un nuovo responsabile.");
			System.out.println("12.Modifica dell’orario della prenotazione di una struttura (se possibile).");
			System.out.println("13.Caricamento di un corso organizzato da un centro, con l’assegnazione di eventuali allenatori.");
			System.out.println("14.Stampa annuale di un report che mostri i dati delle strutture, incluso il numero totale di giorni in cui è stata libera.");
			System.out.println("15.Stampa annuale di un report che mostri i dati delle strutture, incluso il numero di ore in cui sono state occupate negli ultimi due anni.");
			inserito=k.nextInt();
		
			switch (inserito) {
			case 0:
				System.exit(0);
			case 1:
				OP_01.runOP_01();
				break;
			case 2:
				OP_02.runOP_02();
				break;
			case 3:
				OP_03.runOP_03();
				break;
			case 4:
				OP_04.runOP_04();
				break;
			case 5:
				OP_05.runOP_05();
				break;
			case 6:
				OP_06.runOP_06();
				break;
			case 7:
				OP_07.runOP_07();
				break;
			case 8:
				OP_08.runOP_08();
				break;
			case 9:
				OP_09.runOP_09();
				break;
			case 10:
				OP_10.runOP_10();
				break;
			case 11:
				OP_11.runOP_11();
				break;
			case 12:
				OP_12.runOP_12();
				break;
			case 13:	
				OP_13.runOP_13();
				break;
			case 14:
				OP_14.runOP_14();
				break;	
			case 15:
				OP_15.runOP_15();
				break;
			}			
		}while(inserito!=0);
	}
}
