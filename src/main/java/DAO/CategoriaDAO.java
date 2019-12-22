package DAO;

import UTILITIES.MediaKey;

import java.sql.*;

public class CategoriaDAO {
    private static CategoriaDAO inst = null;
    private String url = "jdbc:mysql://localhost/MEDIACENTER?" +
            "serverTimezone=UTC&user=root&password=dockermysql";

    public CategoriaDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static CategoriaDAO getInstance() {
        if (inst == null) {
            inst = new CategoriaDAO();
        }
        return inst;
    }

    public void putCategoria(String email, String categoria, MediaKey mediaKey) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM Utilizador_Media  " +
                    "where Utilizador_email='" + email + "' " +
                    "and Media_nomeMedia='" + mediaKey.getNome() + "' " +
                    "and Media_artista='" + mediaKey.getArtista() + "'");
            String sql = "INSERT INTO Utilizador_Media VALUES ('" +
                    email + "','" +
                    mediaKey.getNome() + "','" +
                    mediaKey.getArtista() + "','" +
                    categoria + "')";
            stm.executeUpdate(sql);
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

    public String getCategoria(Object key, MediaKey mediaKey) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stm = conn.createStatement();
            String sql = "SELECT categoria FROM Utilizador_Media " +
                    "where Utilizador_email='" + key + "' " +
                    "and Media_nomeMedia='" + mediaKey.getNome() + "' " +
                    "and Media_artista='" + mediaKey.getArtista() + "'";
            ResultSet rs = stm.executeQuery(sql);
            String categoria = null;
            if (rs.next()) categoria = rs.getString(1);
            return categoria;
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
