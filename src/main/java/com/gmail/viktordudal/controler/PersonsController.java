package com.gmail.viktordudal.controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.viktordudal.model.Person;
import com.gmail.viktordudal.service.PersonService;

@WebServlet(urlPatterns = "/")
public class PersonsController extends HttpServlet {

    private static PersonService personService = new PersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String specId = req.getParameter("specId");
        List<Person> people;
        if (specId != null){
            people = personService.getBySpec(specId);
        } else {
            people = personService.getAll();
        }
        req.setAttribute("persons", people);
        req.getRequestDispatcher("/WEB-INF/pages/all_persons.jsp").forward(req, resp);
    }
}
