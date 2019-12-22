/*
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package DAO;

import LN.Residentes.Utilizador;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class UtilizadorDAO implements Map<String, Utilizador>, Serializable {

    private static UtilizadorDAO inst = null;

    private String url = "jdbc:mysql://localhost/mediacenter?" +
            "serverTimezone=UTC&user=root&password=password";

    public UtilizadorDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static UtilizadorDAO getInstance() {
        if (inst == null) {
            inst = new UtilizadorDAO();
        }
        return inst;
    }
/*
    public Map<Media,String> categoriasByUtilizador(String email){
        try (Connection conn = DriverManager.getConnection(url)) {
            Map<Media,String> cat = new HashMap<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM mediacenter.utilizador_media " +
                    "where Utilizador_email='"+email+"'");
            while (rs.next()) {
                MediaDAO m = new MediaDAO();
                MediaKey key = new MediaKey(rs.getString(2),rs.getString(3));
                cat.put(m.get(key),rs.getString(4));
                System.out.println(key.toString()+m.get(key)+" : "+rs.getString(4));
            }
            return cat;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

 */

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
            ResultSet rs = stm.executeQuery("SELECT mediacenter.utilizador FROM Utilizador");
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
            ResultSet rs = st.executeQuery("SELECT mediacenter.utilizador FROM Utilizador");
            return !rs.next();
        } catch (Exception e) {
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
            String sql = "SELECT nome FROM mediacenter.utilizador where email='" + key + "'";
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (Exception e) {
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
    public Utilizador get(Object key) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Utilizador ut = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM mediacenter.utilizador WHERE email='" + key + "'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                ut = new Utilizador(rs.getString(4),
                        rs.getString(2),
                        rs.getString(1),
                        rs.getString(3));

            }
            return ut;
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
    public Utilizador put(String key, Utilizador value) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM mediacenter.utilizador WHERE email='" + key + "'");
            String sql = "INSERT INTO Utilizador VALUES ('" +
                    value.getNome() + "','" +
                    key + "'," +
                    value.getPassword() + "," +
                    "biblioteca" + ")";
            stm.executeUpdate(sql);
            return new Utilizador(
                    value.getBiblioteca().getCod(),
                    value.getNome(),
                    value.getEmail(),
                    value.getPassword());
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
    public Utilizador remove(Object key) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Utilizador ut = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE '" + key + "' FROM Utilizador";
            stm.executeUpdate(sql);
            return ut;
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
    public void putAll(Map<? extends String, ? extends Utilizador> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM mediacenter.utilizador");
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
    public Set<String> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Utilizador> values() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Collection<Utilizador> col = new ArrayList<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM mediacenter.utilizador");
            while (rs.next())
                col.add(new Utilizador(
                        rs.getString(4),
                        rs.getString(2),
                        rs.getString(1),
                        rs.getString(3)));
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
    public Set<Entry<String, Utilizador>> entrySet() {
        throw new UnsupportedOperationException();
    }


}
