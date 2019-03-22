package com.gmail.viktordudal.controler;

import com.gmail.viktordudal.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/person_info")
public class PersonInfoController extends HttpServlet {

    private static PersonService personService = new PersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        String personId = req.getParameter("personId");
//        if (Integer.parseInt(personId) == 1){
//            req.setAttribute("person_info", personService.getById(1));
//            req.getRequestDispatcher("/WEB-INF/pages/person_info.jsp").forward(req, resp);
//        }
//        if (Integer.parseInt(personId) == 2){
//            req.setAttribute("person_info", personService.getById(2));
//            req.getRequestDispatcher("/WEB-INF/pages/person_info.jsp").forward(req, resp);
//        }
//        if (Integer.parseInt(personId) == 3){
//            req.setAttribute("person_info", personService.getById(3));
//            req.getRequestDispatcher("/WEB-INF/pages/person_info.jsp").forward(req, resp);
//        }
    }
}
