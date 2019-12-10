import DAO.UtilizadorDAO;
import LN.MediaCenter;
import LN.Residentes.Utilizador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainTest  {

    public static void main(String[] args) {
        Map<String, Utilizador> users = new UtilizadorDAO();
        Utilizador fr = users.get("francisco@email.com");
        System.out.println("nome: "+fr.getNome());
        System.out.println("email: "+fr.getEmail());
        System.out.println("pass: "+fr.getPassword());
        System.out.println(users.containsKey("rita@email.com"));

    }
}
