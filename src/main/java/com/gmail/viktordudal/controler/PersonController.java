package com.gmail.viktordudal.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet(urlPatterns = "/person")
public class PersonController extends HttpServlet {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    private static final String CREATE = "create";
    private static final String UPDATE = "update";

    private static PersonService personService = new PersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String personId = req.getParameter("personId");
        String action = req.getParameter("action");
        req.setAttribute("specializations", Specialization.values());
        if (CREATE.equals(action)){
            req.getRequestDispatcher("/WEB-INF/pages/new_person_cv.jsp").forward(req, resp);
            return;
        }
        if (personId != null){
            req.setAttribute("person", personService.getById(Long.parseLong(personId)));
            if (UPDATE.equals(action)){
                req.getRequestDispatcher("/WEB-INF/pages/new_person_cv.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/pages/person_info.jsp").forward(req, resp);
            }
            return;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String personId = req.getParameter("personId");
        Map<String, String> data = new HashMap<>();
        if (personId != null){
            if(personService.deleteById(Long.parseLong(personId))){
                data.put("result","true");
                data.put("message", "Person has been deleted successfully!");
            } else {
                data.put("result", "false");
                data.put("message", "Person didn't deleteById!");
            }
        } else {
            data.put("result", "false");
            data.put("message", "Error!");
        }
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(data);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(jsonInString);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String personId = req.getParameter("personId");


            if (personId != null){
//              personService.updatePerson(newPerson);
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

                personService.insertNewPerson(person, contact, skills);
            }

        req.setAttribute("specializations", Specialization.values());
        req.setAttribute("persons", personService.getAll());

        req.getRequestDispatcher("/WEB-INF/pages/all_persons.jsp").forward(req, resp);
    }


//        private Set<Company> getCompanies(HttpServletRequest req) {
//        Set<Company> companies = new HashSet<>();
//        int index = 1;
//        String companyName = req.getParameter("companyName" + index);
//        while (companyName != null && !companyName.isEmpty()){
//            companies.add(Company.builder()
//                    .companyName(companyName)
//                    .position(req.getParameter("position" + index))
//                    .workedFrom(LocalDate.parse(req.getParameter("workedFrom" + index)))
//                    .workedTill(LocalDate.parse(req.getParameter("workedTill" + index)))
//                    .build());
//            companyName = req.getParameter("companyName" + ++index);
//        }
//        return companies;
//    }
//
//    private LocalDate getLocalDate(String value) {
//        try {
//            return LocalDate.parse(value, DATE_TIME_FORMATTER);
//        } catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//

//
//    private Contact getContact(HttpServletRequest req) {
//        return Contact.builder()
//                .city(req.getParameter("city"))
//                .address(req.getParameter("address"))
//                .phoneNumber(req.getParameter("phoneNumber"))
//                .email(req.getParameter("email"))
//                .build();
//    }
//
//    private Person createPerson(HttpServletRequest req) {
//        Person person = new Person();
//        person.setName(req.getParameter("name"));
//        person.setSurname(req.getParameter("surname"));
//        person.setSpecialization(Specialization.getByName(req.getParameter("specializ")));
//        if (req.getParameter("dateOfBirth") != null) {
//            person.setDateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth"), DATE_TIME_FORMATTER));
//        }
//        return person;
    }
}
