/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçanlo Ferreira A84073
 */
package LN;

import LN.Residentes.Utilizador;
import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UtilizadorDAO implements Map<String, Utilizador> {
    private static UtilizadorDAO inst = null;

    public UtilizadorDAO() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            throw new NullPointerException(e.getMessage());
        }
    }

    public static UtilizadorDAO getInstance(){
        if (inst==null){
            inst = new UtilizadorDAO();
        }
        return inst;
    }

    public int hashCode() {
        return this.inst.hashCode();
    }

    @Override
    public int size() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/utilizador?user=root&password=bolinhosdeatum")) {
            int i = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT email FROM TUtilizador");
            for (;rs.next();i++);
            return i;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public boolean isEmpty() {
        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/utilizador?user=root&password=bolinhosdeatum")){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT nome FROM TUtilizador");
            return !rs.next();
        }catch(Exception e){
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public boolean containsKey(Object key) throws NullPointerException{
        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/utilizador?user=root&password=bolinhosdeatum")){
            Statement st = con.createStatement();
            String sql = "SELECT nome FROM TUtilizador where email='"+(String)key+"'";
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        }catch(Exception e){
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Utilizador get(Object key) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/utilizador?user=root&password=bolinhosdeatum")) {
            Utilizador ut = null;
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM TUtilizador WHERE numero='"+key+"'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next())
                ut = new Utilizador(null,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3));
            return ut;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public Utilizador put(String key, Utilizador value) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/utilizador?user=root&password=bolinhosdeatum")) {
            Utilizador al = null;
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM TUtilizador WHERE email='"+key+"'");
            String sql = "INSERT INTO TUtilizador VALUES ('"+
                    value.getNome()+"','"+
                    key+"',"+
                    value.getPassword()+","+
                    "biblioteca"+")";
            int i  = stm.executeUpdate(sql);
            return new Utilizador(
                    value.getBiblioteca(),
                    value.getNome(),
                    value.getEmail(),
                    value.getPassword());
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public Utilizador remove(Object key) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/utilizador?user=root&password=bolinhosdeatum")) {
            Utilizador ut = this.get(key);
            Statement stm = conn.createStatement();
            String sql = "DELETE '"+key+"' FROM TUtilizador";
            int i  = stm.executeUpdate(sql);
            return ut;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public void putAll(Map<? extends String, ? extends Utilizador> m) {

    }

    @Override
    public void clear() {
        try(Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/utilizador?user=root&password=bolinhosdeatum")){
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM TUtilizador");
        } catch(Exception e){
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Utilizador> values() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/utilizador?user=root&password=bolinhosdeatum")) {
            Collection<Utilizador> col = new HashSet<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM TUtilizador");
            for (;rs.next();) {
                col.add(new Utilizador(
                        null,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public Set<Entry<String, Utilizador>> entrySet() {
        return null;
    }
}
