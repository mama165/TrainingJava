package fr.kata.decisiontree.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;

public class FileExporter {
    public File exportFile(String fileName, Consumer<Writer> contentWriter) throws IOException {
        File file = new File("export/" + fileName);
        try (Writer writer = new FileWriter(file)) {
            contentWriter.accept(writer);
            return file;
        }
    }

//    public static void main(String[] args) throws IOException {
//        FileExporter fileExporter = new FileExporter();
//        FlattenExporterWriter flattenExporterWriter = new FlattenExporterWriter();
//        fileExporter.exportFile("flattened.txt", flattenExporterWriter::writeContent);
//    }
}
