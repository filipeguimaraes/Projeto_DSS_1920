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

        void upload(String path, String nome, String col, String artista, String cat)
                throws MediaException, IOException ;

        boolean validaFich(String path) throws IOException;

        void reproduzirMedia(MediaKey key);

        void reproduz(String path);

        void setEmailOn(String email) throws IOException;

        void removePermissao() throws IOException;

        void setPermissaoResidente() throws IOException;

        void setPermissaoAdministrador() throws IOException;

        void setPremissaoConvidado() throws IOException;

        void iniciarSessao(String email, String password)
                throws UtilizadorException, AdminException, PermissaoException, IOException;

        void registaUtilizador(String nome, String email, String password) throws IOException;

        String copiaFicheiro(String path) throws IOException;

        boolean eAdmin() throws IOException;

        boolean eUtilizador() throws IOException;

        boolean eConvidado() throws IOException;

        Utilizador getUtilizador(String email) throws IOException;

        List<Media> getMedias() throws IOException;

        Biblioteca getBibliotecaByNome(String selectedItem) throws IOException;

        String getEmailOn() throws IOException;

        List<Biblioteca> getBibliotecas() throws IOException;

}
