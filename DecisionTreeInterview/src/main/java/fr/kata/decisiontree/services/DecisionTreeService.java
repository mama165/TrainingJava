package fr.kata.decisiontree.services;

import fr.kata.decisiontree.application.FileInMemoryAdapter;
import fr.kata.decisiontree.architecture.FileAdapter;
import fr.kata.decisiontree.architecture.IObtainLines;
import fr.kata.decisiontree.domain.FileReader;
import fr.kata.decisiontree.domain.IRequestLines;

import java.io.IOException;

public class DecisionTreeService implements IBuildTree {
    // TODO : https://www.programcreek.com/2011/03/java-read-a-file-line-by-line-code-example/
    // TODO : https://blog.octo.com/architecture-hexagonale-trois-principes-et-un-exemple-dimplementation/#orga

    @Override
    public void buildFile(String path) throws IOException {
        final IObtainLines fileAdapter = new FileAdapter(path);
        final IRequestLines fileReader = new FileReader(fileAdapter);
        final FileInMemoryAdapter fileInMemoryAdapter = new FileInMemoryAdapter(fileReader);
//        FileInMemoryAdapter fileInMemoryAdapter = new FileInMemoryAdapter(new FileReader(new FileAdapter(path)));
        fileInMemoryAdapter.flatten();
    }
}
