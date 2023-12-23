package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entity.Hall;
import Entity.Session;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteHall")
public class ManagerDeleteHall extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/deleteHall start");
        // Delete code here
        int delHall = Integer.parseInt(req.getParameter("delHall"));
        // Prevent hall from being deleted if a session is using it
        int inSession = 0;
        ArrayList<Session> sessList = Session.getSessionList();
        for (Session sess : sessList) {
            if (sess.getHall().getHallID() == delHall) {
                inSession++;
                break;
            }
        }
        if (inSession == 0) {
            ArrayList<Hall> hallList = Hall.getHallList();
            for (Hall hall : hallList) {
                if (hall.getHallID() == delHall) {
                    hallList.remove(hall);
                    break;
                }
            }
            Hall.writeHallFile();
            RequestDispatcher reqDispatcher;
            reqDispatcher = req.getRequestDispatcher("managerHalls.jsp");
            reqDispatcher.forward(req, res);
        } else {
            PrintWriter out = res.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('The hall you are trying to delete exists in a session!');");
            out.println("location='managerHalls.jsp';");
            out.println("</script>");
        }
    }
}