package fr.kata.decisiontree.domain;

import java.util.List;

public interface IFlattener {
    List<String> flatten(List<String> linesFromFile) throws InvalidFileTreeFormat;
}
