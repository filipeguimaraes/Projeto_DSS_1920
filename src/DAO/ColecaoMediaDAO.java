package DAO;

import LN.Media;
import UTILITIES.MediaKey;

import java.io.Serializable;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ColecaoMediaDAO implements Serializable {

    private static ColecaoMediaDAO inst = null;
    private String url = "jdbc:mysql://localhost/mediacenter?" +
            "serverTimezone=UTC&user=root&password=bolinhosdeatum";

    public ColecaoMediaDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static ColecaoMediaDAO getInstance() {
        if (inst == null) {
            inst = new ColecaoMediaDAO();
        }
        return inst;
    }


    public Map<MediaKey, Media> get(String codColecao) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Map<MediaKey, Media> m = new HashMap<>();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM mediacenter.colecao_media " +
                    "where Colecao_codColecao='" + codColecao + "'";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                MediaKey key = new MediaKey(rs.getString(1), rs.getString(2));
                m.put(key, MediaDAO.getInstance().get(key));
            }
            return m;
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

    public Media mediaOnColecao(String codColecao, Connection conn) throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT FROM mediacenter.colecao_media " +
                "where Colecao_codColecao='" + codColecao + "'");
        Media media = null;
        if (rs.next()) {
            media = new Media(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3));
        }
        return media;
    }


    public Media add(MediaKey key, Media value, String codCol) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM mediacenter.colecao_media  " +
                    "where Media_nomeMedia='" + key.getNome() + "' " +
                    "and Media_artista='" + key.getArtista() + "' " +
                    "and Colecao_codColecao='" + codCol + "'");

            String sql = "INSERT INTO mediacenter.colecao_media VALUES ('" +
                    value.getNomeMedia() + "','" +
                    value.getArtista() + "','" +
                    codCol + "')";
            stm.executeUpdate(sql);
            return new Media(
                    value.getNomeMedia(),
                    value.getPath(),
                    value.getArtista());
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
