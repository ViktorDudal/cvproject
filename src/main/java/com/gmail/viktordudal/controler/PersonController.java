package com.gmail.viktordudal.controler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.viktordudal.service.PersonService;

@WebServlet(urlPatterns = "/persons")
public class PersonController extends HttpServlet {

//    private static PersonService personService = new PersonService();

//    public static void main(String[] args) {
//        System.out.println(personService.getAll());
//        System.out.println(personService.getById(3L));
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        out.print("<h1>Hello Servlet</h1>");
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println(personService.getById(3L));
    }
}
