package edu.gatech.cs2340.todo.controller;

import edu.gatech.cs2340.todo.model.Todo;
import edu.gatech.cs2340.todo.model.TodoDb;
import edu.gatech.cs2340.todo.model.TodoDbTreeMapImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={
        "/list", // GET
        "/create", // POST 
        "/update/*", // PUT
        "/delete/*" // DELETE
    })
public class TodoServlet extends HttpServlet {

    TodoDb todoDb = new TodoDbTreeMapImpl();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doPost()");
        // Handle the hidden HTML form field that simulates
        // HTTP PUT and DELETE methods.
        String operation = (String) request.getParameter("operation");
        // If form didn't contain an operation field and
        // we're in doPost(), the operation is POST
        if (null == operation) operation = "POST";
        System.out.println("operation is " + operation);
        if (operation.equalsIgnoreCase("PUT")) {
            System.out.println("Delegating to doPut().");
            doPut(request, response);
        } else if (operation.equalsIgnoreCase("DELETE")) {
            System.out.println("Delegating to doDelete().");
            doDelete(request, response);
        } else {
            String title = request.getParameter("title");
            String task = request.getParameter("task");
            todoDb.create(new Todo(title, task));
            request.setAttribute("todos", todoDb.list());
            RequestDispatcher dispatcher = 
                getServletContext().getRequestDispatcher("/list.jsp");
            dispatcher.forward(request,response);
        }
    }

    /**
     * Called when HTTP method is GET 
     * (e.g., from an <a href="...">...</a> link).
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doGet()");
        request.setAttribute("todos", todoDb.list());
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/list.jsp");
        dispatcher.forward(request,response);
    }

    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doPut()");
        String title = (String) request.getParameter("title");
        String task = (String)  request.getParameter("task");
        Integer id = getId(request);
        todoDb.update(id, new Todo(title, task));
        request.setAttribute("todos", todoDb.list());
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/list.jsp");
        dispatcher.forward(request,response);
    }

    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doDelete()");
        Integer id = getId(request);
        todoDb.delete(id);
        request.setAttribute("todos", todoDb.list());
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/list.jsp");
        dispatcher.forward(request,response);
    }

    private Integer getId(HttpServletRequest request) {
        String uri = request.getPathInfo();
        // Strip off the leading slash, e.g. "/2" becomes "2"
        String idStr = uri.substring(1, uri.length()); 
        return Integer.parseInt(idStr);
    }

}
