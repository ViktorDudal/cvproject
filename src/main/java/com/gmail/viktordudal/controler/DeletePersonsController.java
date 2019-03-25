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
import java.util.List;

@WebServlet(urlPatterns = "/deletePerson")
public class DeletePersonsController extends HttpServlet {

    private static PersonService personService = new PersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Person> persons = personService.getAll();
        req.setAttribute("persons", persons);

        req.setAttribute("specializations", Specialization.values());
        req.getRequestDispatcher("/WEB-INF/pages/all_persons.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String personId = req.getParameter("personId");
        if (personId != null){
            personService.deleteById(Long.parseLong(personId));
        }
        List<Person> persons = personService.getAll();
        req.setAttribute("persons", persons);
        req.setAttribute("specializations", Specialization.values());
        req.setAttribute("message", "Person deleted successfully");
        req.getRequestDispatcher("/WEB-INF/pages/all_persons.jsp").forward(req, resp);
    }
}
