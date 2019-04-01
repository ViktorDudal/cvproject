package com.gmail.viktordudal.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gmail.viktordudal.service.LocalDateDeserializer;
import com.gmail.viktordudal.service.LocalDateSerializer;
import com.gmail.viktordudal.service.ValidatorModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Objects;

public class Company {

    @Size(min = 2, max = 25, message = "Field 'companyName' must be between 2 and 25 characters")
    private String companyName;

//    @Size(min = 2, max = 15, message = "Field 'position' must be between 2 and 15 characters")
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

    public static CompanyBuilder builder(){
        return new CompanyBuilder();
    }

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

    public static class CompanyBuilder {
        private Company newCompany;

        private CompanyBuilder() {
            newCompany = new Company();
        }

        public CompanyBuilder companyName(String companyName) {
            newCompany.companyName = companyName;
            return this;
        }

        public CompanyBuilder position(String position) {
            newCompany.position = position;
            return this;
        }

        public CompanyBuilder workedFrom(LocalDate workedFrom){
            newCompany.workedFrom = workedFrom;
            return this;
        }

        public CompanyBuilder workedTill(LocalDate workedTill){
            newCompany.workedTill = workedTill;
            return this;
        }

        public Company build(){
            new ValidatorModel().validate(newCompany);
            return newCompany;
        }
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", workedFrom=" + workedFrom +
                ", workedTill=" + workedTill +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(companyName, company.companyName) &&
                Objects.equals(position, company.position) &&
                Objects.equals(workedFrom, company.workedFrom) &&
                Objects.equals(workedTill, company.workedTill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, position, workedFrom, workedTill);
    }
}
