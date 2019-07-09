package MVC.Controller.ServletLivre;

import MVC.Controller.connectBdd;
import MVC.Model.Livre;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ServletAddLivre")
public class ServletAddLivre extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();

        Object user = session.getAttribute("user");
        if((user!=null && !session.isNew()) || (request.getRequestURI().equals("/index.jsp")) )
        {
            Livre livre=new Livre();

        String [] auteur = (request.getParameter("num")).split(":");
        int num = Integer.parseInt(auteur[0]);

        livre.setIssn(Integer.parseInt(request.getParameter("ISSN")));
        livre.setTitre(request.getParameter("titre"));
        livre.setResume(request.getParameter("resume"));
        livre.setNbrPage(Integer.parseInt(request.getParameter("nbrPage")));
        livre.setDomaine(request.getParameter("domaine"));



        connectBdd connectBdd=new connectBdd();

        try {
            connectBdd.addLivre(livre);
            connectBdd.addEcrit(livre.getIssn(),num);
            session.setAttribute("user","admin");
            response.sendRedirect("Main.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }else {
            response.sendRedirect("index.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




    }
}
