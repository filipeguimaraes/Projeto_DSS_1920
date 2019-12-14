/**
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package DAO;

import LN.Colecao;

import java.sql.*;
import java.util.*;

public class ColecaoDAO implements Map<String, Colecao> {
    private static ColecaoDAO inst = null;
    private String url = "jdbc:mysql://localhost/mediacenter?" +
            "serverTimezone=UTC&user=root&password=bolinhosdeatum";

    public ColecaoDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static ColecaoDAO getInstance() {
        if (inst == null) {
            inst = new ColecaoDAO();
        }
        return inst;
    }

    public String getCodCol(String nome) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stm = conn.createStatement();
            String sql = "SELECT codColecao FROM mediacenter.colecao " +
                    "WHERE nomeColecao='" + nome + "'";
            ResultSet rs = stm.executeQuery(sql);
            String cod = null;
            if (rs.next()) cod = rs.getString(1);
            return cod;
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

    public Colecao putOnBiblioteca(String key, Colecao value, String codBibli) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM mediacenter.colecao  " +
                    "where codColecao='" + key + "'");
            String sql = "INSERT INTO mediacenter.colecao VALUES ('" +
                    key + "','" +
                    codBibli + "','" +
                    value.getNomeCol() + "')";
            stm.executeUpdate(sql);
            return new Colecao(value.getCodCol(),
                    value.getNomeCol());
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


    public List<String> getByBiblioteca(String codBiblioteca) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            List<String> m = new ArrayList<>();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM mediacenter.colecao " +
                    "where Biblioteca_cod='" + codBiblioteca + "'";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                m.add(rs.getString(1));
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

    public Colecao getByBiblioteca(String codBiblioteca, String codColecao) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Colecao m = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM mediacenter.colecao " +
                    "where codColecao='" + codColecao + "' " +
                    "and Biblioteca_cod='"+codBiblioteca+"'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next())
                m = new Colecao(rs.getString(1),
                        rs.getString(3));
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
    public int size() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT codColecao FROM mediacenter.colecao");
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
            ResultSet rs = st.executeQuery("SELECT codColecao FROM mediacenter.colecao");
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
            String sql = "SELECT codColecao FROM mediacenter.colecao " +
                    "where codColecao='" + key + "'";
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
    public Colecao get(Object key) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Colecao m = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM mediacenter.colecao " +
                    "where codColecao='" + key + "'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next())
                m = new Colecao(rs.getString(1),
                        rs.getString(3));
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
    public Colecao put(String key, Colecao value) {
        throw new UnsupportedOperationException();
        /*
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM mediacenter.colecao  " +
                    "where codColecao='" + key + "'");

            String sql = "INSERT INTO mediacenter.colecao VALUES " +
                    "('" + value.getMediasCol() + "','0','" + value.getNomeCol() + "')";
            stm.executeUpdate(sql);
            return new Colecao(value.getCodCol(),
                    value.getNomeCol());
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

         */
    }

    @Override
    public Colecao remove(Object key) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Colecao m = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM mediacenter.colecao  " +
                    "where codColecao='" + key + "'";
            stm.executeUpdate(sql);
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
    public void putAll(Map<? extends String, ? extends Colecao> m) {
        throw new UnsupportedOperationException();
    }


    @Override
    public void clear() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM mediacenter.colecao");
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
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Set<String> keys = new HashSet<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM mediacenter.colecao");
            while (rs.next()) keys.add(rs.getString(1));
            return keys;
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
    public List<Colecao> values() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            List<Colecao> col = new ArrayList<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM mediacenter.colecao");

            while (rs.next()) {
                col.add(new Colecao(rs.getString(1),
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
    public Set<Entry<String, Colecao>> entrySet() {
        throw new UnsupportedOperationException();
    }
}


