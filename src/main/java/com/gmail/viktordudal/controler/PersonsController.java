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
            Person person = new Person();
            Contact contact = new Contact();
            Set<Company> companies = new HashSet<>();
            Set<String> skills = new TreeSet<>();
            person.setSurname(req.getParameter("surname"));
            person.setName(req.getParameter("name"));
            person.setDateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth")));
            person.setSpecialization(Specialization.getByName(req.getParameter("specializ")));
            contact.setCity(req.getParameter("city"));
            contact.setAddress(req.getParameter("address"));
            contact.setPhoneNumber(req.getParameter("phoneNumber"));
            contact.setEmail(req.getParameter("email"));

            int index_company = 0;
            String companyName = req.getParameter("companyName" + index_company);
            while (companyName != null && !companyName.isEmpty()){
                companies.add(Company.builder()
                        .companyName(companyName)
                        .position(req.getParameter("position" + index_company))
                        .workedFrom(LocalDate.parse(req.getParameter("workedFrom" + index_company)))
                        .workedTill(LocalDate.parse(req.getParameter("workedTill" + index_company)))
                        .build());
//                if (index_company == 0){
//                    index_company += 2;
//                } else {
//                    index_company += 1;
//                }
                companyName = req.getParameter("companyName" + ++index_company);
            }
            person.setCompanies(companies);

            int index = 0;
            String[] skillValues = req.getParameterValues("skill" + index);
            while (skillValues != null && skillValues.length != 0){
                for (String skill: skillValues) {
                    skills.add(skill);
                }
//                if (index == 0){
//                    index += 2;
//                } else {
//                    index += 1;
//                }
                skillValues = req.getParameterValues("skill" + ++index);
            }
            person.setSkills(skills);

            personService.updatePerson(Long.parseLong(personId), person, contact, skills, companies);
        } else {
            Person person = new Person();
            Contact contact = new Contact();
            Set<Company> companies = new HashSet<>();
            Set<String> skills = new TreeSet<>();
            person.setSurname(req.getParameter("surname"));
            person.setName(req.getParameter("name"));
            person.setDateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth")));
            person.setSpecialization(Specialization.getByName(req.getParameter("specializ")));
            contact.setCity(req.getParameter("city"));
            contact.setAddress(req.getParameter("address"));
            contact.setPhoneNumber(req.getParameter("phoneNumber"));
            contact.setEmail(req.getParameter("email"));

            int index_company = 1;
            String companyName = req.getParameter("companyName" + index_company);
            while (companyName != null && !companyName.isEmpty()){
                companies.add(Company.builder()
                        .companyName(companyName)
                        .position(req.getParameter("position" + index_company))
                        .workedFrom(LocalDate.parse(req.getParameter("workedFrom" + index_company)))
                        .workedTill(LocalDate.parse(req.getParameter("workedTill" + index_company)))
                        .build());
                companyName = req.getParameter("companyName" + ++index_company);
            }
            person.setCompanies(companies);

            int index = 1;
            String[] skillValues = req.getParameterValues("skill" + index);
            while (skillValues != null && skillValues.length != 0){
                for (String skill: skillValues) {
                    skills.add(skill);
                }
                skillValues = req.getParameterValues("skill" + ++index);
            }
            person.setSkills(skills);

            personService.insertNewPerson(person, contact, skills, companies);
        }

        req.setAttribute("specializations", Specialization.values());
        req.setAttribute("persons", personService.getAll());

        req.getRequestDispatcher("/WEB-INF/pages/all_persons.jsp").forward(req, resp);
    }
}
