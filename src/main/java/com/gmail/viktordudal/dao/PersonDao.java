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
    private static final String SELECT_PERSON_BY_ID = "select * from person where p.id = ?;";
    private static final String SELECT_CONTACT_BY_ID = "select * from contact where person_id = ?";
    private static final String SELECT_JOB_BY_ID = "select * from jobs where person_id = ?";
    private static final String SELECT_SKILLS_BY_ID = "select * from person_skills ps join skills s on s.id = ps.skill_id where ps.person_id = 6;";
    private static final String DELETE_CONTACT_BY_ID = "DELETE FROM contact WHERE person_id = ?;";
    private static final String DELETE_JOB_BY_ID = "DELETE FROM jobs WHERE person_id = ?;";
    private static final String DELETE_PERSON_SKILLS_BY_ID = "DELETE FROM person_skills WHERE person_id = ?;";
    private static final String DELETE_PERSON_BY_ID = "DELETE from person where id = ?;";
    private static final String INSERT_NEW_PERSON = "insert into person (surname, name, date_of_birth, specialization) values (?,?,?,?);";
    private static final String INSERT_NEW_CONTACT = "insert into contact (city, address, phone_number, email, person_id) values (?,?,?,?,?);";
    private static final String CREATE_NEW_JOB = "insert into jobs (person_id, company_name, position, worked_from, worked_till) values (?,?,?,?,?);";

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
            PreparedStatement statement = connection.prepareStatement("select * from person p join contact ct on p.id = ct.person_id join jobs j on p.id = j.person_id join person_skills ps on ps.person_id = p.id join skills s on s.id = ps.skill_id where p.id = ?;");
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

//    private Contact getContactById(long id){
//        Contact contact = null;
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(SELECT_CONTACT_BY_ID);) {
//
//            statement.setLong(1, id);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()){
//                    Contact.builder()
//                            .city(resultSet.getString("city"))
//                            .address(resultSet.getString("address"))
//                            .phoneNumber(resultSet.getString("phone_number"))
//                            .email(resultSet.getString("email"))
//                            .build();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return contact;
//    }
//
//    private Company getCompanyById(long id) {
//        Company company = null;
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(SELECT_JOB_BY_ID);) {
//
//            statement.setLong(1, id);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()){
//                Company.builder()
//                        .companyName(resultSet.getString("company_name"))
//                        .position(resultSet.getString("position"))
//                        .workedFrom(LocalDate.parse(resultSet.getString("worked_from")))
//                        .workedTill(LocalDate.parse(resultSet.getString("worked_till")))
//                        .build();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return company;
//    }
//
//    private String getSkillsById(long id) {
//        String skill = null;
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(SELECT_SKILLS_BY_ID);) {
//
//            statement.setLong(1, id);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()){
//                skill = resultSet.getString("skill");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return skill;
//    }


    @Override
    public void insert(Person person) {

        long id = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(INSERT_NEW_PERSON/*, Statement.RETURN_GENERATED_KEYS*/)) {

                pstmt.setString(1, person.getSurname());
                pstmt.setString(2, person.getName());
                pstmt.setDate(3, Date.valueOf(person.getDateOfBirth()));
                pstmt.setString(4, person.getSpecialization().getName());

            /*int affectedRows = */pstmt.executeUpdate();
             //check the affected rows
//            if (affectedRows > 0) {
//                // get the ID back
//                try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                    if (rs.next()) {
//                        id = rs.getLong(1);
//                    }
//                } catch (SQLException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
//        System.out.println("The ID is" + id);
//        return person;
    }


//    public Person createNewPerson() throws SQLException {
//        Person person = null;
//        try (
//                Connection connection = DatabaseConnection.getConnection();
//                PreparedStatement statement = connection.prepareStatement(CREATE_NEW_PERSON/*, Statement.RETURN_GENERATED_KEYS*/);
//        ) {
//            statement.setString(1, person.getSurname());
//            statement.setString(2, person.getName());
//            statement.setDate(3, Date.valueOf(person.getDateOfBirth()));
//            statement.setString(4, person.getSpecialization().getName());
//
//            int affectedRows = statement.executeUpdate();
//
//            if (affectedRows == 0) {
//                throw new SQLException("Creating user failed, no rows affected.");
//            }
//
//            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    person.setId(generatedKeys.getLong(1));
//                }
//                else {
//                    throw new SQLException("Creating user failed, no ID obtained.");
//                }
//            }
//        }
//        return person;
//    }

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
             PreparedStatement statementSkills = connection.prepareStatement(DELETE_PERSON_SKILLS_BY_ID);) {
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
