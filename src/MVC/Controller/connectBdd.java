package MVC.Controller;

import MVC.Model.Auteur;
import MVC.Model.Livre;

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

    public void editAuteur(Auteur auteur) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("update auteur SET  nom=? , prenom = ? , naissance=?  where num=? ");

        statement.setString(1,auteur.getNom());
        statement.setString(2,auteur.getPrenom());
        statement.setDate(3,(Date) auteur.getNaissance());
        statement.setInt(4 ,auteur.getNum());


        statement.executeUpdate();

        statement.close();
    }

    public void deleteAuteur(int num)throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM auteur WHERE num=? ");
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


    public ResultSet allLivre() throws SQLException {

      PreparedStatement statement = connection.prepareStatement("SELECT l.* , a.nom , a.prenom FROM livre l , ecrit e , auteur a WHERE a.num=e.num and l.issn=e.issn");
        ResultSet resultSet=statement.executeQuery();
        return resultSet;
    }

    public ResultSet allAuteur() throws SQLException {

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM auteur order by nom asc");
        ResultSet resultSet=statement.executeQuery();
        return resultSet;
    }



    public ResultSet searchL(String slivre) throws SQLException {

        PreparedStatement state = connection.prepareStatement("SELECT l.* , a.nom , a.prenom FROM livre l , ecrit e , auteur a WHERE a.num=e.num and l.issn=e.issn and l.titre=?");
        state.setString(1,slivre);
        ResultSet resultSet = state.executeQuery();

        return resultSet;
    }

    public ResultSet searchA(int sauteur) throws SQLException {

        PreparedStatement state = connection.prepareStatement("select * from livre where issn in(" +
                                                                      "select issn from ecrit where num=?)");
        state.setInt(1,sauteur);

        ResultSet resultSet = state.executeQuery();
        return resultSet;
    }

    public ResultSet searchD(String sdomaine) throws SQLException {

        PreparedStatement state = connection.prepareStatement("SELECT l.* , a.nom , a.prenom FROM livre l , ecrit e , auteur a WHERE a.num=e.num and l.issn=e.issn and l.domaine=?");
        state.setString(1,sdomaine);

        ResultSet resultSet = state.executeQuery();
        return resultSet;
    }

    public ResultSet searchLA(String slivre, int sauteur) throws SQLException {

        PreparedStatement state = connection.prepareStatement("select * from livre where titre=? and issn in " +
                                                          " (select issn from ecrit where num=? )");
        state.setString(1,slivre);
        state.setInt(2,sauteur);

        ResultSet resultSet = state.executeQuery();
        return resultSet;

    }

    public ResultSet searchLD(String slivre, String sdomaine) throws SQLException {

        PreparedStatement state = connection.prepareStatement("SELECT l.* , a.nom , a.prenom FROM livre l , ecrit e , auteur a WHERE a.num=e.num and l.issn=e.issn and l.titre=? and l.domaine=?");
        state.setString(1,slivre);
        state.setString(2,sdomaine);

        ResultSet resultSet = state.executeQuery();
        return resultSet;
    }

    public ResultSet searchAD(int sauteur, String sdomaine) throws SQLException {
        PreparedStatement state = connection.prepareStatement("select * from livre where domaine=? and issn in " +
                                                                      " (select issn from ecrit where num=?)");
        state.setString(1,sdomaine);
        state.setInt(2,sauteur);

        ResultSet resultSet = state.executeQuery();
        return resultSet;

    }

    public ResultSet searchLAD(String slivre, int sauteur, String sdomaine) throws SQLException {
        PreparedStatement state = connection.prepareStatement("select * from livre where titre=? and domaine =?and issn in " +
                                                                     " (select issn from ecrit where num=?)");
        state.setString(1,slivre);
        state.setInt(3,sauteur);
        state.setString(2,sdomaine);

        ResultSet resultSet = state.executeQuery();
        return resultSet;

    }
}
