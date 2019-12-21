/**
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package DAO;

import LN.Media;
import UTILITIES.MediaKey;

import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class MediaDAO implements Map<MediaKey, Media>, Serializable {
    private static MediaDAO inst = null;
    private String url = "jdbc:mysql://localhost/mediacenter?" +
            "serverTimezone=UTC&user=root&password=bolinhosdeatum";

    public MediaDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static MediaDAO getInstance() {
        if (inst == null) {
            inst = new MediaDAO();
        }
        return inst;
    }

    public Map<String, String> categoriasByUtilizador(String nomeMedia, String artista) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Map<String, String> cat = new HashMap<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM mediacenter.utilizador_media " +
                    "where Media_nomeMedia='" + nomeMedia + "' and Media_artista='" + artista + "'");
            while (rs.next()) {
                cat.put(rs.getString(1), rs.getString(4));
            }
            return cat;
        } catch (SQLException e) {
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

    public int hashCode() {
        return inst.hashCode();
    }

    @Override
    public int size() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT nomeMedia FROM mediacenter.media");
            while (rs.next()) i++;
            return i;
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
    public boolean isEmpty() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT nomeMedia FROM mediacenter.media");
            return !rs.next();
        } catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            if (con != null)
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }


    @Override
    public boolean containsKey(Object key) throws NullPointerException {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            MediaKey chave = (MediaKey) key;
            String sql = "SELECT nomeMedia FROM mediacenter.media " +
                    "where nomeMedia='" + chave.getNome() + "' " +
                    "and artista='" + chave.getArtista() + "'";
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            if (con != null)
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Media get(Object key) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Media m = null;
            Statement stm = conn.createStatement();
            MediaKey chave = (MediaKey) key;
            String nome = chave.getNome();
            String artista = chave.getArtista();
            String sql = "SELECT * FROM mediacenter.media " +
                    "where nomeMedia='" + nome + "' and artista='" + artista + "'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                m = new Media(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3));
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


    @Override
    public Media put(MediaKey key, Media value) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM mediacenter.media  " +
                    "where nomeMedia='" + key.getNome() + "' and artista='" + key.getNome() + "'");
            String sql = "INSERT INTO mediacenter.media VALUES ('" +
                    value.getNomeMedia() + "','" +
                    value.getPath() + "','" +
                    value.getArtista() + "')";
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

    @Override
    public Media remove(Object key) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Media m = this.get(key);
            MediaKey chave = (MediaKey) key;
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM mediacenter.media  " +
                    "where nomeMedia='" + chave.getNome() + "' and artista='" + chave.getNome() + "'";
            stm.executeUpdate(sql);
            return m;
        } catch (SQLException e) {
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
    public void putAll(Map<? extends MediaKey, ? extends Media> m) {
        throw new UnsupportedOperationException();
    }


    @Override
    public void clear() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM mediacenter.media");
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
    public Set<MediaKey> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Media> values() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            List<Media> col = new ArrayList<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM mediacenter.media");
            while (rs.next()) {
                col.add(new Media(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
            return col;
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
    public Set<Entry<MediaKey, Media>> entrySet() {
        throw new UnsupportedOperationException();
    }
}

