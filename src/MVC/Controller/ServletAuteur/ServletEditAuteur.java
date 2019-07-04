package MVC.Controller.ServletAuteur;

import MVC.Controller.connectBdd;
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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Auteur auteur= new Auteur();

        auteur.setNum(Integer.parseInt(request.getParameter("num")));
        auteur.setNom(request.getParameter("nom"));
        auteur.setPrenom(request.getParameter("prenom"));
        auteur.setNaissance(Date.valueOf(request.getParameter("naissance")));

        connectBdd connectBdd=new connectBdd();

        try {
            connectBdd.editAuteur(auteur,1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
