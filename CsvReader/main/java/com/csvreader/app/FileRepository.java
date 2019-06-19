package com.csvreader.app;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface FileRepository {
    List<String> get(String url) throws IOException, ExecutionException, InterruptedException;
}
