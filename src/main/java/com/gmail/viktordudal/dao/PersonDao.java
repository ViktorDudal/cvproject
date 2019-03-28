package com.gmail.viktordudal.dao;

import com.gmail.viktordudal.model.Company;
import com.gmail.viktordudal.model.Contact;
import com.gmail.viktordudal.model.Person;
import com.gmail.viktordudal.model.Specialization;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonDao extends AbstractDao<Person> {

    private static final String SELECT_ALL = "SELECT * FROM person;";
    private static final String SELECT_BY_ID = "select * from person p inner join contact using (id) join jobs j on p.id = j.person_id join person_skills ps on ps.person_id = p.id join skills s on s.id = ps.skill_id where p.id = ?;";
    private static final String SQL = "INSERT INTO person (surname, name) VALUES(?,?)";
    private static final String DELETE_CONTACT_BY_ID = "DELETE FROM contact WHERE person_id = ?;";
    private static final String DELETE_JOB_BY_ID = "DELETE FROM jobs WHERE person_id = ?;";
    private static final String DELETE_PERSON_SKILLS_BY_ID = "DELETE FROM person_skills WHERE person_id = ?;";
    private static final String DELETE_PERSON_BY_ID = "DELETE from person where id = ?;";

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

    @Override
    public Person getById(long id) {

        Person person = null;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            Set<Company> companies = new HashSet<>();
            Set<String> skills = new HashSet<>();
            while (resultSet.next()){
                if (person == null) {
                    person = parsePerson(resultSet);
                    person.setContact(parsContact(resultSet));
                }
                companies.add(parsCompany(resultSet));
                skills.add(parsSkills(resultSet));
            }
            person.setCompanies(companies);
            person.setSkills(skills);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    @Override
    public void insertPersons(List<Person> persons) {

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {
            int count = 0;

            for (Person person : persons) {
                statement.setString(1, person.getSurname());
                statement.setString(2, person.getName());

                statement.addBatch();
                count++;
                // execute every 100 rows or less
                if (count % 100 == 0 || count == persons.size()) {
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

    @Override
    public boolean deleteById(Long id) {
        deleteByIdByContact(id);
        deleteByIdByJob(id);
        deleteByIdByPersoSkill(id);
        deleteByIdByPerson(id);
        return true;
    }

    private boolean deleteByIdByContact(Long id) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statementContact = connection.prepareStatement(DELETE_CONTACT_BY_ID);
            statementContact.setLong(1, id);
            statementContact.executeUpdate();
            System.out.println("" + statementContact.executeUpdate());
            statementContact.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean deleteByIdByJob(Long id) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statementJobs = connection.prepareStatement(DELETE_JOB_BY_ID);
            statementJobs.setLong(1, id);
            statementJobs.executeQuery();
            statementJobs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean deleteByIdByPersoSkill(Long id) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statementSkills = connection.prepareStatement(DELETE_PERSON_SKILLS_BY_ID);
            statementSkills.setLong(1, id);
            statementSkills.executeQuery();
            statementSkills.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean deleteByIdByPerson(Long id){
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statementPerson = connection.prepareStatement(DELETE_PERSON_BY_ID);
            statementPerson.setLong(1, id);
            statementPerson.executeQuery();
            statementPerson.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String parsSkills(ResultSet resultSet) throws SQLException {
        return resultSet.getString("skill");
    }

    private Company parsCompany(ResultSet resultSet) throws SQLException {
        return Company.builder()
                .companyName(resultSet.getString("company_name"))
                .position(resultSet.getString("position"))
                .workedFrom(LocalDate.parse(resultSet.getString("worked_from")))
                .workedTill(LocalDate.parse(resultSet.getString("worked_till")))
                .build();
    }

    private Contact parsContact(ResultSet resultSet) throws SQLException {
        return Contact.builder()
                .city(resultSet.getString("city"))
                .address(resultSet.getString("address"))
                .phoneNumber(resultSet.getString("phone_number"))
                .email(resultSet.getString("email"))
                .build();
    }

    private Person parsePerson(ResultSet resultSet) throws SQLException {
        return Person.builder()
                .id(resultSet.getLong("id"))
                .surname(resultSet.getString("surname"))
                .name(resultSet.getString("name"))
                .dateOfBirth(LocalDate.parse(resultSet.getString("date_of_birth")))
                .specialization(Specialization.getByName(resultSet.getString("specialization")))
                .build();
    }
}
