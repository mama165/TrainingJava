package com.csvreader.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CSVReaderServiceTest {
    private static final String URL = "http://fake.com";

    private CSVReaderService csvReaderService;
    @Mock
    private FileRepository fileRepository;
    @Mock
    private  MessageQueueRepository messageQueueRepository;

    @BeforeEach
    void setup() {
        csvReaderService = new CSVReaderService(fileRepository, messageQueueRepository);
    }

    @Test
    void should_throw_IOException_when_url_undefined() throws InterruptedException, ExecutionException, IOException {
        when(fileRepository.get(anyString())).thenThrow(IOException.class);

        assertThrows(IOException.class, () ->
                csvReaderService.compute(URL)
        );
        verifyZeroInteractions(messageQueueRepository);
    }

    @Test
    void should_throw_InterruptedException_when_url_undefined() throws InterruptedException, ExecutionException, IOException {
        when(fileRepository.get(anyString())).thenThrow(InterruptedException.class);

        assertThrows(InterruptedException.class, () ->
                csvReaderService.compute(URL)
        );
        verifyZeroInteractions(messageQueueRepository);
    }

    @Test
    void should_throw_ExecutionException_when_url_undefined() throws InterruptedException, ExecutionException, IOException {
        when(fileRepository.get(anyString())).thenThrow(ExecutionException.class);

        assertThrows(ExecutionException.class, () ->
                csvReaderService.compute(URL)
        );
        verifyZeroInteractions(messageQueueRepository);
    }

    @Test
    void should_publish_movies_when_computing_from_url() throws InterruptedException, ExecutionException, IOException {
        List<String> mockedFileContents = Arrays.asList(
                "tconst\ttitleType\tprimaryTitle\toriginalTitle\tisAdult\tstartYear\tendYear\truntimeMinutes\tgenres",
                "tt0000001\tshort\tCarmencita\tCarmencita\t0\t1894\t\\N\t1\tDocumentary,Short",
                "tt0000002\tmovie\tLe clown et ses chiens\tLe clown et ses chiens\t0\t1892\t\\N\t5\tComedy,Short",
                "tt0000003\tshort\tPauvre Pierrot\tPauvre Pierrot\t0\t1892\t\\N\t4\tAnimation,Comedy,Romance",
                "tt0000004\tmovie\tUn bon bock\tUn bon bock\t0\t1892\t\\N\t\\N\tAnimation,Short",
                "tt0000005\tshort\tBlacksmith Scene\tBlacksmith Scene\t0\t1893\t\\N\t1\tComedy,Short",
                "tt0000006\tmovie\tChinese Opium Den\tChinese Opium Den\t0\t1894\t\\N\t1\tComedy,Animation",
                "tt9916710\ttvEpisode\tEpisode dated 21 February 2012\tEpisode dated 21 February 2012\t0\t2012\t\\N\t\\N\t\\N",
                "tt9918910\tmovie\tEpisode dated 21 February 2012\tEpisode dated 21 February 2012\t0\t2012\t\\N\t\\N\tShort,Comedy,Romance,Documentary"
        );


        List<Movie> expectedMovies = Arrays.asList(
                Movie.create("tt0000002", "movie","Le clown et ses chiens", "Le clown et ses chiens", "0", "1892", "\\N", "5", "Comedy,Short"),
                Movie.create("tt0000006", "movie", "Chinese Opium Den", "Chinese Opium Den", "0", "1894", "\\N", "1", "Comedy,Animation"),
                Movie.create("tt9916710", "movie", "Episode dated 21 February 2012", "Episode dated 21 February 2012", "0", "2012", "\\N", "\\N", "Short,Comedy,Romance,Documentary")
        );

        when(fileRepository.get(URL)).thenReturn(mockedFileContents);

        csvReaderService.compute(URL);
        verify(messageQueueRepository, times(1)).publish(expectedMovies);
        verifyNoMoreInteractions(fileRepository);
        verifyNoMoreInteractions(messageQueueRepository);
    }
}
