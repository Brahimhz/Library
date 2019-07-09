package MVC.Controller.ServletLivre;

import MVC.Controller.connectBdd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletDeleteLivre")
public class ServletDeleteLivre extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);

        Object user = session.getAttribute("user");
        if((user!=null && !session.isNew()) || (request.getRequestURI().equals("/index.jsp")) )
        {

        int issn=Integer.parseInt(request.getParameter("ISSN"));

        connectBdd connectBdd=new connectBdd();
        try {
            connectBdd.deleteEcrit(issn);
            connectBdd.deleteLivre(issn);
            session.setAttribute("user","admin");
            response.sendRedirect("Main.jsp");
        }catch (SQLException e){
            e.printStackTrace();
        }
        }else
        {
            response.sendRedirect("index.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
