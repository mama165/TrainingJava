package InterviewQuestions.FirstInterview;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileBuilder {
    List<BaseCondition> baseConditions;

    public FileBuilder(List<BaseCondition> baseConditions) {
        this.baseConditions = baseConditions;
    }

    private File buildFile() throws IOException {
        String path = "resources/outputs/strategies_1.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                fileOutputStream, StandardCharsets.UTF_8
        );

        try (BufferedWriter writer = new BufferedWriter(outputStreamWriter)) {
            baseConditions.forEach(baseCondition -> {
                try {
                    writer.write(baseCondition.toString());
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        return new File(path);
    }

    public File build() throws IOException {
        return buildFile();
    }
}
