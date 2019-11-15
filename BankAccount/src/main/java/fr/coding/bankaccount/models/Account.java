package fr.coding.bankaccount.models;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Account {
    private final String firstName;
    private final String lastName;
    private final Instant time;

    public Account(String firstName, String lastName, Instant time) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.time = time;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Instant getTime() {
        return time;
    }

    public boolean hasBeenLoyal(int years, Instant today) {
        return today.compareTo(time.plus(years, ChronoUnit.HOURS)) < 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(firstName, account.firstName) &&
                Objects.equals(lastName, account.lastName) &&
                Objects.equals(time, account.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, time);
    }
}
