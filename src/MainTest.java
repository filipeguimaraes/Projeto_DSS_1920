public class MainTest  {

    public static void main(String[] args) {
        String p = "/blablabla.mp3";
        String[] aux = p.split("\\.");
        System.out.println(aux[1]);
    }
}
