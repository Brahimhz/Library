package MVC.Controller.ServletLivre;

import MVC.Controller.DataBase.connectBdd;
import MVC.Model.Livre;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

        livre.setTitre(request.getParameter("titre"));
        livre.setResume(request.getParameter("resume"));
        livre.setNbrPage(Integer.parseInt(request.getParameter("nbrPage")));
        livre.setDomaine(request.getParameter("domaine"));



        connectBdd connectBdd=new connectBdd();

        try {
            connectBdd.addLivre(livre);
            connectBdd.addEcrit(livre.getTitre(),num);
            session.setAttribute("user","admin");
            response.sendRedirect("books.jsp");
        } catch (MySQLIntegrityConstraintViolationException e)
        {
            request.setAttribute("titre" , "false");
            response.sendRedirect("books.jsp");
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
