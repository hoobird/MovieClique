package Control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
//import java.util.ArrayList;

import Entity.Hall;
import Entity.Movie;
import Entity.Session;

@WebServlet("/updateSession")
public class ManagerUpdateSession extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/updateSession start");
        HttpSession session = req.getSession();
        RequestDispatcher reqDispatcher = req.getRequestDispatcher("managerSessions.jsp");
        String saveSessID = req.getParameter("saveSessID");
        if (saveSessID != null && !saveSessID.equals("")) {
            // Directed by save button (request to save edit)
            int newSessHallID = Integer.parseInt(req.getParameter("saveSessHallID"));
            String newSessMovieName = req.getParameter("saveSessMovieName");
            String newSessDay = req.getParameter("saveSessDay");
            String newSessSlot = req.getParameter("saveSessSlot");
            Session currSess = (Session) session.getAttribute("toEdit");
            // Making sure updates are actual changes to values
            if (currSess.getHall().getHallID() != newSessHallID) {
                currSess.setHall(Hall.getHall(newSessHallID));
            }
            if (newSessMovieName != null && !newSessMovieName.equals("")
                    && !currSess.getMov().getName().equals(newSessMovieName)) {
                currSess.setMov(Movie.getMovie(newSessMovieName));
            }
            if (newSessDay != null && !newSessDay.equals("")) {
                int newDay = Integer.parseInt(newSessDay);
                if (currSess.getDay() != newDay) {
                    currSess.setDay(newDay);
                }
            }
            if (newSessSlot != null && !newSessSlot.equals("")) {
                int newSlot = Integer.parseInt(newSessSlot);
                if (currSess.getSlot() != newSlot) {
                    currSess.setSlot(newSlot);
                }
            }
            Session.writeSessionFile();
            session.removeAttribute("toEdit");
            reqDispatcher.forward(req, res);
        } else if (req.getParameter("editSess") != null) {
            // Directed by edit button (request to edit)
            int sessID = Integer.parseInt(req.getParameter("editSess"));
            session.setAttribute("toEdit", Session.getSession(sessID));
            reqDispatcher.forward(req, res);
        } else {
            session.removeAttribute("toEdit");
            reqDispatcher.forward(req, res);
        }
    }
}
