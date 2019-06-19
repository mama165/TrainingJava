package com.csvreader.app;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Movie {
    private static final String COMMA_DELIMITER = ",";
    private static final String NULL_VALUE = "\\N";
    private final String tconst;
    private final String titleType;
    private final String primaryTitle;
    private final String originalTitle;
    private final Boolean isAdult;
    private final Integer startYear;
    private final Integer endYear;
    private final Integer runtimeMinutes;
    private final List<String> genders;

    private Movie(String tconst, String titleType, String primaryTitle, String originalTitle, Boolean isAdult, Integer startYear, Integer endYear, Integer runtimeMinutes, List<String> genders) {
        this.tconst = tconst;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtimeMinutes = runtimeMinutes;
        this.genders = genders;
    }


    public String getTitleType() {
        return titleType;
    }

    public List<String> getGenders() {
        return genders;
    }

    public static Movie create(List<String> values) {
        return Movie.create(
                values.get(0),
                values.get(1),
                values.get(2),
                values.get(3),
                values.get(4),
                values.get(5),
                values.get(6),
                values.get(7),
                values.get(8)
        );
    }

    public static Movie create(String tconst, String titleType, String primaryTitle, String originalTitle, String isAdult, String startYear, String endYear, String runtimeMinutes, String genders) {
        return new Movie(
                NULL_VALUE.equals(tconst) ? null : tconst,
                NULL_VALUE.equals(titleType) ? null : titleType,
                NULL_VALUE.equals(primaryTitle) ? null : primaryTitle,
                NULL_VALUE.equals(originalTitle) ? null : originalTitle,
                NULL_VALUE.equals(isAdult) ? null : Boolean.valueOf(isAdult),
                NULL_VALUE.equals(startYear) ? null : Integer.valueOf(startYear),
                NULL_VALUE.equals(endYear) ? null : Integer.valueOf(endYear),
                NULL_VALUE.equals(runtimeMinutes) ? null : Integer.valueOf(runtimeMinutes),
                NULL_VALUE.equals(genders) ? null : Arrays.asList(genders.split(COMMA_DELIMITER))
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(tconst, movie.tconst) &&
                Objects.equals(titleType, movie.titleType) &&
                Objects.equals(primaryTitle, movie.primaryTitle) &&
                Objects.equals(originalTitle, movie.originalTitle) &&
                Objects.equals(isAdult, movie.isAdult) &&
                Objects.equals(startYear, movie.startYear) &&
                Objects.equals(endYear, movie.endYear) &&
                Objects.equals(runtimeMinutes, movie.runtimeMinutes) &&
                Objects.equals(genders, movie.genders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tconst, titleType, primaryTitle, originalTitle, isAdult, startYear, endYear, runtimeMinutes, genders);
    }
}