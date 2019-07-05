package MVC.Controller;

import MVC.Model.Auteur;
import MVC.Model.Livre;

import javax.servlet.RequestDispatcher;
import java.sql.*;


public class connectBdd {

    private Connection connection;

  public connectBdd(){
      try {

          Class.forName("com.mysql.jdbc.Driver");

          String link="jdbc:mysql://localhost:3306/biblio";

          connection= DriverManager.getConnection(link,"root","");
      }catch (
              SQLException e){
          e.printStackTrace();

      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }

  }


    public void addLivre(Livre livre) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into livre values(?,?,?,?,?)");
        statement.setInt(1,livre.getIssn());
        statement.setString(2,livre.getTitre());
        statement.setString(3,livre.getResume());
        statement.setInt(4,livre.getNbrPage());
        statement.setString(5,livre.getDomaine());

        statement.executeUpdate();

        statement.close();
    }

    public void editLivre(Livre livre) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("update livre SET titre=? , resume = ? , nbrpage=? , domaine =? where issn=? ");
        statement.setString(1,livre.getTitre());
        statement.setString(2,livre.getResume());
        statement.setInt(3,livre.getNbrPage());
        statement.setString(4,livre.getDomaine());
        statement.setInt(5,livre.getIssn());


        statement.executeUpdate();

        statement.close();
    }

    public void deleteLivre(int issn)throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM livre WHERE issn=? ");
        statement.setInt(1,issn);

        statement.executeUpdate();

        statement.close();
    }



    public void addAuteur(Auteur auteur)throws SQLException {

        PreparedStatement statement = connection.prepareStatement("insert into auteur values(?,?,?,?)");
        statement.setInt(1,auteur.getNum());
        statement.setString(2,auteur.getNom());
        statement.setString(3,auteur.getPrenom());
        statement.setDate(4, (Date) auteur.getNaissance());


        statement.executeUpdate();

        statement.close();

    }

    public void editAuteur(Auteur auteur, int num) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("update livre SET num=? , nom=? , prenom = ? , naissance=?  where num=? ");

        statement.setInt(1,auteur.getNum());
        statement.setString(2,auteur.getNom());
        statement.setString(3,auteur.getPrenom());
        statement.setDate(4, (Date) auteur.getNaissance());
        statement.setInt(5,num);


        statement.executeUpdate();

        statement.close();
    }

    public void deleteAuteur(int num)throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM livre WHERE issn=? ");
        statement.setInt(1,num);

        statement.executeUpdate();

        statement.close();
    }



    public void addEcrit(int issn , int num)  throws SQLException{

        PreparedStatement statement = connection.prepareStatement("insert into ecrit values(?,?)");
        statement.setInt(1,issn);
        statement.setInt(2,num);

        statement.executeUpdate();

        statement.close();
    }

    public void editEcrit(int num, int issn)throws SQLException {
        PreparedStatement statement = connection.prepareStatement("update ecrit set num=? where issn=?");
        statement.setInt(1,num);
        statement.setInt(2,issn);

        statement.executeUpdate();

        statement.close();
    }

    public void deleteEcrit(int issn) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM ecrit WHERE issn=? ");
        statement.setInt(1,issn);

        statement.executeUpdate();

        statement.close();

    }




    public ResultSet searchL(String slivre) throws SQLException {


        Statement state = connection.createStatement();
        ResultSet resultSet = state.executeQuery("SELECT l.* , a.nom , a.prenom FROM livre l , ecrit e , auteur a WHERE a.num=e.num and l.issn=e.issn and l.titre=\""+slivre+" \" ");

        return resultSet;
    }

    public ResultSet searchA(int sauteur) throws SQLException {

        Statement state = connection.createStatement();
        ResultSet resultSet = state.executeQuery("select * from livre where issn in(" +
                                                        "select issn from ecrit where num=\""+sauteur+"\" )");
        return resultSet;
    }

    public ResultSet searchD(String sdomaine) throws SQLException {

        Statement state = connection.createStatement();
        ResultSet resultSet = state.executeQuery("SELECT l.* , a.nom , a.prenom FROM livre l , ecrit e , auteur a WHERE a.num=e.num and l.issn=e.issn and l.domaine=\""+sdomaine+"\"");

        return resultSet;
    }

    public ResultSet searchLA(String slivre, int sauteur) throws SQLException {

        Statement state = connection.createStatement();
        ResultSet resultSet = state.executeQuery("select * from livre where titre=\""+slivre+"\" and issn in " +
                                                        " (select * from ecrit where num=\""+sauteur+"\" )");


        return resultSet;

    }

    public ResultSet searchLD(String slivre, String sdomaine) throws SQLException {

        Statement state = connection.createStatement();
        ResultSet resultSet = state.executeQuery("SELECT l.* , a.nom , a.prenom FROM livre l , ecrit e , auteur a WHERE a.num=e.num and l.issn=e.issn and l.titre=\""+slivre+"\" and l.domaine="+sdomaine);

        return resultSet;
    }

    public ResultSet searchAD(int sauteur, String sdomaine) throws SQLException {
        Statement state = connection.createStatement();
        ResultSet resultSet = state.executeQuery("select * from livre where domaine="+sdomaine+" and issn in " +
                                                      " (select * from ecrit where num in " +
                                                          "( select * from auteur where nom ="+sauteur+"))");

        return resultSet;

    }

    public ResultSet searchLAD(String slivre, int sauteur, String sdomaine) throws SQLException {
        Statement state = connection.createStatement();
        ResultSet resultSet = state.executeQuery("select * from livre where titre="+slivre+" and domaine ="+sdomaine+" and issn in " +
                                                        " (select * from ecrit where num=\""+sauteur+"\")");

        return resultSet;

    }
}
