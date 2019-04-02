package com.gmail.viktordudal.controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
}
