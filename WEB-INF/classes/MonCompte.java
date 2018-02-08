import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.sql.*;

@WebServlet("/servlet/MonCompte")
public class MonCompte extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<html><head><title>Chop'ton Stage</title>");
		out.println("<link rel=\"stylesheet\" href=\"/CtS/CSS/index_Lucas.css\">");
		out.println("<META content=\"charset=UTF-8\"></head><body><center>");
		out.println("<h1> Mon Compte </h1>");

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e3) {
			System.err.println("driver non trouvé");
		}

		Connection con = null;
		// connexion à la base
		try {
			String url = "jdbc:postgresql:postgres";
			String nom = "root";
			String mdp = "root";
			con = DriverManager.getConnection(url, nom, mdp);
			Statement stmt = con.createStatement();

			out.println("<div id=\"menu\"><ul><li><a href=\"/CtS/Accueil.html\">Accueil</a></li><li><a href=\"#\">Recherche Stage</a></li><li><a href=\"#\">Contact Partenaires</a></li><li><a href=\"/CtS/servlet/MonCompte\">Mon Espace</a></li><li><a href=\"/CtS/servlet/Inscription\">Inscription</a></li></ul></div>");

			HttpSession session = req.getSession();
			String login = (String) session.getAttribute("login");
			String table = (String) session.getAttribute("table");
			
			if(login!=null){
				out.println("<h2>"+login+"</h2>");
			}
			else{
				res.sendRedirect("http://localhost:8080/CtS/loginDAO.jsp");
			}
			out.println("<img id=\"profile\" src=\"/CtS/Ressources/profile.png\" alt=\"Photo de profil\" />");
			out.println("<br /><a href=\"#\"><img src=\"/CtS/Ressources/parametres.png\" alt=\"Paramètres\" /></a>");
			out.println("<a href=\"/CtS/servlet/DeconnectDAO\"><img src=\"/CtS/Ressources/deconnexion.png\" alt=\"Paramètres\" /></a>");
			
			String queryT2 = "select * from "+ table + " where login = '"+login+"'";
			ResultSet rs = stmt.executeQuery(queryT2);
			while (rs.next()){
				out.println("<p> Nom : " + rs.getString("NOM") );
				out.println("<br /> Prénom : " + rs.getString("PRENOM")+ "</p>" );
			}
			
			
		} catch (Exception e) {
			System.err.println(e.toString() + " failed quelque part good luck have fun");
		} finally {
			try {
				con.close();
			} catch (Exception e2) {

			}
		}
	}
}