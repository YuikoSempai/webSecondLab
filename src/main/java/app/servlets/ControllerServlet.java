package app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

//@WebServlet(
//        name = "ControlServlet",
//        description = "This is my first annotated servlet",
//        urlPatterns = "/control"
//)
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String x = req.getParameter("x");
        String y = req.getParameter("y");
        String r = req.getParameter("R");
        System.out.println(x);
        System.out.println(y);
        System.out.println(r);
        if (x == null || y == null || r == null) {
            resp.setStatus(400);
            return;
        }
        if (!x.equals("") && !y.equals("") && r != "") {
            req.getRequestURL();
            URL url = new URL(req.getRequestURL() + "/check");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes("R=" + r + "&x=" + x + "&y=" + y);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            System.out.println(-1);
            while ((line = rd.readLine()) != null) {
                System.out.println(1);
                response.append(line);
                response.append('\r');
            }
            rd.close();

            //set Status and response
            wr = new DataOutputStream(
                    resp.getOutputStream());
            wr.writeBytes(response.toString());
            wr.close();

            resp.setStatus(connection.getResponseCode());
        } else {
            resp.setStatus(400);
        }
    }
}
