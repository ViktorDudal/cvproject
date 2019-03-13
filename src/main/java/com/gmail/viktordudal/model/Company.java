package com.gmail.viktordudal.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gmail.viktordudal.service.LocalDateDeserializer;
import com.gmail.viktordudal.service.LocalDateSerializer;

import java.time.LocalDate;

public class Company {

    private String companyName;
    private String position;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate workedFrom;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate workedTill;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getWorkedFrom() {
        return workedFrom;
    }

    public void setWorkedFrom(LocalDate workedFrom) {
        this.workedFrom = workedFrom;
    }

    public LocalDate getWorkedTill() {
        return workedTill;
    }

    public void setWorkedTill(LocalDate workedTill) {
        this.workedTill = workedTill;
    }

}
