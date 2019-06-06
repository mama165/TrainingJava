package fr.kata.decisiontree.services;

import fr.kata.decisiontree.domain.InvalidFileTreeFormat;

import java.io.IOException;

public interface IBuildTree {
    void buildFile(String path) throws IOException, InvalidFileTreeFormat;
}
