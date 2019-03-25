package com.gmail.viktordudal.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gmail.viktordudal.model.Company;
import com.gmail.viktordudal.model.Contact;
import com.gmail.viktordudal.model.Person;
import com.gmail.viktordudal.model.Specialization;

public class PersonDao extends AbstractDao<Person> {

    private static final String SELECT_ALL = "SELECT * FROM person INNER JOIN contact USING (id) INNER JOIN company USING (id) INNER JOIN company_person USING (id) INNER JOIN skills USING (id);";
    private static final String SELECT_CONTACT = "SELECT * FROM contact";
    private static final String SELECT_BY_ID = "SELECT * FROM person INNER JOIN contact USING (id)";
    private static final String SQL = "INSERT INTO person (surname, name) VALUES(?,?)";

    @Override
    public List<Person> getAll() {

        List<Person> result = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()){
                result.add(parsePerson(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Person parsePerson(ResultSet resultSet) throws SQLException {
        return Person.builder()
                .id(resultSet.getLong("id"))
                .surname(resultSet.getString("surname"))
                .name(resultSet.getString("name"))
                .dateOfBirth(LocalDate.parse(resultSet.getString("date_of_birth")))
                .contact(Contact.builder()
                        .city(resultSet.getString("city"))
                        .address(resultSet.getString("address"))
                        .phoneNumber(resultSet.getString("phone_number"))
                        .email(resultSet.getString("email"))
                        .build())
                .company(Company.builder()
                        .companyName(resultSet.getString("company_name"))
                        .position(resultSet.getString("position"))
                        .workedFrom(LocalDate.parse(resultSet.getString("worked_from")))
                        .workedTill(LocalDate.parse(resultSet.getString("worked_till")))
                        .build())
                .skills(resultSet.getString("skill"))
                .specialization(Specialization.valueOf(resultSet.getString("specialization")))
                .build();
//        Person person = new Person();
//        int i = 1;
//        person.bu
//        person.setName(resultSet.getString("name"));
//        person.setSurname(resultSet.getString("surname"));
//        person.setDateOfBirth(LocalDate.parse(resultSet.getString("date_of_birth")));
//        person.setContact(Contact.ContactBuilder.builder()
//                                                .city(resultSet.getString("city"))
//                                                .address(resultSet.getString("address"))
//                                                .phoneNumber(resultSet.getString("phone_number"))
//                                                .email(resultSet.getString("email"))
//                                                .build());
//        person.setCompanies();
//        return person;
    }

    @Override
    public Person getById(long id) {

        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(SELECT_BY_ID, id));

            while (resultSet.next()){
                return parsePerson(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void insertPersons(List<Person> person) {

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {
            int count = 0;

            for (Person actor : person) {
                statement.setString(1, actor.getSurname());
                statement.setString(2, actor.getName());

                statement.addBatch();
                count++;
                // execute every 100 rows or less
                if (count % 100 == 0 || count == person.size()) {
                    statement.executeBatch();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override public Person update(Person entity) {
        return null;
    }

    @Override public boolean delete(Person entity) {
        return false;
    }
}
