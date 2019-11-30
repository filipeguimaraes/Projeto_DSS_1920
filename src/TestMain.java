import LN.*;

public class TestMain {

    public static void main(String[] args) {
        Media m = new Media();
        m.setNome("Hello");
        m.setPath("c:\\Media\\Adele - Hello.mp3");

        MediaCenter mc = new MediaCenter();
        mc.adicionaMedia(m);
        mc.reproduzirMedia("Hello");
    }

}
