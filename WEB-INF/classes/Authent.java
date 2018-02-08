import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/servlet/Authent")
public class Authent extends HttpServlet
{
	public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
	{
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println( "<head><title>TestConnexion</title></head>" );
		out.println( "<META content=\"charset=UTF-8\"></head><body><center>" );
		out.println( "<h1>Connexion</h1>" );
		Connection con=null;

		try{
			// enregistrement du driver
			Class.forName("org.postgresql.Driver");
			
			// connexion à la base
			String url = "jdbc:postgresql://psqlserv/n3p1";
			String nom = "ablainl";
			String mdp = "moi";
			con = DriverManager.getConnection(url,nom,mdp);

			// execution de la requete
			Statement stmt = con.createStatement();
			
			String log = req.getParameter("login");
			String motdp = req.getParameter("mdp");
			
			HttpSession session = req.getSession( true );
			
			try{
				String queryT2 = "SELECT * from personne where login = \'"+ log +"\' and mdp = \'"+ motdp +"\'";
				ResultSet rs = stmt.executeQuery(queryT2);
				if(rs.next()){
					session.setAttribute( "login", rs.getString("login"));
					//out.println("<p>Vous êtes connecté avec le compte : " + rs.getString("nom") + " " +rs.getString("prenom") + "</p>");
					res.sendRedirect("http://localhost:8080/CtS/menu.html");
				}
				else{
					//out.println("<p>Authentification incorrecte</p>");
					res.sendRedirect("http://localhost:8080/CtS/login.html");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		catch(Exception e){
			System.out.println("error 404");
		}
		finally{
			try{con.close();}catch(Exception e2){}
		}
		out.println( "</center> </body>" );
	}
}