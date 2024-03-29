package MVC.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletLogin")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String Iuser=getServletConfig().getInitParameter("user");
        String Ipassword = getServletConfig().getInitParameter("password");

        String user = request.getParameter("nom");
        String password=request.getParameter("password");

        if((user.equals(Iuser))&&(password.equals(Ipassword)))
        {
            //response.sendRedirect("Main.jsp");
            HttpSession session= request.getSession();

            if((user!=null && !session.isNew()) || (request.getRequestURI().equals("/index.jsp")) )
            {
                session.setAttribute("user",user);
                this.getServletContext().getRequestDispatcher("/Main.jsp").forward(request,response);
            }
            else
            {
                response.sendRedirect("index.jsp");
            }

        }

        else
        {
            request.setAttribute("erreur",true);


            RequestDispatcher requestDispatcher=request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request,response);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
