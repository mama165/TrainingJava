package fr.kata.decisiontree.domain;

public class InvalidFileTreeFormat extends Exception {
    InvalidFileTreeFormat() {
    }
    public InvalidFileTreeFormat(String line) {
        super("This line is malformed : " + line);
    }
}
