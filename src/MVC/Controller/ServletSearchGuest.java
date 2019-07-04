package MVC.Controller;

import MVC.Model.Auteur;
import MVC.Model.Livre;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletSearchGuest")
public class ServletSearchGuest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Slivre=request.getParameter("Slivre");
        String Sauteur=request.getParameter("Sauteur");
        String Sdomaine=request.getParameter("Sdomaine");


        List<Livre> listL= new ArrayList<Livre>();
        Livre livre = null ;
        List<Auteur> listA= new ArrayList<Auteur>();
        Auteur auteur = null ;

        ResultSet resultSet=null;


        connectBdd connectBdd=new connectBdd();

        if((Slivre.isEmpty()) && (Sauteur.isEmpty()) && (Sdomaine.isEmpty())) //0
        {

            response.sendRedirect("Index.jsp");
        }

        else
        {
            try {
                if((!Slivre.isEmpty()) && (Sauteur.isEmpty()) && (Sdomaine.isEmpty())) //1
                    resultSet= connectBdd.searchL(Slivre);

                else if((Slivre.isEmpty()) && (!Sauteur.isEmpty()) && (Sdomaine.isEmpty())) //2
                {
                    String[] Snum=Sauteur.split(":");
                    int num = Integer.parseInt(Snum[0]);
                    resultSet=connectBdd.searchA(num);
                }


                else if((Slivre.isEmpty()) && (Sauteur.isEmpty()) && (!Sdomaine.isEmpty()))//3
                    resultSet=connectBdd.searchD(Sdomaine);

                else if((!Slivre.isEmpty()) && (!Sauteur.isEmpty()) && (Sdomaine.isEmpty())) //1+2
                {
                    String[] Snum=Sauteur.split(":");
                    int num = Integer.parseInt(Snum[0]);
                    resultSet=connectBdd.searchLA(Slivre,num);
                }

                else if((!Slivre.isEmpty()) && (Sauteur.isEmpty()) && (!Sdomaine.isEmpty()))//1+3
                    resultSet=connectBdd.searchLD(Slivre,Sdomaine);

                else if((Slivre.isEmpty()) && (!Sauteur.isEmpty()) && (!Sdomaine.isEmpty())) //2+3
                {
                    String[] Snum=Sauteur.split(":");
                    int num = Integer.parseInt(Snum[0]);
                    resultSet=connectBdd.searchAD(num,Sdomaine);
                }

                else if((!Slivre.isEmpty()) && (!Sauteur.isEmpty()) && (!Sdomaine.isEmpty())) //1+2+3
                {
                    String[] Snum=Sauteur.split(":");
                    int num = Integer.parseInt(Snum[0]);
                    resultSet=connectBdd.searchLAD(Slivre,num,Sdomaine);
                }

            }
            catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                while (resultSet.next()){

                    livre=new Livre();
                    auteur=new Auteur();


                    livre.setIssn(resultSet.getInt(1));

                    livre.setTitre(resultSet.getString(2));
                    livre.setResume(resultSet.getString(3));
                    livre.setNbrPage(resultSet.getInt(4));
                    livre.setDomaine(resultSet.getString(5));
                    if(Sauteur.isEmpty())
                    {
                        auteur.setNom(resultSet.getString(6));
                        auteur.setPrenom(resultSet.getString(7));
                        listA.add(auteur);
                    }

                    listL.add(livre);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }



            request.setAttribute("listL",listL);

            if(!Sauteur.isEmpty())
            {
                String[] Snum=Sauteur.split(":");
                request.setAttribute("Sauteur",Snum[1]);
                request.setAttribute("type",1);
            }else{
                request.setAttribute("listA",listA);
                request.setAttribute("type",0);
            }


            RequestDispatcher dispatcher= (RequestDispatcher) request.getRequestDispatcher("affichage.jsp");
            dispatcher.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
