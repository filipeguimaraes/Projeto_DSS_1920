/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package DAO;

import LN.Colecao;
import LN.Media;
import UTILITIES.MediaKey;

import java.sql.*;
import java.util.*;

public class ColecaoDAO implements Map<String, Colecao> {
    private static ColecaoDAO inst = null;
    private String url = "jdbc:mysql://localhost/mediacenter?" +
            "serverTimezone=UTC&user=root&password=bolinhosdeatum";

    public ColecaoDAO() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            throw new NullPointerException(e.getMessage());
        }
    }

    public static ColecaoDAO getInstance(){
        if (inst==null){
            inst = new ColecaoDAO();
        }
        return inst;
    }


/*
    public Colecao putOnBiblioteca(String key, Colecao value, String codBibli) {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM mediacenter.colecao  " +
                    "where codColecao='"+key+"'");
            String sql = "INSERT INTO mediacenter.colecao VALUES ('"+
                    value.getMediasCol()+"','"+
                    codBibli+"','"+
                    value.getNomeCol()+"')";
            stm.executeUpdate(sql);
            return new Colecao( value.getCodCol(),
                    value.getMediasCol(),
                    value.getNomeCol());
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally{
            if(conn!=null)
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }
    }

 */

    public List<String> getByBiblioteca(String codBiblioteca) {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
            List<String> m = new ArrayList<>();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM mediacenter.colecao " +
                    "where Biblioteca_cod='"+codBiblioteca+"'";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                m.add(rs.getString(1));
            }
            return m;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally{
            if(conn!=null)
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }
    }
/*
    public Map<String,Colecao> getByBiblioteca(String codBiblioteca) {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
            Map<String,Colecao> m = new HashMap<>();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM mediacenter.colecao " +
                    "where Biblioteca_cod='"+codBiblioteca+"'";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                m.put(rs.getString(1),
                        new Colecao(rs.getString(1),
                                mediasOnColecao(rs.getString(1),conn),
                                rs.getString(3)));
            }
            return m;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally{
            if(conn!=null)
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }
    }

 */

    public List<MediaKey> mediasOnColecao(String codColecao, Connection conn) throws SQLException {
        Statement stm2 = conn.createStatement();
        ResultSet rs2 = stm2.executeQuery("SELECT * FROM mediacenter.colecao_media " +
                "where Colecao_codColecao='"+codColecao+"'");
        List<MediaKey> medias = new ArrayList<>();
        while (rs2.next()){
            MediaKey chave = new MediaKey(
                    rs2.getString(1),
                    rs2.getString(2));
            medias.add(chave);
        }
        return medias;
    }

    public int hashCode() {
        return inst.hashCode();
    }

    @Override
    public int size() {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT codColecao FROM mediacenter.colecao");
            while (rs.next()) i++;
            return i;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally{
            if(conn!=null)
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }
    }

    @Override
    public boolean isEmpty() {
        Connection con = null;
        try{
            con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT codColecao FROM mediacenter.colecao");
            return !rs.next();
        }catch(Exception e){
            throw new NullPointerException(e.getMessage());
        }finally{
            if(con!=null)
                try {
                    con.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }
    }


    @Override
    public boolean containsKey(Object key) throws NullPointerException{
        Connection con = null;
        try{
            con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            String sql = "SELECT codColecao FROM mediacenter.colecao " +
                    "where codColecao='"+key+"'";
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch(Exception e){
            throw new NullPointerException(e.getMessage());
        } finally{
            if(con!=null)
                try {
                    con.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }
    }

    @Override
    public boolean containsValue(Object value){ throw new UnsupportedOperationException(); }


    @Override
    public Colecao get(Object key) {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
            Colecao m = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM mediacenter.colecao " +
                    "where codColecao='"+key+"'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next())
                m = new Colecao(rs.getString(1),
                        mediasOnColecao(rs.getString(1), conn),
                        rs.getString(3));
            return m;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally{
            if(conn!=null)
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }
    }


    @Override
    public Colecao put(String key, Colecao value) {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM mediacenter.colecao  " +
                    "where codColecao='"+key+"'");

            String sql = "INSERT INTO mediacenter.colecao VALUES " +
                    "('"+ value.getMediasCol()+"','0','"+ value.getNomeCol()+"')";
            stm.executeUpdate(sql);
            return new Colecao( value.getCodCol(),
                    value.getMediaKeys(),
                    value.getNomeCol());
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally{
            if(conn!=null)
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }
    }

    @Override
    public Colecao remove(Object key) {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
            Colecao m = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM mediacenter.colecao  " +
                    "where codColecao='"+key+"'";
            stm.executeUpdate(sql);
            return m;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally{
            if(conn!=null)
                try {
                    conn.close();
                }catch (SQLException e){
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
        try{
            conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM mediacenter.media");
        } catch(Exception e){
            throw new NullPointerException(e.getMessage());
        } finally{
            if(conn!=null)
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Colecao> values() {
        Connection conn = null;
        try{
            DriverManager.getConnection(url);
            List<Colecao> col = new ArrayList<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM mediacenter.colecao");

            while(rs.next()) {
                col.add(new Colecao(rs.getString(1),
                        mediasOnColecao(rs.getString(1),conn),
                        rs.getString(3)));
            }

            return col;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally{
            if(conn!=null)
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }
    }



    @Override
    public Set<Entry<String, Colecao>> entrySet() {
        throw new UnsupportedOperationException();
    }
}


