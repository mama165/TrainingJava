package fr.kata.decisiontree.application;

import fr.kata.decisiontree.domain.IRequestLines;
import fr.kata.decisiontree.domain.InvalidFileTreeFormat;
import fr.kata.decisiontree.services.IWriteLines;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.lang.System.lineSeparator;

public class FileInMemoryAdapter {
    private IRequestLines fileReader;
    private IWriteLines inMemoryPublicationStrategy;

    public FileInMemoryAdapter(IRequestLines fileReader) {
        new FileInMemoryAdapter(fileReader, new InMemoryPublicationStrategy());
    }

    public FileInMemoryAdapter(IRequestLines fileReader, IWriteLines inMemoryPublicationStrategy) {
        this.fileReader = fileReader;
        this.inMemoryPublicationStrategy = inMemoryPublicationStrategy;
    }

    public File flatten() throws InvalidFileTreeFormat, IOException {
        List<String> lines = fileReader.getFlattenedLines();
        return inMemoryPublicationStrategy.buildFile(lines);
    }

    private class InMemoryPublicationStrategy implements IWriteLines {
        private static final String filename = "output.txt";

        @Override
        public File buildFile(List<String> lines) throws IOException {
            String separatedLines = String.join(lineSeparator(), lines);

            FileOutputStream outputStream = new FileOutputStream(filename);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    outputStream, StandardCharsets.UTF_8);

            try (BufferedWriter writer = new BufferedWriter(outputStreamWriter)) {
                writer.write(separatedLines);
            }
            return null;
        }
    }
}
