package fr.kata.decisiontree.application;

import fr.kata.decisiontree.domain.IRequestLines;
import fr.kata.decisiontree.services.IWriteLines;

import java.util.List;

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

    public void flatten() {
        List<String> lines = fileReader.giveMeSomeFlattenedLines();
        inMemoryPublicationStrategy.buildFile(lines);
    }

    private class InMemoryPublicationStrategy implements IWriteLines {
        @Override
        public  void  buildFile(List<String> lines) {

        }
    }
}
