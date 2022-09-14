package app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.DataOutputStream;
import java.io.IOException;

//@WebServlet(
//        name = "AreaCheckServlet",
//        description = "This is my first annotated servlet",
//        urlPatterns = "/control/check"
//)
public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double x = Double.parseDouble(req.getParameter("x"));
        double y = Double.parseDouble(req.getParameter("y"));
        double r = Double.parseDouble(req.getParameter("R"));
        String status = "";
        int codeStatus = 400;
        if(!(x<3 && x>-5 && y<3 && y>-3 && r>1 && r<5)){
            resp.setStatus(400);
            return;
        }
        //circle
        if(x>=0 && y<=0 && x*x+y*y<=r*r){
            status = "true";
            codeStatus = 200;
        }
        //triangle
        if(x>=0 && y>=0){
            double x1,y1,x2,y2,x3,y3;
            x1 = 0;
            y1 = 0;
            x2 = 0;
            y2 = r;
            x3 = r/2;
            y3 = 0;
            double var1 = (x1-x)*(y2-y1) - (x2-x1)*(y1-y);
            double var2 = (x2-x)*(y3-y2) - (x3-x2)*(y2-y);
            double var3 = (x3-x)*(y1-y3) - (x1-x3)*(y3-y);
            if((var1>=0 && var2 >=0 && var3>=0) || (var1<=0 && var2<=0 && var3<=0)){
                status = "true";
                codeStatus = 200;
            }
        }
        //square
        if(x<=0 && y>=0 && x>=(-r) && y<=(r/2.0)) {
            status = "true";
            codeStatus = 200;
        }
        writeData(status,codeStatus,resp);
    }

    private void writeData(String status,Integer codeStatus,HttpServletResponse resp) throws IOException {
        DataOutputStream wr = new DataOutputStream (
                resp.getOutputStream());
        wr.writeBytes(status);
        wr.close();
        resp.setStatus(codeStatus);
    }
}
