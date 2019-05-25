package fr.kata.decisiontree.services;

import fr.kata.decisiontree.features.FileRepository;

public class DecisionTreeService implements IBuildTree {
    private final FileRepository fileRepository;

    public DecisionTreeService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    // TODO : https://www.programcreek.com/2011/03/java-read-a-file-line-by-line-code-example/
    // TODO : https://blog.octo.com/architecture-hexagonale-trois-principes-et-un-exemple-dimplementation/#orga

    @Override
    public void buildTree(String path) {
        FileAdapter fileAdapter = new FileAdapter(path);

        FileReader fileReader = new FileReader(fileAdapter);

        FileInMemoryAdapter fileInMemoryAdapter = new FileInMemoryAdapter(fileReader);
//        FileInMemoryAdapter fileInMemoryAdapter = new FileInMemoryAdapter(new FileReader(new FileAdapter(path)));

        fileInMemoryAdapter.buildFile();

//        List<BaseLine> baseLines = BaseLineFormatter.format(file);
//        List<Nodes> nodes = ParentNodeBuilder(baseLines).build();
    }
}
