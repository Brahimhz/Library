package MVC.Controller.ServletLivre;

import MVC.Controller.connectBdd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletDeleteLivre")
public class ServletDeleteLivre extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int issn=0;
        
        connectBdd connectBdd=new connectBdd();
        try {
            connectBdd.deleteLivre(issn);
            connectBdd.deleteEcrit(issn);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
