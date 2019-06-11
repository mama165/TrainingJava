package fr.kata.decisiontree.application;

import java.io.IOException;
import java.io.Writer;

public class FlattenExporterWriter extends FileExporter {
    public void writeContent(Writer writer, String flattenContent) throws IOException {
        writer.write(flattenContent);
    }
}
