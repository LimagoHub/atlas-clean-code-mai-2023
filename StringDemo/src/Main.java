import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {

        StringBuilder s = new StringBuilder("Hallo");

        Instant start = Instant.now();
        for(int i = 0; i < 2_000_000; i++) {
            s.append("a");
        }
        String result = s.toString();
        Instant ende = Instant.now();
        Duration duration = Duration.between(start, ende);

        System.out.println("Duration = " + duration.toMillis());
    }
}