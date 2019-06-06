package fr.kata.decisiontree.domain;

import fr.kata.decisiontree.architecture.IObtainLines;

import java.util.List;

/**
 * Lecture du fichier !!
 */
public class FileReader implements IRequestLines {
    private  IObtainLines fileAdapter;
    private IFlattener flattener;

    public FileReader() {
        new HardCodedFileReader();
    }

    public FileReader(IObtainLines fileAdapter) {
        new FileReader(fileAdapter, new Flattener());
    }

    private FileReader(IObtainLines fileAdapter, IFlattener flattener) {
        this.fileAdapter = fileAdapter;
        this.flattener = flattener;
    }

    @Override
    public List<String> getFlattenedLines() throws InvalidFileTreeFormat {
        List<String> linesFromFile = fileAdapter.extractLines();
        return flattener.flatten(linesFromFile);
    }

    private class HardCodedFileReader implements IObtainLines {
        @Override
        public List<String> extractLines() {
            // TODO : traitement métier
            return null;
        }
    }
}
