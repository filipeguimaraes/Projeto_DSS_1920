package DAO;

import LN.Colecao;
import LN.Media;
import LN.Residentes.Utilizador;
import UTILITIES.MediaKey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class CategoriaDAO implements Map<Utilizador, String> {
    private static CategoriaDAO inst = null;
    private String url = "jdbc:mysql://localhost/mediacenter?" +
            "serverTimezone=UTC&user=root&password=bolinhosdeatum";

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

    public String atribuirCategoria(Utilizador key, MediaKey chaveMedia, String categoria){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM mediacenter.utilizador_media  " +
                    "where Utilizador_email='" + key + "' " +
                    "and Media_nomeMedia='"+chaveMedia.getNome()+"' " +
                    "and Media_artista='"+chaveMedia.getArtista()+"'");
            String sql = "INSERT INTO mediacenter.colecao VALUES ('" +
                    key + "','" +
                    chaveMedia.getNome() + "','" +
                    chaveMedia.getArtista() + "','" +
                    categoria + "')";
            stm.executeUpdate(sql);
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

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String get(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String put(Utilizador key, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends Utilizador, ? extends String> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Utilizador> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> values() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Entry<Utilizador, String>> entrySet() {
        throw new UnsupportedOperationException();
    }
}
