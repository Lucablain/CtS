<!DOCTYPE html>
<html>
	<head>
		<%@ page import="java.sql.*" %>
		<%@ page pageEncoding="UTF-8" %>
		<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> -->
		<title> Connexion </title>
		<link href="/CtS/CSS/index_Lucas.css" rel="stylesheet" />
	</head>
	<body>
		<%
			Class.forName("org.postgresql.Driver");
			Connection con = null;
			String url = "jdbc:postgresql:postgres";
			String nom = "root";
			String mdp = "root";
			con = DriverManager.getConnection(url, nom, mdp);
			Statement stmt = con.createStatement();
		%>
		
		<h1><a href="/CtS/menuDAO.html">Chop'ton Stage</a></h1>
		<h2>Connexion</h2>

		<% String login = (String) session.getAttribute("login");	%>
		<% if(login=="null"||login == null){ %>
		<form method="get" action="servlet/Authentif">
		   <p>
		       <label for="login">Votre Login :</label>
		       <input type="text" name="login" id="login" />
		       
		       <br />
		       <label for="mdp">Votre Mot de Passe :</label>
		       <input type="password" name="mdp" id="mdp" />
		     
		       <br /> 
		       <input type="checkbox" id="checkEnt" name="table" value="entreprise">
	   		   <label for="checkEnt">Entreprise</label>
		       <input type="checkbox" id="checkEc" name="table" value="ecole">
	  		   <label for="checkEc">Ecole</label>
	  		   <input type="checkbox" id="checkEtu" name="table" value="etudiant">
	  		   <label for="checkEtu">Etudiant</label>
	  		  
	  		   <br />
		       <input type="submit" value="Envoyer" />
		   </p>
		</form>
		<% }else{%>
		<h3>Vous êtes déjà connecté !</h3>
		<% }%>
		<% con.close(); %>
	</body>
</html>