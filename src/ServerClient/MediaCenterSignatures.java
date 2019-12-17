package ServerClient;


import DAO.*;
import LN.Biblioteca;
import LN.Exceptions.AdminException;
import LN.Exceptions.MediaException;
import LN.Exceptions.PermissaoException;
import LN.Exceptions.UtilizadorException;
import LN.Media;
import LN.Residentes.Utilizador;
import UTILITIES.MediaKey;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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

        public boolean eAdmin();

        public boolean eUtilizador();

        public boolean eConvidado() ;

        Utilizador getUtilizador(String email);

        List<Media> getMedias();

        Biblioteca getBibliotecaByNome(String selectedItem);

        String getEmailOn();

        List<Biblioteca> getBibliotecas();

}
