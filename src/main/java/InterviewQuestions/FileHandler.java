package InterviewQuestions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileHandler {
    String path;

    public FileHandler(String path) {
        this.path = path;
    }

    public void extractLines() throws IOException {
        String path = this.path;
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(line -> parseLine(line));

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void parseLine(String line) {

    }
}
