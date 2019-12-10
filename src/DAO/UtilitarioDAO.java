/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package DAO;

import LN.Residentes.Administrador;
import LN.Residentes.Utilizador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UtilitarioDAO {
    private static UtilitarioDAO inst = null;
    private String url = "jdbc:mysql://localhost/mediacenter?" +
            "serverTimezone=UTC&user=root&password=bolinhosdeatum";

    public UtilitarioDAO() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            throw new NullPointerException(e.getMessage());
        }
    }

    public static UtilitarioDAO getInstance(){
        if (inst==null){
            inst = new UtilitarioDAO();
        }
        return inst;
    }

    public Administrador getAdmin(){
        try (Connection conn = DriverManager.getConnection(url)) {
            Administrador admin = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT emailAdmin,passAdmin FROM mediacenter.utilitario";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next())
                admin = new Administrador(
                        rs.getString(1),
                        rs.getString(2));
            return admin;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public String pathToMedia(){
        try (Connection conn = DriverManager.getConnection(url)) {
            String path=null;
            Statement stm = conn.createStatement();
            String sql = "SELECT pathToMedia FROM mediacenter.utilitario";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next())
                path = rs.getString(1);
            return path;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }
}
