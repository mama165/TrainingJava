package fr.kata.decisiontree.services;

import java.util.List;

/**
 * Lecture du fichier !!
 */
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
        List<String> linesFromFile = fileAdapter.extractLines();
        return new LineFormatter(linesFromFile).format();
    }

    private class HardCodedFileReader implements IObtainLines {
        @Override
        public List<String> extractLines() {
            // TODO : traitement métier
            return null;
        }
    }
}
