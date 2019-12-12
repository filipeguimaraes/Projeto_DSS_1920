/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package LN;

import DAO.BibliotecaDAO;
import DAO.MediaDAO;
import DAO.UtilitarioDAO;
import DAO.UtilizadorDAO;
import LN.Exceptions.*;
import LN.Residentes.*;
import UTILITIES.MediaKey;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MediaCenter {

    private static MediaCenter inst = null;

    private Administrador admin;
    private String pathParaMedia;
    private Map<String,Biblioteca> bibliotecas;
    private Map<String, Utilizador> utilizadorDAO;
    private Map<MediaKey,Media> mediaDAO;
    private String emailOn;
    private Integer permissao;

    private static Integer administrador=1;
    private static Integer utilizador=2;
    private static Integer convidado=3;

    public static MediaCenter getInstance(){
        if(inst==null){
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
        this.permissao = 0;
    }

    public void adicionaMedia(Media m){
        MediaKey key = new MediaKey(m.getNomeMedia(),m.getArtista());
        mediaDAO.put(key,m);
    }

    public void setAdministrador() {
        this.permissao = administrador;
    }

    /**
     *
     * @param media
     */
    public void existeMedia(String media) {
        // TODO - implement MediaCenter.existeMedia
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param categoria
     * @param media
     */
    public void alteraCategoria(String categoria, String media) {
        // TODO - implement MediaCenter.alteraCategoria
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param nome
     */
    public void colecaoDefault(String nome) {
        // TODO - implement MediaCenter.colecaoDefault
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param nome
     */
    public void colecaoRandom(String nome) {
        // TODO - implement MediaCenter.colecaoRandom
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param artista
     * @param nome
     */
    public void colecaoArtista(String artista, String nome) {
        // TODO - implement MediaCenter.colecaoArtista
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param categoria
     * @param nome
     */
    public void colecaoCategoria(String categoria, String nome) {
        // TODO - implement MediaCenter.colecaoCategoria
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param nome
     */
    public void alteraNome(String nome) {
        // TODO - implement MediaCenter.alteraNome
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param emailDep
     */
    public void alteraEmail(String emailDep) {
        // TODO - implement MediaCenter.alteraEmail
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param pass
     */
    public void alteraPassword(String pass) {
        // TODO - implement MediaCenter.alteraPassword
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     * @param nome
     * @param col
     * @param artista
     * @param cat
     */
    public void upload(String path, String nome, String col, String artista, String cat)
            throws MediaException, IOException {
        if(!validaFich(path)) throw new MediaException("Formato de ficheiro invalido");
        boolean existe = mediaDAO.containsKey("nome");
        Utilizador u = utilizadorDAO.get(emailOn);
        if(existe) {
            //adicionar ao map com string categoria
            //mediaDAO.get("nome").getProprietarios().add(emailOn);
            //m.setCategoria(cat); passar para cima
        } else{
            String pathNovo = copiaFicheiro(path);
            Media m = new Media(nome, pathNovo, artista);
            Biblioteca b = u.getBiblioteca();
            Map<String,Colecao> colecoes = b.getColecoes();
            if (colecoes.containsKey(col)){
                Colecao c = colecoes.get(col);
                c.add(m);
            }else {
                List<Media> med= new ArrayList<>(); //colocar DAO
                med.add(m);
                //Colecao c = new Colecao(col,med,col);
                //b.addColecaoNaBiblioteca(c);
            }
        }
    }

    /**
     *
     * @param path
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
     *
     * @param path
     */
    public void removerMedia(String path) {
        // TODO - implement MediaCenter.removerMedia
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param key Chave que contem o nome da media a reproduzir e o respetivo artista
     */
    public void reproduzirMedia(MediaKey key) {
        reproduz(mediaDAO.get(key).getPath());
    }

    public void reproduz(String path){
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe", path);
            Process start = pb.start();
        } catch (IOException e) {
            ProcessBuilder pb = new ProcessBuilder(
                    "C:\\Program Files\\VideoLAN\\VLC\\vlc.exe", path);
            try {
                Process start = pb.start();
            } catch (IOException ex) {
                System.out.println("Não tem o vlc instalado"+ex);
            }
        }
    }

    public void terminarSessao() {
        // TODO - implement MediaCenter.terminarSessao
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param email
     */
    public void setEmailOn(String email) {
        this.emailOn = email;
    }

    public void removePermissao() {
        this.permissao=null;
    }

    public void apagaConta() {
        // TODO - implement MediaCenter.apagaConta
        throw new UnsupportedOperationException();
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
    public void setPremissaoConvidado() { this.permissao = convidado; }

    /**
     * Metodo que recebendo o email e a password, se corretos coloca o emailOn com o email do utilizador
     * @param email email fornecida
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
            } else if (this.permissao.equals(administrador)){
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
     *
     * @param nome
     * @param email
     * @param password
     */
    public void registaUtilizador(String nome, String email, String password) {
        String codBiblioteca = Integer.toString(bibliotecas.size());
        Biblioteca b = new Biblioteca(codBiblioteca,"Biblioteca de "+nome);
        Utilizador u = new Utilizador(codBiblioteca,nome,email,password);
        utilizadorDAO.put(email,u);
    }

    /**
     *
     * @param path
     */
    public String copiaFicheiro(String path) throws IOException {
        File origem = new File(path);
        String nome = origem.getName();
        File destino = new File(pathParaMedia+nome);
        /*
        if (destino.exists())
                throw new MediaException("O ficheiro já existe no sistema");


         */
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
        return destino.getPath();
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


    //apagar


    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    public String getPathParaMedia() {
        return pathParaMedia;
    }

    public void setPathParaMedia(String pathParaMedia) {
        this.pathParaMedia = pathParaMedia;
    }

    public Map<String, Biblioteca> getBibliotecas() {
        return bibliotecas;
    }

    public void setBibliotecas(Map<String, Biblioteca> bibliotecas) {
        this.bibliotecas = bibliotecas;
    }

    public Map<String, Utilizador> getUtilizadorDAO() {
        return utilizadorDAO;
    }

    public void setUtilizadorDAO(Map<String, Utilizador> utilizadorDAO) {
        this.utilizadorDAO = utilizadorDAO;
    }

    public void setMediaDAO(Map<MediaKey, Media> mediaDAO) {
        this.mediaDAO = mediaDAO;
    }

    public String getEmailOn() {
        return emailOn;
    }

    public Integer getPermissao() {
        return permissao;
    }

    public void setPermissao(Integer permissao) {
        this.permissao = permissao;
    }

    public static Integer getAdministrador() {
        return administrador;
    }

    public static void setAdministrador(Integer administrador) {
        MediaCenter.administrador = administrador;
    }

    public static Integer getUtilizador() {
        return utilizador;
    }

    public static void setUtilizador(Integer utilizador) {
        MediaCenter.utilizador = utilizador;
    }

    public static Integer getConvidado() {
        return convidado;
    }

    public static void setConvidado(Integer convidado) {
        MediaCenter.convidado = convidado;
    }

    public Map<MediaKey, Media> getMediaDAO() {
        return mediaDAO;
    }
}
