/**
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package DAO;

import LN.Biblioteca;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BibliotecaDAO implements Map<String, Biblioteca> {
    private static BibliotecaDAO inst = null;
    private String url = "jdbc:mysql://localhost/mediacenter?" +
            "serverTimezone=UTC&user=root&password=bolinhosdeatum";

    public BibliotecaDAO() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            throw new NullPointerException(e.getMessage());
        }
    }

    public static BibliotecaDAO getInstance(){
        if (inst==null){
            inst = new BibliotecaDAO();
        }
        return inst;
    }

    public int hashCode() {
        return inst.hashCode();
    }

    @Override
    public int size() {
        try (Connection conn = DriverManager.getConnection(url)) {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT cod FROM mediacenter.biblioteca");
            while (rs.next()) i++;
            return i;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public boolean isEmpty() {
        try(Connection con = DriverManager.getConnection(url)){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT cod FROM mediacenter.biblioteca");
            return !rs.next();
        }catch(Exception e){
            throw new NullPointerException(e.getMessage());
        }
    }


    @Override
    public boolean containsKey(Object key) throws NullPointerException{
        try(Connection con = DriverManager.getConnection(url)){
            Statement st = con.createStatement();
            String sql = "SELECT cod FROM mediacenter.biblioteca " +
                    "where cod='"+key+"' ";
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        }catch(Exception e){
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public boolean containsValue(Object value){ throw new UnsupportedOperationException(); }


    @Override
    public Biblioteca get(Object key) {
        try (Connection conn = DriverManager.getConnection(url)) {
            Biblioteca b = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM mediacenter.biblioteca " +
                    "where cod='"+key+"'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next())
                b = new Biblioteca(null,
                        rs.getString(1),
                        rs.getString(2));
            return b;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }



    @Override
    public Biblioteca put(String key, Biblioteca value) {
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM mediacenter.biblioteca " +
                    "where cod='"+key+"'");
            String sql = "INSERT INTO mediacenter.biblioteca VALUES ('"+
                    value.getCod()+"','"+
                    value.getNomeBiblio()+"')";
            int i  = stm.executeUpdate(sql);
            return new Biblioteca(
                    null,
                    value.getNomeBiblio(),
                    value.getCod());
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public Biblioteca remove(Object key) {
        try (Connection conn = DriverManager.getConnection(url)) {
            Biblioteca m = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM mediacenter.biblioteca  " +
                    "where cod='"+key+"'";
            stm.executeUpdate(sql);
            return m;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public void putAll(Map<? extends String, ? extends Biblioteca> m) {
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
    public Collection<Biblioteca> values() {
        try (Connection conn = DriverManager.getConnection(url)) {
            Collection<Biblioteca> col = new HashSet<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM mediacenter.biblioteca");
            for (;rs.next();) {
                col.add(new Biblioteca(null,
                        rs.getString(1),
                        rs.getString(2)));
            }
            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public Set<Entry<String,Biblioteca>> entrySet() {
        throw new UnsupportedOperationException();
    }
}

