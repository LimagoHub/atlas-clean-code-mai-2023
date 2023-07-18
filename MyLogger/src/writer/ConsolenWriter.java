package writer;

public class ConsolenWriter implements Writer{
    @Override
    public void write(final String message) {
        System.out.println(message);
    }
}
