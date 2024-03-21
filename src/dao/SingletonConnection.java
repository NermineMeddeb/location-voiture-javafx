package dao;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	public class SingletonConnection {

		private static Connection con; //l'attribut est privé donc on fait un getter

		public static Connection getCon() {
			return con;
		}
		static {
			//specifier quel driver elle utilise
			try {//forName peut declancher une exception
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}// il comprend qu'il va etablir une connexion a un sgbd sql
			try {//peut declancher une exception
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/location_voiture?useTimezone=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
				System.out.println("connexion éffectuée avec succès...");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

