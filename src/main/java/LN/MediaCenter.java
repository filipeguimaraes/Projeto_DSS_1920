/**
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package LN;


import DAO.*;
import LN.Exceptions.*;
import LN.Residentes.Administrador;
import LN.Residentes.Utilizador;
import UTILITIES.MediaKey;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MediaCenter {

    private static MediaCenter inst = null;

    private Administrador admin;
    private String pathParaMedia;
    private BibliotecaDAO bibliotecas;
    private Map<String, Utilizador> utilizadorDAO;
    private Map<MediaKey, Media> mediaDAO;
    private String emailOn;
    private Integer permissao;

    private static Integer administrador = 1;
    private static Integer utilizador = 2;
    private static Integer convidado = 3;

    public static MediaCenter getInstance() {
        if (inst == null) {
            inst = new MediaCenter();
        }
        return inst;
    }



    public MediaCenter() {

        this.admin = new Administrador();
        this.pathParaMedia = UtilitarioDAO.getInstance().pathToMedia();
        this.bibliotecas = BibliotecaDAO.getInstance();
        this.utilizadorDAO = UtilizadorDAO.getInstance();
        this.mediaDAO = MediaDAO.getInstance();
        this.emailOn = null;
        this.permissao = null;
    }


    /**
     * @param path    Caminho para o ficheiro de origem
     * @param nome    Nome da media
     * @param col     Nome da colecao
     * @param artista Nome do artista
     * @param cat     Categoria da media
     */
    public void upload(String path, String nome, String col, String artista, String cat)
            throws MediaException, IOException {
        if (!validaFich(path))
            throw new MediaException("Formato de ficheiro invalido");

        MediaKey chave = new MediaKey(nome, artista);
        boolean existe = this.mediaDAO.containsKey(chave);

        Utilizador u = this.utilizadorDAO.get(this.getEmailOn());
        Biblioteca b = this.bibliotecas.get(u.getBiblioteca().getCod());
        Colecao colecao = b.getColecaoByNome(col);

        if (colecao == null) {
            String codCol = String.valueOf((ColecaoDAO.getInstance().size() * 10) + 10);
            colecao = new Colecao(codCol, col);
            b.adicionaColecao(colecao);
        }

        if (!existe) {
            String pathNovo = copiaFicheiro(path);
            Media auxiliar = new Media(nome, pathNovo, artista);
            this.mediaDAO.put(chave, auxiliar);
        }

        Media m = this.mediaDAO.get(chave);
        colecao.adicionaMedia(m);
        m.alteraCategoriaPorUtilizador(u,cat);
    }

    /**
     * @param path Caminho onde vai ser verificada a extensão
     */
    public boolean validaFich(String path) {
        String ext = path.split("\\.")[1];
        return ext.equals("mp3")
                || ext.equals("mp4")
                || ext.equals("wav")
                || ext.equals("mov")
                || ext.equals("flv")
                || ext.equals("wmv")
                || ext.equals("mp2")
                || ext.equals("mkv")
                || ext.equals("dts")
                || ext.equals("asf")
                || ext.equals("nut")
                || ext.equals("avi")
                || ext.equals("mpg");
    }

    /**
     * @param key Chave que contem o nome da media a reproduzir e o respetivo artista
     */
    public void reproduzirMedia(MediaKey key) {
        reproduz(this.mediaDAO.get(key).getPath());
    }


    public void reproduz(String path) {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "C:/Program Files (x86)/VideoLAN/VLC/vlc.exe", new File(path).getPath());
            pb.start();
        } catch (IOException e) {
            ProcessBuilder pb = new ProcessBuilder(
                    "C:/Program Files/VideoLAN/VLC/vlc.exe", new File(path).getPath());
            try {
                pb.start();
            } catch (IOException ex) {
                System.out.println("Não tem o vlc instalado" + ex);
            }
        }
    }

    /**
     * @param email Email que esta a iniciar sessão
     */
    public void setEmailOn(String email) {
        this.emailOn = email;
    }

    public void removePermissao() {
        this.emailOn = null;
        this.permissao = null;
    }


    /**
     * Metodo para definir a permissão para residente
     */
    public void setPermissaoResidente() {
        this.permissao = utilizador;
    }

    /**
     * Metodo para definir a permissão para administrador
     */
    public void setPermissaoAdministrador() {
        this.permissao = administrador;
    }

    /**
     * Metodo para definir a permissão para convidado
     */
    public void setPremissaoConvidado() {
        this.permissao = convidado;
    }

    /**
     * Metodo que recebendo o email e a password, se corretos coloca o emailOn com o email do utilizador
     *
     * @param email    email fornecida
     * @param password password fornecida
     */
    public void iniciarSessao(String email, String password)
            throws UtilizadorException, AdminException, PermissaoException {
        if (!this.permissao.equals(convidado)) {
            if (this.permissao.equals(utilizador)) {
                Utilizador u = utilizadorDAO.get(email);
                if (u == null) {
                    throw new UtilizadorException("O email que introduziu não se encontra registado no sistema," +
                            " contacte o administrador para criar a sua conta.");
                }

                if (!u.getPassword().equals(password)) {
                    throw new UtilizadorException("A password que introduziu está incorreta.");
                }
            } else if (this.permissao.equals(administrador)) {
                if (!admin.getEmail().equals(email))
                    throw new AdminException("O email que introduziu está incorreto");

                if (!admin.getPassAdmin().equals(password)) {
                    throw new AdminException("A password que introduziu está incorreta.");
                }


            } else throw new PermissaoException("Não tem permissões");
            setEmailOn(email);
        }
    }

    /**
     * @param nome     nome do urilizador
     * @param email    email do utilizador
     * @param password password do utilizador
     */
    public void registaUtilizador(String nome, String email, String password) {
        String codBiblioteca = String.valueOf(bibliotecas.size() + 1);
        Biblioteca b = new Biblioteca(codBiblioteca, "Biblioteca de " + nome);
        Utilizador u = new Utilizador(codBiblioteca, nome, email, password);
        utilizadorDAO.put(email, u);
    }

    /**
     * @param path Caminho do ficheiro de destino
     */
    public String copiaFicheiro(String path) throws IOException {
        File origem = new File(path);
        String nome = origem.getName();
        File destino = new File(pathParaMedia + nome);
        FileChannel entrada = null;
        FileChannel saida = null;

        try {
            entrada = new FileInputStream(origem).getChannel();
            saida = new FileOutputStream(destino).getChannel();
            entrada.transferTo(0, entrada.size(), saida);
        } finally {
            if (entrada != null && entrada.isOpen())
                entrada.close();
            if (saida != null && saida.isOpen())
                saida.close();
        }

        String aux = destino.getPath().replaceAll(Pattern.quote("\\"), "/");

        return aux;
    }


    public void alteraCategoriaComColecao(String categoria, MediaKey media) {
        this.mediaDAO.get(media);

    }

    public void alteraCategoria(String categoria, MediaKey media) {
        this.mediaDAO
                .get(media)
                .alteraCategoriaPorUtilizador(this.utilizadorDAO.get(emailOn),categoria);
    }

    public boolean eAdmin() {
        return permissao.equals(administrador);
    }

    public boolean eUtilizador() {
        return permissao.equals(utilizador);
    }

    public boolean eConvidado() {
        return permissao.equals(convidado);
    }

    public String getEmailOn() {
        return emailOn;
    }

    public Biblioteca getBibliotecaByNome(String nome){
        return this.bibliotecas.getByNome(nome);
    }

    public List<Biblioteca> getBibliotecas(){
        return this.bibliotecas.values();
    }

    public Utilizador getUtilizador(String email){
        return this.utilizadorDAO.get(email);
    }

    public List<Media> getMedias() {
        return new ArrayList<>(this.mediaDAO.values());
    }
}
