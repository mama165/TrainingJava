package fr.kata.decisiontree.domain;

import java.util.List;

public interface IRequestLines {
    List<String> getFlattenedLines() throws InvalidFileTreeFormat;
}
