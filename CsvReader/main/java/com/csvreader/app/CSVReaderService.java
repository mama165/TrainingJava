package com.csvreader.app;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class CSVReaderService implements ICompute {
    private final FileRepository fileRepository;
    private final MessageQueueRepository messageQueueRepository;
    private static final String DELIMITER = "\t";
    private static final String TITLE_TYPE = "movie";
    private static final String GENDER = "Comedy";

    CSVReaderService(FileRepository fileRepository, MessageQueueRepository messageQueueRepository) {
        this.fileRepository = fileRepository;
        this.messageQueueRepository = messageQueueRepository;
    }

    @Override
    public void compute(String url, String titleType, String gender) throws IOException, InterruptedException, ExecutionException {
        List<String> linesFromFile = fileRepository.get(url);
        List<Movie> unmodifiableMovies = Collections.unmodifiableList(generateMovies(linesFromFile, titleType, gender));
        messageQueueRepository.publish(unmodifiableMovies);
    }

    @Override
    public void compute(String url) throws IOException, InterruptedException, ExecutionException {
        compute(url, TITLE_TYPE, GENDER);
    }

    private List<Movie> generateMovies(List<String> values, String titleType, String gender) {
        return values.stream()
                .skip(1)
                .map(mapIntoMovie)
                .filter(movie -> filterMoviesWithCriteria(movie, titleType, gender))
                .collect(Collectors.toList());
    }

    private Function<String, Movie> mapIntoMovie = line -> {
        List<String> subElements = Arrays.asList(line.split(DELIMITER));
        return Movie.create(subElements);
    };

    private  boolean filterMoviesWithCriteria(Movie movie, String titleType, String gender) {
        return titleType.equals(movie.getTitleType())
                && movie.getGenders().contains(gender);
    }
}
