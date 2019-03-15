package com.gmail.viktordudal.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gmail.viktordudal.service.LocalDateDeserializer;
import com.gmail.viktordudal.service.LocalDateSerializer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class Company {

    @NotNull(message = "Field 'companyName' cannot be null")
    @Size(min = 2, max = 25, message = "Field 'companyName' must be between 2 and 25 characters")
    private String companyName;

    @NotNull(message = "Field 'position' cannot be null")
    @Size(min = 2, max = 15, message = "Field 'position' must be between 2 and 15 characters")
    private String position;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "Field 'position' cannot be null")
    @Past(message = "Wrong date")
    private LocalDate workedFrom;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "Field 'position' cannot be null")
    @PastOrPresent(message = "Wrong date")
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

  /*  public static class Builder {
        private Company newCompany;

        public Builder() {
            newCompany = new Company();
        }

        public Builder withCompanyName(String companyName) {
            newCompany.companyName = companyName;
            return this;
        }

        public Builder withPosition(String position) {
            newCompany.position = position;
            return this;
        }

        public Builder withWorkedFrom(LocalDate workedFrom){
            newCompany.workedFrom = workedFrom;
            return this;
        }

        public Builder withWorkedTill(LocalDate workedTill){
            newCompany.workedTill = workedTill;
            return this;
        }

        public Company build(){
            return newCompany;
        }
    }*/
}
