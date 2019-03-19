package com.gmail.viktordudal.controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.viktordudal.service.PersonService;

@WebServlet(urlPatterns = "/person")
public class PersonController extends HttpServlet {

    private static PersonService personService = new PersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("person", new ArrayList<>(Collections.singletonList(personService.getById(1L))));
        req.getRequestDispatcher("/WEB-INF/pages/person.jsp").forward(req, resp);
    }
}
