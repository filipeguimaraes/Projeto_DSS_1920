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

        public boolean validaFich(String path) throws IOException;

        public void reproduzirMedia(MediaKey key);

        public void reproduz(String path);

        public void setEmailOn(String email) throws IOException;

        public void removePermissao() throws IOException;

        public void setPermissaoResidente() throws IOException;

        public void setPermissaoAdministrador() throws IOException;

        public void setPremissaoConvidado() throws IOException;

        public void iniciarSessao(String email, String password)
                throws UtilizadorException, AdminException, PermissaoException, IOException;

        public void registaUtilizador(String nome, String email, String password) throws IOException;

        public String copiaFicheiro(String path) throws IOException;

        public boolean eAdmin() throws IOException;

        public boolean eUtilizador() throws IOException;

        public boolean eConvidado() throws IOException;

        public BibliotecaDAO getBibliotecas() throws IOException;

        public Map<String, Utilizador> getUtilizadorDAO() throws IOException;

        public String getEmailOn() throws IOException;

        public Map<MediaKey, Media> getMediaDAO() throws IOException;


}
