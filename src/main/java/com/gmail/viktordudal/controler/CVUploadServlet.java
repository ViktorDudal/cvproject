package com.gmail.viktordudal.controler;

import com.gmail.viktordudal.model.Person;
import com.gmail.viktordudal.model.Specialization;
import com.gmail.viktordudal.service.ParseFile;
import com.gmail.viktordudal.service.PersonService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;
import java.util.List;

@WebServlet("/upload")
public class CVUploadServlet extends HttpServlet {

    private static final String PERSON_ADDED_MESSAGE = "Person from file added to DB  successfully";
    private static final String PERSON_ADDED_ERROR = "Error! Person from file did't add to DB";

    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private String filePath = "C:\\temp\\";
    private File file;

    private PersonService personService = new PersonService();

    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp)
            throws ServletException, java.io.IOException {

        resp.sendRedirect("/");
    }


    public void doPost(HttpServletRequest req,
                       HttpServletResponse resp)
            throws ServletException, java.io.IOException {

        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if( !isMultipart ){
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);
        factory.setRepository(new File(filePath));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax( maxFileSize );

        boolean isAdded = false;

        try{
            List fileItems = upload.parseRequest(req);
            Iterator i = fileItems.iterator();
            while(i.hasNext())
            {
                FileItem fi = (FileItem)i.next();
                if ( !fi.isFormField () )
                {
                    String fileName = fi.getName();
                    if( fileName.lastIndexOf("\\") >= 0 ){
                        file = new File( filePath +
                                fileName.substring( fileName.lastIndexOf("\\"))) ;
                    }else{
                        file = new File( filePath +
                                fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                    }
                    fi.write( file );

                    isAdded = personService.addPersonFromFile(file.getAbsolutePath());
                }
            }
            if(isAdded){
                req.setAttribute("message", PERSON_ADDED_MESSAGE);
            } else{
                req.setAttribute("errorMessage", PERSON_ADDED_ERROR);
            }

            req.setAttribute("persons", personService.getAll());
            req.setAttribute("specializations", Specialization.values());
            req.getRequestDispatcher("/WEB-INF/pages/all_persons.jsp").forward(req, resp);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
