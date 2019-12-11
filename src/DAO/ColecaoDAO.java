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



    public Colecao putOnBiblioteca(String key, Colecao value, String codBibli) {
        try (Connection conn = DriverManager.getConnection(url)) {
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
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Map<String,Colecao> getByBiblioteca(String codBiblioteca) {
        try (Connection conn = DriverManager.getConnection(url)) {
            Map<String,Colecao> m = null;
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
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public List<Media> mediasOnColecao(String codColecao, Connection conn) throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM mediacenter.colecao_media " +
                "where Colecao_codColecao='"+codColecao+"'");
        List<Media> medias = new ArrayList<>();
        while (rs.next()){
            MediaKey chave = new MediaKey(rs.getString(1),rs.getString(2));
            medias.add(MediaDAO.getInstance().get(chave));
        }
        return medias;
    }

    public int hashCode() {
        return inst.hashCode();
    }

    @Override
    public int size() {
        try (Connection conn = DriverManager.getConnection(url)) {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT codColecao FROM mediacenter.colecao");
            while (rs.next()) i++;
            return i;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public boolean isEmpty() {
        try(Connection con = DriverManager.getConnection(url)){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT codColecao FROM mediacenter.colecao");
            return !rs.next();
        }catch(Exception e){
            throw new NullPointerException(e.getMessage());
        }
    }


    @Override
    public boolean containsKey(Object key) throws NullPointerException{
        try(Connection con = DriverManager.getConnection(url)){
            Statement st = con.createStatement();
            String sql = "SELECT codColecao FROM mediacenter.colecao " +
                    "where codColecao='"+key+"'";
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        }catch(Exception e){
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public boolean containsValue(Object value){ throw new UnsupportedOperationException(); }


    @Override
    public Colecao get(Object key) {
        try (Connection conn = DriverManager.getConnection(url)) {
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
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }


    @Override
    public Colecao put(String key, Colecao value) {
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM mediacenter.colecao  " +
                    "where codColecao='"+key+"'");

            String sql = "INSERT INTO mediacenter.colecao VALUES " +
                    "('"+ value.getMediasCol()+"','0','"+ value.getNomeCol()+"')";
            stm.executeUpdate(sql);
            return new Colecao( value.getCodCol(),
                    value.getMediasCol(),
                    value.getNomeCol());
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public Colecao remove(Object key) {
        try (Connection conn = DriverManager.getConnection(url)) {
            Colecao m = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM mediacenter.colecao  " +
                    "where codColecao='"+key+"'";
            stm.executeUpdate(sql);
            return m;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public void putAll(Map<? extends String, ? extends Colecao> m) {
        throw new UnsupportedOperationException();
    }


    @Override
    public void clear() {
        try(Connection conn = DriverManager.getConnection(url)){
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM mediacenter.media");
        } catch(Exception e){
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Colecao> values() {
        try (Connection conn = DriverManager.getConnection(url)) {
            List<Colecao> col = new ArrayList<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM mediacenter.colecao");

            while(rs.next()) {
                col.add(new Colecao(rs.getString(1),
                        mediasOnColecao(rs.getString(1),conn),
                        rs.getString(3)));
            }

            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }



    @Override
    public Set<Entry<String, Colecao>> entrySet() {
        throw new UnsupportedOperationException();
    }
}


