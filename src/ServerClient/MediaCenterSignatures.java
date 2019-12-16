package ServerClient;


import DAO.*;
import LN.Exceptions.AdminException;
import LN.Exceptions.MediaException;
import LN.Exceptions.PermissaoException;
import LN.Exceptions.UtilizadorException;
import LN.Media;
import LN.Residentes.Utilizador;
import UTILITIES.MediaKey;

import java.io.IOException;
import java.util.Map;

public interface MediaCenterSignatures {

        public void upload(String path, String nome, String col, String artista, String cat)
                throws MediaException, IOException ;

        public boolean validaFich(String path);

        public void reproduzirMedia(MediaKey key);

        public void reproduz(String path);

        public void setEmailOn(String email);

        public void removePermissao();

        public void setPermissaoResidente();

        public void setPermissaoAdministrador();

        public void setPremissaoConvidado();

        public void iniciarSessao(String email, String password)
                throws UtilizadorException, AdminException, PermissaoException;

        public void registaUtilizador(String nome, String email, String password);

        public String copiaFicheiro(String path) throws IOException;

        public boolean eAdmin();

        public boolean eUtilizador();

        public boolean eConvidado();

        public BibliotecaDAO getBibliotecas();

        public Map<String, Utilizador> getUtilizadorDAO();

        public String getEmailOn();

        public Map<MediaKey, Media> getMediaDAO();


}
