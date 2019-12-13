/**
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package DAO;

import java.sql.*;

public class UtilitarioDAO {
    private static UtilitarioDAO inst = null;
    private String url = "jdbc:mysql://localhost/mediacenter?" +
            "serverTimezone=UTC&user=root&password=bolinhosdeatum";

    public UtilitarioDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static UtilitarioDAO getInstance() {
        if (inst == null) {
            inst = new UtilitarioDAO();
        }
        return inst;
    }

    public String getPassAdmin() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            String admin = "";
            Statement stm = conn.createStatement();
            String sql = "SELECT passAdmin FROM mediacenter.utilitario";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) admin = rs.getString(1);
            return admin;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public String getEmailAdmin() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            String admin = "";
            Statement stm = conn.createStatement();
            String sql = "SELECT emailAdmin FROM mediacenter.utilitario";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) admin = rs.getString(1);
            return admin;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public String pathToMedia() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            String path = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT pathToMedia FROM mediacenter.utilitario";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next())
                path = rs.getString(1);
            System.out.println(path);
            return path;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}
