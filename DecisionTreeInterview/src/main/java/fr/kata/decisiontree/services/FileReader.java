package fr.kata.decisiontree.services;

import java.util.List;

public class FileReader implements IRequestLines {
    private IObtainLines fileAdapter;

    public FileReader() {
        new HardCodedFileReader();
    }

    public FileReader(IObtainLines fileAdapter) {
        this.fileAdapter = fileAdapter;
    }

    @Override
    public List<String> giveMeSomeLines() {
        return fileAdapter.getLines();
    }

    private class HardCodedFileReader  implements  IObtainLines{
        @Override
        public List<String> getLines() {
            return null;
        }
    }
}
