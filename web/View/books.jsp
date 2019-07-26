<%--
  Created by IntelliJ IDEA.
  User: try
  Date: 26/07/2019
  Time: 02:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html"  pageEncoding="UTF-8" language="java" %>
<%@page import="MVC.Model.Livre" %>
<%@page import="java.util.List" %>
<%@ page import="MVC.Model.Auteur" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<% Object user=session.getAttribute("user");
    Object t=request.getAttribute("titre");
    if (user!=null)
    {
        if (t!=null)
        {
            String titre=String.valueOf(t);
            if (titre.equals("false"))
            {
%>
<script>alert("titre exist dans la base de donnee")</script>
<%
        }
        }
%>

<!DOCTYPE html>
<html>
<head>

    <script src="https://use.fontawesome.com/03c8a23dee.js"></script>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="afficheAdmin.css">
    <title>JSP Page</title>

</head>
<body>

<header class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand  mb-0 h1" href="Main.jsp" style="font-size: 28px">Biblio</a>

    <div class="collapse navbar-collapse  mt-0" id="navbarNav">


        <ul class="navbar-nav navbar-nav flex-row ml-md-auto d-none d-md-flex">
            <button type="button" class="btn btn-light btn-lg" data-toggle="modal" data-target="#popuplivre" style="margin: 0px 10px"><img src="add.svg"  width="30" height="30"  alt="" class="d-inline-block align-top" ></button>

            <div class="modal" id="popuplivre" tabindex="-1">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-header justify-content-center">
                            <h4 class="modal-title">nouveau livre</h4>
                        </div>
                        <div class="modal-body">
                            <form method="post" action="/addlivre" autocomplete="off">

                                <div class="form-group">
                                    <input type="text" name="titre" placeholder="Titre" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="resume" placeholder="Résumé" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <input type="number" name="nbrPage" placeholder="Nombre de pages" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="domaine" placeholder="domaine" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <%@ page import = "java.sql.*" %>
                                    <%
                                        Class.forName("com.mysql.jdbc.Driver");
                                        String link="jdbc:mysql://localhost:3306/biblio";
                                        Connection connection= DriverManager.getConnection(link,"root","");

                                        Statement state = connection.createStatement();
                                        ResultSet resultSet = state.executeQuery("select * from auteur order by num asc ");



                                    %>
                                    <input type="text" name="num" placeholder="auteur" class="form-control" list="Sauteur" required>
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




        </ul>

            <li class="nav-item">
                <a href="/LogOut" > <button type="button" class="btn btn-light btn-lg" name="dec" style="margin: 0px 10px" >Disconnect</button></a>
                <a href="Main.jsp"> <button type="button" class="btn btn-light btn-lg" name="recherche" style="margin: 0px 10px" onclick="test">Recherche</button></a>




        </li>

    </div>
</header>



<div class="container">


    <table class="table table-striped table-dark table-bordered">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Titre</th>
            <th scope="col">Résumé</th>
            <th scope="col">nombre de pages</th>
            <th scope="col">domaine</th>
            <th scope="col">Auteur</th>
            <th scope="col" style="text-align: center">Actions</th>


        </tr>
        </thead>
        <tbody>




        <%
            Class.forName("com.mysql.jdbc.Driver");
            String link1="jdbc:mysql://localhost:3306/biblio";
            Connection connection1= DriverManager.getConnection(link1,"root","");

            Statement state1 = connection1.createStatement();
            Statement state2 = connection1.createStatement();

            ResultSet resultSet1 = state1.executeQuery("select * from auteur order by nom asc ");
            ResultSet resultSet2 = state2.executeQuery("SELECT l.* , a.nom , a.prenom FROM livre l , ecrit e , auteur a WHERE a.num=e.num and l.issn=e.issn order by l.issn");



                while (resultSet2.next()) {
                    out.println("<tr>");
                    out.println("<th scope=\"row\">"+resultSet2.getString(1)+"</th>");
                    out.println("<td>"+resultSet2.getString(2)+"</td>");
                    out.println("<td><div>"+resultSet2.getString(3)+"</div></td>");
                    out.println("<td>"+resultSet2.getString(4)+"</td>");
                    out.println("<td>"+resultSet2.getString(5)+"</td>");
                    out.println("<td>"+resultSet2.getString(6)+" "+resultSet2.getString(7)+"</td>");
                    out.println("<td>\n");
                    out.println("<button type=\"button\" class=\"btn btn-danger badge-pill\" name=\"supprimer\" data-toggle=\"modal\" data-target=\"#popupsupplivre"+resultSet2.getString(1)+"\" >Supprimer</button>\n");
                    out.println("<div class=\"modal\" id=\"popupsupplivre"+resultSet2.getString(1)+"\" tabindex=\"-1\">\n" +
                            "                   <div class=\"modal-dialog modal-sm\">\n" +
                            "                       <div class=\"modal-content\">\n" +
                            "                           <div class=\"modal-header justify-content-center\">\n" +
                            "                               <h4 class=\"modal-title\" style=\" color:black \">Suuprimer Livre</h4>\n" +
                            "                           </div>\n" +
                            "                           <div class=\"modal-body\">\n" +
                            "                               <form method=\"post\" action=\"/deletelivre\">\n" +
                            "\n" +
                            "                                   <div style=\"text-align: center\">\n" +
                            "                                       <input type=\"hidden\" value="+resultSet2.getString(1)+" name=\"ISSN\">\n" +
                            "                                       <label style=\" color:black \"> Vous voulez vraiment Supprimer le livre : "+resultSet2.getString(2)+" ?</label>\n" +
                            "                                   </div>\n" +
                            "                                   <div style=\"text-align: center\">\n" +
                            "                                       <button class=\"btn btn-primary btn-group-justified \" type=\"submit\" onclick=\"test\">Oui</button>\n" +
                            "                                   </div>\n" +
                            "                               </form>\n" +
                            "                           </div>\n" +
                            "                       </div>\n" +
                            "                   </div>\n" +
                            "               </div>");
                    out.println("<button type=\"button\" class=\"btn btn-primary badge-pill\" name=\"modifier\"  data-toggle=\"modal\" data-target=\"#popupeditlivre"+resultSet2.getString(1)+"\">Modifier</button>\n");
                    out.println("<div class=\"modal\" id=\"popupeditlivre"+resultSet2.getString(1)+"\" tabindex=\"-1\">\n" +
                            "            \t  <div class=\"modal-dialog modal-sm\">\n" +
                            "            \t  \t<div class=\"modal-content\">\n" +
                            "            \t  \t\t<div class=\"modal-header justify-content-center\">\n" +
                            "            \t  \t\t\t<h4 class=\"modal-title\" style=\" color:black \">Modifier Livre</h4>\n" +
                            "            \t  \t\t</div>\n" +
                            "            \t  \t\t<div class=\"modal-body\">\n" +
                            "            \t  \t\t\t<form method=\"post\" action=\"/editlivre\" autocomplete=\"off\">\n" +
                            "<div class=\"form-group\">\n" +
                            "                                    <input type=\"text\" name=\"ISSN\"  class=\"form-control\" value="+resultSet2.getString(1)+" readonly>\n" +
                            "                                </div>"+
                            "            \t  \t\t\t\t<div class=\"form-group\">\n" +
                            "            \t  \t\t\t\t\t<input type=\"text\" name=\"titre\"  class=\"form-control\" value=\" "+resultSet2.getString(2)+"\" autocomplete=\"false\" required>"+
                            "            \t  \t\t\t\t</div>\n" +
                            "            \t  \t\t\t\t<div class=\"form-group\">\n" +
                            "            \t  \t\t\t\t\t<input type=\"text\" name=\"resume\"  class=\"form-control\" value=\" "+resultSet2.getString(3)+"\" autocomplete=\"false\" required>\n" +
                            "            \t  \t\t\t\t</div> \n" +
                            "                                            <div class=\"form-group\">\n" +
                            "            \t  \t\t\t\t\t<input type=\"number\" name=\"nbrpage\"  class=\"form-control\" value="+resultSet2.getString(4)+" autocomplete=\"false\" required>\n" +
                            "            \t  \t\t\t\t</div> \n" +
                            "                                               <div class=\"form-group\">\n" +
                            "            \t  \t\t\t\t\t<input type=\"text\" name=\"domaine\"  class=\"form-control\" value=\" "+resultSet2.getString(5)+"\" autocomplete=\"false\" required>\n" +
                            "            \t  \t\t\t\t</div> \n" );
                    out.println("<input type=\"text\" name=\"num\"  class=\"form-control\" list=\"Sauteur\" autocomplete=\"false\" required>\n" +
                            "                                    <datalist id=\"Sauteur\" >");
                    while (resultSet1.next())
                    {
                        out.println("<option value=\""+(String.valueOf(resultSet1.getInt(1))+":"+(resultSet1.getString(2)+" "+(resultSet1.getString(3))))+"\" ></option>");
                    }
                    out.println("     </datalist>\n" +
                            "                                </div>");
                    out.println("                                            <div style=\"text-align: center\">    \n" +
                            "                           <button class=\"btn btn-primary btn-group-justified \" id=\"valideLivre\"   type=\"submit\" onclick=\"test\">Valider </button>\n" +
                            "            \t\t\t</div> \n" +
                            "                                        \n" +
                            "                                        </form>\n" +
                            "            \t  \t\t</div>\n" +
                            "            \t  \t</div>\n" +
                            "            \t </div>\n" +
                            "            </div>\n" +
                            "            ");
                    out.println("</td>");
                    out.println("</tr>");
                }
        %>

        <script>
            function test() {
                <%
                      session.setAttribute("user","admin");
                %>
            }
        </script>

        </tbody>
    </table>





</div>














<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


</body>
</html>

<%

}
else
{%>
<script>
    alert('Vous Devez Authentifier Avant d\'Atteindre Cette Page ');
    window.location = '/index.jsp';
</script>
<%}%>