package DAO;

import LN.Media;
import UTILITIES.MediaKey;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ColecaoMediaDAO {

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
            if (rs.next()) {
                m.put(new MediaKey(rs.getString(1),
                                rs.getString(2)),
                        mediaOnColecao(codColecao, conn));
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
}
