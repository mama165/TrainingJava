package fr.kata.decisiontree.features;

import java.io.File;

public interface FileRepository {
    File find(String path);
}
