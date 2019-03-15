package com.gmail.viktordudal.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gmail.viktordudal.model.Person;

public class PersonDao extends AbstractDao<Person> {

    private static final String SELECT_ALL = "SELECT * FROM person INNER JOIN contact ON person.contact_id = contact.id";
    private static final String SELECT_BY_ID = "SELECT * FROM person WHERE id = ?";


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
//        person.getContact().setCity(resultSet.getString("city"));
        return person;
    }

    @Override
    public Person getById(long id) {

        Person person = null;
        String sql = "SELECT * FROM teachers WHERE id = ?";

        try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                person = parsePersonById(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    private Person parsePersonById(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setName(resultSet.getString("name"));
        person.setSurname(resultSet.getString("surname"));
        person.setDateOfBirth(LocalDate.parse(resultSet.getString("date_of_birth")));
//        person.getContact().setCity(resultSet.getString("city"));
        return person;
    }

    @Override public Person update(Person entity) {
        return null;
    }

    @Override public Person insert(Person entity) {
        return null;
    }

    @Override public boolean delete(Person entity) {
        return false;
    }

    public void writeInTable(Connection connect) throws SQLException {

        String query1 = "INSERT INTO person VALUES ('Vasyl', 'Lelek', '26.07.1985')";
        String query2 = "INSERT INTO person VALUES ('Oleg', 'Petrov', '11.02.1981')";
        Statement statement = connect.createStatement();
        statement.executeUpdate(query1);
        statement.executeUpdate(query2);

    }

    public void readFromTable (Connection connect) throws SQLException {
        String query = "SELECT * FROM person";
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            System.out.println();
            for (int i = 1; i<4; i++) {
                System.out.print(resultSet.getString(i) + "\t");
            }
        }
    }

    public void deleteInTable(Connection connect) throws SQLException {

        String query = "DELETE FROM person WHERE name = 'Vasyl'";
        Statement statement = connect.createStatement();
        statement.executeUpdate(query);

    }

}
