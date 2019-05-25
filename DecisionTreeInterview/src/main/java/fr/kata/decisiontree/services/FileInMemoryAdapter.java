package fr.kata.decisiontree.services;

import java.util.List;

public class FileInMemoryAdapter {
    private FileReader fileReader;
    private IWriteLines inMemoryPublicationStrategy;

    public FileInMemoryAdapter(FileReader fileReader) {
        new FileInMemoryAdapter(fileReader, new InMemoryPublicationStrategy());
    }

    public FileInMemoryAdapter(FileReader fileReader, IWriteLines inMemoryPublicationStrategy) {
        this.fileReader = fileReader;
        this.inMemoryPublicationStrategy = inMemoryPublicationStrategy;
    }

    public void buildFile() {
        List<String> strings = fileReader.giveMeSomeLines();
        inMemoryPublicationStrategy.buildFile(strings);
    }

    private class InMemoryPublicationStrategy implements IWriteLines {
        @Override
        public  void  buildFile(List<String> strings) {

        }
    }
}
