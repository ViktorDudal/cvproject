package com.gmail.viktordudal.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gmail.viktordudal.model.Contact;
import com.gmail.viktordudal.model.Person;

public class PersonDao extends AbstractDao<Person> {

    private static final String SELECT_ALL = "SELECT * FROM person INNER JOIN contact USING (id)";
    private static final String SELECT_BY_ID = "SELECT * FROM person WHERE id = %d";
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
        Person person = new Person();
        person.setName(resultSet.getString("name"));
        person.setSurname(resultSet.getString("surname"));
        person.setDateOfBirth(LocalDate.parse(resultSet.getString("date_of_birth")));
//        person.setContact(new setCity(resultSet.getString("city")));
        return person;
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

        Person mockPerson = new Person();
        mockPerson.setName("Mocked person name");

        return mockPerson;
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
