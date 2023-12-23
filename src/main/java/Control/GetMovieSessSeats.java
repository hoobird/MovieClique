package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import Entity.Movie;
import Entity.Session;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/getmvSessSeats")
public class GetMovieSessSeats extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String sessionID = req.getParameter("sessionID");
        session.setAttribute("currentBookingSessionID", sessionID);
        Session currentSession = Session.getSession(Integer.parseInt(sessionID));

        String[] currSessionInfo = {currentSession.getMov().getName(),  
                Session.getDaysInWeek()[currentSession.getDay()-1] ,
                Session.getTimeSlots()[currentSession.getSlot()-1]};
        session.setAttribute("currSessionInfo", currSessionInfo);
        RequestDispatcher rd = req.getRequestDispatcher("MovieSeatsChoosingPage.jsp");
        rd.forward(req, res);
    }
}
