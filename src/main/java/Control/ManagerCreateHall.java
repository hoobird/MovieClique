package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entity.Hall;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createHall")
public class ManagerCreateHall extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/createHall start");
        int hallID = Integer.parseInt(req.getParameter("hID"));
        // int hallNo = Integer.parseInt(req.getParameter("hNo"));
        System.out.println("hallID: " + hallID/* + "\nhallNo: " + hallNo */);
        // Prevent duplicate halls
        PrintWriter out = res.getWriter();
        ArrayList<Hall> hallList = Hall.getHallList();
        int hallDups = 0;
        for (Hall hall : hallList) {
            // Attempting to add a duplicate hall
            if (!hallList.isEmpty() && hall.getHallID() == hallID) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('The hall you are trying to create already exists!');");
                out.println("location='managerCreateHall.jsp';");
                out.println("</script>");
                hallDups++;
                break;
            }
        }
        // Creating a hall that is not a duplicate
        if (hallDups == 0) {
            new Hall(hallID);
            RequestDispatcher reqDispatcher;
            reqDispatcher = req.getRequestDispatcher("managerHalls.jsp");
            reqDispatcher.forward(req, res);
        }
    }
}