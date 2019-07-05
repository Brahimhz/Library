package MVC.Controller.ServletLivre;

import MVC.Controller.connectBdd;
import MVC.Model.Livre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletEditLivre")
public class ServletEditLivre extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Livre livre=new Livre();

        livre.setIssn(Integer.parseInt(request.getParameter("ISSN")));
        livre.setTitre(request.getParameter("titre"));
        livre.setResume(request.getParameter("resume"));
        livre.setNbrPage(Integer.parseInt(request.getParameter("nbrpage")));
        livre.setDomaine(request.getParameter("domaine"));
        String[] auteur =(request.getParameter("num")).split(":");

        connectBdd connectBdd=new connectBdd();

        try {
            connectBdd.editLivre(livre);
            connectBdd.editEcrit(Integer.parseInt(auteur[0]),livre.getIssn());
            response.sendRedirect("Main.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
