package com.gmail.viktordudal.controler;

import com.gmail.viktordudal.model.Company;
import com.gmail.viktordudal.model.Contact;
import com.gmail.viktordudal.model.Person;
import com.gmail.viktordudal.model.Specialization;
import com.gmail.viktordudal.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@WebServlet(urlPatterns = "/new_person_cv")
public class NewPersonCVController extends HttpServlet {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    private PersonService personService = new PersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String personId = req.getParameter("personId");
        if (personId != null){
            req.setAttribute("person", personService.getById(Long.parseLong(personId)));
        }
        req.setAttribute("specializations", Specialization.values());
        req.getRequestDispatcher("/WEB-INF/pages/new_person_cv.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Person newPerson = createPerson(req);

        newPerson.setContact(getContact(req));

        Set<Company> companies = getCompanies(req);
        newPerson.setCompanies(companies);

        Set<String> skills = getSkills(req);
        newPerson.setSkills(skills);

        personService.addNewPerson(newPerson);

        req.setAttribute("specializations", Specialization.values());
        req.setAttribute("persons", personService.getAll());

        req.getRequestDispatcher("/WEB-INF/pages/all_persons.jsp").forward(req, resp);
    }

    private Set<Company> getCompanies(HttpServletRequest req) {
        Set<Company> companies = new HashSet<>();
        int index = 1;
        String companyName = req.getParameter("companyName" + index);
        while (companyName != null && !companyName.isEmpty()){
            companies.add(Company.builder()
                    .companyName(companyName)
                    .position(req.getParameter("position" + index))
                    .workedFrom(getLocalDate(req.getParameter("workedFrom" + index)))
                    .workedTill(getLocalDate(req.getParameter("workedTill" + index)))
                    .build());
            companyName = req.getParameter("companyName" + ++index);
        }
        return companies;
    }

    private LocalDate getLocalDate(String value) {
        try {
            return LocalDate.parse(value, DATE_TIME_FORMATTER);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private Set<String> getSkills(HttpServletRequest req) {
        Set<String> skills = new TreeSet<>();
        int index = 1;
        String skillValue = req.getParameter("skill" + index);
        while (skillValue != null){
            skills.add(skillValue);
            skillValue = req.getParameter("skill" + ++index);
        }
        return skills;
    }

    private Contact getContact(HttpServletRequest req) {
        return Contact.builder()
                .city(req.getParameter("city"))
                .address(req.getParameter("address"))
                .phoneNumber(req.getParameter("phoneNumber"))
                .email(req.getParameter("email"))
                .build();
    }

    private Person createPerson(HttpServletRequest req) {
        Person person = new Person();
        person.setName(req.getParameter("name"));
        person.setSurname(req.getParameter("surname"));
        person.setSpecialization(Specialization.getByName(req.getParameter("specializ")));
        if (req.getParameter("dateOfBirth") != null) {
            person.setDateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth"), DATE_TIME_FORMATTER));
        }
        return person;
    }
}
