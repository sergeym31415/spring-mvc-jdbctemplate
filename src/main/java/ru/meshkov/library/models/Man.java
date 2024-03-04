package ru.meshkov.library.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Man {
    private int id;

    @NotEmpty(message = "FIO should not be empty")
    @Size(min = 2, max = 100, message = "FIO should be between 2 and 100 characters")
    private String fio;

    @Min(value = 0, message = "YearBirth should be greater than 0")
    private int yearBirth;

    public Man() {
    }

    public Man(int id, String fio, int yearBirth) {
        this.id = id;
        this.fio = fio;
        this.yearBirth = yearBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    @Override
    public String toString() {
        return "Man{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", yearBirth=" + yearBirth +
                '}';
    }
}
