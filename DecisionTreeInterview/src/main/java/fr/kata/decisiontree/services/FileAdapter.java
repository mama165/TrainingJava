package fr.kata.decisiontree.services;

import java.util.List;

public class FileAdapter implements IObtainLines {
    private List<String> lines;

    public FileAdapter(String path) {
        this.lines = extractStringsFromFile(path);
    }

    private List<String> extractStringsFromFile(String path) {
        return null;
    }

    @Override
    public List<String> getLines() {
        return lines;
    }
}
