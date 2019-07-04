package MVC.Controller.ServletAuteur;

import MVC.Controller.connectBdd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletDeleteAuteur")
public class ServletDeleteAuteur extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        connectBdd connectBdd=new connectBdd();
        try {
            connectBdd.deleteAuteur(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
