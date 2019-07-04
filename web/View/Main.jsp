<%-- 
    Document   : index
    Created on : 30 mai 2019, 17:17:06
    Author     : mus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="MVC.Model.Livre" %>
<%@page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
          
        <script src="https://use.fontawesome.com/03c8a23dee.js"></script>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<link rel="stylesheet" type="text/css" href="index.css">
        <title>JSP Page</title>
	
    </head>
    <body>
        <header class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand  mb-0 h1" href="#" style="font-size: 28px">Biblio</a>

  <div class="collapse navbar-collapse  mt-0" id="navbarNav">
     
    </ul>
      <ul class="navbar-nav navbar-nav flex-row ml-md-auto d-none d-md-flex">
           <li class="nav-item">
            <button type="button" class="btn btn-light btn-lg" data-toggle="modal" data-target="#popuplivre" style="margin: 0px 10px">+livre</button>
           
             <div class="modal" id="popuplivre" tabindex="-1">
            	  <div class="modal-dialog modal-sm">
            	  	<div class="modal-content">
            	  		<div class="modal-header justify-content-center">
            	  			<h4 class="modal-title">nouveau livre</h4>
            	  		</div>
            	  		<div class="modal-body">
            	  			<form method="get" action="/addlivre" autocomplete="off">
                                <div class="form-group">
                                    <input type="number" name="ISSN" placeholder="ISSN" class="form-control">
                                </div>
                                <div class="form-group">
            	  					<input type="text" name="titre" placeholder="Titre" class="form-control">
            	  				</div>
            	  				<div class="form-group">
            	  					<input type="text" name="resume" placeholder="Résumé" class="form-control">
            	  				</div> 
                                            <div class="form-group">
            	  					<input type="number" name="nbrPage" placeholder="Nombre de pages" class="form-control">
            	  				</div> 
                                               <div class="form-group">
            	  					<input type="text" name="domaine" placeholder="domaine" class="form-control">
            	  				</div>
                                <div class="form-group">
                                    <%@ page import = "java.sql.*" %>
                                    <%
                                        Class.forName("com.mysql.jdbc.Driver");
                                        String link="jdbc:mysql://localhost:3306/biblio";
                                        Connection connection= DriverManager.getConnection(link,"root","");

                                        Statement state = connection.createStatement();
                                        ResultSet resultSet = state.executeQuery("select * from auteur order by nom asc ");



                                    %>
                                    <input type="text" name="num" placeholder="auteur" class="form-control" list="Sauteur">
                                    <datalist id="Sauteur">
                                        <%
                                            while (resultSet.next())
                                            {


                                        %>
                                        <option value="<%= (String.valueOf(resultSet.getInt(1))+":"+(resultSet.getString(2)+" "+(resultSet.getString(3))))%>"></option>

                                        <% }
                                        resultSet.close();
                                        state.close();
                                        connection.close();%>
                                    </datalist>
                                </div>


                                <div style="text-align: center">
                           <button class="btn btn-primary btn-group-justified " id="valideLivre"   type="submit">Valider</button>
            			</div> 
                                        
                                        </form>
            	  		</div>
            	  	</div>
            	 </div>
            </div>
            
            
            
            
            
            
            <button type="button" class="btn btn-light btn-lg" data-toggle="modal" data-target="#popupauteur" style="margin: 0px 10px">+auteur</button>
           
           <div class="modal" id="popupauteur" tabindex="-1">
            	  <div class="modal-dialog modal-sm">
            	  	<div class="modal-content">
            	  		<div class="modal-header justify-content-center">
            	  			<h4 class="modal-title">nouveau Auteur</h4>
            	  		</div>
            	  		<div class="modal-body">
            	  			<form method="get" action="/addAuteur">
            	  				<div class="form-group">
            	  					<input type="number" name="num" placeholder="Numero d'Auteur" class="form-control" min="0">
            	  				</div>
                                <div class="form-group">
                                    <input type="text" name="nom" placeholder="Nom" class="form-control">
                                </div>
            	  				<div class="form-group">
            	  					<input type="text" name="prenom" placeholder="Prenom" class="form-control">
            	  				</div> 
                                            <div class="form-group">

            	  					<input type="Date" name="naissance" placeholder="Date de Naissance" class="form-control">
            	  				</div> 
                                              
                                            <div style="text-align: center">    
                           <button class="btn btn-primary btn-group-justified " id="valideAuteur"   type="submit">Valider</button>
            			</div> 
                                        
                                        </form>
            	  		</div>
            	  	</div>
            	 </div>
            </div>
            
            
            
            
            
            
           <a href="index.jsp"><button type="button" class="btn btn-light btn-lg" data-toggle="modal" data-target="#popupModal" style="margin: 0px 10px">Disconnect</button></a>

           </li>
         
         <li class="nav-item">
         <a class="nav-link" > <img src="books.svg"  width="40" height="40" class="d-inline-block align-top" alt=""></a>

      </li>
      </ul>
    
  </div>
</header>
        
        
        
        <form method="post" action="/search" id="frm" autocomplete="off">
        <div class="form-group">

              <div class="form-group row">
    <label for="colFormLabelLg" class="col-sm-4 col-form-label col-form-label-md">Livre</label>
    <div class="col-sm-8">
      <input type="text" class="form-control form-control-md" name="Slivre" placeholder="Le nom du livre">
    </div>
            </div>



            <div class="form-group row">
                <label for="colFormLabelLg" class="col-sm-4 col-form-label col-form-label-md">Auteur</label>

                <%@ page import = "java.sql.*" %>
                <%
                    Class.forName("com.mysql.jdbc.Driver");
                    String link1="jdbc:mysql://localhost:3306/biblio";
                    Connection connection1= DriverManager.getConnection(link1,"root","");

                    Statement state1= connection1.createStatement();
                    ResultSet resultSet1= state1.executeQuery("select * from auteur order by nom asc ");



                %>
                <div class="col-sm-8">
                    <input type="text" class="form-control form-control-md" name="Sauteur" placeholder="Le nom d'auteur" list="Sauteur1" autocomplete="false">
                    <datalist id="Sauteur1">
                    <%
                        while (resultSet1.next())
                        {


                    %>
                        <option value="<%= (String.valueOf(resultSet1.getInt(1))+":"+(resultSet1.getString(2)+" "+(resultSet1.getString(3))))%>"></option>

                   <% } %>
                    </datalist>





    </div>
            </div>
            
                    <div class="form-group row">
    <label for="colFormLabelLg" class="col-sm-4 col-form-label col-form-label-md">Domaine</label>
    <div class="col-sm-8">
      <input type="text" class="form-control form-control-md" name="Sdomaine" placeholder="Le nom du domaine">
    </div>
            </div>
        </div>
            
            <div class="recherche">
                
<button  class="btn btn-light btn-lg" type="submit">Recherche</button>


            </div>
            

        </form>











        
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>        
        
        
    </body>
</html>
