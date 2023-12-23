package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entity.Hall;
import Entity.Movie;
import Entity.Session;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createSession")
public class ManagerCreateSession extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/createSession start");
        int hID = Integer.parseInt(req.getParameter("hID"));
        Hall hall = Hall.getHall(hID);
        String movName = req.getParameter("mov");
        Movie movie = Movie.getMovie(movName);
        int day = Integer.parseInt(req.getParameter("day"));
        int slot = Integer.parseInt(req.getParameter("slot"));
        System.out.println(
                "Hall: " + hall.getHallID() + "\nMovie: " + movie.getName() + "\nslot:" + slot + "\nday:" + day);
        // Prevent clashing of sessions
        ArrayList<Session> sessionList = Session.getSessionList();
        int sessionClashes = 0;
        PrintWriter out = res.getWriter();
        for (Session session : sessionList) {
            // Attempting to add a session with clashes
            if (session.getHall() == hall && session.getDay() == day && session.getSlot() == slot) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('The session you are trying to create conflicts with an existing one!');");
                out.println("location='managerCreateSession.jsp';");
                out.println("</script>");
                sessionClashes++;
                break;
            }
        }
        // Creating a session that does not clash
        if (sessionClashes == 0) {
            new Session(hall, movie, day, slot);
            RequestDispatcher reqDispatcher;
            reqDispatcher = req.getRequestDispatcher("managerSessions.jsp");
            reqDispatcher.forward(req, res);
        }
    }
}