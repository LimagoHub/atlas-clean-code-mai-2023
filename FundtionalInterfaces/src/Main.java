import java.sql.SQLOutput;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {

        doIt(System.out::println);
    }

    public static void doIt(Consumer consumer) {
        String gruss = "Hallo";
        consumer.accept(gruss);
    }
}