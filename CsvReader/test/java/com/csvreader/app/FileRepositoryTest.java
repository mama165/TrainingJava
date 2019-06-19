package com.csvreader.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FileRepositoryTest {
    private FileRepositoryImpl fileRepositoryImpl;
    private static final String URL = "https://datasets.imdbws.com/title.basics.tsv.gz";

    @BeforeEach
    void setup() {
        fileRepositoryImpl = new FileRepositoryImpl();
    }

    @AfterEach
    void clean() throws FileNotFoundException {
        File file = new File("title.basics.tsv");
        File fileGZ = new File("title.basics.tsv.gz");
        file.delete();
        fileGZ.delete();
    }

    @Test
    void should_extract_limited_lines_from_external_file() throws IOException {
        List<String> output = fileRepositoryImpl.get(URL);
        List<String> outputLimited = output.stream().limit(10).collect(Collectors.toList());

        List<String> expected = Arrays.asList(
                "tconst\ttitleType\tprimaryTitle\toriginalTitle\tisAdult\tstartYear\tendYear\truntimeMinutes\tgenres",
                "tt0000001\tshort\tCarmencita\tCarmencita\t0\t1894\t\\N\t1\tDocumentary,Short",
                "tt0000002\tshort\tLe clown et ses chiens\tLe clown et ses chiens\t0\t1892\t\\N\t5\tAnimation,Short",
                "tt0000003\tshort\tPauvre Pierrot\tPauvre Pierrot\t0\t1892\t\\N\t4\tAnimation,Comedy,Romance",
                "tt0000004\tshort\tUn bon bock\tUn bon bock\t0\t1892\t\\N\t\\N\tAnimation,Short",
                "tt0000005\tshort\tBlacksmith Scene\tBlacksmith Scene\t0\t1893\t\\N\t1\tComedy,Short",
                "tt0000006\tshort\tChinese Opium Den\tChinese Opium Den\t0\t1894\t\\N\t1\tShort",
                "tt0000007\tshort\tCorbett and Courtney Before the Kinetograph\tCorbett and Courtney Before the Kinetograph\t0\t1894\t\\N\t1\tShort,Sport",
                "tt0000008\tshort\tEdison Kinetoscopic Record of a Sneeze\tEdison Kinetoscopic Record of a Sneeze\t0\t1894\t\\N\t1\tDocumentary,Short",
                "tt0000009\tmovie\tMiss Jerry\tMiss Jerry\t0\t1894\t\\N\t45\tRomance"
        );

        assertEquals(expected, outputLimited);
    }
}
