package com.csvreader.app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

public class FileRepositoryImpl implements FileRepository {
    private static final String GZIP_FILE_NAME = "title.basics.tsv.gz";
    private static final String FILE_NAME = "title.basics.tsv";
    private static final int BUFFER_SIZE = 8096;


    @Override
    public List<String> get(String url) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(GZIP_FILE_NAME)) {
            URL webSite = new URL(url);
            ReadableByteChannel readChannel = Channels.newChannel(webSite.openStream());
            FileChannel writeChannel = fileOutputStream.getChannel();
            writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE);
            decompressGzipFile();
            return convertIntoString();
        }
    }

    private void decompressGzipFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(FileRepositoryImpl.GZIP_FILE_NAME);
        try (
                GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            while ((len = gzipInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
        }
    }

    private List<String> convertIntoString() throws IOException {
        List<String> list;
        Path path = Paths.get(FileRepositoryImpl.FILE_NAME);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            list = br.lines().collect(Collectors.toList());
            return list;
        }
    }
}
