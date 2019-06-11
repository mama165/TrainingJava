package fr.kata.decisiontree.application;

import java.io.FileWriter;
import java.io.IOException;

//lambda expressions and the execute around method (EAM) pattern to
//manage resources

public class FileWriterEAM  {
    private final FileWriter writer;

    private FileWriterEAM(final String fileName) throws IOException {
        writer = new FileWriter(fileName);
    }
    private void close() throws IOException {
        System.out.println("close called automatically...");
        writer.close();
    }
    public void writeStuff(final String message) throws IOException {
        writer.write(message);
    }
    //...

    public static void use(final String fileName, final UseInstance<FileWriterEAM, IOException> block) throws IOException {

        final FileWriterEAM writerEAM = new FileWriterEAM(fileName);
        try {
            block.accept(writerEAM);
        } finally {
            writerEAM.close();
        }
    }

    @FunctionalInterface
    public interface UseInstance<T, X extends Throwable> {
        void accept(T instance) throws X;
    }

    public static void main(final String[] args) throws IOException {

        FileWriterEAM.use("eam.txt", writerEAM -> writerEAM.writeStuff("sweet"));

        FileWriterEAM.use("eam2.txt", writerEAM -> {
            writerEAM.writeStuff("how");
            writerEAM.writeStuff("sweet");
        });

        FileWriterEAM.use("eam3.txt", FileWriterEAM::writeIt);

    }


    void writeIt() throws IOException{
        this.writeStuff("How ");
        this.writeStuff("sweet ");
        this.writeStuff("it is");

    }

}