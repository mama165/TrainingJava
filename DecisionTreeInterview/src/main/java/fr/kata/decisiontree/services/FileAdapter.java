package fr.kata.decisiontree.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Read file in memory
 * Coté infrastructure (Architecture hexagonal)
 */
public class FileAdapter implements IObtainLines {
    private final List<String> lines;

    public FileAdapter(String path) throws IOException {
        this.lines = extractStringsFromFile(path);
    }

    private List<String> extractStringsFromFile(String path) throws IOException {
        List<String> lines = Files.lines(new File(path).toPath())
                .collect(Collectors.toList());
        return Collections.unmodifiableList(lines);
    }

    @Override
    public List<String> extractLines() {
        return lines;
    }
}
