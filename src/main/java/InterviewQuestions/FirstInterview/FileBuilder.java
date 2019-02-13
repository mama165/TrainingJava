package InterviewQuestions.FirstInterview;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;

public class FileBuilder {
    List<BaseCondition> baseConditions;

    public FileBuilder(List<BaseCondition> baseConditions) {
        this.baseConditions = baseConditions;
    }

    private Optional<File> buildFile() throws IOException {
        String path = "resources/outputs/strategies_1.txt";
        File file;
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                fileOutputStream, StandardCharsets.UTF_8
        );

        try (BufferedWriter writer = new BufferedWriter(outputStreamWriter)) {
            file = Paths.get(path).toFile();
            baseConditions.forEach(baseCondition -> {
                try {
                    writer.write(baseCondition.toString());
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        return of(file);
    }

    public Optional<File> build() throws IOException {
        return buildFile();
    }
}
