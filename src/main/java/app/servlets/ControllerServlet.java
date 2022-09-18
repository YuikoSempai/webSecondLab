package app.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import app.model.Model;
//@WebServlet(
//        name = "ControlServlet",
//        description = "This is my first annotated servlet",
//        urlPatterns = "/control"
//)
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = Model.getInstance();
        System.out.println("model: " + model.getLastData());
        String x = req.getParameter("x");
        String y = req.getParameter("y");
        String r = req.getParameter("R");
        if (x == null || y == null || r == null) {
            resp.setStatus(400);
            return;
        }
        if (!x.equals("") && !y.equals("") && r != "") {
            req.getRequestURL();
            ServletContext context= getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/check");
            rd.forward(req, resp);
        } else {
            resp.setStatus(400);
        }
    }
}
