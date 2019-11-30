package LN;

import LN.Residentes.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MediaCenter {

    private Administrador admin;
    private Map<String,Biblioteca> bibliotecas;//map
    private Map<String,Utilizador> utilizadores;//map
    private Map<String,Media> medias;//map
    private String emailOn;
    private Integer permissao;
    private static Integer administrador=1;
    private static Integer convidado=3;
    private static Integer utilizador=2;

    public MediaCenter() {
        this.admin = new Administrador();
        this.bibliotecas = new HashMap<>();
        this.utilizadores = new HashMap<>();
        this.medias = new HashMap<>();
        this.emailOn = null;
        this.permissao = 0;
    }

    public void adicionaMedia(Media m){
        medias.put(m.getNomeMedia(),m);
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
    public void upload(String path, String nome, String col, String artista, String cat) {
        // TODO - implement MediaCenter.upload
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     */
    public void validaPath(String path) {
        // TODO - implement MediaCenter.validaPath
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     */
    public void validaFich(String path) {
        // TODO - implement MediaCenter.validaFich
        throw new UnsupportedOperationException();
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
     * @param nomeMedia nome da media a reproduzer
     */
    public void reproduzirMedia(String nomeMedia) {
        reproduz(medias.get(nomeMedia).getPath());
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
        // TODO - implement MediaCenter.removePermissao
        throw new UnsupportedOperationException();
    }

    public void apagaConta() {
        // TODO - implement MediaCenter.apagaConta
        throw new UnsupportedOperationException();
    }

    public void setPermissaoResidente() {
        // TODO - implement MediaCenter.setPermissaoResidente
        throw new UnsupportedOperationException();
    }

    public void setPermissaoAdministrador() {
        // TODO - implement MediaCenter.setPermissaoAdministrador
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param email
     * @param password
     */
    public void iniciarSessao(String email, String password) {
        // TODO - implement MediaCenter.iniciarSessao
        throw new UnsupportedOperationException();
    }

    public void setPremissaoConvidado() {
        // TODO - implement MediaCenter.setPremissaoConvidado
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param nome
     * @param email
     * @param password
     */
    public void registaUtilizador(String nome, String email, String password) {
        // TODO - implement MediaCenter.registaUtilizador
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     */
    public void copiaFicheiro(String path) {
        // TODO - implement MediaCenter.copiaFicheiro
        throw new UnsupportedOperationException();
    }

}
