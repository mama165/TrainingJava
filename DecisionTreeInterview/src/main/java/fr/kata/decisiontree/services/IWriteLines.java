package fr.kata.decisiontree.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IWriteLines {
    File buildFile(List<String> strings) throws IOException;
}
