package com.gmail.viktordudal.controler;

import com.gmail.viktordudal.model.Person;
import com.gmail.viktordudal.model.Specialization;
import com.gmail.viktordudal.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/new_person_cv")
public class NewPersonCVController extends HttpServlet {

    private PersonService personService = new PersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String personId = req.getParameter("personId");
        if (personId != null){
            req.setAttribute("person", personService.getById(Long.parseLong(personId)));
        }
        req.getRequestDispatcher("/WEB-INF/pages/new_person_cv.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Person newPerson = new Person();
        newPerson.setName(req.getParameter("name"));
        newPerson.setSurname(req.getParameter("surname"));
        personService.addNewPerson(newPerson);
        req.setAttribute("specializations", Specialization.values());
        req.setAttribute("persons", personService.getAll());
        req.getRequestDispatcher("/WEB-INF/pages/all_persons.jsp").forward(req, resp);
    }
}
