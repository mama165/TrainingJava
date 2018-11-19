package OCA;

public class oca {

    public static void main(String [] args) {
        infiniteLoop();
    }

    // Page 96
    public static void infiniteLoop(){
        for (int i = 0; i <10;) {
            i = i++;
            System.out.println("Hello World)");
        }
    }
}
