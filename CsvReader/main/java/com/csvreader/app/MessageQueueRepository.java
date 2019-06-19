package com.csvreader.app;

import java.util.List;

public interface MessageQueueRepository {
    void publish(List<Movie> movies);
}
