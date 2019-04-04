package com.gmail.viktordudal.controler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.viktordudal.model.Company;
import com.gmail.viktordudal.model.Contact;
import com.gmail.viktordudal.model.Person;
import com.gmail.viktordudal.model.Specialization;
import com.gmail.viktordudal.service.PersonService;

@WebServlet(urlPatterns = "/")
public class PersonsController extends HttpServlet {

    private static PersonService personService = new PersonService();

    List<Person> persons;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String specId = req.getParameter("specId");
        Specialization specialization = Specialization.getByName(specId);
        if (specialization != null) {
            persons = personService.getBySpec(specialization);
        } else {
            persons = personService.getAll();
        }
        if (persons != null && !persons.isEmpty()) {
            req.setAttribute("persons", persons);
        } else {
            if (specialization != null) {
                req.setAttribute("message", "There are no CV for " + specialization.getName());
            } else {
                req.setAttribute("message", "There are no CV in DB");
            }
        }
        req.setAttribute("specializations", Specialization.values());
        req.getRequestDispatcher("/WEB-INF/pages/all_persons.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String personId = req.getParameter("personId");


        if (personId != null){
            personService.updatePerson(Long.parseLong(personId), setPersonByRequest(req));
        } else {
            personService.insertNewPerson(setPersonByRequest(req));
        }

        req.setAttribute("specializations", Specialization.values());
        req.setAttribute("persons", personService.getAll());

        req.getRequestDispatcher("/WEB-INF/pages/all_persons.jsp").forward(req, resp);
    }

    private Contact setContactByRequest(HttpServletRequest req){
        Contact contact = new Contact();
        return contact.builder()
                       .city(req.getParameter("city"))
                       .address(req.getParameter("address"))
                       .phoneNumber(req.getParameter("phoneNumber"))
                       .email(req.getParameter("email"))
                       .build();
    }

    private Set<Company> setCompanyByRequest(HttpServletRequest req) {
        Set<Company> companies = new HashSet<>();
        int index = 0;
        String companyName = req.getParameter("companyName" + index);
        while (companyName != null && !companyName.isEmpty()){
            companies.add(Company.builder()
                    .companyName(companyName)
                    .position(req.getParameter("position" + index))
                    .workedFrom(LocalDate.parse(req.getParameter("workedFrom" + index)))
                    .workedTill(LocalDate.parse(req.getParameter("workedTill" + index)))
                    .build());
            companyName = req.getParameter("companyName" + ++index);
        }
        return companies;
    }

    private Set<String> setSkillsByRequest(HttpServletRequest req) {
        Set<String> skills = new TreeSet<>();
        int index = 0;
        String[] skillValues = req.getParameterValues("skill" + index);
        while (skillValues != null && skillValues.length != 0){
            for (String skill: skillValues) {
                skills.add(skill);
            }
            skillValues = req.getParameterValues("skill" + ++index);
        }
        return skills;
    }

    private Person setPersonByRequest(HttpServletRequest req) {
        Person person = new Person();
        return person.builder()
                     .surname(req.getParameter("surname"))
                     .name(req.getParameter("name"))
                     .dateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth")))
                     .specialization(Specialization.getByName(req.getParameter("specializ")))
                     .contact(setContactByRequest(req))
                     .company(setCompanyByRequest(req))
                     .skills(setSkillsByRequest(req))
                     .build();
    }
}
