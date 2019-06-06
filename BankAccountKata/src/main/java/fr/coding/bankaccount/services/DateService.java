package fr.coding.bankaccount.services;

import java.time.Instant;

import static java.time.Instant.now;

public interface DateService {
    default Instant getDate() {
        return now();
    }
}
