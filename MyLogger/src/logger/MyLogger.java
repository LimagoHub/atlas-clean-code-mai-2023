package logger;

import writer.Writer;

public class MyLogger {

    private final Writer writer;

    public MyLogger(final Writer writer) {
        this.writer = writer;
    }

    public void log(String message) {
        final String prefix = "Mai 10, 2023 9:34:11 VORM. Main main\nINFO: ";
        writer.write(prefix + message);
    }
}
