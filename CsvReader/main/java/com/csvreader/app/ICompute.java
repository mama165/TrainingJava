package com.csvreader.app;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface ICompute {
    void compute(String url, String type, String gender) throws IOException, InterruptedException, ExecutionException;
    void compute(String url) throws IOException, InterruptedException, ExecutionException;
}
