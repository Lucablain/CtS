import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/servlet/Inscription")
public class Inscription extends HttpServlet
{
	int cpt=0;
	boolean Errormdp = false;
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<head><title>Inscription</title>");
		out.println("<link rel=\"stylesheet\" href=\"/CtS/CSS/index_Lucas.css\">");
		out.println("<META content=\"charset=UTF-8\"></head><body><center>");
		out.println("<h1> Inscription </h1>");
		Connection con=null;
		
		try{
			// enregistrement du driver
			Class.forName("org.postgresql.Driver");
						
			// connexion à la base
			String url = "jdbc:postgresql:postgres";
			String nom = "root";
			String mdp = "root";
			con = DriverManager.getConnection(url,nom,mdp);

			// execution de la requete
			Statement stmt = con.createStatement();
			
			HttpSession session = req.getSession();
			
			System.out.println(Errormdp);
			
			out.println("<div id=\"menu\"><ul><li><a href=\"/CtS/Accueil.html\">Accueil</a></li><li><a href=\"#\">Recherche Stage</a></li><li><a href=\"#\">Contact Partenaires</a></li><li><a href=\"/CtS/servlet/MonCompte\">Mon Espace</a></li><li><a href=\"/CtS/servlet/Inscription\">Inscription</a></li></ul></div>");
			
			if(Errormdp==true){
				out.println("<h2>ERREUR DE MOT DE PASSE</h2>");
				Errormdp=false;
				System.out.println(Errormdp);
			}
			
			out.println("<form method=\"post\"><p>");
			out.println("<label for=\"nom\">Votre Nom : </label>");
			out.println("<input type=\"text\" name=\"nom\" id=\"nom\" />");
			out.println("<br /><label for=\"prenom\">Votre Prénom : </label>");
			out.println("<input type=\"text\" name=\"prenom\" id=\"prenom\" />");
			out.println("<br /><label for=\"login\">Votre Login : </label>");
			out.println("<input type=\"text\" name=\"login\" id=\"login\" />");
			out.println("<br /><label for=\"mdp\">Mot de Passe : </label>");
			out.println("<input type=\"password\" name=\"mdp\" id=\"mdp\" />");
			out.println("<br /><label for=\"Cmdp\">Confirmer Mot de Passe : </label>");
			out.println("<input type=\"password\" name=\"Cmdp\" id=\"Cmdp\" />");
			out.println("<br /><input type=\"checkbox\" id=\"checkEnt\" name=\"table\" value=\"entreprise\" />");
			out.println("<label for=\"checkEnt\">Entreprise</label>");
			out.println("<input type=\"checkbox\" id=\"checkEc\" name=\"table\" value=\"ecole\" />");
			out.println("<label for=\"checkEc\">Ecole</label>");
			out.println("<input type=\"checkbox\" id=\"checkEtu\" name=\"table\" value=\"etudiant\" />");
			out.println("<label for=\"checkEtu\">Etudiant</label>");
			out.println("<br /><input type=\"submit\" value=\"Envoyer\" />");
			out.println("</p></form>");
			
			System.out.println(Errormdp);
			
		}
		catch(Exception e){
			System.out.println("error 404");
		}
		finally{
			try{con.close();}catch(Exception e2){}
		}
		out.println( "</center> </body>" );
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println( "<head><title>Modif Informations</title></head>" );
		out.println( "<META content=\"charset=UTF-8\"></head><body><center>" );
		Connection con=null;
		
		try{
			// enregistrement du driver
			Class.forName("org.postgresql.Driver");
						
			// connexion à la base
			String url = "jdbc:postgresql:postgres";
			String nom = "root";
			String mdp = "root";
			con = DriverManager.getConnection(url,nom,mdp);

			// execution de la requete
			Statement stmt = con.createStatement();
			
			HttpSession session = req.getSession();
			
			System.out.println(Errormdp);
			
			String mdpA = req.getParameter("mdp");
			String mdpB = req.getParameter("Cmdp");
			
			if(mdpA.equals(mdpB)){
				if(req.getParameter("nom")!=null){
					System.out.println("Création Compte");
					String queryNom = "insert into "+req.getParameter("table")+" values ("+cpt+",'"+req.getParameter("nom")+"','"+req.getParameter("prenom")+"','"+req.getParameter("login")+"','"+req.getParameter("mdp")+"');";
					int rs = stmt.executeUpdate(queryNom);
				}
				cpt++;
				res.sendRedirect("http://localhost:8080/CtS/servlet/MonCompte");
			}
			else{
				Errormdp=true;
				System.out.println(Errormdp);
				res.sendRedirect("http://localhost:8080/CtS/servlet/Inscription");
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{con.close();}catch(Exception e2){}
		}
		out.println( "</center> </body>" );
	}
}