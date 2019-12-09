import LN.MediaCenter;

import java.io.File;

public class MainTest  {

    public static void main(String[] args) {
        MediaCenter mc = new MediaCenter();
        String p = "c:/lindo/blablabla.mp3";
        String[] aux = p.split("\\.");
        System.out.println(aux[1]);
        File med = new File(p);
        System.out.println(med.getName());
    }
}
