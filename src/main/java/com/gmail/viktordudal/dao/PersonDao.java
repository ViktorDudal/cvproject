package com.gmail.viktordudal.dao;

import com.gmail.viktordudal.model.Company;
import com.gmail.viktordudal.model.Contact;
import com.gmail.viktordudal.model.Person;
import com.gmail.viktordudal.model.Specialization;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class PersonDao extends AbstractDao<Person> {

    private static final String SELECT_ALL = "SELECT * FROM person;";
    private static final String SELECT_BY_ID = "select * from person p join contact c on p.id = c.person_id join jobs j on p.id = j.person_id join skills s on p.id = s.person_id where p.id = ?;";
    private static final String DELETE_CONTACT_BY_ID = "delete from contact WHERE person_id = ?;";
    private static final String DELETE_JOB_BY_ID = "delete from jobs WHERE person_id = ?;";
    private static final String DELETE_SKILLS_BY_ID = "delete from skills WHERE person_id = ?;";
    private static final String DELETE_PERSON_BY_ID = "delete from person WHERE id = ?;";
    private static final String INSERT_NEW_PERSON = "insert into person (surname, name, date_of_birth, specialization) values (?,?,?,?) RETURNING id;";
    private static final String INSERT_NEW_CONTACT = "insert into contact (person_id, city, address, phone_number, email) values (?,?,?,?,?);";
    private static final String INSERT_NEW_SKILLS = "insert into skills (person_id, skill) values (?,?);";
    private static final String INSERT_NEW_JOB = "insert into jobs (person_id, company_name, position, worked_from, worked_till) values (?,?,?,?,?);";

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
    public Person insertPerson(Person person, Contact contact, Set<String> skills) {
        Connection connection = null;
        long person_id = 0;
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(INSERT_NEW_PERSON)) {
            statement.setString(1, person.getSurname());
            statement.setString(2, person.getName());
            statement.setDate(3, Date.valueOf(person.getDateOfBirth()));
            statement.setString(4, person.getSpecialization().getName());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                person_id = resultSet.getLong("id");
            } else {
                System.out.println("TABLE IS EMPTY");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try (PreparedStatement statement = connection.prepareStatement(INSERT_NEW_CONTACT)) {
            statement.setLong(1, person_id);
            statement.setString(2, contact.getCity());
            statement.setString(3, contact.getAddress());
            statement.setString(4, contact.getPhoneNumber());
            statement.setString(5, contact.getEmail());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        try (PreparedStatement statement = connection.prepareStatement(INSERT_NEW_SKILLS)) {
            statement.setLong(1, person_id);
            Iterator<String> itr = skills.iterator();
            while(itr.hasNext()){
                statement.setString(2, itr.next());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        person.setContact(contact);
        return person;
    }

    @Override public Person update(Person entity) {
        return null;
    }




    @Override
    public boolean deleteById(Long id) {
        boolean isContactDeleted = deleteContactByUserId(id);
        boolean isJobDeleted = deleteByIdByJob(id);
        boolean isSkillsDeleted = deleteByIdByPersonSkill(id);
        boolean isPersonDeleted = deletePersonById(id);
        return isContactDeleted && isJobDeleted && isSkillsDeleted && isPersonDeleted;
    }

    private boolean deleteContactByUserId(Long id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statementContact = connection.prepareStatement(DELETE_CONTACT_BY_ID);) {
            statementContact.setLong(1, id);
            return statementContact.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean deleteByIdByJob(Long id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statementJobs = connection.prepareStatement(DELETE_JOB_BY_ID);) {
            statementJobs.setLong(1, id);
            return statementJobs.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean deleteByIdByPersonSkill(Long id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statementSkills = connection.prepareStatement(DELETE_SKILLS_BY_ID);) {
            statementSkills.setLong(1, id);
            return statementSkills.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean deletePersonById(Long id){
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statementPerson = connection.prepareStatement(DELETE_PERSON_BY_ID);) {
            statementPerson.setLong(1, id);
            return statementPerson.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
