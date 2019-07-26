package MVC.Controller.ServletAuteur;

import MVC.Controller.DataBase.connectBdd;
import MVC.Model.Auteur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "ServletEditAuteur")
public class ServletEditAuteur extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Auteur auteur= new Auteur();

        auteur.setNum(Integer.parseInt(request.getParameter("Num")));
        auteur.setNom(request.getParameter("Nom"));
        auteur.setPrenom(request.getParameter("Prenom"));
        auteur.setNaissance(Date.valueOf(request.getParameter("Date")));

        connectBdd connectBdd=new connectBdd();

        try {
            connectBdd.editAuteur(auteur);
            response.sendRedirect("author.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
