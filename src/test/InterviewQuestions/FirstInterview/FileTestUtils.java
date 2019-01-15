package InterviewQuestions.FirstInterview;

import java.io.File;
import java.net.URL;

public class FileTestUtils {
    public static File getFile(String path) {
        ClassLoader classLoader = FileTestUtils.class.getClassLoader();
        URL resource = classLoader.getResource(path);
        File file = new File(resource.getPath());

        return file;
    }
}
