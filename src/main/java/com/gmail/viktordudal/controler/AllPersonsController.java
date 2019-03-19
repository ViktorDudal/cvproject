package com.gmail.viktordudal.controler;

import com.gmail.viktordudal.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/allpersons")
public class AllPersonsController extends HttpServlet {

    private static PersonService personService = new PersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        PrintWriter out = resp.getWriter();
//        out.println(personService.getAll());
        req.setAttribute("all", personService.getAll());
        req.getRequestDispatcher("/WEB-INF/pages/allpersons.jsp").forward(req, resp);
    }
}

